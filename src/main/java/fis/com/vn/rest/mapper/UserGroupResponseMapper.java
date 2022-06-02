package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.UserGroupEntity;
import fis.com.vn.rest.response.UserGroupResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserGroupResponseMapper extends LcMapper<UserGroupEntity, UserGroupResponse> {
}
