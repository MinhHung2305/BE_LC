package fis.com.vn.rest.bank;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.CorporateUserResponse;
import fis.com.vn.service.common.UserGroupService;
import fis.com.vn.service.corporate.CorporateUserService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bank")
@Slf4j
@Api(tags = "Bank Corporate User Management")
public class BankCorporateUserManagementController {
    @Autowired
    CorporateUserService corporateUserService;

    @Autowired
    UserGroupService userGroupService;

    /**
     * get All Corporate User.
     *
     * @return ResponseEntity<BaseResponse < List < CorporateUserResponse>>>
     */
    @GetMapping("/user/getAllCorporateUser")
    @Secured({"ROLE_view_corporate_user_management"})
    public ResponseEntity<BaseResponse<List<CorporateUserResponse>>> getAllCorporateUser() {
        try {
            List<CorporateUserResponse> userInfoList = corporateUserService.getAllUserCorporate();
            return ResponseFactory.success(HttpStatus.OK, userInfoList, "Get All Corporate User success");
        } catch (LCPlatformException e) {
            return ResponseFactory.success(HttpStatus.CONFLICT, null, "Get All Corporate User fail");
        }
    }

    /**
     * get Corporate User.
     *
     * @param id : id for Corporate.
     * @return : ResponseEntity<BaseResponse<BankUserResponse>>
     */
    @GetMapping("/user/getCorporateUser/{id}")
    @Secured({"ROLE_view_corporate_user_management"})
    public ResponseEntity<BaseResponse<CorporateUserResponse>> getCorporateUser(@PathVariable String id) {
        try {
            CorporateUserResponse userInfo = corporateUserService.getCorporateUserById(id);
            return ResponseFactory.success(HttpStatus.OK, userInfo, "Get Corporate User success");
        } catch (LCPlatformException e) {
            return ResponseFactory.success(HttpStatus.CONFLICT, null, "Get Corporate User fail");
        }
    }
}