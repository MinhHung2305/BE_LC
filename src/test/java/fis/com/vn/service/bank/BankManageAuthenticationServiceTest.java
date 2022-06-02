package fis.com.vn.service.bank;

import fis.com.vn.rest.mapper.UserInfoRequestMapper;
import fis.com.vn.rest.request.BankUserRequest;
import fis.com.vn.rest.response.BankUserResponse;
import fis.com.vn.service.common.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("local")
class BankManageAuthenticationServiceTest {
    @Autowired
    BankManageAuthenticationService bankManageAuthenticationService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserInfoRequestMapper userInfoRequestMapper;

    @Test
    void getManageAuthenticationAll() {
        List<BankUserResponse> listManageAuth = bankManageAuthenticationService.getManageAuthenticationAll();
        System.out.println(listManageAuth);
    }

    @Test
    void getBankAuthenticationUser() {
        BankUserResponse bankUserResponse = bankManageAuthenticationService.getBankAuthenticationUser("352");
        System.out.println(bankUserResponse);
    }

    @Test
    void updateUser() {
        try{
            bankManageAuthenticationService.updateUser(bankUserRequest);
        } catch(Exception e){
            System.out.println("Error");
        }

    }

    @Test
    void deleteUser() {
        try{
            bankManageAuthenticationService.deleteUser("475");
        } catch(Exception e){
            System.out.println("Error");
        }
    }
    BankUserRequest bankUserRequest = BankUserRequest.builder()
            .id("475")
            .userName("LOCBT2Test").build();
}