package fis.com.vn.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorporateAccountResponse {

	private Long corporateAccountId;
	private String corporateCode;
	private String corporateName;
	private String corporateAccountNumber;
	private String corporateAccountName;
	private String corporateAccountType;
	private Integer corporateAccountStatus;
	private String bankName;

}
