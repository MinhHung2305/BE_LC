package fis.com.vn.service.corporate.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.service.corporate.CorporateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class CorporateServiceImplTest {

    @Autowired
    CorporateService corporateService;
    @Test
    void createCorporate() throws LCPlatformException {

    }
}