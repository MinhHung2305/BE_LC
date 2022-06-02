package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.Fee;
import fis.com.vn.rest.request.FeeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeeRequestMapper extends LcMapper<Fee, FeeRequest>{
}
