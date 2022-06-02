package fis.com.vn.service.corporate.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.Corporate;
import fis.com.vn.model.entity.Email;
import fis.com.vn.model.entity.UserGroupEntity;
import fis.com.vn.model.entity.UserInfoEntity;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.repository.UserGroupRepository;
import fis.com.vn.repository.UserInfoRepository;
import fis.com.vn.rest.mapper.CorporateUserRequestMapper;
import fis.com.vn.rest.mapper.CorporateUserResponseMapper;
import fis.com.vn.rest.request.CorporateUserRequest;
import fis.com.vn.rest.request.PasswordRequest;
import fis.com.vn.rest.response.CorporateUserResponse;
import fis.com.vn.service.common.KeycloakService;
import fis.com.vn.service.corporate.CorporateService;
import fis.com.vn.service.corporate.CorporateUserService;
import fis.com.vn.service.common.impl.UserInfoServiceImpl;
import fis.com.vn.service.notification.EmailService;
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
import java.util.List;

@Service
@Slf4j
public class CorporateUserServiceImpl implements CorporateUserService {

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    CorporateService corporateService;

    @Autowired
    KeycloakService keycloakService;

    @Autowired
    CorporateUserResponseMapper corporateUserResponseMapper;

    @Autowired
    CorporateUserRequestMapper  corporateUserRequestMapper;

	@Autowired
	UserInfoServiceImpl userInfoServiceImpl;

	@Autowired
	EmailService emailService;

	@Value("${keycloak.realm}")
	String keycloakRealm;

	@Autowired
	Keycloak keycloak;

    @Transactional
    @Override
    public List<CorporateUserResponse> getAllUserCorporate() throws LCPlatformException {
        List<CorporateUserResponse> corporateUserResponseList = new ArrayList<>();

        List<UserInfoEntity> userList = userInfoRepository.getByUserType(Constant.USER_TYPE_CORPORATE);
        for (UserInfoEntity userInfo : userList) {
            List<UserGroupEntity> userGroupList = userInfo.getUserGroupEntitys();
            CorporateUserResponse corporateUserResponse = new CorporateUserResponse();
            corporateUserResponse = corporateUserResponseMapper.toDomain(userInfo);
            if (userInfo.getCorporate() != null) {
                corporateUserResponse.setCorporateId(userInfo.getCorporate().getCorporateId());
                corporateUserResponse.setCorporateCode(userInfo.getCorporate().getCorporateCode());
                corporateUserResponse.setCorporateName(userInfo.getCorporate().getCorporateName());
            }
            if (userGroupList != null && !userGroupList.isEmpty()) {
                corporateUserResponse.setUserGroupCode(userGroupList.get(0).getUserGroupCode());
            }
            corporateUserResponseList.add(corporateUserResponse);
        }
        return corporateUserResponseList;
    }

    @Transactional
    @Override
    public CorporateUserResponse getCorporateUserById(String id) throws LCPlatformException {
        CorporateUserResponse corporateUserResponse = new CorporateUserResponse();

        UserInfoEntity userInfo = userInfoRepository.getById(Long.parseLong(id));
        List<UserGroupEntity> userGroupList = userInfo.getUserGroupEntitys();
        corporateUserResponse = corporateUserResponseMapper.toDomain(userInfo);
        corporateUserResponse.setCorporateId(userInfo.getCorporate().getCorporateId());
        corporateUserResponse.setCorporateCode(userInfo.getCorporate().getCorporateCode());
        corporateUserResponse.setCorporateName(userInfo.getCorporate().getCorporateName());
        if (userGroupList != null && !userGroupList.isEmpty()) {
            corporateUserResponse.setUserGroupCode(userGroupList.get(0).getUserGroupCode());
            corporateUserResponse.setUserGroupName(userGroupList.get(0).getUserGroupName());
        }
        return corporateUserResponse;
    }

