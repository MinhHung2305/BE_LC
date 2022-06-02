package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.ContractAddendum;
import fis.com.vn.rest.request.ContractAddendumRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractAddendumRequestMapper extends LcMapper<ContractAddendum, ContractAddendumRequest> {
}
