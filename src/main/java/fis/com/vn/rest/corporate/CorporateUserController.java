package fis.com.vn.rest.corporate;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.request.CorporateUserRequest;
import fis.com.vn.rest.request.PasswordRequest;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.CorporateUserResponse;
import fis.com.vn.service.corporate.CorporateUserService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corporate")
@Slf4j
//@Secured({ "ROLE_corporate", "ROLE_admin" })
@Api(tags = "Corporate User controller")
public class CorporateUserController {

	@Autowired
	CorporateUserService corporateUserService;

	@GetMapping("/user/getAll")
	@Secured({ "ROLE_view_corporate_user_management" })
	public ResponseEntity<BaseResponse<List<CorporateUserResponse>>> getAllCorporateUser() {
		try {
			List<CorporateUserResponse> userInfoList = corporateUserService.getAllUserCorporate();
			return ResponseFactory.success(HttpStatus.OK, userInfoList, "GetAll UserGroup success");
		} catch (LCPlatformException e) {
			return ResponseFactory.success(HttpStatus.CONFLICT, null, "GetAll Corporate User fail");
		}
	}
	
	@GetMapping("/user/get/{id}")
	@Secured({ "ROLE_view_corporate_user_management" })
	public ResponseEntity<BaseResponse<CorporateUserResponse>> getCorporateUser(@PathVariable String id) {
		try {
			CorporateUserResponse userInfo = corporateUserService.getCorporateUserById(id);
			return ResponseFactory.success(HttpStatus.OK, userInfo, "Get UserGroup success");
		} catch (LCPlatformException e) {
			return ResponseFactory.success(HttpStatus.CONFLICT, null, "Get Corporate User fail");
		}
	}

	@PostMapping("/user/create/{corporateId}")
	@Secured({ "ROLE_create_corporate_user_management" })
	public ResponseEntity<BaseResponse<String>> createCorporateUser(@RequestBody List<CorporateUserRequest> userRequest,
			@PathVariable String corporateId) {
		try {
			corporateUserService.createUser(corporateId, userRequest);
			return ResponseFactory.success(HttpStatus.OK, "Success", "Create UserGroup success");
		} catch (LCPlatformException e) {
			return ResponseFactory.success(HttpStatus.CONFLICT, e.getMessage(), "Create Corporate User fail");
		}
	}

	@PostMapping("/user/update/{corporateId}/{userId}")
	@Secured({ "ROLE_create_corporate_user_management" })
	public ResponseEntity<BaseResponse<String>> updateCorporateUser(@RequestBody CorporateUserRequest userRequest,
			@PathVariable String corporateId, @PathVariable String userId) {
		try {
			corporateUserService.updateUser(corporateId, userId, userRequest);
			return ResponseFactory.success(HttpStatus.OK, "Success", "Update UserGroup success");
		} catch (LCPlatformException e) {
			return ResponseFactory.success(HttpStatus.CONFLICT, e.getMessage(), "Update Corporate User fail");
		}
	}

	@DeleteMapping("/user/delete/{id}")
	@Secured({ "ROLE_delete_corporate_user_management" })
	public ResponseEntity<BaseResponse<String>> deleteCorporateUser(@PathVariable String id) {
		try {
			corporateUserService.deleteByUserInfoId(id);

			return ResponseFactory.success(HttpStatus.OK, "Success", "Create UserGroup success");
		} catch (Exception e) {
			return ResponseFactory.success(HttpStatus.BAD_REQUEST, e.getMessage(), "Create Corporate User fail");
		}
	}

	@PutMapping("/user/forgetPasswordUserCorporate")
	public ResponseEntity<BaseResponse<String>> forgetPasswordUserCorporate(@RequestBody PasswordRequest request) {

		String userCode = request.getUserCode();
		String resChangePass = corporateUserService.forgetPasswordUserCorporate(request);

		return ResponseFactory.success(HttpStatus.OK, resChangePass, "Reset password success");
	}

}
