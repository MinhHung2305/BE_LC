package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.ContractLicense;
import fis.com.vn.rest.request.ContractLicenseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractLicenseRequestMapper extends LcMapper<ContractLicense, ContractLicenseRequest> {
}
