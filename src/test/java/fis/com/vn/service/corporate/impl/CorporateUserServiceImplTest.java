package fis.com.vn.service.corporate.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.service.corporate.CorporateUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class CorporateUserServiceImplTest {
    @Autowired
    CorporateUserService corporateUserService;

    @Test
    void getAllUserCorporate() throws LCPlatformException {
        corporateUserService.getAllUserCorporate();
    }
}