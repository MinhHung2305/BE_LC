package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.BankRating;
import fis.com.vn.rest.request.BankRatingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankRatingRequestMapper extends LcMapper<BankRating, BankRatingRequest>{
}
