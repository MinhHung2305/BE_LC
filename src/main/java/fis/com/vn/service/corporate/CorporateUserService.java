package fis.com.vn.service.corporate;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.request.CorporateUserRequest;
import fis.com.vn.rest.request.PasswordRequest;
import fis.com.vn.rest.response.CorporateUserResponse;

import java.util.List;

public interface CorporateUserService {
	List<CorporateUserResponse> getAllUserCorporate() throws LCPlatformException;
    void createUser(String corporateId, List<CorporateUserRequest> corporateUserRequestList) throws LCPlatformException;
	void updateUser(String corporateId, String userId, CorporateUserRequest corporateUserRequest) throws LCPlatformException;
    void deleteByUserInfoId(String id) throws LCPlatformException;
	CorporateUserResponse getCorporateUserById(String id) throws LCPlatformException;
	String forgetPasswordUserCorporate(String userCode);
	String forgetPasswordUserCorporate(PasswordRequest request);
}
