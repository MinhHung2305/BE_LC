package fis.com.vn.service.notification.impl;

import fis.com.vn.model.entity.Template;
import fis.com.vn.service.notification.TemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class TemplateServiceImplTest {

    @Autowired
    TemplateService templateService;

    @Test
    void getTemplateByCode() {
        Template templateByCode = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_005);
        System.out.println(templateByCode);
    }
}