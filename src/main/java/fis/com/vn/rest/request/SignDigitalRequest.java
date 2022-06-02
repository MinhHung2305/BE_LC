package fis.com.vn.rest.request;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class SignDigitalRequest {
    private String maKy;
    private String agreementUUID;
    private String codeTransaction;
    private String otp;
    private Long applicationOpeningLcId;
}
