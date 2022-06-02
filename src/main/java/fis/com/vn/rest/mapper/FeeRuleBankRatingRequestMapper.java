package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.FeeRuleBankRating;
import fis.com.vn.rest.request.FeeRuleBankRatingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeeRuleBankRatingRequestMapper extends LcMapper<FeeRuleBankRating,FeeRuleBankRatingRequest>{
}
