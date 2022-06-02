package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.FeeRules;
import fis.com.vn.rest.request.FeeRulesRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeeRulesRequestMapper extends LcMapper<FeeRules, FeeRulesRequest>{
}
