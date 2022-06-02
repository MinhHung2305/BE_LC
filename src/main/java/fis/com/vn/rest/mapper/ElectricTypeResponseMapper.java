package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.ElectricType;
import fis.com.vn.rest.response.ElectricTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ElectricTypeResponseMapper extends LcMapper<ElectricType, ElectricTypeResponse>{
}
