package fis.com.vn.rest.response;

import lombok.*;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OcrCorporateSignatureResponse implements Serializable {
    private String serial;
    private String signature;
    private String subject;
    private String signtime;
    private String certificate;
    private String from;
    private String to;
    private String validity;
    private String issuer;
    private String mst;
}
