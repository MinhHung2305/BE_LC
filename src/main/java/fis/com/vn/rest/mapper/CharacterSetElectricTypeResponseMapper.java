package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.CharacterSetElectricType;
import fis.com.vn.rest.response.CharacterSetElectricTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CharacterSetElectricTypeResponseMapper extends LcMapper<CharacterSetElectricType, CharacterSetElectricTypeResponse>{
}
