package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.ApplicationOpeningLc;
import fis.com.vn.rest.response.ApplicationOpeningLcResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationOpeningLcResponseMapper extends LcMapper<ApplicationOpeningLc, ApplicationOpeningLcResponse>{
}
