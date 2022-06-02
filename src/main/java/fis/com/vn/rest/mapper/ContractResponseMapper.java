package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.Contract;
import fis.com.vn.rest.response.ContractResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractResponseMapper extends LcMapper<Contract, ContractResponse>{}
