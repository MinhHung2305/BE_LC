package fis.com.vn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.util.ResponseFactory;

@RestController
@RequestMapping("/bank")
public class BankUserController {
	
	@Secured({ "ROLE_bank" })
	@GetMapping("/user")
	public ResponseEntity<BaseResponse<String>> getUser() {
		return ResponseFactory.success(HttpStatus.OK, "Bank User", "Get Bank User");
	}
}
