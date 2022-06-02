package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.CharacterSetSwift;
import fis.com.vn.rest.response.CharacterSetSwiftResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CharacterSetSwiftResponseMapper extends LcMapper<CharacterSetSwift, CharacterSetSwiftResponse>{
}
