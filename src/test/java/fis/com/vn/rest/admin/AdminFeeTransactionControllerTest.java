package fis.com.vn.rest.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.FeeTransactionEntity;
import fis.com.vn.service.admin.AdminFeeTransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("local")
class AdminFeeTransactionControllerTest {
    @Autowired
    AdminFeeTransactionService adminFeeTransactionService;

    @Test
    void getAlListOfFeeTransaction() throws LCPlatformException {
        List<FeeTransactionEntity> feeTransactionEntityList = adminFeeTransactionService.getAllFeeTransaction();
        System.out.println(feeTransactionEntityList);
    }

    @Test
    void getDetailFeeTransaction() throws LCPlatformException{
        FeeTransactionEntity feeTransactionEntity = adminFeeTransactionService.getDetailFeeTransaction("6");
        System.out.println(feeTransactionEntity);
    }

    @Test
    void createRecordFeeTransaction() throws LCPlatformException{
        FeeTransactionEntity feeTransaction = FeeTransactionEntity.builder()
                .feeTransactionCode("100")
                .feeTransactionName("100")
                .feeTransactionStatus(1)
                .feeTransactionChannel("100")
                .build();
        FeeTransactionEntity feeTransaction1 = adminFeeTransactionService.createRecordFeeTransaction(feeTransaction);
        System.out.println(feeTransaction1);
    }

    @Test
    void updateRecordFeeTransaction() throws LCPlatformException{
    }

    @Test
    void deleteRecordFeeTransaction() throws LCPlatformException{
    }
}