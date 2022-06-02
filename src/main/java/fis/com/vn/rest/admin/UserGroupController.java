package fis.com.vn.rest.admin;

import fis.com.vn.model.entity.UserGroupEntity;
import fis.com.vn.rest.request.UserGroupRequest;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.UserGroupResponse;
import fis.com.vn.service.common.KeycloakService;
import fis.com.vn.service.common.UserGroupService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
//@Secured({ "ROLE_admin"})
@Api(tags = "Admin group controller")
public class UserGroupController {

	@Autowired
	UserGroupService userGroupService;

	@Autowired
	KeycloakService keycloakService;

	@GetMapping("/group/getUserGroup")
	@Secured({ "ROLE_view_fpt_group_management"})
	public ResponseEntity<BaseResponse<List<UserGroupEntity>>> getUserGroup() {

		List<UserGroupEntity> userGroupList = userGroupService.findAll();
		return ResponseFactory.success(HttpStatus.OK, userGroupList, "Get All User Group");
	}

	@GetMapping("/group/getUserGroupByCode")
	@Secured({ "ROLE_view_fpt_group_management"})
	public ResponseEntity<BaseResponse<UserGroupEntity>> getUserGroupByCode(@RequestParam String userGroupCode) {

		UserGroupEntity userGroupEntity = userGroupService.findByUserGroupCode(userGroupCode);
		if (userGroupEntity != null) {
			return ResponseFactory.success(HttpStatus.OK, userGroupEntity, "Get User Group By Code success");
		} else {
			return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Cannot find UserGroup");
		}
	}

	@GetMapping("/group/getUserGroupById")
	@Secured({ "ROLE_view_fpt_group_management"})
	public ResponseEntity<BaseResponse<UserGroupEntity>> getUserGroupById(@RequestParam Long id) {
		UserGroupEntity userGroupEntity = new UserGroupEntity();
		userGroupEntity = userGroupService.findByUserGroupId(id);
		if (userGroupEntity != null) {
			return ResponseFactory.success(HttpStatus.OK, userGroupEntity, "Get User Group By Id success");
		} else {
			return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Cannot find UserGroup");
		}
	}

	@GetMapping("/group/getAllUserGroupCode")
	@Secured({ "ROLE_view_fpt_group_management"})
	public ResponseEntity<BaseResponse<List<String>>> getAllUserGroupCode() {

		List<String> listUserGroupCode = new ArrayList<String>();
		List<UserGroupEntity> userGroupList = userGroupService.findAll();
		for (UserGroupEntity userGroupEntity : userGroupList) {
			String userGroupCode = userGroupEntity.getUserGroupCode();
			if (userGroupCode.length() > 0)
				listUserGroupCode.add(userGroupCode);
		}
		return ResponseFactory.success(HttpStatus.OK, listUserGroupCode, "Get All User Group");
	}

	@PostMapping("/group/createUserGroup")
	@Secured({ "ROLE_create_fpt_group_management"})
	public ResponseEntity<BaseResponse<String>> createUserGroup(@AuthenticationPrincipal Jwt principal, @RequestBody UserGroupRequest userGroup) {
		String userId = principal.getSubject();
		UserGroupResponse userG = userGroupService.createUserGroup(userId, userGroup);
		if (userG != null) {
			return ResponseFactory.success(HttpStatus.OK, "Create: " + userG.toString(), "Create UserGroup success");
		} else {
			return ResponseFactory.success(HttpStatus.CONFLICT, "Create UserGroup already exist",
					"Create UserGroup fail");
		}
	}

	@PutMapping("/group/updateUserGroup")
	@Secured({ "ROLE_edit_fpt_group_management"})
	public ResponseEntity<BaseResponse<String>> updateUserGroup(@RequestBody UserGroupEntity userGroup,
			@RequestParam Long id) {

		userGroup.setId(id);
		UserGroupEntity userG = userGroupService.updateUserGroup(userGroup);
		if (userG != null) {
			return ResponseFactory.success(HttpStatus.OK, "Update: " + userG.toString(), "Update UserGroup success");
		} else {
			return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, "Update UserGroup does not exist",
					"Update UserGroup fail");
		}
	}


	@DeleteMapping("/group/deleteUserGroup")
	@Secured({ "ROLE_delete_fpt_group_management"})
	public ResponseEntity<BaseResponse<String>> deleteUserGroup(@RequestParam Long id) {

		UserGroupEntity userG = userGroupService.deleteByUserGroupCode(id);
		if (userG != null) {
			return ResponseFactory.success(HttpStatus.OK, "Delete: " + userG.toString(), "Delete UserGroup success");
		} else {
			return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, "Delete UserGroup does not exist",
					"Delete UserGroup fail");
		}
	}

	@GetMapping("/group/searchUserGroup")
	@Secured({ "ROLE_view_fpt_group_management"})
	public ResponseEntity<BaseResponse<List<UserGroupEntity>>> searchUserGroup(@RequestBody UserGroupEntity userGroup) {

		List<UserGroupEntity> userGroupList = userGroupService.searchUserGroup(userGroup);
		return ResponseFactory.success(HttpStatus.OK, userGroupList, "Search User Group");
	}

	@GetMapping("/group/getPermissionUserGroup")
	@Secured({ "ROLE_view_fpt_group_management"})
	public ResponseEntity<BaseResponse<UserGroupResponse>> getPermissionUserGroup(@RequestParam String userGroupCode) {

		UserGroupResponse userGroupResponse = userGroupService.getPermissionUserGroup(userGroupCode);
		return ResponseFactory.success(HttpStatus.OK, userGroupResponse, "Get Permission User Group");
	}

	@PutMapping("/group/updatePermissionUserGroup")
	@Secured({ "ROLE_grant_fpt_group_management"})
	public ResponseEntity<BaseResponse<String>> updateRoleUserGroup(@RequestParam String userGroupCode,
			@RequestBody List<String> permission) {

		UserGroupEntity userG = userGroupService.updatePermissionUserGroup(userGroupCode, permission);
		if (userG != null) {
			return ResponseFactory.success(HttpStatus.OK, "Success", "Update Permission User Group");
		} else {
			return ResponseFactory.success(HttpStatus.OK, "Fail", "Update Permission User Group");
		}
	}
 
}
