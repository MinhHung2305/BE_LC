package fis.com.vn.rest;

import com.google.common.collect.Lists;
import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.model.entity.BankInfo;
import fis.com.vn.model.entity.UserGroupEntity;
import fis.com.vn.model.entity.UserInfoEntity;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.model.enumerate.RolesInfo;
import fis.com.vn.repository.UserGroupRepository;
import fis.com.vn.rest.mapper.UserGroupSearchResponseMapper;
import fis.com.vn.rest.mapper.UserInfoRequestMapper;
import fis.com.vn.rest.mapper.UserInfoResponseMapper;
import fis.com.vn.rest.request.*;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.UserGroupSearchInUserRespone;
import fis.com.vn.rest.response.UserGroupSearchRespone;
import fis.com.vn.rest.response.UserInfoResponse;
import fis.com.vn.service.common.BankInfoService;
import fis.com.vn.service.common.KeycloakService;
import fis.com.vn.service.common.UserGroupService;
import fis.com.vn.service.common.UserInfoService;
import fis.com.vn.util.Constant;
import fis.com.vn.util.PageSupport;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "User controller")
public class UserCommandController {

    @Value("${keycloak.auth-server-url}")
    public String keyCloakAuthServerUrl;

    @Value("${keycloak.realm}")
    public String keycloakRealm;

    @Value("${keycloak.resource}")
    public String resourceClientId;

    @Value("${keycloak.credentials.secret}")
    public String resourceClientSecret;

    @Autowired
    private Keycloak keycloak;

    @Autowired
    private KeycloakService keycloakService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserGroupService userGroupService;

    @Autowired
    UserInfoRequestMapper userInfoRequestMapper;

    @Autowired
    UserInfoResponseMapper userInfoResponseMapper;

    @Autowired
    UserGroupSearchResponseMapper userGroupSearchResponseMapper;

    @Autowired
    private BankInfoService bankInfoService;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<AccessTokenResponse>> login(@RequestBody SignInRequest request) throws LCPlatformException {
        // To Do
        Optional<UserInfoEntity> userLoginByUserCode = userInfoService.getUserLoginByUserCode(request.getUsername());
        if(!userLoginByUserCode.isPresent()) {
            throw new LCPlatformException(ResponseCode.ACCOUNT_IS_NOT_CORRECT);
        }

        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("secret", resourceClientSecret);
        clientCredentials.put("grant_type", "password");

        Configuration configuration = new Configuration(keyCloakAuthServerUrl, keycloakRealm, resourceClientId,
                clientCredentials, null);
        AuthzClient authzClient = AuthzClient.create(configuration);

        AccessTokenResponse response = null;
        try {
            response = authzClient.obtainAccessToken(request.getUsername(), request.getPassword());
        } catch (Exception e) {
            throw new LCPlatformException(ResponseCode.ACCOUNT_IS_NOT_CORRECT);
        }

        log.info(response.toString());
        return ResponseFactory.success(HttpStatus.OK, response, "Login success");
    }

    @PostMapping("/logout")
    public ResponseEntity<BaseResponse<String>> logout(@RequestParam String sessionState) {

        log.info(sessionState.toString());


        RealmResource realmResource = keycloak.realm(keycloakRealm);
        realmResource.deleteSession(sessionState);
        realmResource.logoutAll();

        return ResponseFactory.success(HttpStatus.OK, "Logout success");
    }

    @GetMapping("/getUserLogin")
    public ResponseEntity<BaseResponse<UserInfoResponse>> getUserLogin(@AuthenticationPrincipal Jwt principal) {

        String userId = principal.getSubject();
        UserInfoResponse userInfoResponse = userInfoResponseMapper.toDomain(userInfoService.getUserLogin(userId));
        if(Objects.equals(userInfoResponse.getUserType(), "BANK")){
            List<UserGroupEntity> userGroupEntityList = userGroupRepository.getByUserId(userInfoResponse.getId());
            if(!userGroupEntityList.isEmpty()){
                userInfoResponse.setGroupTypeList(userGroupEntityList.stream().map(x -> x.getGroupType()).collect(Collectors.toSet()));
            }
        }

        //
        if(userInfoResponse.getBankCode() != null && !userInfoResponse.getBankCode().isEmpty()){
            BankInfo bankInfo = bankInfoService.getByBankCode(userInfoResponse.getBankCode());
            userInfoResponse.setBankId(bankInfo.getBankId());
        }
        return ResponseFactory.success(HttpStatus.OK, userInfoResponse, "Get UserLogin success");
    }

