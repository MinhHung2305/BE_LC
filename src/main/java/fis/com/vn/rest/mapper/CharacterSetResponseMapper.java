package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.CharacterSet;
import fis.com.vn.rest.response.CharacterSetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CharacterSetResponseMapper extends LcMapper<CharacterSet, CharacterSetResponse>{
}
