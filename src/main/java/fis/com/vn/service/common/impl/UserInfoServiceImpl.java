package fis.com.vn.service.common.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.model.entity.*;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.model.enumerate.UserStatus;
import fis.com.vn.repository.BankInfoRepository;
import fis.com.vn.repository.UserGroupRepository;
import fis.com.vn.repository.UserInfoRepository;
import fis.com.vn.rest.mapper.CorporateUserResponseMapper;
import fis.com.vn.rest.mapper.UserInfoRequestMapper;
import fis.com.vn.rest.mapper.UserInfoResponseMapper;
import fis.com.vn.rest.request.PasswordRequest;
import fis.com.vn.rest.request.UserInfoRequest;
import fis.com.vn.rest.response.CorporateUserResponse;
import fis.com.vn.rest.response.UserInfoResponse;
import fis.com.vn.service.AsyncSendEmailService;
import fis.com.vn.service.common.UserGroupService;
import fis.com.vn.service.common.UserInfoService;
import fis.com.vn.service.corporate.CorporateService;
import fis.com.vn.service.notification.EmailService;
import fis.com.vn.service.notification.TemplateService;
import fis.com.vn.service.notification.impl.EmailServiceImpl;
import fis.com.vn.util.Constant;
import fis.com.vn.util.FilesUtils;
import fis.com.vn.util.ModelMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Value("${fpt.website}")
    private String fptWebsite;

    @Value("$fpt.hotline")
    private String hotline;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    UserInfoRequestMapper userInfoRequestMapper;

    @Autowired
    UserInfoResponseMapper userInfoResponseMapper;

    @Autowired
    UserGroupService userGroupService;

    @Value("${keycloak.realm}")
    public String keycloakRealm;

    @Autowired
    private Keycloak keycloak;

    @Autowired
    CorporateService corporateService;

    @Autowired
    TemplateService templateService;

    @Autowired
    EmailService emailService;

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    private BankInfoRepository bankInfoRepository;

    @Autowired
    private AsyncSendEmailService asyncSendEmailService;

    @Autowired
    private CorporateUserResponseMapper corporateUserResponseMapper;

    @Override
    public UserInfoEntity getUserInfo(String userId) {
        List<UserInfoEntity> userInfoList = userInfoRepository.findByUserId(userId);
        if (!userInfoList.isEmpty()) {
            return userInfoList.get(0);
        }
        return null;
    }

    @Override
    public List<UserInfoEntity> getAll() {
        return userInfoRepository.findAll(Sort.by(Sort.Direction.DESC, "lastModifiedDate"));
    }

    @Override
    public UserInfoEntity getUserInfoById(Long id) {
        try {

            return userInfoRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.info("Error getUserInfoById : " + e);
            return null;
        }
    }

    @Override
    public List<UserInfoEntity> getByCorporateIdOrderById(String corporateId) {
        return userInfoRepository.getByCorporateIdOrderById(Long.parseLong(corporateId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoResponse createUserInfo(UserInfoRequest userInfoRequest) throws LCPlatformException {
        Response response = null;
        String passwordRandom = FilesUtils.randomPassword();

        long count = userInfoRepository.countByEmailIsIgnoreCase(userInfoRequest.getEmail());

        if (count > 1) {
            throw new LCPlatformException(ResponseCode.EMAIL_EXISTED);
        }


        RealmResource realmResource = keycloak.realm(keycloakRealm);
        UsersResource usersRessource = realmResource.users();

        Long id = userInfoRepository.getSequence();
        String userCode = "";
        if (userInfoRequest.getUserType().toUpperCase().equals(Constant.USER_TYPE_BANK)) {
            userCode = userInfoRequest.getBankCode() + "." + userInfoRequest.getBranchLevel() + "."
                    + String.format("%03d", id + 1);
        } else {
            userCode = Constant.USER_TYPE_FPT + String.format("%03d", id + 1);
        }
        userInfoRequest.setUserCode(userCode);
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userInfoRequest.getUserCode());
        user.setFirstName(userInfoRequest.getUserName());
        user.setLastName(userInfoRequest.getUserName());
        user.setEmail(userInfoRequest.getEmail());
        user.setEnabled(userInfoRequest.getEnable());
        String password = "";
        if (userInfoRequest.getPassword() != null
                && !userInfoRequest.getPassword().isEmpty()
                && !userInfoRequest.getPassword().trim().isEmpty()) {
            password = userInfoRequest.getPassword();
        } else {
            password = passwordRandom;
        }
        response = usersRessource.create(user);

        if (response.getStatus() == 201) {

            String userId = CreatedResponseUtil.getCreatedId(response);
            userInfoRequest.setUserId(userId);

            usersRessource = realmResource.users();
            UserResource userResource = usersRessource.get(userId);
            if (!password.isEmpty()) {
                CredentialRepresentation passwordCred = new CredentialRepresentation();
                passwordCred.setTemporary(false);
                passwordCred.setType(CredentialRepresentation.PASSWORD);
                passwordCred.setValue(password);
                // Set password credential
                userResource.resetPassword(passwordCred);
            }

            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity = userInfoRequestMapper.toEntity(userInfoRequest);
            userInfoEntity.setUserId(userId);
            userInfoEntity.setUserName(userInfoRequest.getUserName());
            List<UserGroupEntity> userGroupEntities = new ArrayList<>();
            List<String> userGroupCodes = userInfoRequest.getListUserGroupCode();
            List<String> listGroupId = new ArrayList<>();
            for (String code : userGroupCodes) {
                UserGroupEntity userGroupEntity = userGroupService.findByUserGroupCode(code);
                userGroupEntities.add(userGroupEntity);
                listGroupId.add(userGroupEntity.getGroupId());

            }
            userInfoEntity.setUserGroupEntitys(userGroupEntities);
            UserInfoEntity userInfoAdd = userInfoRepository.save(userInfoEntity);

            try {
                listGroupId.forEach(groupId -> {
                    keycloak.realm(keycloakRealm).users().get(userId).joinGroup(groupId);
                });
            } catch (Exception e) {
                keycloak.realm(keycloakRealm).users().delete(userId);
                throw new LCPlatformException(ResponseCode.BAD_REQUEST);
            }


            try {
                Email email = null;
                if (userInfoRequest.getUserType().toUpperCase().equals(Constant.USER_TYPE_BANK)) {
                    email = getEmail003(userInfoAdd, password);
                } else {
                    email = getEmail005(userInfoAdd, password);
                }

                emailService.sendEmailWithTemplate(email);
            } catch (Exception e) {
                log.error(e.fillInStackTrace().getMessage());
                keycloak.realm(keycloakRealm).users().delete(userId);
                throw new LCPlatformException(ResponseCode.BAD_REQUEST, "Email exception");
            }

            return userInfoResponseMapper.toDomain(userInfoAdd);
        }
        if (response != null) {
            response.close();
        }
        throw new LCPlatformException(ResponseCode.CONFLICT);

    }

    @Override
    public UserInfoResponse updateUserInfo(UserInfoRequest userInfoRequest, Long id) throws LCPlatformException {

        try {

            UserInfoEntity userInfoEntity = getUserInfoById(id);
            String userId = userInfoEntity.getUserId();
            List<UserGroupEntity> oldUserGroups = userInfoEntity.getUserGroupEntitys();

            RealmResource realmResource = keycloak.realm(keycloakRealm);
            UsersResource usersRessource = realmResource.users();
            UserResource userResource = usersRessource.get(userId);

            UserRepresentation user = new UserRepresentation();
//            user.setUsername(userInfoRequest.getUserCode());
            user.setFirstName(userInfoRequest.getUserName());
            user.setLastName(userInfoRequest.getUserName());
            user.setEmail(userInfoRequest.getEmail());
            user.setEnabled(userInfoRequest.getEnable());

            userResource.update(user);
            ModelMapperUtils.mapper(userInfoRequest, userInfoEntity);
//            UserInfoEntity userInfoEntityUp = userInfoRequestMapper.toEntity(userInfoRequest);
//            userInfoEntityUp.setId(id);
//            userInfoEntityUp.setUserId(userId);
//            userInfoEntityUp.setUserCode(userInfoEntity.getUserCode());
//            userInfoEntityUp.setCreatedBy(userInfoEntity.getCreatedBy());
//            userInfoEntityUp.setCreatedDate(userInfoEntity.getCreatedDate());
            List<UserGroupEntity> userGroupEntities = new ArrayList<>();
            List<String> userGroupCodes = userInfoRequest.getListUserGroupCode();
            List<String> listGroupId = new ArrayList<>();
            for (String code : userGroupCodes) {
                UserGroupEntity userGroupEntity = userGroupService.findByUserGroupCode(code);
                userGroupEntities.add(userGroupEntity);
                listGroupId.add(userGroupEntity.getGroupId());
            }
            userInfoEntity.setUserGroupEntitys(userGroupEntities);

            oldUserGroups.forEach(oldUserGroup -> {
                keycloak.realm(keycloakRealm).users().get(userId).leaveGroup(oldUserGroup.getGroupId());
            });

            listGroupId.forEach(groupId -> {
                keycloak.realm(keycloakRealm).users().get(userId).joinGroup(groupId);
            });


            UserInfoEntity userInfoUp = null;
            try {
                userInfoUp = userInfoRepository.save(userInfoEntity);
                return userInfoResponseMapper.toDomain(userInfoUp);
            } catch (Exception e) {
                oldUserGroups.forEach(oldUserGroup -> {
                    keycloak.realm(keycloakRealm).users().get(userId).joinGroup(oldUserGroup.getGroupId());
                });
                listGroupId.forEach(groupId -> {
                    keycloak.realm(keycloakRealm).users().get(userId).leaveGroup(groupId);
                });
                log.error(e.getMessage());
            }
            return null;
        } catch (Exception e) {
            log.error(e.fillInStackTrace().getMessage());
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
    }

    @Override
    public UserInfoResponse deleteByUserInfoId(Long id) {
        try {
            UserInfoEntity userInfo = getUserInfoById(id);
            log.info("Delete UserInfo success: " + userInfo.toString());

            RealmResource realmResource = keycloak.realm(keycloakRealm);
            UsersResource usersRessource = realmResource.users();
            UserResource userResource = usersRessource.get(userInfo.getUserId());
            userResource.remove();

            userInfoRepository.deleteById(id);
            return userInfoResponseMapper.toDomain(userInfo);
        } catch (Exception e) {
            log.info("Delete UserInfo fail: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<UserInfoEntity> searchUser(String bankCode, String userId, String userName, String userType,
                                           String userStatus, String channel, String branchLevel, String groupType, String role) {
        return userInfoRepository.searchUser(bankCode, userId, userName, userType, userStatus,
                channel, branchLevel, groupType, role);
    }

    @Override
    public List<UserInfoEntity> getByCorporateId(String corporateId) {
        return userInfoRepository.getByCorporateId(Long.parseLong(corporateId));
    }

    @Override
    public List<UserInfoEntity> createAllCorporateUserInfo(List<UserInfoEntity> userInfoEntityList) throws LCPlatformException, SendEmailException, InterruptedException {
        List<UserInfoEntity> userInfoList = new ArrayList<>();
//        String passwordNew = FilesUtils.randomPassword();
//        Long id = userInfoRepository.getSequence();
        List<CorporateUserResponse> corporateUserResponseList = new ArrayList<>();
        for (UserInfoEntity userInfo : userInfoEntityList) {
            Response response = null;
            String passwordNew = FilesUtils.randomPassword();
            try {

                RealmResource realmResource = keycloak.realm(keycloakRealm);
                UsersResource usersRessource = realmResource.users();

                String userCode = userInfo.getCorporate().getCorporateCode() + String.format("%03d", userInfo.getId());
                userInfo.setUserCode(userCode);
                UserRepresentation user = new UserRepresentation();
                user.setUsername(userInfo.getUserCode());
                user.setFirstName(userInfo.getUserName());
                user.setEmail(userInfo.getEmail());
                user.setEnabled(true);
                response = usersRessource.create(user);

                if (response.getStatus() == 201) {
                    String userId = CreatedResponseUtil.getCreatedId(response);
                    userInfo.setUserId(userId);

                    usersRessource = realmResource.users();
                    UserResource userResource = usersRessource.get(userId);
                    CredentialRepresentation passwordCred = new CredentialRepresentation();
                    passwordCred.setTemporary(false);
                    passwordCred.setType(CredentialRepresentation.PASSWORD);
//                    passwordCred.setValue(Constant.PASSWORD_DEFAULT);
                    passwordCred.setValue(passwordNew);
                    // Set password credential
                    userResource.resetPassword(passwordCred);

                    List<UserGroupEntity> listUserGroupEntity = userInfo.getUserGroupEntitys();
                    try {
                        listUserGroupEntity.forEach(userGroupEntity -> {
                            keycloak.realm(keycloakRealm).users().get(userId).joinGroup(userGroupEntity.getGroupId());
                        });
                    } catch (Exception e) {
                        keycloak.realm(keycloakRealm).users().delete(userId);
                        throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                    }
                } else {
                    log.info("Create fail: username or email already exist");
                    throw new LCPlatformException(ResponseCode.USER_NAME_EXISTED);
                }

            } catch (Exception e) {
                log.info("Error createUserInfo: " + e);
                throw new LCPlatformException(ResponseCode.USER_NAME_EXISTED);
            } finally {
                if (response != null) {
                    response.close();
                }
            }
//            gitHubLookupService.sendEmail(userInfo, passwordNew);
//            this.sendEmail(userInfo, passwordNew);
            CorporateUserResponse corporateUserResponse = corporateUserResponseMapper.toDomain(userInfo);
            corporateUserResponse.setCorporateName(userInfo.getCorporate().getCorporateName());
            corporateUserResponse.setPassword(passwordNew);
            corporateUserResponseList.add(corporateUserResponse);
            userInfoList.add(userInfo);
        }
        asyncSendEmailService.sendEmail001(corporateUserResponseList);
        List<UserInfoEntity> listUserFpts = userInfoRepository.getByUserType("FPT");
        if(listUserFpts.size() > 0){
            asyncSendEmailService.sendEmailFPT(userInfoList, listUserFpts);
        }
        return userInfoList;
    }

    public void sendEmail(UserInfoEntity userInfo, String passwordNew) throws InterruptedException{
        try {
            Email email = getEmail001(userInfo, passwordNew);
            emailService.sendEmailWithTemplate(email);
            Thread.sleep(1000L);
        } catch (Exception e) {
            log.info("Error createUserInfo: " + e);
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
//        return CompletableFuture.completedFuture(userInfo);
    }


    @Override
    public UserInfoResponse updateStatusUserInfo(UserInfoEntity userIn) {
        UserInfoEntity userInfoUp = userInfoRepository.save(userIn);
        Email email = getEmail002C(userInfoUp);
        try {
            emailService.sendEmailWithTemplate(email);
        } catch (SendEmailException e) {
            log.debug("---Error when sendMail::: " + e.getMessage() + "---");
        }
        return userInfoResponseMapper.toDomain(userInfoUp);

    }

    @Override
    public UserInfoEntity getUserLogin(String userId) {
        List<UserInfoEntity> userList = userInfoRepository.findByUserId(userId);

        UserInfoEntity userInfoLogin = new UserInfoEntity();
        if (!userList.isEmpty()) {
            userInfoLogin = userList.get(0);
        }
        return userInfoLogin;
    }

    @Override
    public String changePasswordUser(String passwordNew, String userId) {
        try {
            if (!passwordNew.isEmpty() && !userId.isEmpty()) {
                CredentialRepresentation passwordCred = new CredentialRepresentation();
                passwordCred.setTemporary(false);
                passwordCred.setType(CredentialRepresentation.PASSWORD);
                passwordCred.setValue(passwordNew);
                keycloak.realm(keycloakRealm).users().get(userId).resetPassword(passwordCred);
                return "Success";
            } else {
                return "Fail";
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return "Fail";
        }
    }

    @Override
    public String forgetPasswordUser(String userCode) {
        try {
            String passwordNew = FilesUtils.randomPassword();
            log.info("passwordForget: " + passwordNew);
            List<UserInfoEntity> userInfoList = userInfoRepository.findByUserCode(userCode);
            if (!userInfoList.isEmpty() && userInfoList.size() > 0) {
                UserInfoEntity userInfo = userInfoRepository.findByUserCode(userCode).get(0);
                String userId = userInfo.getUserId();
                if (!passwordNew.isEmpty() && !userId.isEmpty()) {
                    CredentialRepresentation passwordCred = new CredentialRepresentation();
                    passwordCred.setTemporary(false);
                    passwordCred.setType(CredentialRepresentation.PASSWORD);
                    passwordCred.setValue(passwordNew);
                    keycloak.realm(keycloakRealm).users().get(userId).resetPassword(passwordCred);

                    // Send email corporate user reset password
                    Email email = getEmail012(userInfo, passwordNew);
                    emailService.sendEmailWithTemplate(email);

                    return "Success";
                } else {
                    return "Fail";
                }
            } else {
                return "User does not exist";
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return "Fail";
        }
    }

    @Override
    public String forgetPasswordUser(PasswordRequest request) {
        String userCode = request.getUserCode();
        List<UserInfoEntity> userInfoList = userInfoRepository.findByUserCode(userCode);
        if (!userInfoList.isEmpty() && userInfoList.size() > 0) {
            UserInfoEntity userInfo = userInfoRepository.findByUserCode(userCode).get(0);
            String userId = userInfo.getUserId();

            if(!request.getEmail().equals(userInfo.getEmail())) {
                throw new LCPlatformException(ResponseCode.ACCOUNT_EMAIL_IS_NOT_CORRECT);
            }

            String passwordNew = FilesUtils.randomPassword();
            if (!passwordNew.isEmpty() && !userId.isEmpty()) {
                CredentialRepresentation passwordCred = new CredentialRepresentation();
                passwordCred.setTemporary(false);
                passwordCred.setType(CredentialRepresentation.PASSWORD);
                passwordCred.setValue(passwordNew);
                keycloak.realm(keycloakRealm).users().get(userId).resetPassword(passwordCred);

                try {
                    Email email = null;
                    if (userInfo.getUserType().toUpperCase().equals(Constant.USER_TYPE_BANK)) {
                        List<BankInfo> bankInfoList = bankInfoRepository.findByBankCode(userInfo.getBankCode());
                        if (!bankInfoList.isEmpty() && bankInfoList.size() > 0) {
                            BankInfo bankInfo = bankInfoList.get(0);
                            email = getEmail010(userInfo, bankInfo, passwordNew);
                        }
                    } else {
                        email = getEmail012(userInfo, passwordNew);
                    }

                    emailService.sendEmailWithTemplate(email);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                }


                return "Success";
            } else {
                throw new LCPlatformException(ResponseCode.ACCOUNT_IS_NOT_CORRECT);
            }
        } else {
            throw new LCPlatformException(ResponseCode.ACCOUNT_IS_NOT_CORRECT);
        }
    }

    @Override
    public String resetPasswordUser(String userId) {

        String passwordNew = FilesUtils.randomPassword();
        List<UserInfoEntity> userInfoList = userInfoRepository.findByUserId(userId);
        if (!userInfoList.isEmpty() && userInfoList.size() > 0) {
            UserInfoEntity userInfo = userInfoList.get(0);
            if (!passwordNew.isEmpty() && !userId.isEmpty()) {
                CredentialRepresentation passwordCred = new CredentialRepresentation();
                passwordCred.setTemporary(false);
                passwordCred.setType(CredentialRepresentation.PASSWORD);
                passwordCred.setValue(passwordNew);

                try {
                    keycloak.realm(keycloakRealm).users().get(userId).resetPassword(passwordCred);
                } catch (Exception e) {
                    throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                }

                try {
                    Email email = getEmail011(userInfo, passwordNew);
                    emailService.sendEmailWithTemplate(email);
                } catch (Exception e) {
                    log.error(" Can not send email");
                    throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                }

                return "Success";
            }
        }
        return "Fail";

    }

    @Override
    public String resetPasswordUserCorporate(String userId) {

        String passwordNew = FilesUtils.randomPassword();

        List<UserInfoEntity> userInfoList = userInfoRepository.findByUserId(userId);
        if (!userInfoList.isEmpty() && userInfoList.size() > 0) {
            UserInfoEntity userInfo = userInfoList.get(0);

            if (!passwordNew.isEmpty() && !userId.isEmpty()) {
                CredentialRepresentation passwordCred = new CredentialRepresentation();
                passwordCred.setTemporary(false);
                passwordCred.setType(CredentialRepresentation.PASSWORD);
                passwordCred.setValue(passwordNew);
                try {
                    keycloak.realm(keycloakRealm).users().get(userId).resetPassword(passwordCred);
                } catch (Exception e) {
                    throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                }

                // Send email corporate user reset password
                try {
                    Email email = getEmail007(userInfo, passwordNew);
                    emailService.sendEmailWithTemplate(email);
                } catch (Exception e) {
                    log.error(" Can not send email");
                    throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                }

                return "Success";
            }
        }
        return "Fail";

    }

    @Override
    public String resetPasswordUserBank(String userId) throws SendEmailException {

        String passwordNew = FilesUtils.randomPassword();
        log.info("passwordReset: " + passwordNew);

        List<UserInfoEntity> userInfoList = userInfoRepository.findByUserId(userId);
        if (!userInfoList.isEmpty() && userInfoList.size() > 0) {
            UserInfoEntity userInfo = userInfoList.get(0);
            List<BankInfo> bankInfoList = bankInfoRepository.findByBankCode(userInfo.getBankCode());
            if (!bankInfoList.isEmpty() && bankInfoList.size() > 0) {
                BankInfo bankInfo = bankInfoList.get(0);
                if (!passwordNew.isEmpty() && !userId.isEmpty()) {
                    CredentialRepresentation passwordCred = new CredentialRepresentation();
                    passwordCred.setTemporary(false);
                    passwordCred.setType(CredentialRepresentation.PASSWORD);
                    passwordCred.setValue(passwordNew);
                    try {
                        keycloak.realm(keycloakRealm).users().get(userId).resetPassword(passwordCred);
                    } catch (Exception e) {
                        throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                    }


                    // Send email bank user reset password
                    try {
                        Email email = getEmail011(userInfo, passwordNew);
                        emailService.sendEmailWithTemplate(email);
                    } catch (Exception e) {
                        log.error(" Can not send email");
                        throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                    }

                    return "Success";
                }
            }
        }
        return "Fail";

    }

    @Override
    public Optional<UserInfoEntity> getUserLoginByUserCode(String userCode) {
        return userInfoRepository.findByUserCodeIsIgnoreCase(userCode);
    }

    /**
     * get Email by Template 001C.
     *
     * @param userInfoAdd
     * @param password
     * @return
     */
    public Email getEmail001C(UserInfoEntity userInfoAdd, String password) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_001C);
        Email email = new Email();
        email.setTo(new String[]{userInfoAdd.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(contentEmailTemplate001C(password, userInfoAdd, template));
        return email;
    }

    /**
     * get content Email in Template.
     *
     * @param password
     * @param userInfoAdd
     * @param template
     * @return content : String
     */
    public String contentEmailTemplate001C(String password, UserInfoEntity userInfoAdd, Template template) {
        return template.getTemplateContent().replace("$user_name", userInfoAdd.getUserName())
                .replace("$user_code", userInfoAdd.getUserCode())
                .replace("$password", password);
    }

    /**
     * get content Email in Template 002C.
     *
     * @param userInfoUp
     * @return
     */
    public Email getEmail002C(UserInfoEntity userInfoUp) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_002C);
        Email email = new Email();
        email.setTo(new String[]{userInfoUp.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate002C(userInfoUp, template));
        return email;
    }

    /**
     * get Content for email in Template Email_002C.
     *
     * @param userInfoUp
     * @param template
     * @return content : String
     */
    public String getContentTemplate002C(UserInfoEntity userInfoUp, Template template) {
        String status = "";
        status = UserStatus.valueOf(userInfoUp.getUserStatus()).getDescription();
        String content = template.getTemplateContent();
        content = content.replace("$user_name", userInfoUp.getUserName()).replace("$user_status", status);
        return content;
    }

    /**
     * get Content for email in Template Email_001.
     *
     * @param userInfoUp
     * @returnN
     */
    public Email getEmail001(UserInfoEntity userInfoUp, String password) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_001);
        Email email = new Email();
        email.setTo(new String[]{userInfoUp.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate001(userInfoUp, template, password));
        return email;
    }

    /**
     * get Content for email in Template Email_001.
     *
     * @param userInfoUp
     * @param template
     * @return content : String
     */
    public String getContentTemplate001(UserInfoEntity userInfoUp, Template template, String password) {
        String status = "";
        status = UserStatus.valueOf(userInfoUp.getUserStatus()).getDescription();
        String content = template.getTemplateContent();
        content = content.replace("$corporate_name", userInfoUp.getCorporate().getCorporateName())
                .replace("$user_name", userInfoUp.getUserName())
                .replace("$user_code", userInfoUp.getUserCode())
                .replace("$password", password)
                .replace("$link_website", fptWebsite)
                .replace("$hotline_number", hotline);
        return content;
    }

    /**
     * get Content for email in Template Email_002.
     *
     * @param userInfoUp
     * @return
     */
    public Email getEmail002(UserInfoEntity userInfoUp, String password) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_002);
        Email email = new Email();
        email.setTo(new String[]{userInfoUp.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate002(userInfoUp, template, password));
        return email;
    }

    /**
     * get Content for email in Template Email_002.
     *
     * @param userInfoUp
     * @param template
     * @return content : String
     */
    public String getContentTemplate002(UserInfoEntity userInfoUp, Template template, String password) {
        String status = "";
        status = UserStatus.valueOf(userInfoUp.getUserStatus()).getDescription();
        String content = template.getTemplateContent();
        content = content.replace("corporate_name", userInfoUp.getCorporate().getCorporateName())
                .replace("$user_name", userInfoUp.getUserName())
                .replace("$user_code", userInfoUp.getUserCode())
                .replace("$password", password)
                .replace("$link_website", fptWebsite)
                .replace("$hotline_number", hotline);
        return content;
    }

    /**
     * get Content for email in Template Email_003.
     *
     * @param userInfoUp UserInfoEntity
     * @return Email
     */
    public Email getEmail003(UserInfoEntity userInfoUp, String password) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_003);
        return Email.builder()
                .to(new String[]{userInfoUp.getEmail()})
                .subject(template.getTemplateSubject())
                .htmlBody(getContentTemplate003(userInfoUp, template, password))
                .build();
    }

    /**
     * get Content for email in Template Email_003.
     *
     * @param userInfoUp userInfoUp
     * @param template template
     * @return content : String
     */
    public String getContentTemplate003(UserInfoEntity userInfoUp, Template template, String password) {
        String content = template.getTemplateContent();
        BankInfo bankInfoByBankCode = bankInfoRepository.findBankInfoByBankCode(userInfoUp.getBankCode());
        content = content.replace("$bank_name", bankInfoByBankCode.getBankName())
                .replace("$user_name", userInfoUp.getUserName())
                .replace("$user_code", userInfoUp.getUserCode())
                .replace("$password", password)
                .replace("$link_website", fptWebsite)
                .replace("$hotline_number", hotline);
        return content;
    }

    /**
     * get Content for email in Template Email_005.
     *
     * @param userInfoUp
     * @return
     */
    public Email getEmail005(UserInfoEntity userInfoUp, String password) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_005);
        return Email.builder()
                .to(new String[]{userInfoUp.getEmail()})
                .subject(template.getTemplateSubject())
                .htmlBody(getContentTemplate005(userInfoUp, template, password))
                .build();
    }

    /**
     * get Content for email in Template Email_005.
     *
     * @param userInfoUp
     * @param template
     * @return content : String
     */
    public String getContentTemplate005(UserInfoEntity userInfoUp, Template template, String password) {
        String status = "";
        status = UserStatus.valueOf(userInfoUp.getUserStatus()).getDescription();
        String content = template.getTemplateContent();
        content = content.replace("$user_name", userInfoUp.getUserName())
                .replace("$user_code", userInfoUp.getUserCode())
                .replace("$password", password);
        return content;
    }

    /**
     * get Content for email in Template Email_006.
     *
     * @param userInfoUp
     * @return
     */
    public Email getEmail006(UserInfoEntity userInfoUp, String password) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_006);
        Email email = new Email();
        email.setTo(new String[]{userInfoUp.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate006(userInfoUp, template, password));
        return email;
    }

    /**
     * get Content for email in Template Email_006.
     *
     * @param userInfoUp
     * @param template
     * @return content : String
     */
    public String getContentTemplate006(UserInfoEntity userInfoUp, Template template, String password) {
        String status = "";
        status = UserStatus.valueOf(userInfoUp.getUserStatus()).getDescription();
        String content = template.getTemplateContent();
        content = content.replace("$corporate_name", userInfoUp.getCorporate().getCorporateName())
                .replace("$user_name", userInfoUp.getUserName())
                .replace("$password", password)
                .replace("$hotline_number", hotline);
        return content;
    }

    /**
     * get Content for email in Template Email_007.
     *
     * @param userInfoUp
     * @return
     */
    public Email getEmail007(UserInfoEntity userInfoUp, String password) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_007);
        Email email = new Email();
        email.setTo(new String[]{userInfoUp.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate007(userInfoUp, template, password));
        return email;
    }

    /**
     * get Content for email in Template Email_007.
     *
     * @param userInfoUp
     * @param template
     * @return content : String
     */
    public String getContentTemplate007(UserInfoEntity userInfoUp, Template template, String password) {
        String content = template.getTemplateContent();
        content = content.replace("$corporate_name", userInfoUp.getCorporate().getCorporateName())
                .replace("$user_name", userInfoUp.getUserName())
                .replace("$password", password)
                .replace("$hotline_number", hotline);
        return content;
    }

    /**
     * get Content for email in Template Email_008.
     *
     * @param userInfoUp userInfoUp
     * @return Email
     */
    public Email getEmail008(UserInfoEntity userInfoUp, BankInfo bankInfo, String password) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_008);
        Email email = new Email();
        email.setTo(new String[]{userInfoUp.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate008(userInfoUp, bankInfo, template, password));
        return email;
    }

    /**
     * get Content for email in Template Email_008.
     *
     * @param userInfoUp userInfoUp
     * @param template template
     * @return content : String
     */
    public String getContentTemplate008(UserInfoEntity userInfoUp, BankInfo bankInfo, Template template, String
            password) {
        String content = template.getTemplateContent();
        content = content.replace("$bank_name", bankInfo.getBankName())
                .replace("$user_name", userInfoUp.getUserName())
                .replace("$password", password)
                .replace("$hotline_number", hotline);
        return content;
    }


    /**
     * get Content for email in Template Email_010.
     *
     * @param userInfoUp userInfoUp
     * @return Email
     */
    public Email getEmail010(UserInfoEntity userInfoUp, BankInfo bankInfo, String password) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_010);
        Email email = new Email();
        email.setTo(new String[]{userInfoUp.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate010(userInfoUp, bankInfo, template, password));
        return email;
    }

    public Email getEmail004(UserInfoEntity userInfoUp, BankInfo bankInfo, String password) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_005);
        Email email = new Email();
        email.setTo(new String[]{userInfoUp.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate004(userInfoUp, bankInfo, template, password));
        return email;
    }

    /**
     * get Content for email in Template Email_001.
     *
     * @param userInfoUp userInfoUp
     * @param template template
     * @return content : String
     */
    public String getContentTemplate004(UserInfoEntity userInfoUp, BankInfo bankInfo, Template template, String password) {
        String content = template.getTemplateContent();
        content = content.replace("$bank_name", bankInfo.getBankName())
                .replace("$user_name", userInfoUp.getUserName())
                .replace("$user_code", userInfoUp.getUserCode())
                .replace("$password", password)
                .replace("$link_website", fptWebsite)
                .replace("$hotline_number", hotline);
        return content;
    }

    /**
     * get Content for email in Template Email_010.
     *
     * @param userInfoUp
     * @param template
     * @return content : String
     */
    public String getContentTemplate010(UserInfoEntity userInfoUp, BankInfo bankInfo, Template template, String
            password) {
        String status = "";
        status = UserStatus.valueOf(userInfoUp.getUserStatus()).getDescription();
        String content = template.getTemplateContent();
        content = content.replace("$bank_name", bankInfo.getBankName())
                .replace("$user_name", userInfoUp.getUserName())
                .replace("$password", password)
                .replace("$hotline_number", hotline);
        return content;
    }

    /**
     * get Content for email in Template Email_011.
     *
     * @param userInfoUp
     * @return
     */
    public Email getEmail011(UserInfoEntity userInfoUp, String password) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_011);
        Email email = new Email();
        email.setTo(new String[]{userInfoUp.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate011(userInfoUp, template, password));
        return email;
    }

    /**
     * get Content for email in Template Email_011.
     *
     * @param userInfoUp userInfoUp
     * @param template template
     * @return content : String
     */
    public String getContentTemplate011(UserInfoEntity userInfoUp, Template template, String password) {
        String status = "";
        status = UserStatus.valueOf(userInfoUp.getUserStatus()).getDescription();
        String content = template.getTemplateContent();
        content = content.replace("$user_name", userInfoUp.getUserName())
                .replace("$user_code", userInfoUp.getUserCode())
                .replace("$password", password);
        return content;
    }

    /**
     * get Content for email in Template Email_012.
     *
     * @param userInfoUp
     * @return
     */
    public Email getEmail012(UserInfoEntity userInfoUp, String password) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_012);
        Email email = new Email();
        email.setTo(new String[]{userInfoUp.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate012(userInfoUp, template, password));
        return email;
    }

    /**
     * get Content for email in Template Email_012.
     *
     * @param userInfoUp
     * @param template
     * @return content : String
     */
    public String getContentTemplate012(UserInfoEntity userInfoUp, Template template, String password) {
        String status = "";
        status = UserStatus.valueOf(userInfoUp.getUserStatus()).getDescription();
        String content = template.getTemplateContent();
        content = content.replace("$user_name", userInfoUp.getUserName())
                .replace("$user_code", userInfoUp.getUserCode())
                .replace("$password", password);
        return content;
    }

    //    /**
