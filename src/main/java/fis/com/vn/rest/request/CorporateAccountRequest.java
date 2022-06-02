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
public class CorporateAccountRequest {

	private Long corporateAccountId;
	
    private String bankId;

    private String corporateAccountNumber;

    private String corporateAccountName;

    private String corporateAccountType;

    private Integer corporateAccountStatus;
}
