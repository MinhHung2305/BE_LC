package fis.com.vn.rest.bank;

import fis.com.vn.model.entity.Corporate;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.CorporateResponse;
import fis.com.vn.service.corporate.CorporateService;
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
@Api(tags = "Bank Enterprise Customer Management")
public class BankCorporateCustomerManagementController {
    @Autowired
    CorporateService corporateService;

    /**
     * get All Corporate.
     *
     * @return ResponseEntity<BaseResponse<List<Corporate>>>
     */
    @GetMapping("/user/getAllCorporate")
    @Secured({"ROLE_view_corporate_group_management" })
    public ResponseEntity<BaseResponse<List<Corporate>>> getAllCorporateUser() {
        List<Corporate> corporateList = corporateService.findAll();
        return ResponseFactory.success(HttpStatus.OK, corporateList);
    }

    /**
     * View Record Details Corporate.
     *
     * @param id : id for Corporate.
     * @return : ResponseEntity<BaseResponse<CorporateResponse>>
     */
    @GetMapping("/user/getCorporate/{id}")
    @Secured({ "ROLE_view_corporate_group_management" })
    public ResponseEntity<BaseResponse<CorporateResponse>> getBankAuthenticationUser(@PathVariable String id) {
        CorporateResponse corporateRes = corporateService.getById(Long.parseLong(id));
        return ResponseFactory.success(HttpStatus.OK, corporateRes);
    }
}
