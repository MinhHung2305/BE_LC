package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.FeeRules;
import fis.com.vn.rest.response.FeeRulesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeeRulesResponseMapper extends LcMapper<FeeRules, FeeRulesResponse>{
}
