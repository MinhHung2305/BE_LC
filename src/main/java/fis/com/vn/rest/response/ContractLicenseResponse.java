package fis.com.vn.rest.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractLicenseResponse {
    private Long contractLicenseId;
    private LicenseResponse licenseResponse;
    private ContractResponse contractResponse;
    private String licenseDescription;
}
