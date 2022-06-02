package fis.com.vn.service.notification.impl;

import fis.com.vn.exception.SendEmailException;
import fis.com.vn.model.entity.*;
import fis.com.vn.model.enumerate.UserStatus;
import fis.com.vn.rest.response.CorporateUserResponse;
import fis.com.vn.service.notification.EmailService;
import fis.com.vn.service.notification.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String noreplyAddress;

    @Value("${domain.server}")
    private String domain;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SimpleMailMessage template;

    @Autowired
    TemplateService templateService;

    public static final String EMAIL_001C = "Email_001C";
    public static final String EMAIL_002C = "Email_002C";
    public static final String EMAIL_001 = "Email_001";
    public static final String EMAIL_002 = "Email_002";
    public static final String EMAIL_003 = "Email_003";
    public static final String EMAIL_004 = "Email_004";
    public static final String EMAIL_005 = "Email_005";
    public static final String EMAIL_006 = "Email_006";
    public static final String EMAIL_007 = "Email_007";
    public static final String EMAIL_008 = "Email_008";
    public static final String EMAIL_010 = "Email_010";
    public static final String EMAIL_011 = "Email_011";
    public static final String EMAIL_012 = "Email_012";
    public static final String EMAIL_015 = "Email_015";
    public static final String EMAIL_017A = "Email_017A";
    public static final String EMAIL_017B = "Email_017B";
    public static final String EMAIL_018 = "Email_018";
    public static final String EMAIL_019 = "Email_019";
    public static final String EMAIL_020 = "Email_020";
    public static final String EMAIL_021A = "Email_021A";
    public static final String EMAIL_021B = "Email_021B";
    public static final String EMAIL_022 = "Email_022";
    public static final String EMAIL_075 = "Email_075";
    public static final String EMAIL_081 = "Email_081";
    public static final String EMAIL_035 = "Email_035";
    public static final String EMAIL_049 = "Email_049";
    public static final String EMAIL_051 = "Email_051";
    public static final String EMAIL_049b = "Email_049b";

    public static final String STANDARD_CHARSETS_UTF8 = "UTF-8";

    public void sendEmail(String[] to, String[] cc, String[] bcc, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, STANDARD_CHARSETS_UTF8);
        helper.setFrom(noreplyAddress);
        helper.setTo(to);
        if (cc != null && cc.length > 0) {
            helper.setCc(cc);
        }
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        emailSender.send(message);
    }

    @Override
    public void sendEmailWithAttachment(String[] to, String[] cc, String[] bcc, String subject, String htmlBody, File file) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, STANDARD_CHARSETS_UTF8);
        helper.setFrom(noreplyAddress);
        helper.setTo(to);
        if (cc != null && cc.length > 0) {
            helper.setCc(cc);
        }
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        helper.addAttachment(file.getName(), file);

        emailSender.send(message);

    }

    @Override
    public void sendEmail(Email email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, STANDARD_CHARSETS_UTF8);
        helper.setFrom(noreplyAddress);
        helper.setTo(email.getTo());
        if (email.getCc() != null && email.getCc().length > 0) {
            helper.setCc(email.getCc());
        }
        if (email.getBcc() != null && email.getBcc().length > 0) {
            helper.setBcc(email.getBcc());
        }
        helper.setSubject(email.getSubject());
        helper.setText(email.getHtmlBody(), true);
        emailSender.send(message);
    }

    @Override
    public void sendEmailWithAttachment(Email email, String fileName) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, STANDARD_CHARSETS_UTF8);
        helper.setFrom(noreplyAddress);
        helper.setTo(email.getTo());
        if (email.getTo() != null && email.getTo().length > 0) {
            helper.setCc(email.getTo());
        }
        if (email.getBcc() != null && email.getBcc().length > 0) {
            helper.setBcc(email.getBcc());
        }
        helper.setSubject(email.getSubject());
        helper.setText(email.getHtmlBody(), true);
        if (fileName == null || fileName.isEmpty() || fileName.trim().isEmpty()) {
            helper.addAttachment(fileName, email.getFileAttachment());
        } else {
            helper.addAttachment(email.getFileAttachment().getName(), email.getFileAttachment());
        }

        emailSender.send(message);
    }

    @Override
    /**
     * Send Email when create user success.
     *
     * @param email
     */
    public void sendEmailWithTemplate(Email email) throws SendEmailException {
        try {
            this.sendEmail(email);
            log.debug("Sendmail success::: \n to " + email.getTo() + ", subject:::" + email.getSubject());
        } catch (MessagingException e) {
            log.debug("---Error when sendMail::: " + e.getMessage() + "---");
            // TODO handle insert log in DB.
        }
        log.debug("------Stop flow send email.-------");
    }

    @Override
    public Email getEmail001(CorporateUserResponse userInfoUp) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_001);
        Email email = new Email();
        email.setTo(new String[]{userInfoUp.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate001(userInfoUp, template, userInfoUp.getPassword()));
        return email;
    }

    public String getContentTemplate001(CorporateUserResponse userInfoUp, Template template, String password) {
        String status = "";
        status = UserStatus.valueOf(userInfoUp.getUserStatus()).getDescription();
        String content = template.getTemplateContent();
        content = content.replace("$corporate_name", userInfoUp.getCorporateName())
                .replace("$user_name", userInfoUp.getUserName())
                .replace("$user_code", userInfoUp.getUserCode())
                .replace("$password", password)
                .replace("$link_website", "fptWebsite")
                .replace("$hotline_number", "hotline");
        return content;
    }

    @Override
    public List<Email> getEmail017A(List<UserInfoEntity> userInfoUpList, Contract contract) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_017A);
        List<Email> emailList = new ArrayList<>();
        for (UserInfoEntity userInfoUp : userInfoUpList) {
            Email email = new Email();
            email.setTo(new String[]{userInfoUp.getEmail()});
            email.setSubject(template.getTemplateSubject());
            email.setHtmlBody(getContentTemplate017A(userInfoUp.getUserName(), contract, template));
            emailList.add(email);
        }
        return emailList;
    }

    @Override
    public List<Email> getEmail017B(List<UserInfoEntity> userInfoUpList, Contract contract) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_017B);
        List<Email> emailList = new ArrayList<>();
        for (UserInfoEntity userInfoUp : userInfoUpList) {
            Email email = new Email();
            email.setTo(new String[]{userInfoUp.getEmail()});
            email.setSubject(template.getTemplateSubject());
            email.setHtmlBody(getContentTemplate017A(userInfoUp.getUserName(), contract, template));
            emailList.add(email);
        }
        return emailList;
    }

    @Override
    public List<Email> getEmail018(List<UserInfoEntity> userInfoUpList, Contract contract) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_018);
        List<Email> emailList = new ArrayList<>();
        for (UserInfoEntity userInfoUp : userInfoUpList) {
            Email email = new Email();
            email.setTo(new String[]{userInfoUp.getEmail()});
            email.setSubject(template.getTemplateSubject());
            email.setHtmlBody(getContentTemplate017A(userInfoUp.getUserName(), contract, template));
            emailList.add(email);
        }
        return emailList;
    }

    @Override
    public List<Email> getEmail019(List<UserInfoEntity> userInfoUpList, Contract contract) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_019);
        List<Email> emailList = new ArrayList<>();
        for (UserInfoEntity userInfoUp : userInfoUpList) {
            Email email = new Email();
            email.setTo(new String[]{userInfoUp.getEmail()});
            email.setSubject(template.getTemplateSubject());
            email.setHtmlBody(getContentTemplate017A(userInfoUp.getUserName(), contract, template));
            emailList.add(email);
        }
        return emailList;
    }

    @Override
    public List<Email> getEmail020(List<UserInfoEntity> userInfoUpList, Contract contract) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_020);
        List<Email> emailList = new ArrayList<>();
        for (UserInfoEntity userInfoUp : userInfoUpList) {
            Email email = new Email();
            email.setTo(new String[]{userInfoUp.getEmail()});
            email.setSubject(template.getTemplateSubject());
            email.setHtmlBody(getContentTemplate017A(userInfoUp.getUserName(), contract, template));
            emailList.add(email);
        }
        return emailList;
    }

    @Override
    public List<Email> getEmail021A(List<UserInfoEntity> userInfoUpList, Contract contract) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_021A);
        List<Email> emailList = new ArrayList<>();
        for (UserInfoEntity userInfoUp : userInfoUpList) {
            Email email = new Email();
            email.setTo(new String[]{userInfoUp.getEmail()});
            email.setSubject(template.getTemplateSubject());
            email.setHtmlBody(getContentTemplate017A(userInfoUp.getUserName(), contract, template));
            emailList.add(email);
        }
        return emailList;
    }

    @Override
    public List<Email> getEmail021A(List<UserInfoEntity> userInfoUpList, List<UserInfoEntity> userInfoUpListCC, Contract contract) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_021A);
        List<Email> emailList = new ArrayList<>();
        List<String> listUserTo = new ArrayList<>();
        List<String> listUserCc = new ArrayList<>();
        StringBuilder userNameTo = new StringBuilder();
        for (UserInfoEntity userInfoTo : userInfoUpList) {
            listUserTo.add(userInfoTo.getEmail());
            userNameTo.append(userInfoTo.getUserName()+", ");
        }
        for (UserInfoEntity userInfoCC : userInfoUpListCC) {
            listUserCc.add(userInfoCC.getEmail());
        }
        String[] arrayUserTo = new String[listUserTo.size()];
        listUserTo.toArray(arrayUserTo);

        String[] arrayUserCc = new String[listUserCc.size()];
        listUserCc.toArray(arrayUserCc);

        Email email = new Email();
        email.setTo(arrayUserTo);
        email.setCc(arrayUserCc);
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate017A(userNameTo.toString(), contract, template));
        emailList.add(email);
        return emailList;
    }

    @Override
    public List<Email> getEmail021B(List<UserInfoEntity> userInfoUpList, Contract contract) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_021B);
        List<Email> emailList = new ArrayList<>();
        for (UserInfoEntity userInfoUp : userInfoUpList) {
            Email email = new Email();
            email.setTo(new String[]{userInfoUp.getEmail()});
            email.setSubject(template.getTemplateSubject());
            email.setHtmlBody(getContentTemplate017A(userInfoUp.getUserName(), contract, template));
            emailList.add(email);
        }
        return emailList;
    }

    @Override
    public List<Email> getEmail021B(List<UserInfoEntity> userInfoUpList, List<UserInfoEntity> userInfoUpListCC, Contract contract) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_021B);
        List<Email> emailList = new ArrayList<>();
        List<String> listUserTo = new ArrayList<>();
        List<String> listUserCc = new ArrayList<>();
        StringBuilder userNameTo = new StringBuilder();
        for (UserInfoEntity userInfoTo : userInfoUpList) {
            listUserTo.add(userInfoTo.getEmail());
            userNameTo.append(userInfoTo.getUserName() + ", ");
        }
        for (UserInfoEntity userInfoCC : userInfoUpListCC) {
            listUserCc.add(userInfoCC.getEmail());
        }
        String[] arrayUserTo = new String[listUserTo.size()];
        listUserTo.toArray(arrayUserTo);

        String[] arrayUserCc = new String[listUserCc.size()];
        listUserCc.toArray(arrayUserCc);

        Email email = new Email();
        email.setTo(arrayUserTo);
        email.setCc(arrayUserCc);
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate017A(userNameTo.toString(), contract, template));
        emailList.add(email);
        return emailList;
    }

    @Override
    public List<Email> getEmail022(List<UserInfoEntity> userInfoUpList, Contract contract) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_022);
        List<Email> emailList = new ArrayList<>();
        for (UserInfoEntity userInfoUp : userInfoUpList) {
            Email email = new Email();
            email.setTo(new String[]{userInfoUp.getEmail()});
            email.setSubject(template.getTemplateSubject());
            email.setHtmlBody(getContentTemplate017A(userInfoUp.getUserName(), contract, template));
            emailList.add(email);
        }
        return emailList;
    }

    @Override
    public List<Email> getEmail022(List<UserInfoEntity> userInfoUpList, List<UserInfoEntity> userInfoUpListCC, Contract contract) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_022);
        List<Email> emailList = new ArrayList<>();
        List<String> listUserTo = new ArrayList<>();
        List<String> listUserCc = new ArrayList<>();
        StringBuilder userNameTo = new StringBuilder();
        for (UserInfoEntity userInfoTo : userInfoUpList) {
            listUserTo.add(userInfoTo.getEmail());
            userNameTo.append(userInfoTo.getUserName() + ", ");
        }
        for (UserInfoEntity userInfoCC : userInfoUpListCC) {
            listUserCc.add(userInfoCC.getEmail());
        }
        String[] arrayUserTo = new String[listUserTo.size()];
        listUserTo.toArray(arrayUserTo);

        String[] arrayUserCc = new String[listUserCc.size()];
        listUserCc.toArray(arrayUserCc);

        Email email = new Email();
        email.setTo(arrayUserTo);
        email.setCc(arrayUserCc);
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate017A(userNameTo.toString(), contract, template));
        emailList.add(email);
        return emailList;
    }

    @Override
    public Email getEmail035(String bankInfoName, String corporateBuyName, String emailAccountant, String proposalCodeRelease) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_035);
        Email email = new Email();
        email.setTo(new String[]{emailAccountant});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate035(bankInfoName, corporateBuyName, template, proposalCodeRelease));
        return email;
    }

    @Override
    public Email getEmail049(UserInfoEntity userInfo, ApplicationOpeningLc applicationOpeningLc) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_049);
        Email email = new Email();
        email.setTo(new String[]{userInfo.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate049(userInfo, template, applicationOpeningLc));
        return email;
    }

    @Override
    public Email getEmail049b(UserInfoEntity userInfo, ApplicationOpeningLc applicationOpeningLc) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_049b);
        Email email = new Email();
        email.setTo(new String[]{userInfo.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate049(userInfo, template, applicationOpeningLc));
        return email;
    }

    @Override
    public Email getEmail051(BankInfo bankInfo, String corporateBuyName, String proposalCodeRelease, UserInfoEntity userInfo, UserInfoEntity userMaker) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_051);
        Email email = new Email();
        email.setTo(new String[]{userInfo.getEmail()});
        email.setCc(new String[]{userMaker.getEmail()});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate051(bankInfo.getBankName(), template, corporateBuyName, proposalCodeRelease));
        return email;
    }

    @Override
    public Email getEmail075(String financingLimitCode, String approverEmail) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_075);
        Email email = new Email();
        email.setTo(new String[]{approverEmail});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate075(financingLimitCode, template));
        return email;
    }

    @Override
    public Email getEmail081(String financingLimitCode, String approverEmail) {
        Template template = templateService.getTemplateByCode(EmailServiceImpl.EMAIL_081);
        Email email = new Email();
        email.setTo(new String[]{approverEmail});
        email.setSubject(template.getTemplateSubject());
        email.setHtmlBody(getContentTemplate075(financingLimitCode, template));
        return email;
    }

    public String getContentTemplate017A(String userName, Contract contract, Template template) {
        String content = template.getTemplateContent();
        content = content.replace("$userName", userName);
        content = content.replace("$contractCode", contract.getContractCode());
        content = content.replace("$contractId", contract.getContractId().toString());
        content = content.replace("$domain", domain);
        return content;
    }

    public String getContentTemplate035(String bankName, String corporateBuyName,  Template template, String proposalCodeRelease) {
        String content = template.getTemplateContent();
        content = content.replace("$bankInfoName", bankName)
                .replace("$corporateBuyName ", corporateBuyName)
                .replace("$proposalCodeRelease", proposalCodeRelease);
        return content;
    }

    public String getContentTemplate049(UserInfoEntity userInfo, Template template, ApplicationOpeningLc applicationOpeningLc) {
        String content = template.getTemplateContent();
        content = content.replace("$proposalCodeRelease", applicationOpeningLc.getProposalCodeRelease())
                .replace("$accontantName", userInfo.getUserName())
                .replace("$link", "https://dev-lc.xcbt.online/cm-home/lc-request-manage/view/" + applicationOpeningLc.getId());
        return content;
    }

    public String getContentTemplate049b(UserInfoEntity userInfo, Template template, ApplicationOpeningLc applicationOpeningLc) {
        String content = template.getTemplateContent();
        content = content.replace("$proposalCodeRelease", applicationOpeningLc.getProposalCodeRelease())
                .replace("$accontantName", userInfo.getUserName())
                .replace("$link", "https://dev-lc.xcbt.online/cm-home/lc-request-manage/view/" + applicationOpeningLc.getId());
        return content;
    }

    public String getContentTemplate051(String bankName, Template template, String corporateBuyName, String proposalCodeRelease) {
        String content = template.getTemplateContent();
        content = content.replace("$bankName", bankName)
                .replace("$corporateBuy ", corporateBuyName)
                .replace("$proposalCodeRelease", proposalCodeRelease);
        return content;
    }

    public String getContentTemplate075(String financingLimitCode, Template template) {
        String content = template.getTemplateContent();
        content = content.replace("$financingLimitCode", financingLimitCode);
        return content;
    }

}