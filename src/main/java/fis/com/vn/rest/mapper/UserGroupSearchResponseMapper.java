package fis.com.vn.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import fis.com.vn.model.entity.UserGroupEntity;
import fis.com.vn.rest.response.UserGroupSearchRespone;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserGroupSearchResponseMapper extends LcMapper<UserGroupEntity, UserGroupSearchRespone> {
}
