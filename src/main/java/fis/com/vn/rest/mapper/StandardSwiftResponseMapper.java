package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.StandardSwift;
import fis.com.vn.rest.response.StandardSwiftResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StandardSwiftResponseMapper extends LcMapper<StandardSwift, StandardSwiftResponse> {
}
