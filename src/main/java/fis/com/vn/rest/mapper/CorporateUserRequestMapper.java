package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.UserInfoEntity;
import fis.com.vn.rest.request.CorporateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CorporateUserRequestMapper extends LcMapper<UserInfoEntity, CorporateUserRequest> {
}
