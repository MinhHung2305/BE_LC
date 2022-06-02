package fis.com.vn.service.bank;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.request.BankUserRequest;
import fis.com.vn.rest.response.BankUserResponse;

import java.util.List;

public interface BankManageAuthenticationService {
    List<BankUserResponse> getManageAuthenticationAll();
    BankUserResponse getBankAuthenticationUser(String id);
    void updateUser(BankUserRequest BankUserRequest) throws LCPlatformException;
    void deleteUser(String id) throws LCPlatformException;
}
