package fis.com.vn.service.common;

import fis.com.vn.model.entity.UserGroupEntity;
import fis.com.vn.rest.request.UserGroupRequest;
import fis.com.vn.rest.response.UserGroupResponse;
import fis.com.vn.util.PageSupport;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserGroupService {
	UserGroupEntity findByUserGroupCode(String userGroupCode);
	
	UserGroupEntity findByUserGroupId(Long userGroupId);

	UserGroupResponse findUserGroupById(Long userGroupId);
	
	PageSupport<UserGroupEntity> findAll(Pageable pageable);

	List<UserGroupEntity> findAll();

	UserGroupResponse createUserGroup(String userId, UserGroupRequest userGroup);

	UserGroupEntity updateUserGroup(UserGroupEntity userGroup);

	UserGroupResponse updateUserGroup(UserGroupRequest userGroup);

	UserGroupEntity deleteByUserGroupCode(Long id);

	List<UserGroupEntity> searchUserGroup(UserGroupEntity userGroup);
	
	UserGroupResponse getPermissionUserGroup(String userGroupCode);

	UserGroupResponse getPermission(String userType);
	
	UserGroupEntity updatePermissionUserGroup(String userGroupCode, List<String> permission);

	List<UserGroupEntity> searchUserGroupInUser(String branchLevel, String groupType);
	
	List<UserGroupEntity> searchUserGroupInUser(String branchLevel, String groupType, String roles);

	List<UserGroupEntity> searchGroupInUserFPT();

	List<UserGroupEntity> getByUserType(String userType);

	List<UserGroupEntity> getByGroupType(String groupType);
}
