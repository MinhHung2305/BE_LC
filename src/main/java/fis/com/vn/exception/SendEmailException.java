package fis.com.vn.exception;

import lombok.Getter;

import javax.mail.MessagingException;

@Getter
public class SendEmailException extends MessagingException {
    /**
     * Message for exception.
     */
    private String message;

    public SendEmailException(String message){
        this.message = message;
    }
}
