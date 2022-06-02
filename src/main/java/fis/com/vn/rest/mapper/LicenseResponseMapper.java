package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.License;
import fis.com.vn.rest.response.LicenseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LicenseResponseMapper extends LcMapper<License, LicenseResponse>{
}
