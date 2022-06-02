package fis.com.vn.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import fis.com.vn.model.entity.Corporate;
import fis.com.vn.rest.response.CorporateResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CorporateResponseMapper extends LcMapper<Corporate, CorporateResponse> {
}
