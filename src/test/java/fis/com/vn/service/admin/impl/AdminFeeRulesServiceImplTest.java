package fis.com.vn.service.admin.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.request.FeeRulesRequest;
import fis.com.vn.rest.response.FeeRulesResponse;
import fis.com.vn.service.admin.AdminFeeRulesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@ActiveProfiles("local")
class AdminFeeRulesServiceImplTest {
    @Autowired
    AdminFeeRulesService adminFeeRulesService;

    @Test
    void getAllFeeRules() throws LCPlatformException {
        List<FeeRulesResponse> listRes = adminFeeRulesService.getAllFeeRules();
        System.out.println(listRes);
    }

    @Test
    void getDetailFeeRules() throws LCPlatformException {
        FeeRulesResponse feeRulesResponse = adminFeeRulesService.getDetailFeeRules("2");
        System.out.println(feeRulesResponse);
    }

    @Test
    void createRecordFeeRules() throws LCPlatformException{
        FeeRulesRequest feeRulesReq = FeeRulesRequest.builder()
                .feeRulesId(Long.valueOf("100"))
                .feeId((long) 1)
                .feeTransactionId((long) 1)
                .feeRulesCode("100")
                .feeRulesName("Test")
                .feeRulesBase("Xếp hạng ngân hàng")
                .feeRulesBaseValue("Test")
                .feeRulesApplyDateTo(LocalDateTime.now())
                .feeRulesDescription("Test")
                .feeRulesMethod("Test")
                .feeRulesRule("Tỷ lệ phí(%)")
                .feeRulesValue(100)
                .feeRulesRate(100)
                .feeRulesMinValue(100)
                .feeRulesMaxValue(100)
                .feeRulesTaxVat(Boolean.TRUE)
                .feeRulesVat(100)
                .build();
        adminFeeRulesService.createRecordFeeRules(feeRulesReq);
        System.out.println("ok");
    }

    @Test
    void updateRecordFeeRules() throws LCPlatformException{
        FeeRulesRequest feeRulesReq = FeeRulesRequest.builder()
                .feeRulesId(Long.valueOf("100"))
                .feeRulesBase("Test Update")
                .build();
        adminFeeRulesService.updateRecordFeeRules(feeRulesReq);
        System.out.println("ok");
    }

    @Test
    void deleteRecordFeeRules() throws LCPlatformException{
        adminFeeRulesService.deleteRecordFeeRules("9");
    }

}