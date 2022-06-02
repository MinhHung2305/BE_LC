package fis.com.vn.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import fis.com.vn.model.entity.CorporateAccount;
import fis.com.vn.rest.response.CorporateAccountResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CorporateAccountResponseMapper extends LcMapper<CorporateAccount, CorporateAccountResponse> {
}
