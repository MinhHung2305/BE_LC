package fis.com.vn.rest.bank;

import fis.com.vn.model.entity.UserGroupEntity;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.UserGroupResponse;
import fis.com.vn.service.common.UserGroupService;
import fis.com.vn.util.Constant;
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
@Api(tags = "Bank Corporate Accounts")
public class BankCorporateAccountsController {
    @Autowired
    UserGroupService userGroupService;

    /**
     * get All User Group.
     *
     * @return ResponseEntity<BaseResponse<List<UserGroupEntity>>>
     */
    @GetMapping("/group/co/getUserGroups")
    @Secured({"ROLE_view_corporate_group_management"})
    public ResponseEntity<BaseResponse<List<UserGroupEntity>>> getUserGroups() {
        List<UserGroupEntity> userGroupEntities = userGroupService.getByUserType(Constant.USER_TYPE_CORPORATE);
        if (userGroupEntities != null) {
            log.info("Get: " + userGroupEntities);
            return ResponseFactory.success(HttpStatus.OK, userGroupEntities, "Get User Group By Id success");
        } else {
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Cannot find UserGroup");
        }
    }

    /**
     * get User Group.
     *
     * @param id : id Group.
     * @return : ResponseEntity<BaseResponse<UserGroupResponse>>
     */
    @GetMapping("/group/co/getUserGroupById")
    @Secured({"ROLE_view_corporate_group_management"})
    public ResponseEntity<BaseResponse<UserGroupResponse>> getCorporateUserGroupById(@PathVariable Long id) {
        UserGroupResponse userGroupRes = null;
        userGroupRes = userGroupService.findUserGroupById(id);
        if (userGroupRes != null) {
            log.error("Get: " + userGroupRes);
            return ResponseFactory.success(HttpStatus.OK, userGroupRes, "Get User Group By Id success");
        } else {
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Cannot find UserGroup");
        }
    }
}
