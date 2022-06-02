package fis.com.vn.rest.bank;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.request.BankUserRequest;
import fis.com.vn.rest.request.PasswordRequest;
import fis.com.vn.rest.response.BankUserResponse;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.service.bank.BankUserService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
@Slf4j
//@Secured({ "ROLE_corporate", "ROLE_admin" })
@Api(tags = "Bank User Controller")
public class BankAdminUserController {
    @Autowired
    BankUserService bankUserService;

    @GetMapping("/user/getAll")
//    @Secured({"ROLE_view_bank_user_management"})
    public ResponseEntity<BaseResponse<List<BankUserResponse>>> getAllCorporateUser() {
        try {
            List<BankUserResponse> userInfoList = bankUserService.getAllUserBank();
            return ResponseFactory.success(HttpStatus.OK, userInfoList, "GetAll BankUser success");
        } catch (LCPlatformException e) {
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "GetAll BankUser fail");
        }
    }

    @GetMapping("/user/get/{id}")
//    @Secured({"ROLE_view_bank_user_management"})
    public ResponseEntity<BaseResponse<BankUserResponse>> getCorporateUser(@PathVariable String id) {
        try {
            BankUserResponse userInfo = bankUserService.getBankUserById(id);
            return ResponseFactory.success(HttpStatus.OK, userInfo, "Get BankUser success");
        } catch (LCPlatformException e) {
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Get BankUser fail");
        }
    }

    @PostMapping("/user/create")
//    @Secured({"ROLE_create_bank_user_management"})
    public ResponseEntity<BaseResponse<String>> createCorporateUser(@RequestBody BankUserRequest userRequest) throws LCPlatformException {

        bankUserService.createUser(userRequest);
        return ResponseFactory.success(HttpStatus.OK, "Success", "Create BankUser success");

    }

    @PutMapping("/user/update/{id}")
//    @Secured({"ROLE_edit_bank_user_management"})
    public ResponseEntity<BaseResponse<String>> updateCorporateUser(@AuthenticationPrincipal Jwt principal, @RequestBody BankUserRequest userRequest,
                                                                    @PathVariable String id) {
        String userId = principal.getSubject();
//        try {
            userRequest.setId(id);
            bankUserService.updateUser(userId, userRequest);
            return ResponseFactory.success(HttpStatus.OK, "Success", "Update BankUser success");
//        } catch (LCPlatformException e) {
//            return ResponseFactory.success(HttpStatus.BAD_REQUEST, e.getMessage(), "Update BankUser fail");
//        }
    }

    @DeleteMapping("/user/delete/{id}")
//    @Secured({"ROLE_delete_bank_user_management"})
    public ResponseEntity<BaseResponse<String>> deleteCorporateUser(@AuthenticationPrincipal Jwt principal, @PathVariable String id) {
//        try {
            String userId = principal.getSubject();
            bankUserService.deleteUser(userId, id);

            return ResponseFactory.success(HttpStatus.OK, "Success", "Delete BankUser success");
//        } catch (Exception e) {
//            return ResponseFactory.success(HttpStatus.BAD_REQUEST, e.getMessage(), "Delete BankUser fail");
//        }
    }

    @PutMapping("/user/forgetPasswordUserBank")
    public String forgetPasswordUser(@RequestBody PasswordRequest request) {

        String userCode = request.getUserCode();
        String passwordNew = request.getPasswordNew();
        String resChangePass = bankUserService.forgetPasswordUserBank(userCode);
        return resChangePass;
    }

}
