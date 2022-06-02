package fis.com.vn.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageError {
    private int codeError;
    private String messageErrorName;
    private String messageEnglish;
    private String messageVietnamese;
    private String moduleCode;

    public MessageError(int codeError, String messageErrorName, String messageEnglish, String messageVietnamese) {
        this.codeError = codeError;
        this.messageErrorName = messageErrorName;
        this.messageEnglish = messageEnglish;
        this.messageVietnamese = messageVietnamese;
    }
}

