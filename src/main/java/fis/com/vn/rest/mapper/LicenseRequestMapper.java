package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.License;
import fis.com.vn.rest.request.LicenseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LicenseRequestMapper extends LcMapper<License, LicenseRequest>{
}
