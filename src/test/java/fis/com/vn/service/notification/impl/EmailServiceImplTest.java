package fis.com.vn.service.notification.impl;

import fis.com.vn.exception.SendEmailException;
import fis.com.vn.model.entity.Email;
import fis.com.vn.model.entity.Template;
import fis.com.vn.model.entity.UserInfoEntity;
import fis.com.vn.service.common.impl.UserInfoServiceImpl;
import fis.com.vn.service.notification.EmailService;
import fis.com.vn.service.notification.TemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("local")
class EmailServiceImplTest {

    @Autowired
    TemplateService templateService;

    @Autowired
    EmailService emailService;

    @Autowired
    UserInfoServiceImpl userInfoServiceImpl;

    @Test
    void sendEmail() {
    }

    @Test
    void sendEmailWithTemplate() throws SendEmailException {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_005);
        Email email = Email.builder()
                .to(new String[]{"buitheloc79912181@gmail.com"})
                .subject(template.getTemplateSubject())
                .htmlBody("123")
                .build();
        System.out.println(email);

        emailService.sendEmailWithTemplate(email);
    }

    @Test
    void sendEmailWithTemplate011() throws SendEmailException {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_011);
        Email email = Email.builder()
                .to(new String[]{"huyennt187@fpt.com.vn"})
                .subject(template.getTemplateSubject())
                .htmlBody(template.getTemplateContent())
                .build();
        System.out.println(email);

        emailService.sendEmailWithTemplate(email);
    }

    @Test
    void sendEmailWithTemplate015() throws SendEmailException {
        String emailTo = "anhvankhe99@gmail.com";
        List<UserInfoEntity> userList = new ArrayList<>();
        UserInfoEntity userInfo1 = new UserInfoEntity();
        UserInfoEntity userInfo2 = new UserInfoEntity();
        userInfo1.setUserCode("User001");
        userInfo1.setUserName("UserName001");
        userInfo2.setUserCode("User002");
        userInfo2.setUserCode("UserName002");

        userList.add(userInfo1);
        userList.add(userInfo2);

        Email email = userInfoServiceImpl.getEmail015(userInfo1, emailTo, userList);
        emailService.sendEmailWithTemplate(email);
    }
}