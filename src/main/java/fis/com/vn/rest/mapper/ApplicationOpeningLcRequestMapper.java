package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.ApplicationOpeningLc;
import fis.com.vn.rest.request.ApplicationOpeningLcRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationOpeningLcRequestMapper extends LcMapper<ApplicationOpeningLc, ApplicationOpeningLcRequest> {
}
