package fis.com.vn.rest.corporate;

import fis.com.vn.model.entity.UserGroupEntity;
import fis.com.vn.rest.mapper.UserGroupRequestMapper;
import fis.com.vn.rest.request.UserGroupRequest;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.UserGroupResponse;
import fis.com.vn.service.common.KeycloakService;
import fis.com.vn.service.common.UserGroupService;
import fis.com.vn.util.Constant;
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

import java.util.List;

@RestController
@RequestMapping("/corporate")
@Slf4j
//@Secured({ "ROLE_corporate", "ROLE_admin" })
@Api(tags = "Corporate Group controller")
public class CorporateUserGroupController {

	@Autowired
	UserGroupService userGroupService;

	@Autowired
	KeycloakService keycloakService;
	
	@Autowired
	UserGroupRequestMapper userGroupRequestMapper;

	@GetMapping("/group/co/getUserGroups")
	@Secured({ "ROLE_view_corporate_group_management" })
	public ResponseEntity<BaseResponse<List<UserGroupEntity>>> getUserGroups() {
		List<UserGroupEntity> userGroupEntities = userGroupService.getByUserType(Constant.USER_TYPE_CORPORATE);
		if (userGroupEntities != null) {
			log.info("Get: " + userGroupEntities.toString());
			return ResponseFactory.success(HttpStatus.OK, userGroupEntities, "Get User Group By Id success");
		} else {
			return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Cannot find UserGroup");
		}
	}

	@PostMapping("/group/co/createUserGroup")
	@Secured({ "ROLE_create_corporate_group_management" })
	public ResponseEntity<BaseResponse<String>> createCorporateUserGroup(@AuthenticationPrincipal Jwt principal, @RequestBody UserGroupRequest userGroup) {
		String userId = principal.getSubject();
		UserGroupRequest userGroupAdd = userGroup;
		userGroupAdd.setUserType(Constant.USER_TYPE_CORPORATE);
		UserGroupResponse userG = userGroupService.createUserGroup(userId, userGroupAdd);
		if (userG != null) {
			log.info("Create: " + userG.toString());
			return ResponseFactory.success(HttpStatus.OK, "Create: " + userG.toString(), "Create UserGroup success");
		} else {
			return ResponseFactory.success(HttpStatus.CONFLICT, "Create UserGroup already exist",
					"Create UserGroup fail");
		}
	}

	@GetMapping("/group/co/getUserGroupById")
	@Secured({ "ROLE_view_corporate_group_management" })
	public ResponseEntity<BaseResponse<UserGroupResponse>> getCorporateUserGroupById(@RequestParam Long id) {
		UserGroupResponse UserGroupResponse = new UserGroupResponse();
		UserGroupResponse = userGroupService.findUserGroupById(id);
		if (UserGroupResponse != null) {
			log.info("Get: " + UserGroupResponse.toString());
			return ResponseFactory.success(HttpStatus.OK, UserGroupResponse, "Get User Group By Id success");
		} else {
			return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Cannot find UserGroup");
		}
	}

	@PutMapping("/group/co/updateUserGroup/{id}")
	@Secured({ "ROLE_edit_corporate_group_management" })
	public ResponseEntity<BaseResponse<String>> updateCorporateUserGroup(@RequestBody UserGroupRequest userGroup,
			@PathVariable String id) {
		
		UserGroupEntity userGroupUp = userGroupRequestMapper.toEntity(userGroup);
		userGroupUp.setId(Long.parseLong(id));
		UserGroupEntity userGroupResponse = userGroupService.updateUserGroup(userGroupUp);
		if (userGroupResponse != null) {
			log.info("Update: " + userGroupResponse.toString());
			return ResponseFactory.success(HttpStatus.OK, "Update: " + userGroupResponse.toString(),
					"Update UserGroup success");
		} else {
			return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, "Update UserGroup does not exist",
					"Update UserGroup fail");
		}
	}

	@DeleteMapping("/group/co/deleteUserGroup")
	@Secured({ "ROLE_delete_corporate_group_management" })
	public ResponseEntity<BaseResponse<String>> deleteCorporateUserGroup(@RequestParam Long id) {

		UserGroupEntity userG = userGroupService.deleteByUserGroupCode(id);
		if (userG != null) {
			log.info("Delete: " + userG.toString());
			return ResponseFactory.success(HttpStatus.OK, "Delete: " + userG.toString(), "Delete UserGroup success");
		} else {
			return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, "Delete UserGroup does not exist",
					"Delete UserGroup fail");
		}
	}

	@PutMapping("/group/co/updatePermissionUserGroup")
	@Secured({ "ROLE_grant_corporate_group_management" })
	public ResponseEntity<BaseResponse<String>> updatePermissionUserGroup(@RequestParam String userGroupCode,
			@RequestBody List<String> permission) {

		UserGroupEntity userG = userGroupService.updatePermissionUserGroup(userGroupCode, permission);
		if (userG != null) {
			return ResponseFactory.success(HttpStatus.OK, "Success", "Update Permission User Group");
		} else {
			return ResponseFactory.success(HttpStatus.OK, "Fail", "Update Permission User Group");
		}
	}
}