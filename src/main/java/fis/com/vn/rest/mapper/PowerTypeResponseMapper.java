package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.PowerType;
import fis.com.vn.rest.response.PowerTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PowerTypeResponseMapper extends LcMapper<PowerType, PowerTypeResponse>{
}
