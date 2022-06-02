package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.LcClassify;
import fis.com.vn.rest.response.LcClassifyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LcClassifyResponseMapper extends LcMapper<LcClassify, LcClassifyResponse> {
}
