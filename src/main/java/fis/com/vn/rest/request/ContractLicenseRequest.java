package fis.com.vn.rest.request;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ContractLicenseRequest {
    private Long contractLicenseId;
    private LicenseRequest licenseRequest;
    private Integer contractId;
    private Long applicationOpeningLcId;
    private String licenseDescription;
}
