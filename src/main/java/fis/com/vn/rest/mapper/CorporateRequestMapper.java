package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.Corporate;
import fis.com.vn.rest.request.CorporateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CorporateRequestMapper extends LcMapper<Corporate, CorporateRequest> {
}