//     * get Content for email in Template Email_015.
//     *
//     * @param userInfoUp
//     * @return
//     */
    public Email getEmail015(UserInfoEntity userInfoUp, String emailFpt, List<UserInfoEntity> userList) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_015);
        Email email = new Email();
        email.setTo(new String[]{emailFpt});
        email.setSubject(template.getTemplateSubject());
        String userListStr = getUserListCreated(userList);
        email.setHtmlBody(getContentTemplate015(userInfoUp, template, userListStr));
        return email;
    }

    /**
     * get user List for html.
     *
     * @param userList
     * @return : String contractAddendum html.
     */
    private String getUserListCreated(List<UserInfoEntity> userList) {
        StringBuffer userListResult = new StringBuffer();
        if (userList != null) {
            for (int i = 0; i < userList.size(); i++) {
                UserInfoEntity userInfoEntity = userList.get(i);

                userListResult
                        .append("<tr><td>Mã người dùng ").append(i + 1 + ": ").append("</td><td>").append(userInfoEntity.getUserCode())
                        .append("</td></tr><tr><td>Tên hiển thị:</td><td>").append(userInfoEntity.getUserName())
                        .append("</td></tr><br>");
            }
        }
        String line = userListResult.toString();
        byte[] byteText = line.getBytes(StandardCharsets.UTF_8);
        return new String(byteText, StandardCharsets.UTF_8);
    }

    //
//    /**
//     * get Content for email in Template Email_015.
//     *
//     * @param userInfoUp
//     * @param template
//     * @return content : String
//     */
    private String getContentTemplate015(UserInfoEntity userInfoUp, Template template, String listUserCreated) {
        String status = "";
        status = UserStatus.valueOf(userInfoUp.getUserStatus()).getDescription();
        String content = template.getTemplateContent();
        content = content.replace("$corporate_code", userInfoUp.getCorporate().getCorporateCode())
                .replace("$corporate_name", userInfoUp.getCorporate().getCorporateName())
                .replace("$listUserInfo", listUserCreated);

        return content;
    }

}
