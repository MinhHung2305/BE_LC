package fis.com.vn.rest.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.FeeTransactionEntity;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.service.admin.AdminFeeTransactionService;
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
//@Secured({"role_bank_fee_transaction", "fpt_management"})
@Api(tags = "Admin fee transaction controller")
public class AdminFeeTransactionController {
    @Autowired
    AdminFeeTransactionService adminFeeTransactionService;

    @GetMapping("/fee/getAllBankFeeTransaction")
    //@Secured({"view_role_bank_fee_transaction"})
    public ResponseEntity<BaseResponse<List<FeeTransactionEntity>>> getAlListOfFeeTransaction() throws LCPlatformException {
        List<FeeTransactionEntity> feeTransactionEntityList = adminFeeTransactionService.getAllFeeTransaction();
        return ResponseFactory.success(HttpStatus.OK, feeTransactionEntityList, "Get All List Of Bank Fee Transaction Success");
    }

    @GetMapping("/fee/getBankFeeTransaction/{id}")
    //@Secured({"view_role_bank_fee_transaction"})
    public ResponseEntity<BaseResponse<FeeTransactionEntity>> getDetailFeeTransaction(@PathVariable String id) throws LCPlatformException{
        FeeTransactionEntity feeTransactionEntity = adminFeeTransactionService.getDetailFeeTransaction(id);
        return ResponseFactory.success(HttpStatus.OK, feeTransactionEntity, "Get Record Detail Of Bank Fee Transaction Success");
    }

    @PostMapping("/fee/createBankFeeTransaction")
    //@Secured({"create_role_bank_fee_transaction"})
    public ResponseEntity<BaseResponse<FeeTransactionEntity>> createRecordFeeTransaction(@RequestBody FeeTransactionEntity feeTr) throws LCPlatformException{
        FeeTransactionEntity feeTransactionEntity = null;
        // Check exception when create fee transaction.
        try{
            feeTransactionEntity = adminFeeTransactionService.createRecordFeeTransaction(feeTr);
        } catch(Exception e){
            log.error("Exception when create fee transaction " +e.getMessage());
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        if(feeTransactionEntity == null){
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Create Record Of Bank Fee Defeat");
        }
        return ResponseFactory.success(HttpStatus.CREATED, feeTransactionEntity, "Create Record Of Bank Fee Success");
    }

    @PutMapping("/fee/updateBankFeeTransaction")
    //@Secured({"edit_role_bank_fee_transaction"})
    public ResponseEntity<BaseResponse<FeeTransactionEntity>> updateRecordFeeTransaction(@RequestBody FeeTransactionEntity feeTr) throws LCPlatformException{
        FeeTransactionEntity feeTransactionEntity = adminFeeTransactionService.updateRecordFeeTransaction(feeTr);
        if(feeTransactionEntity == null){
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null, "Update Record Of Bank Fee Defeat");
        }
        return ResponseFactory.success(HttpStatus.OK, feeTransactionEntity, "Update Record Of Bank Fee Success");
    }

    @DeleteMapping("/fee/deleteBankFeeTransaction/{id}")
    //@Secured({"delete_role_bank_fee_transaction"})
    public ResponseEntity<BaseResponse<String>> deleteRecordFeeTransaction(@PathVariable String id) throws LCPlatformException{
        adminFeeTransactionService.deleteRecordFeeTransaction(id);
        return ResponseFactory.success(HttpStatus.OK, "", "Delete Record Of Transaction Fee Success");
    }
}
