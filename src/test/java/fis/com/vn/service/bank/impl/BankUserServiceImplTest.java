package fis.com.vn.service.bank.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.response.BankUserResponse;
import fis.com.vn.service.bank.BankUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class BankUserServiceImplTest {
    @Autowired
    BankUserService bankUserService;

    @Test
    void getAllUserBank() throws LCPlatformException {
        List<BankUserResponse> listBank =  bankUserService.getAllUserBank();
        System.out.println(listBank);
    }
}