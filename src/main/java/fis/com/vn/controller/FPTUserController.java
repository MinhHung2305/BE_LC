package fis.com.vn.controller;

import fis.com.vn.service.common.UserInfoService;
import fis.com.vn.service.common.impl.KeycloakServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.util.ResponseFactory;

@RestController
@RequestMapping("/admin")
@Secured({ "ROLE_admin"})
public class FPTUserController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private KeycloakServiceImpl keycloakService;
	
	@Secured({"ROLE_view_group"})
	@GetMapping("/user")
	@ApiOperation(value = "Get User Group")
	public ResponseEntity<BaseResponse<String>> getUser(@RequestParam String userId) {

		return ResponseFactory.success(HttpStatus.OK, "Bank User", "Get Bank User");
	}

	@Secured({"ROLE_delete_group"})
	@GetMapping("/delete")
	@ApiOperation(value = "Delete User Group")
	public ResponseEntity<BaseResponse<String>> delete() {

		return ResponseFactory.success(HttpStatus.OK, "Delete roles", "Delete Bank User");
	}

	@Secured({ "ROLE_create_group" })
	@GetMapping("/create")
	@ApiOperation(value = "Create User Group")
	public ResponseEntity<BaseResponse<String>> create() {

		return ResponseFactory.success(HttpStatus.OK, "create roles", "Create Bank User");
	}

	@Secured({ "ROLE_create_group" })
	@GetMapping("/update")
	public ResponseEntity<BaseResponse<String>> update() {

		return ResponseFactory.success(HttpStatus.OK, "update roles", "Update Bank User");
	}

	@GetMapping("/group")
	public ResponseEntity<BaseResponse<String>> getGroup() {
		keycloakService.getGroups("c9df6620-5194-4400-b001-e92e0a6bca1e");
		return ResponseFactory.success(HttpStatus.OK, "update roles", "Update Bank User");
	}
}