    @GetMapping("/getUsers")
    public ResponseEntity<BaseResponse<List<UserInfoResponse>>> getUser() {
        try {

            List<UserInfoEntity> userInfoList = userInfoService.getAll();
            List<UserInfoResponse> userInfoResList = new ArrayList<UserInfoResponse>();
            for (UserInfoEntity userInfo : userInfoList) {
                UserInfoResponse userInRes = userInfoResponseMapper.toDomain(userInfo);
                List<UserGroupEntity> setUserGroups = userInfo.getUserGroupEntitys();
                Set<String> channels = new HashSet<String>();
                String branchLevels = "";
                String groupTypes = "";
                String roles = "";
                List<String> userGroupCodes = new ArrayList<String>();
                Set<String> setRoles = new HashSet<>();
                Set<String> setBranchLevels = new HashSet<>();
                Set<String> setGroupTypes = new HashSet<>();
                for (UserGroupEntity ug : setUserGroups) {
                    if (ug.getChannels() != null && !ug.getChannels().isEmpty()) {
                        for (String chanel : ug.getChannels().split(",")) {
                            channels.add(chanel);
                        }
                    }
                    if (ug.getBranchLevel() != null && !ug.getBranchLevel().isEmpty()) {
                        setBranchLevels.add(ug.getBranchLevel());
                    }
                    if (ug.getGroupType() != null && !ug.getGroupType().isEmpty()) {
                        setGroupTypes.add(ug.getGroupType());
                    }
                    if (ug.getRole() != null && !ug.getRole().isEmpty()) {
                        setRoles.add(RolesInfo.valueOf(ug.getRole().toUpperCase()).getDescription());
                    }
                    roles = String.join(" + ", setRoles);

                    userGroupCodes.add(ug.getUserGroupCode());
                }
                branchLevels = setBranchLevels.toString();
                groupTypes = setGroupTypes.toString();
                userInRes.setChannels(Lists.newArrayList(channels));
                userInRes.setBranchLevels(branchLevels.substring(1, branchLevels.length() - 1));
                userInRes.setGroupTypes(groupTypes.substring(1, groupTypes.length() - 1));
                userInRes.setUserGroupCodes(userGroupCodes);
                userInRes.setRoles(roles);

                userInfoResList.add(userInRes);
            }
            return ResponseFactory.success(HttpStatus.OK, userInfoResList, "Get All User Group");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Error getUsers: " + e.getMessage());
            return ResponseFactory.success(HttpStatus.OK, null, "Get All User Group Fail");
        }
    }

