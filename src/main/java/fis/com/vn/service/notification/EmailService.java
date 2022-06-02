package fis.com.vn.service.notification;

import fis.com.vn.exception.SendEmailException;
import fis.com.vn.model.entity.*;
import fis.com.vn.rest.response.CorporateUserResponse;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface EmailService {
    void sendEmail(String[] to, String[] cc, String[] bcc, String subject, String htmlBody) throws MessagingException;

    void sendEmailWithAttachment(String[] to, String[] cc, String[] bcc, String subject, String htmlBody, File file) throws MessagingException, FileNotFoundException;

    void sendEmail(Email email) throws MessagingException;

    void sendEmailWithAttachment(Email email, String fileName) throws MessagingException, FileNotFoundException;

    void sendEmailWithTemplate(Email email) throws SendEmailException;

    List<Email> getEmail017A(List<UserInfoEntity> userInfoUpList, Contract contract);

    List<Email> getEmail017B(List<UserInfoEntity> userInfoUpList, Contract contract);

    List<Email> getEmail018(List<UserInfoEntity> userInfoUpList, Contract contract);

    List<Email> getEmail019(List<UserInfoEntity> userInfoUpList, Contract contract);

    List<Email> getEmail020(List<UserInfoEntity> userInfoUpList, Contract contract);

    List<Email> getEmail021A(List<UserInfoEntity> userInfoUpList, Contract contract);

    List<Email> getEmail021A(List<UserInfoEntity> userInfoUpList, List<UserInfoEntity> userInfoUpListCC, Contract contract);

    List<Email> getEmail021B(List<UserInfoEntity> userInfoUpList, Contract contract);

    List<Email> getEmail021B(List<UserInfoEntity> userInfoUpList, List<UserInfoEntity> userInfoUpListCC, Contract contract);

    List<Email> getEmail022(List<UserInfoEntity> userInfoUpList, Contract contract);

    List<Email> getEmail022(List<UserInfoEntity> userInfoUpList, List<UserInfoEntity> userInfoUpListCC, Contract contract);

    Email getEmail049(UserInfoEntity userInfo, ApplicationOpeningLc applicationOpeningLc);

    Email getEmail049b(UserInfoEntity userInfo, ApplicationOpeningLc applicationOpeningLc);

    Email getEmail051(BankInfo bankInfo, String corporateBuyName, String proposalCodeRelease, UserInfoEntity userInfo, UserInfoEntity userMaker);

    Email getEmail075(String financingLimtCode, String approverEmail);

    Email getEmail081(String financingLimtCode, String approverEmail);

    Email getEmail035(String bankInfoName, String corporateBuyName, String emailAccountant, String proposalCodeRelease);

    Email getEmail001(CorporateUserResponse corporateUserResponse);
}
