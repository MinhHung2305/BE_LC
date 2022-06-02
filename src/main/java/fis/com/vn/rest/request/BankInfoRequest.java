package fis.com.vn.rest.request;

import lombok.*;

import javax.persistence.Column;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankInfoRequest {

    private String bankName;

    private String bankCode;

    private String swiftCode;

    private String bankShortName;

    private String bankPhoneNumber;

    private String bankFaxNumber;

    private String bankEmailAddress;

    private String bankAddress;

    private Integer bankStatus;
}
