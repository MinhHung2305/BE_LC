package fis.com.vn.rest.bank;

import fis.com.vn.rest.request.BankUserRequest;
import fis.com.vn.rest.response.BankUserResponse;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.service.bank.BankManageAuthenticationService;
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
@RequestMapping("/bank")
@Slf4j
@Api(tags = "Bank Manage Authentication Controller")
public class BankManageAuthenticationController {
    @Autowired
    BankManageAuthenticationService bankManageAuthenticationService;

    /**
     * List Data Manage Authentication.
     *
     * @return ResponseEntity<BaseResponse < List < BankUserResponse>>>
     */
    @GetMapping("/user/getManageAuthAll")
    @Secured({"ROLE_view_bank_user_management"})
    public ResponseEntity<BaseResponse<List<BankUserResponse>>> getManageAuthenticationAll() {
        List<BankUserResponse> userInfoList = bankManageAuthenticationService.getManageAuthenticationAll();
        return ResponseFactory.success(HttpStatus.OK, userInfoList, "Get List Data Manage Authentication Success");
    }

    /**
     * View Record Details Manage Authentication.
     *
     * @param id : id for Bank Manage Authentication.
     * @return : ResponseEntity<BaseResponse<List<BankUserResponse>>>
     */
    @GetMapping("/user/getAuthentication/{id}")
    @Secured({"ROLE_view_bank_user_management"})
    public ResponseEntity<BaseResponse<BankUserResponse>> getBankAuthenticationUser(@PathVariable String id) {
        BankUserResponse userInfoList = bankManageAuthenticationService.getBankAuthenticationUser(id);
        return ResponseFactory.success(HttpStatus.OK, userInfoList, "Get Record Details Manage Authentication Success");
    }

    /**
     * Update Bank Manage Authentication.
     *
     * @param userRequest : Request from User.
     * @param id          : id for Bank Manage Authentication.
     * @return : ResponseEntity<BaseResponse<List<BankUserResponse>>>
     */
    @PutMapping("/user/updateAuthentication/{id}")
    @Secured({"ROLE_edit_bank_user_management"})
    public ResponseEntity<BaseResponse<String>> updateBankManageAuthentication(@RequestBody BankUserRequest userRequest, @PathVariable String id) {
        try {
            bankManageAuthenticationService.updateUser(userRequest);
            return ResponseFactory.success(HttpStatus.OK, "Success", "Get Record Details Manage Authentication Success");
        } catch (Exception e) {
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Get BankUser fail");
        }
    }

    /**
     * Delete Authentication for id.
     *
     * @param id : id for Bank Manage Authentication.
     * @return : ResponseEntity<BaseResponse<List<BankUserResponse>>>
     */
    @DeleteMapping("/user/deleteAuthentication/{id}")
    @Secured({"ROLE_delete_bank_user_management"})
    public ResponseEntity<BaseResponse<String>> deleteCorporateUser(@PathVariable String id) {
        try {
            bankManageAuthenticationService.deleteUser(id);
            return ResponseFactory.success(HttpStatus.OK, "Success", "Delete BankUser success");
        } catch (Exception e) {
            return ResponseFactory.success(HttpStatus.BAD_REQUEST, e.getMessage(), "Delete BankUser fail");
        }
    }
}