    @GetMapping("/getUserById")
    public ResponseEntity<BaseResponse<UserInfoResponse>> getUserById(@RequestParam Long id) {
        try {
            UserInfoEntity userInfoEntity = userInfoService.getUserInfoById(id);
            UserInfoResponse userInRes = userInfoResponseMapper.toDomain(userInfoEntity);

            List<UserGroupEntity> setUserGroups = userInfoEntity.getUserGroupEntitys();
            Set<String> channels = new HashSet<String>();
            String branchLevels = "";
            String groupTypes = "";
            String roles = "";
            Set<String> setBranchLevels = new HashSet<>();
            Set<String> setGroupTypes = new HashSet<>();
            Set<String> setRoles = new HashSet<>();
            List<String> userGroupCodes = new ArrayList<String>();
            for (UserGroupEntity ug : setUserGroups) {
                if (ug.getChannels() != null && !ug.getChannels().isEmpty()) {
                    for (String chanel : ug.getChannels().split(",")) {
                        channels.add(chanel);
                    }
                }
                userGroupCodes.add(ug.getUserGroupCode());
                if (ug.getBranchLevel() != null && !ug.getBranchLevel().isEmpty()) {
                    setBranchLevels.add(ug.getBranchLevel());
                }
                if (ug.getGroupType() != null && !ug.getGroupType().isEmpty()) {
                    setGroupTypes.add(ug.getGroupType());
                }
                if (ug.getRole() != null && !ug.getRole().isEmpty()) {
                    setRoles.add(RolesInfo.valueOf(ug.getRole().toUpperCase()).getName());
                }
                roles = String.join(",", setRoles);
            }
            branchLevels = setBranchLevels.toString();
            groupTypes = setGroupTypes.toString();
            userInRes.setChannels(Lists.newArrayList(channels));
            userInRes.setBranchLevels(branchLevels.substring(1, branchLevels.length() - 1));
            userInRes.setGroupTypes(groupTypes.substring(1, groupTypes.length() - 1));
            userInRes.setUserGroupCodes(userGroupCodes);
            userInRes.setRoles(roles);

            return ResponseFactory.success(HttpStatus.OK, userInRes, "Get UserInfo By Id success");

        } catch (Exception e) {
            log.info("Error getUserById: " + e.getMessage());
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Cannot find UserInfo");
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<BaseResponse<String>> createUser(@RequestBody UserInfoRequest userInfoRequest) throws LCPlatformException {
        UserInfoResponse res = userInfoService.createUserInfo(userInfoRequest);
        if (res != null) {
            log.info("Create user success: " + res.toString());
            return ResponseFactory.success(HttpStatus.OK, "Create", "Create success");
        } else {
            return ResponseFactory.success(HttpStatus.CONFLICT, "Create",
                    "Create fail UserName or Email already exists");
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<BaseResponse<String>> updateUser(@RequestBody UserInfoRequest userInfoRequest,
                                                           @RequestParam Long id) throws LCPlatformException {
        UserInfoResponse res = userInfoService.updateUserInfo(userInfoRequest, id);
        if (res != null) {
            log.info("Update UserInfo success: " + res.toString());
            return ResponseFactory.success(HttpStatus.OK, "Update UserInfo", "Update UserInfo success");
        } else {
            return ResponseFactory.success(HttpStatus.CONFLICT, "Update UserInfo",
                    "Update fail UserName or Email already exists");
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<BaseResponse<String>> deleteUser(@RequestParam Long id) {
        UserInfoResponse userIn = userInfoService.deleteByUserInfoId(id);
        if (userIn != null) {
            log.info("Delete: " + userIn.toString());
            return ResponseFactory.success(HttpStatus.OK, "Delete: " + userIn.toString(), "Delete UserInfo success");
        } else {
            log.info("Delete Fail: ");
            return ResponseFactory.success(HttpStatus.OK, "Delete: " + null, "Delete UserInfo success");
        }
    }

    @GetMapping("/searchUser")
    public List<UserInfoEntity> searchUserGroup(@RequestBody SearchUserInfoRequest searchUser) {

        try {
            List<UserInfoEntity> userList = userInfoService.searchUser(searchUser.getBankCode(), searchUser.getUserId(),
                    searchUser.getUserName(), searchUser.getUserType(), searchUser.getUserStatus(),
                    searchUser.getChannel(), searchUser.getBranchLevel(), searchUser.getGroupType(),
                    searchUser.getRole());
            log.info(userList.toString());
            return userList;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @PutMapping("/updateUserStatus")
    public ResponseEntity<BaseResponse<String>> activeUser(@RequestBody String status, @RequestParam Long id) {

        try {
            UserInfoEntity userIn = userInfoService.getUserInfoById(id);
            userIn.setUserStatus(Integer.parseInt(status));
            UserInfoResponse userInfoResponse = userInfoService.updateStatusUserInfo(userIn);
            log.info(userInfoResponse.toString());
            return ResponseFactory.success(HttpStatus.OK, "User active success", "Active success");
        } catch (Exception e) {
            return ResponseFactory.success(HttpStatus.OK, "User active fail", "Active fail");
        }
    }

    @PostMapping("/createRole")
    public ResponseEntity<BaseResponse<String>> createRole(@RequestBody RoleRequest roleRequest) {

        RealmResource realmResource = keycloak.realm(keycloakRealm);
        RolesResource rolesRessource = realmResource.roles();

        RoleRepresentation role = new RoleRepresentation();
        role.setName(roleRequest.getName());

        rolesRessource.create(role);

        log.info(roleRequest.toString());
        return ResponseFactory.success(HttpStatus.OK, "Create", "Create success");
    }

    @GetMapping("/roles")
    public ResponseEntity<BaseResponse<PageSupport<RoleRepresentation>>> getRoles(
            @RequestParam(name = "page", defaultValue = PageSupport.FIRST_PAGE_NUM) int page,
            @RequestParam(name = "size", defaultValue = PageSupport.DEFAULT_PAGE_SIZE) int size) {
        return ResponseFactory.success(HttpStatus.OK, keycloakService.getRoles(PageRequest.of(page, size)),
                "Create role success");
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<BaseResponse<AccessTokenResponse>> refreshToken(@RequestParam String refreshToken) throws LCPlatformException {
        // To Do
        log.info(refreshToken.toString());

        AccessTokenResponse response = keycloakService.refreshToken(refreshToken);
        log.info(response.toString());

        return ResponseFactory.success(HttpStatus.OK, response, "Refresh Token");
    }

    @GetMapping("/getAllGroup")
    public ResponseEntity<BaseResponse<List<UserGroupSearchRespone>>> getAllGroup() {
        List<UserGroupSearchRespone> userGroupResponseList = new ArrayList<UserGroupSearchRespone>();

        List<UserGroupEntity> userGroupList = userGroupService.findAll();
        for (UserGroupEntity userGroup : userGroupList) {
            userGroupResponseList.add(userGroupSearchResponseMapper.toDomain(userGroup));
        }

        return ResponseFactory.success(HttpStatus.OK, userGroupResponseList, "Get All UserGroupSearch success");
    }

    @GetMapping("/searchUserGroupInUser")
    public ResponseEntity<BaseResponse<List<UserGroupSearchInUserRespone>>> searchUserGroupInUser(
            @RequestParam String branchLevel, @RequestParam String groupType, @RequestParam String roles,
            @RequestParam String userType) {

        List<UserGroupEntity> userGroupList = new ArrayList<>();
        List<UserGroupSearchInUserRespone> userGroupCodeList = new ArrayList<>();
        try {
            if (userType.toUpperCase().equals(Constant.USER_TYPE_BANK)) {
                if(roles.equalsIgnoreCase("maker") || roles.equalsIgnoreCase("Approver")) {
                    userGroupList = userGroupService.searchUserGroupInUser(branchLevel, groupType, roles);
                } else {
                    userGroupList = userGroupService.searchUserGroupInUser(branchLevel, groupType);
                }

                for (UserGroupEntity userGroup : userGroupList) {
                    UserGroupSearchInUserRespone groupSearch = new UserGroupSearchInUserRespone();
                    groupSearch.setUserGroupCode(userGroup.getUserGroupCode());
                    groupSearch.setChannels(userGroup.getChannels());
                    userGroupCodeList.add(groupSearch);
                }
                return ResponseFactory.success(HttpStatus.OK, userGroupCodeList,
                        "get searchUserGroupInUserBank success");

            } else {
                userGroupList = userGroupService.searchGroupInUserFPT();
                for (UserGroupEntity userGroup : userGroupList) {
                    UserGroupSearchInUserRespone groupSearch = new UserGroupSearchInUserRespone();
                    groupSearch.setUserGroupCode(userGroup.getUserGroupCode());
                    groupSearch.setChannels(userGroup.getChannels());
                    userGroupCodeList.add(groupSearch);
                }
                return ResponseFactory.success(HttpStatus.OK, userGroupCodeList,
                        "get searchUserGroupInUserFPT success");
            }
        } catch (Exception e) {
            log.info("Error searchUserGroupInUser: " + e.getMessage());
            return ResponseFactory.success(HttpStatus.OK, null, "Error searchUserGroupInUser: ");
        }

    }

    @PutMapping("/changePasswordUser")
    public String changePasswordUser(@RequestBody PasswordRequest request, @AuthenticationPrincipal Jwt principal) {

        String userId = principal.getSubject();
        String passwordNew = request.getPasswordNew();
        String resChangePass = userInfoService.changePasswordUser(passwordNew,userId);
        return resChangePass;
    }

    @PutMapping("/forgetPasswordUser")
    public ResponseEntity<BaseResponse<String>> forgetPasswordUser(@RequestBody PasswordRequest request) {

        String resChangePass = userInfoService.forgetPasswordUser(request);
        return ResponseFactory.success(HttpStatus.OK, resChangePass, "Reset password success");
    }

    @PutMapping("/resetPasswordUser")
    public ResponseEntity<BaseResponse<String>> resetPasswordUser(@RequestBody PasswordRequest request) {

        String userId = request.getUserId();
        String resChangePass = userInfoService.resetPasswordUser(userId);
        return ResponseFactory.success(HttpStatus.OK, resChangePass, "Reset password success");

    }

    @PutMapping("/resetPasswordUserCorporate")
    public ResponseEntity<BaseResponse<String>> resetPasswordUserCorporate(@RequestBody PasswordRequest request) {

        String userId = request.getUserId();
        String resChangePass = userInfoService.resetPasswordUserCorporate(userId);
        return ResponseFactory.success(HttpStatus.OK, resChangePass, "Reset password success");
    }

    @PutMapping("/resetPasswordUserBank")
    public ResponseEntity<BaseResponse<String>> resetPasswordUserBank(@RequestBody PasswordRequest request) throws SendEmailException {

        String userId = request.getUserId();
        String resChangePass = userInfoService.resetPasswordUserBank(userId);
        return ResponseFactory.success(HttpStatus.OK, resChangePass, "Reset password success");
    }
}