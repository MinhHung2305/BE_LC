package fis.com.vn.service.corporate.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.repository.*;
import fis.com.vn.rest.request.ContractRequest;
import fis.com.vn.rest.response.ContractResponse;
import fis.com.vn.rest.response.TransactionCodeResponse;
import fis.com.vn.service.corporate.CorporateContractService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class CorporateContractServiceImplTest {
    @Mock
    CorporateContractService corporateContractService;

    @Mock
    UserInfoRepository userInfoRepository;

    @Autowired
    ContractAddendumRepository contractAddendumRepository;

    @Autowired
    LicenseRepository licenseRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CorporateAccountRepository corporateAccountRepository;

    @Test
    void getAllContract() throws LCPlatformException {
        corporateContractService.getAllContract();
        System.out.println("Test");
    }

    @Test
    void changeStateContract() throws LCPlatformException, SendEmailException {
        ContractRequest contractRequest = ContractRequest.builder()
                .contractId(64)
                .status(1)
                .build();
        corporateContractService.changeStateContract(contractRequest);
        System.out.println(contractRequest);
    }

    @Test
    void getContractById() throws LCPlatformException{
        ContractResponse contractResponse = corporateContractService.getContractById(58);
        System.out.println(contractResponse);
    }

    @Test
    void deleteContractById() throws LCPlatformException {
        corporateContractService.deleteContractById(86);
    }

    @Test
    void generatePdfFile() throws LCPlatformException {
        ContractRequest contractRequest = ContractRequest.builder()
                .contractId(100)
                .amountReductionTolerance("ab")
                .buyerCorporateId(348)
                .cargoInsurance("Bao hiem hang hoa UPDATE")
                .caseOfForceMajeure("Truong hop bat kha khang")
                .commodityId(1)
                .contractEstablishmentDate(LocalDate.now())
                .contractNo("00000JQL")
                .contractValue(BigDecimal.valueOf(100))
                .contractVat(10)
                .currency("VND")
                .deliveryDeadline(LocalDate.now())
                .deliveryLocation("Dia diem nhan hang")
                .deliveryTerm("Thoi han giao hang")
                .deliveryVehicle("ab")
                .descriptionCommodity("Mo ta hang hoa")
                .disputeSettlementProcedures("Thu tuc tranh chap hop dong")
                .generalTerms("Dieu khoan chung")
                .goodsWarranty("Bao hanh hang hoa")
                .latePaymentInterestRate(10)
                .lcId(1)
                .lcPayment("lcPayment")
                .listLicence(licenseRepository.findAll())
                .products(productRepository.findAll())
                .contractAddendum(contractAddendumRepository.findAll())
                .bankAccountId(4)
                .sellerCorporateId(322)
                .representativeBuyer(537)
                .representativeSeller(533)
                .paymentMethods(3)
                .pursuantLaw("Test")
                .toleranceIncreaseAmount("Test")
                .placeDelivery("Test")
                .transferPayments("Test")
                .goodsWarranty("Test")
                .paymentTermLc("Test")
                .productQuality("productQuality")
                .termsOfExchange("termsOfExchange")
                .goodsWarranty("goodsWarranty")
                .paymentTermLc("paymentTermLc")
                .lcPayment("lcPayment")
                .latePaymentInterestRate(1)
                .cargoInsurance("cargoInsurance")
                .obligationsBuyer("obligationsBuyer")
                .obligationsSeller("obligationsSeller")
                .regulationsPenaltiesAndContractCompensation("regulationsPenaltiesAndContractCompensation")
                .disputeSettlementProcedures("disputeSettlementProcedures")
                .caseOfForceMajeure("caseOfForceMajeure")
                .validityContract("validityContract")
                .generalTerms("generalTerms")
                .currency("currency")
                .termsOfExchange("termsOfExchange")
                .build();

        String templateName = "Test.pdf";
        corporateContractService.generatePdfFile(templateName, contractRequest);
    }

    @Test
    void signInSignature() throws LCPlatformException{
        String userId = "322";
        Integer contractId = 58;
        String signPosition = "ĐẠI DIỆN BÊN MUA";
        TransactionCodeResponse transactionCodeResponse = corporateContractService.signInSignature(userId,contractId,signPosition);
    }
}