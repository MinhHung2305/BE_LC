package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.BankRating;
import fis.com.vn.rest.response.BankRatingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankRatingResponseMapper extends LcMapper<BankRating, BankRatingResponse>{
}
