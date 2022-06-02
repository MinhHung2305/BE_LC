package fis.com.vn.service.bank.impl;

import fis.com.vn.exception.SendEmailException;
import fis.com.vn.rest.request.FinancingLimitRequest;
import fis.com.vn.service.bank.FinancingLimitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class FinancingLimitServiceImplTest {

    @Autowired
    private FinancingLimitService financingLimitService;

    @Test
    void create() throws SendEmailException {
        FinancingLimitRequest financingLimitRequest = new FinancingLimitRequest();
        financingLimitRequest.setContractNumberLimit("VPB1");
        financingLimitRequest.setBankId(1L);
        financingLimitRequest.setTypeLimit("1000");
        financingLimitRequest.setContractNumberLimit("001");
        financingLimitRequest.setMoneyType("VND");
        financingLimitRequest.setDescriptionOfTransactions("abc");
        financingLimitRequest.setRequestARefund(true);
        financingLimitRequest.setTotalLimit(10000L);
        System.out.println(financingLimitService.create("0104128565615", financingLimitRequest));
    }

    @Test
    void search() {
        FinancingLimitRequest financingLimitRequest = new FinancingLimitRequest();
        financingLimitRequest.setBankId(null);
        financingLimitRequest.setTypeLimit(null);
        financingLimitRequest.setMoneyType(null);
        financingLimitRequest.setFinancingLimitCode(null);
        financingLimitRequest.setTotalLimit(null);
        System.out.println(financingLimitService.search("0104128565615", financingLimitRequest));
    }

    @Test
    void view() {
        System.out.println(financingLimitService.view(5L));
    }

    @Test
    void update() throws SendEmailException {
        FinancingLimitRequest financingLimitRequest = new FinancingLimitRequest();
        financingLimitRequest.setId(1L);
        financingLimitRequest.setContractNumberLimit("VPB1");
        financingLimitRequest.setBankId(1L);
        financingLimitRequest.setTypeLimit("1000");
        financingLimitRequest.setContractNumberLimit("001");
        financingLimitRequest.setMoneyType("VND");
        financingLimitRequest.setDescriptionOfTransactions("abc");
        financingLimitRequest.setRequestARefund(true);
        financingLimitRequest.setTotalLimit(10000L);
        System.out.println(financingLimitService.update("0104128565615",financingLimitRequest));
    }

    @Test
    void releaseBankSearch() {
        FinancingLimitRequest financingLimitRequest = new FinancingLimitRequest();
        financingLimitRequest.setBankId(null);
        financingLimitRequest.setTypeLimit(null);
        financingLimitRequest.setMoneyType(null);
        financingLimitRequest.setFinancingLimitCode(null);
        financingLimitRequest.setTotalLimit(null);
        financingLimitRequest.setStatusForSearchRealeaseBank(null);
        System.out.println(financingLimitService.releaseBankSearch("0104128565615", financingLimitRequest));
    }
}