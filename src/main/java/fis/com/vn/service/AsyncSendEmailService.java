package fis.com.vn.service;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.model.entity.Email;
import fis.com.vn.model.entity.Template;
import fis.com.vn.model.entity.UserInfoEntity;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.repository.UserInfoRepository;
import fis.com.vn.rest.response.CorporateUserResponse;
import fis.com.vn.service.common.impl.UserInfoServiceImpl;
import fis.com.vn.service.notification.EmailService;
import fis.com.vn.service.notification.TemplateService;
import fis.com.vn.service.notification.impl.EmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsyncSendEmailService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncSendEmailService.class);

//    private final RestTemplate restTemplate;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    TemplateService templateService;

    @Autowired
    EmailService emailService;

    @Autowired
    UserInfoServiceImpl userInfoService;

//    public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }

    @Async("threadPoolTaskExecutor")
    public void sendEmail001(List<CorporateUserResponse> corporateUserResponseList) throws InterruptedException{
        try {
            for (CorporateUserResponse user: corporateUserResponseList) {
                Email email = emailService.getEmail001(user);
                emailService.sendEmailWithTemplate(email);
                logger.info("email: " + email);
                Thread.sleep(1000L);
                logger.info("thread: " + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            logger.info("Error createUserInfo: " + e);
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
//        return CompletableFuture.completedFuture(userInfo);
    }

    @Async("threadPoolTaskExecutor")
    public void sendEmailFPT(List<UserInfoEntity> userInfoList, List<UserInfoEntity> listUserFpts) throws InterruptedException{
        try {
            for (UserInfoEntity userFPT : listUserFpts) {
                Email email015 = userInfoService.getEmail015(userInfoList.get(0), userFPT.getEmail(), userInfoList);
                emailService.sendEmailWithTemplate(email015);
                Thread.sleep(1000L);
                logger.info("thread: " + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            logger.info("Error createUserInfo: " + e);
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
    }

//    @Async("threadPoolTaskExecutor")
    public void findUser(Long id) throws InterruptedException, SendEmailException {
        logger.info("Looking up " + id);
//        String url = String.format("https://api.github.com/users/%s", user);
//        User results = restTemplate.getForObject(url, User.class);
        // Artificial delay of 1s for demonstration purposes
//        UserInfoEntity results = userInfoRepository.findById(id).get();
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_011);
        Email email = Email.builder()
                .to(new String[]{"huyennt187@fpt.com.vn"})
                .subject(template.getTemplateSubject())
                .htmlBody(template.getTemplateContent())
                .build();
        System.out.println(email);

        emailService.sendEmailWithTemplate(email);
        Thread.sleep(1000L);
//        return CompletableFuture.completedFuture(results);
//        return null;
    }

    @Async("threadPoolTaskExecutor")
    public void sendEmailCM04(List<Email> listEmail) throws InterruptedException{
        try {
            for (Email email: listEmail) {
                emailService.sendEmailWithTemplate(email);
                logger.info("email: " + email);
                Thread.sleep(1000L);
                logger.info("thread: " + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            logger.info("Error sendEmailCM04: " + e);
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
    }

}
