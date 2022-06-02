package fis.com.vn.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import fis.com.vn.model.entity.UserInfoEntity;
import fis.com.vn.rest.response.CorporateUserResponse;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CorporateUserResponseMapper extends LcMapper<UserInfoEntity, CorporateUserResponse> {
}