    @Transactional
    @Override
    public void createUser(String corporateId, List<CorporateUserRequest> corporateUserRequestList)
            throws LCPlatformException {
        List<UserInfoEntity> userList = new ArrayList<>();
        try {
            Corporate corporate = corporateService.getById(corporateId);
            if (corporateUserRequestList != null && !corporateUserRequestList.isEmpty()) {
                for (CorporateUserRequest userInfo : corporateUserRequestList) {
                    Long id = userInfoRepository.getSequence();
                    String userCode = corporate.getCorporateCode() + String.format("%03d", id + 1);
                    userInfo.setUserCode(userCode);
                    RealmResource realmResource = keycloak.realm(keycloakRealm);
                    UsersResource usersRessource = realmResource.users();
                    UserRepresentation user = new UserRepresentation();
                    user.setUsername(userInfo.getUserCode());
                    user.setFirstName(userInfo.getUserCode());
                    user.setLastName(userInfo.getUserName());
                    user.setEmail(userInfo.getEmail());
                    user.setEnabled(true);
                    Response response = usersRessource.create(user);
                    if (response.getStatus() == 201) {
                        String userId = CreatedResponseUtil.getCreatedId(response);
                        String passwordRandom = FilesUtils.randomPassword();
                        // Set password credential
                        usersRessource = realmResource.users();
                        UserResource userResource = usersRessource.get(userId);
                        CredentialRepresentation passwordCred = new CredentialRepresentation();
                        passwordCred.setTemporary(false);
                        passwordCred.setType(CredentialRepresentation.PASSWORD);
                        passwordCred.setValue(passwordRandom);
                        userResource.resetPassword(passwordCred);

                        UserInfoEntity userInfoEntity = new UserInfoEntity();
                        userInfoEntity.setUserId(CreatedResponseUtil.getCreatedId(response));
                        userInfoEntity.setUserCode(userInfo.getUserCode());
                        userInfoEntity.setUserName(userInfo.getUserName());
                        userInfoEntity.setPhoneNumber(userInfo.getPhoneNumber());
                        userInfoEntity.setEmail(userInfo.getEmail());
                        userInfoEntity.setUserStatus(userInfo.getUserStatus());
                        userInfoEntity.setUserType(Constant.USER_TYPE_CORPORATE);
                        userInfoEntity.setChannelInit(userInfo.getChannelInit());
                        userInfoEntity.setUserGroupEntitys(
                                userGroupRepository.findByUserGroupCode(userInfo.getUserGroupCode()));
                        userInfoEntity.setCorporate(corporate);
                        userList.add(userInfoEntity);

                        Email email = userInfoServiceImpl.getEmail001(userInfoEntity, passwordRandom);
                        // Send email for user when create success.
                        emailService.sendEmailWithTemplate(email);
                    } else {
                        throw new LCPlatformException(ResponseCode.CONFLICT);
                    }
                }
            }
            userInfoRepository.saveAll(userList);
        } catch (Exception e) {
            log.error("Create User" + e.getMessage());
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
    }

    @Transactional
    @Override
    public void updateUser(String corporateId, String id, CorporateUserRequest corporateUserRequest)
            throws LCPlatformException {
        try {
            UserInfoEntity userInfo = userInfoRepository.getById(Long.parseLong(id));
            Corporate corporate = corporateService.getById(corporateId);
            UserInfoEntity userInfoUp = new UserInfoEntity();
            if (corporateUserRequest != null) {
                userInfoUp = corporateUserRequestMapper.toEntity(corporateUserRequest);
                userInfoUp.setId(Long.parseLong(id));
                userInfoUp.setUserType(Constant.USER_TYPE_CORPORATE);
                userInfoUp.setUserId(userInfo.getUserId());
                userInfoUp.setChannelInit(userInfo.getChannelInit());
                userInfoUp.setCreatedBy(userInfo.getCreatedBy());
                userInfoUp.setCreatedDate(userInfo.getCreatedDate());
//                userInfo.setUserCode(corporateUserRequest.getUserCode());
//                userInfo.setUserName(corporateUserRequest.getUserName());
//                userInfo.setPhoneNumber(corporateUserRequest.getPhoneNumber());
//                userInfo.setDateOfIdentity(corporateUserRequest.getDateOfIdentity());
//                userInfo.setIdentityType(corporateUserRequest.getIdentityType());
//                userInfo.setIdentityNumber(corporateUserRequest.getIdentityNumber());
//                userInfo.setIssuedByIdentity(corporateUserRequest.getIssuedByIdentity());
//                userInfo.setEmail(corporateUserRequest.getEmail());
//                userInfo.setUserStatus(corporateUserRequest.getUserStatus());
//                userInfo.setPosition(corporateUserRequest.getPosition());
//
//                userInfo.setImageFrontOfIdentity(corporateUserRequest.getImageFrontOfIdentity());
//                userInfo.setImageBackOfIdentity(corporateUserRequest.getImageBackOfIdentity());
//                userInfo.setImagePortraitOfIdentity(corporateUserRequest.getImagePortraitOfIdentity());
//                userInfo.setDateOfBirth(corporateUserRequest.getDateOfBirth());

                userInfoUp.setUserGroupEntitys(
                        userGroupRepository.findByUserGroupCode(corporateUserRequest.getUserGroupCode()));
                userInfoUp.setCorporate(corporate);
            }
            userInfoRepository.save(userInfoUp);
        } catch (Exception e) {
            log.error("Update User Exception :" + e.getMessage());
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
    }

    @Transactional
    @Override
    public void deleteByUserInfoId(String id) throws LCPlatformException {
        String userId = null;
        try {
            UserInfoEntity userInfo = userInfoRepository.getById(Long.parseLong(id));
            if (userInfo != null) {
                userId = userInfo.getUserId();
                userInfoRepository.deleteById(Long.parseLong(id));
            }
        } catch (Exception e) {
            log.error("Delete User Exception :" + e.getMessage());
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        if (userId != null)
            keycloakService.deleteUser(userId);
    }

    @Override
    public String forgetPasswordUserCorporate(String userCode) {
        try {
            String passwordNew = FilesUtils.randomPassword();
            log.info("passwordForget: " + passwordNew);
            List<UserInfoEntity> userInfoList = userInfoRepository.findByUserCode(userCode);
            if (!userInfoList.isEmpty() && userInfoList.size() > 0) {
                UserInfoEntity userInfo = userInfoList.get(0);
                String userId = userInfo.getUserId();
                if (!passwordNew.isEmpty() && !userId.isEmpty()) {
                    CredentialRepresentation passwordCred = new CredentialRepresentation();
                    passwordCred.setTemporary(false);
                    passwordCred.setType(CredentialRepresentation.PASSWORD);
                    passwordCred.setValue(passwordNew);
                    keycloak.realm(keycloakRealm).users().get(userId).resetPassword(passwordCred);

                    // Send email corporate user forget password
                    Email email = userInfoServiceImpl.getEmail006(userInfo, passwordNew);
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
    public String forgetPasswordUserCorporate(PasswordRequest request) {

            String passwordNew = FilesUtils.randomPassword();
            String userCode = request.getUserCode();
            List<UserInfoEntity> userInfoList = userInfoRepository.findByUserCode(userCode);
            if (!userInfoList.isEmpty() && userInfoList.size() > 0) {
                UserInfoEntity userInfo = userInfoList.get(0);
                String userId = userInfo.getUserId();
                if(!request.getEmail().equals(userInfo.getEmail())) {
                    throw new LCPlatformException(ResponseCode.ACCOUNT_EMAIL_IS_NOT_CORRECT);
                }

                if (!passwordNew.isEmpty() && !userId.isEmpty()) {
                    CredentialRepresentation passwordCred = new CredentialRepresentation();
                    passwordCred.setTemporary(false);
                    passwordCred.setType(CredentialRepresentation.PASSWORD);
                    passwordCred.setValue(passwordNew);
                    keycloak.realm(keycloakRealm).users().get(userId).resetPassword(passwordCred);

                    try {
                        // Send email corporate user forget password
                        Email email = userInfoServiceImpl.getEmail006(userInfo, passwordNew);
                        emailService.sendEmailWithTemplate(email);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return "Success";
                } else {
                    throw new LCPlatformException(ResponseCode.ACCOUNT_IS_NOT_CORRECT);
                }
            } else {
                throw new LCPlatformException(ResponseCode.ACCOUNT_IS_NOT_CORRECT);
            }

    }
}
