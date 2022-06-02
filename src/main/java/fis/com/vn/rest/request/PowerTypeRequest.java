package fis.com.vn.rest.request;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerTypeRequest {
    private Long id;
    private String powerType;
    private String powerName;
    private String purposeUse;
    private String version;
    private String powerCodeFpt;
    private String senderReferenceNumber;
    private String recipientReferenceNumber;
    private Long sendingBankId;
    private Long receivingBankId;
    private String information;
}
