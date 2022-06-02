package fis.com.vn.service.bank.impl;

import com.google.common.collect.Lists;
import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.BankInfo;
import fis.com.vn.model.entity.Email;
import fis.com.vn.model.entity.UserGroupEntity;
import fis.com.vn.model.entity.UserInfoEntity;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.repository.BankInfoRepository;
import fis.com.vn.repository.UserGroupRepository;
import fis.com.vn.repository.UserInfoRepository;
import fis.com.vn.rest.mapper.BankUserRequestMapper;
import fis.com.vn.rest.mapper.BankUserResponseMapper;
import fis.com.vn.rest.request.BankUserRequest;
import fis.com.vn.rest.response.BankUserResponse;
import fis.com.vn.service.bank.BankUserService;
import fis.com.vn.service.common.KeycloakService;
import fis.com.vn.service.common.impl.UserInfoServiceImpl;
import fis.com.vn.service.notification.EmailService;
import fis.com.vn.service.notification.TemplateService;
import fis.com.vn.util.Constant;
import fis.com.vn.util.FilesUtils;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BankUserServiceImpl implements BankUserService {

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    BankUserResponseMapper bankUserResponseMapper;

    @Autowired
    BankUserRequestMapper bankUserRequestMapper;

    @Value("${keycloak.realm}")
    String keycloakRealm;

    @Autowired
    KeycloakService keycloakService;

    @Autowired
    Keycloak keycloak;

    @Autowired
    private BankInfoRepository bankInfoRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    private UserInfoServiceImpl userInfoServiceImpl;

    @Autowired
    TemplateService templateService;

    /**
     * Get List Data User for Bank.
     *
     * @return : List<BankUserResponse>
     */
    @Override
    public List<BankUserResponse> getAllUserBank() throws LCPlatformException {
        List<BankUserResponse> bankUserResponseList = new ArrayList<>();
        // TODO Lấy tất cả user có role là BANK.
        List<UserInfoEntity> userList = userInfoRepository.getByUserType(Constant.USER_TYPE_BANK);

        if (userList.isEmpty()) {
            return bankUserResponseList;
        }
        // TODO Chạy vòng for map dữ liệu từ UserInfoEntity sang BankUserResponse
        for (UserInfoEntity userInfo : userList) {
            List<String> listGroupCode = new ArrayList<>();
            BankUserResponse bankUserResponse = bankUserResponseMapper.toDomain(userInfo);
            String branchLevels = "";
            String groupTypes = "";
            String roles = "";
            Set<String> channels = new HashSet<>();
            // TODO Chạy vòng for UserGroupEntity đối với mỗi userInfo
            for (UserGroupEntity group : userInfo.getUserGroupEntitys()) {
                listGroupCode.add(group.getUserGroupCode());
                branchLevels = group.getBranchLevel();
                groupTypes = group.getGroupType();
                roles = group.getRole();
                if (group.getChannels() != null && !group.getChannels().equals("")) {
                    for (String chanel : group.getChannels().split(",")) {
                        channels.add(chanel);
                    }
                }
            }
            bankUserResponse.setBranchLevels(branchLevels);
            bankUserResponse.setGroupTypes(groupTypes);
            bankUserResponse.setRoles(roles);
            bankUserResponse.setChannels(Lists.newArrayList(channels));
            bankUserResponse.setUserGroupCodes(listGroupCode);
            bankUserResponseList.add(bankUserResponse);
        }
        return bankUserResponseList;
    }

    /**
     * Get Record Detail User for Bank.
     *
     * @param id : Id.
     * @return : BankUserResponse
     */
    @Override
    public BankUserResponse getBankUserById(String id) throws LCPlatformException {
        BankUserResponse bankUserResponse;

        // TODO Lấy thông tin UserInfoEntity theo id
        UserInfoEntity userInfo = userInfoRepository.getById(Long.parseLong(id));
        List<String> listGroupCode = new ArrayList<>();
        bankUserResponse = bankUserResponseMapper.toDomain(userInfo);
        String branchLevels = "";
        String groupTypes = "";
        String roles = "";
        Set<String> channels = new HashSet<>();
        for (UserGroupEntity group : userInfo.getUserGroupEntitys()) {
            listGroupCode.add(group.getUserGroupCode());
            branchLevels = group.getBranchLevel();
            groupTypes = group.getGroupType();
            roles = group.getRole();
            if (group.getChannels() != null && !group.getChannels().equals("")) {
                for (String chanel : group.getChannels().split(",")) {
                    channels.add(chanel);
                }
            }
        }
        List<UserGroupEntity> userGroupEntityList = userGroupRepository.getByUserId(bankUserResponse.getId());
        if(!userGroupEntityList.isEmpty()){
            bankUserResponse.setUserGroupTypeList(userGroupEntityList.stream().map(x -> x.getGroupType()).collect(Collectors.toSet()));
        }
        bankUserResponse.setBranchLevels(branchLevels);
        bankUserResponse.setGroupTypes(groupTypes);
        bankUserResponse.setRoles(roles);
        bankUserResponse.setChannels(Lists.newArrayList(channels));
        bankUserResponse.setUserGroupCodes(listGroupCode);
        return bankUserResponse;
    }

    /**
     * Create User for Bank
     *
     * @param bankUserRequest
     * @throws LCPlatformException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(BankUserRequest bankUserRequest) throws LCPlatformException {
        Response response = null;
        // out when bankUserRequest is null.
        if (bankUserRequest == null) {
            return;
        }


        Long id = userInfoRepository.getSequence();
        String userCode = bankUserRequest.getBankCode() + "." + String.format("%03d", id + 1);
        bankUserRequest.setUserCode(userCode);
        RealmResource realmResource = keycloak.realm(keycloakRealm);
        UsersResource usersRessource = realmResource.users();
        UserRepresentation user = new UserRepresentation();
        user.setUsername(bankUserRequest.getUserCode());
        user.setFirstName(bankUserRequest.getUserName());
        user.setEmail(bankUserRequest.getEmail());
        user.setEnabled(true);

        response = usersRessource.create(user);
        // Check when create User success.
        if (response.getStatus() == 201) {
            String userId = CreatedResponseUtil.getCreatedId(response);
            String password = FilesUtils.randomPassword();
            // Set password credential
            usersRessource = realmResource.users();
            UserResource userResource = usersRessource.get(userId);
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(password);
            userResource.resetPassword(passwordCred);

            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity = bankUserRequestMapper.toEntity(bankUserRequest);
            userInfoEntity.setUserId(CreatedResponseUtil.getCreatedId(response));
            userInfoEntity.setUserType(Constant.USER_TYPE_BANK);
            List<UserGroupEntity> listGroup = new ArrayList<>();
            for (String groupCode : bankUserRequest.getUserGroupCodes()) {
                listGroup.add(userGroupRepository.findByUserGroupCode(groupCode).get(0));
            }
            userInfoEntity.setUserGroupEntitys(listGroup);
            userInfoRepository.save(userInfoEntity);
            try {
                List<BankInfo> bankInfoList = bankInfoRepository.findByBankCode(userInfoEntity.getBankCode());
                if (!bankInfoList.isEmpty()) {
                    BankInfo bankInfo = bankInfoList.get(0);
                    Email email = userInfoServiceImpl.getEmail004(userInfoEntity, bankInfo, password);
                    emailService.sendEmailWithTemplate(email);
                }
            } catch (Exception e) {
                keycloak.realm(keycloakRealm).users().delete(userId);
                throw new LCPlatformException(ResponseCode.BAD_REQUEST, "Email exception");
            }

        } else {
            throw new LCPlatformException(ResponseCode.CONFLICT);
        }
        response.close();

    }

    public void checkUserGroupType(String userIdLogin, String userBankId)
    {
        Set<String> userGroupTypes = new HashSet<>();
        List<UserGroupEntity> userGroupEntityList = userGroupRepository.getByUserId(Long.parseLong(userBankId));
        List<UserGroupEntity> userGroupEntityOfUserLoginList = userGroupRepository.getByUserId(userInfoServiceImpl.getUserLogin(userIdLogin).getId());
        if(!userGroupEntityList.isEmpty()){
            userGroupTypes = userGroupEntityList.stream().filter(x -> x.getGroupType().equals("bank-admin")).map(y -> y.getGroupType()).collect(Collectors.toSet());
            Set<String> userGTOfUserLogin = new HashSet<>();
            if(!userGroupTypes.isEmpty()){
                userGTOfUserLogin = userGroupEntityOfUserLoginList.stream().filter(x -> x.getGroupType().equals("bank-admin")).map(y -> y.getGroupType()).collect(Collectors.toSet());
                if(userGTOfUserLogin.isEmpty()){
                    throw new LCPlatformException(ResponseCode.MANIPULATE_ADMIN_BANK_USER_NOT_ALLOWED);
                }
            }
        }
    }

    @Override
    public void updateUser(String userIdLogin, BankUserRequest bankUserRequest) throws LCPlatformException {
        if (bankUserRequest == null) {
            log.error("bankUserRequest is null when update");
            return;
        }
        this.checkUserGroupType(userIdLogin, bankUserRequest.getId());
        try {
            UserInfoEntity userInfo = userInfoRepository.getById(Long.parseLong(bankUserRequest.getId()));
            String userId = userInfo.getUserId();
            RealmResource realmResource = keycloak.realm(keycloakRealm);
            UsersResource usersResource = realmResource.users();
            UserResource userResource = usersResource.get(userId);

            UserRepresentation user = new UserRepresentation();
            user.setFirstName(bankUserRequest.getUserName());
            user.setEmail(bankUserRequest.getEmail());
            user.setEnabled(true);
            userResource.update(user);

            UserInfoEntity bankUserUp = bankUserRequestMapper.toEntity(bankUserRequest);
            bankUserUp.setId(userInfo.getId());
            bankUserUp.setUserId(userInfo.getUserId());
            bankUserUp.setUserCode(userInfo.getUserCode());
            bankUserUp.setCreatedDate(userInfo.getCreatedDate());
            bankUserUp.setCreatedBy(userInfo.getCreatedBy());
            List<UserGroupEntity> listGroup = new ArrayList<>();
            for (String groupCode : bankUserRequest.getUserGroupCodes()) {
                listGroup.add(userGroupRepository.findByUserGroupCode(groupCode).get(0));
            }
            bankUserUp.setUserGroupEntitys(listGroup);

            userInfoRepository.save(bankUserUp);
        } catch (Exception e) {
            log.error("Update User Exception :" + e.getMessage());
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
    }

    @Override
    public void deleteUser(String userIdLogin, String id) throws LCPlatformException {
        this.checkUserGroupType(userIdLogin, id);
        try {
            UserInfoEntity userInfo = userInfoRepository.getById(Long.parseLong(id));
            keycloakService.deleteUser(userInfo.getUserId());
            userInfoRepository.delete(userInfo);
            log.debug("Delete BankUser success: " + userInfo.toString());
        } catch (Exception e) {
            log.debug("Delete BankUser Exception : " + e.getMessage());
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
    }

    @Override
    public String forgetPasswordUserBank(String userCode) {
        try {
            String passwordNew = FilesUtils.randomPassword();
            log.info("passwordForget: " + passwordNew);
            List<UserInfoEntity> userInfoList = userInfoRepository.findByUserCode(userCode);
            if (!userInfoList.isEmpty()) {
                UserInfoEntity userInfo = userInfoList.get(0);
                List<BankInfo> bankInfoList = bankInfoRepository.findByBankCode(userInfo.getBankCode());
                if (!bankInfoList.isEmpty()) {
                    BankInfo bankInfo = bankInfoList.get(0);
                    String userId = userInfo.getUserId();
                    if (!passwordNew.isEmpty() && !userId.isEmpty()) {
                        CredentialRepresentation passwordCred = new CredentialRepresentation();
                        passwordCred.setTemporary(false);
                        passwordCred.setType(CredentialRepresentation.PASSWORD);
                        passwordCred.setValue(passwordNew);
                        keycloak.realm(keycloakRealm).users().get(userId).resetPassword(passwordCred);

                        // Send email corporate user reset password
                        Email email = userInfoServiceImpl.getEmail010(userInfo, bankInfo, passwordNew);
                        emailService.sendEmailWithTemplate(email);

                        return "Success";
                    } else {
                        return "Fail";
                    }
                } else {
                    return "User does not exist";
                }
            } else {
                return "User does not exist";
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return "Fail";
        }
    }

}
