package fis.com.vn.service.bank;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.request.BankUserRequest;
import fis.com.vn.rest.response.BankUserResponse;

import java.util.List;

public interface BankUserService {
	List<BankUserResponse> getAllUserBank() throws LCPlatformException;
	BankUserResponse getBankUserById(String id) throws LCPlatformException;
    void createUser(BankUserRequest BankUserRequest) throws LCPlatformException;
	void updateUser(String userId, BankUserRequest BankUserRequest) throws LCPlatformException;
    void deleteUser(String userId, String id) throws LCPlatformException;
	String forgetPasswordUserBank(String userCode);
}
