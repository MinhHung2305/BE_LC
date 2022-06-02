package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.UserInfoEntity;
import fis.com.vn.rest.response.BankUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankUserResponseMapper extends LcMapper<UserInfoEntity, BankUserResponse> {
}
