package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.FinancingLimit;
import fis.com.vn.rest.response.FinancingLimitResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FinancingLimitResponseMapper extends LcMapper<FinancingLimit, FinancingLimitResponse>{
}
