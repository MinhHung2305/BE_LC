package fis.com.vn.rest.mapper;


import fis.com.vn.model.entity.UserInfoEntity;
import fis.com.vn.rest.request.BankUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankUserRequestMapper extends LcMapper<UserInfoEntity, BankUserRequest> {
}

