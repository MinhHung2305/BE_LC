package fis.com.vn.service.corporate;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.ContractLicense;
import fis.com.vn.rest.request.ContractLicenseRequest;

import java.util.List;

public interface ContractLicenseService {
    ContractLicense createContractLicense(ContractLicense contractLicense) throws LCPlatformException;
    ContractLicense create(ContractLicenseRequest contractLicenseRequest) throws LCPlatformException;
    List<ContractLicense> insertOrUpdateContractLicense(List<ContractLicenseRequest> contractLicenseList, Integer contractId);
    List<ContractLicense> insertOrUpdateContractLicenseForApplicationOpeningLc(List<ContractLicenseRequest> contractLicenseList, Long applicationOpeningLcId);
    List<ContractLicense> insertContractLicenseForApplicationOpeningLc(List<ContractLicenseRequest> contractLicenseList, Long applicationOpeningLcId);
}
