package fis.com.vn.rest.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.request.FeeRulesRequest;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.FeeRulesResponse;
import fis.com.vn.service.admin.AdminFeeRulesService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
//@Secured({"", "fpt_management"})
@Api(tags = "Admin fee rules controller")
public class AdminBankFeeRulesController {
    @Autowired
    AdminFeeRulesService adminFeeRulesService;

    @GetMapping("/fee/getAllBankFeeRules")
    //@Secured({""})
    public ResponseEntity<BaseResponse<List<FeeRulesResponse>>> getAllFeeRules() throws LCPlatformException {
        List<FeeRulesResponse> feeRuleRes = adminFeeRulesService.getAllFeeRules();
        return ResponseFactory.success(HttpStatus.OK, feeRuleRes, "Get All Bank Fee Rules Success");
    }

    @GetMapping("/fee/getBankFeeRules/{id}")
    //@Secured({""})
    public ResponseEntity<BaseResponse<FeeRulesResponse>> getDetailBankFee(@PathVariable String id) throws LCPlatformException{
        FeeRulesResponse feeRules = adminFeeRulesService.getDetailFeeRules(id);
        return ResponseFactory.success(HttpStatus.OK, feeRules, "Get Record Detail Bank Fee Rules Success");
    }

    @PostMapping("/fee/createBankFeeRules")
    //@Secured({""})
    public ResponseEntity<BaseResponse<FeeRulesResponse>> createRecordBankFeeRules(@RequestBody FeeRulesRequest feeRulesReq) throws LCPlatformException{
        adminFeeRulesService.createRecordFeeRules(feeRulesReq);
        return ResponseFactory.success(HttpStatus.CREATED, null, "Create Record Bank Fee Rules Success");
    }

    @PutMapping("/fee/updateBankFeeRules")
    //@Secured({""})
    public ResponseEntity<BaseResponse<FeeRulesResponse>> updateRecordBankFeeRules(@RequestBody FeeRulesRequest feeRulesReq) throws LCPlatformException{
        adminFeeRulesService.updateRecordFeeRules(feeRulesReq);
        return ResponseFactory.success(HttpStatus.CREATED, null, "Update Record Bank Fee Rules Success");
    }

    @DeleteMapping("/fee/deleteBankFeeRules/{id}")
    //@Secured({""})
    public void deleteRecordBankFee(@PathVariable String id) throws LCPlatformException{
        adminFeeRulesService.deleteRecordFeeRules(id);
    }

}
