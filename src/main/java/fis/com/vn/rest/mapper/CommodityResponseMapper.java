package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.Commodity;
import fis.com.vn.rest.response.CommodityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommodityResponseMapper extends LcMapper<Commodity, CommodityResponse> {
}
