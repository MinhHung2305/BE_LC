package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.Contract;
import fis.com.vn.rest.request.ContractRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractRequestMapper extends LcMapper<Contract, ContractRequest> {
}
