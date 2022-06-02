package fis.com.vn.rest.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.mapper.FeeResponseMapper;
import fis.com.vn.rest.request.FeeRequest;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.FeeResponse;
import fis.com.vn.service.admin.AdminListOfBankFeeService;
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
@RequestMapping("/admin")
@Slf4j
//@Secured({"ROLE_role_bank_fee", "ROLE_fpt_management"})
@Api(tags = "Admin Fee controller")
public class AdminFeeController {
    @Autowired
    AdminListOfBankFeeService adminListOfBankFeeService;
    @Autowired
    FeeResponseMapper feeResponseMapper;

    @GetMapping("/fee/getAllBankFee")
    @Secured({"ROLE_view_role_bank_fee"})
    public ResponseEntity<BaseResponse<List<FeeResponse>>> getAllListOfBankFee() throws LCPlatformException {
        List<FeeResponse> feeResponseList = adminListOfBankFeeService.getAlListOfBankFee();
        return ResponseFactory.success(HttpStatus.OK, feeResponseList, "Get All List Of Bank Fee Success");
    }

    @GetMapping("/fee/getAllBankFee/{statusActive}")
    @Secured({"ROLE_view_role_bank_fee"})
    public ResponseEntity<BaseResponse<List<FeeResponse>>> getAlListOfBankFee(@PathVariable Integer statusActive) throws LCPlatformException {
        List<FeeResponse> feeResponseList = adminListOfBankFeeService.getAlListOfBankFee(statusActive);
        return ResponseFactory.success(HttpStatus.OK, feeResponseList, "Get All List Of Bank Fee Success");
    }

    @GetMapping("/fee/getBankFee/{id}")
    @Secured({"ROLE_view_role_bank_fee"})
    public ResponseEntity<BaseResponse<FeeResponse>> getDetailBankFee(@PathVariable String id) throws LCPlatformException {
        FeeResponse feeResponse = adminListOfBankFeeService.getDetailBankFee(id);
        return ResponseFactory.success(HttpStatus.OK, feeResponse, "Get Record Detail Of Bank Fee Success");
    }

    @PostMapping("/fee/createBankFee")
    @Secured({"ROLE_create_role_bank_fee"})
    public ResponseEntity<BaseResponse<FeeResponse>> createRecordBankFee(@RequestBody FeeRequest feeRequest) throws LCPlatformException {
        FeeResponse feeResult = adminListOfBankFeeService.createRecordBankFee(feeRequest);
        if (feeResult == null) {
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Create Record Of Bank Fee Defeat");
        }
        return ResponseFactory.success(HttpStatus.CREATED, feeResult, "Create Record Of Bank Fee Success");
    }

    @PutMapping("/fee/updateBankFee")
    @Secured({"ROLE_edit_role_bank_fee"})
    public ResponseEntity<BaseResponse<FeeResponse>> updateRecordBankFee(@RequestBody FeeRequest feeRequest) throws LCPlatformException {
        FeeResponse feeResult = adminListOfBankFeeService.updateRecordBankFee(feeRequest);
        if (feeResult == null) {
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Update Record Of Bank Fee Defeat");
        }
        return ResponseFactory.success(HttpStatus.OK, feeResult, "Update Record Of Bank Fee Success");
    }

    @DeleteMapping("/fee/deleteBankFee/{id}")
    @Secured({"ROLE_delete_role_bank_fee"})
    public void deleteRecordBankFee(@PathVariable String id) throws LCPlatformException {
        adminListOfBankFeeService.deleteRecordBankFee(id);
    }

    @GetMapping("/fee/getAllCorporateFee")
    //@Secured({"ROLE_view_role_corporate_fee"})
    public ResponseEntity<BaseResponse<List<FeeResponse>>> getAllCorporateFee() throws LCPlatformException {
        List<FeeResponse> listFeeRes = adminListOfBankFeeService.getAllCorporateFee();
        return ResponseFactory.success(HttpStatus.OK, listFeeRes, "Get All List Corporate Fee Success");
    }

    @GetMapping("/fee/getCorporateFee/{id}")
    //@Secured({"ROLE_view_role_corporate_fee"})
    public ResponseEntity<BaseResponse<FeeResponse>> getDetailCorporateFee(@PathVariable String id) throws LCPlatformException {
        FeeResponse feeRes = adminListOfBankFeeService.getDetailCorporateFee(id);
        return ResponseFactory.success(HttpStatus.OK, feeRes, "Get Record Detail Of Corporate Fee Success");
    }

    @PostMapping("/fee/createCorporateFee")
    //@Secured({"ROLE_create_role_corporate_fee"})
    public ResponseEntity<BaseResponse<FeeResponse>> createRecordCorporateFee(@RequestBody FeeRequest feeReq) throws LCPlatformException {
        FeeResponse feeResResult = adminListOfBankFeeService.createRecordCorporateFee(feeReq);
        if (feeResResult == null) {
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Create Record Of Corporate Fee Defeat");
        }
        return ResponseFactory.success(HttpStatus.CREATED, feeResResult, "Create Record Of Bank Fee Success");
    }

    @PutMapping("/fee/updateCorporateFee")
    //@Secured({"ROLE_edit_role_corporate_fee"})
    public ResponseEntity<BaseResponse<FeeResponse>> updateRecordCorporateFee(@RequestBody FeeRequest feeReq) throws LCPlatformException {
        FeeResponse feeResResult = adminListOfBankFeeService.updateRecordCorporateFee(feeReq);
        if (feeResResult == null) {
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Update Record Of Bank Fee Defeat");
        }
        return ResponseFactory.success(HttpStatus.OK, feeResResult, "Update Record Of Bank Fee Success");
    }

    @DeleteMapping("/fee/deleteCorporateFee/{id}")
    //@Secured({"ROLE_delete_role_corporate_fee"})
    public void deleteRecordCorporateFee(@PathVariable String id) throws LCPlatformException {
        adminListOfBankFeeService.deleteRecordBankFee(id);
    }
}
