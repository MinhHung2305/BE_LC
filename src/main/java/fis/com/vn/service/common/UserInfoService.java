package fis.com.vn.service.common;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.model.entity.UserInfoEntity;
import fis.com.vn.rest.request.PasswordRequest;
import fis.com.vn.rest.request.UserInfoRequest;
import fis.com.vn.rest.response.UserInfoResponse;

import java.util.List;
import java.util.Optional;


public interface UserInfoService {
    UserInfoEntity getUserInfo(String userId);

    List<UserInfoEntity> getAll();

    UserInfoEntity getUserInfoById(Long id);
    
	UserInfoResponse createUserInfo(UserInfoRequest userInfoRequest) throws LCPlatformException;

	UserInfoResponse updateUserInfo(UserInfoRequest userInfoRequest,Long id) throws LCPlatformException;
	
	UserInfoResponse deleteByUserInfoId(Long id);
	
	List<UserInfoEntity> searchUser(String bankCode, String userId, String userName, String userType, String userStatus,
			String channel, String branchLevel, String groupType, String role);

	List<UserInfoEntity> getByCorporateId(String corporateId);

	List<UserInfoEntity> getByCorporateIdOrderById(String corporateId);

	List<UserInfoEntity> createAllCorporateUserInfo(List<UserInfoEntity> userInfoEntity) throws LCPlatformException, SendEmailException, InterruptedException;

	UserInfoResponse updateStatusUserInfo(UserInfoEntity userIn);

	UserInfoEntity getUserLogin(String userId);

	String changePasswordUser(String passwordNew, String userId);

	String forgetPasswordUser(String userCode);

	String forgetPasswordUser(PasswordRequest request);

	String resetPasswordUser(String userId);

	String resetPasswordUserCorporate(String userId);

	String resetPasswordUserBank(String userId) throws SendEmailException;

	Optional<UserInfoEntity> getUserLoginByUserCode(String userCode);
}
