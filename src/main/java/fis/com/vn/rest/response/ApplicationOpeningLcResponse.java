package fis.com.vn.rest.response;

import fis.com.vn.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationOpeningLcResponse {
    private Long id;
    private BankInfo bankInfo;
    private String proposalCodeRelease;
    private Integer contractType;
    private String contractCode;
    private String contractNumber;
    private String contractFile;
    private Date proposalDate;
    private String dateOfIdentity;
    private String lcType;
    private Corporate corporateBuy;
    private Corporate corporateSell;
    private BankInfo bankConfirm;
    private BankInfo bankTranfer;
    private Integer confirmationInstruction;
    private String confirmingBankRequest;
    private String moneyType;
    private BigInteger valueLc;
    private Float negativeTolerance;
    private Float positiveTolerance;
    private Integer termOfPayment;
    private String noteTermOfPayment;
    private String paymentAmount;
    private Date dueDate;
    private String dueAddress;
    private String fee;
    private Boolean partialShipment;
    private Boolean transhipment;
    private String placeOfReceipt;

    private String placeOfDestination;

    private String portOfDeparture;

    private Date lastestDeliveryDate;

    private String deliveryTime;

    private String deliveryTerm;

    private String descriptionOfGoods;

    private String periodForPresentation;

    private String otherCondition;

    private Boolean ttReimbursement;

    private CorporateAccount holdAccount;

    private CorporateAccount feeAccount;

    private CorporateAccount paymentAccount;

    private String commitmentCustomer;

    private String portOfDestination;

    private String presentationAt;

    private String reasonForRefusal;

    private List<Product> products;

    private Integer status;

    private String bankConfirmAddress;

    private String fileKySo;

    private String urlViewFileKySo;

    private String urlViewContractFile;
    private Integer product_type;
    private BankInfo bankConfirmRequest;
    private BankInfo bankPresentationAt;
    private String fileKySoBase64;
    private Integer statusSignDigital;
    private BigInteger totalValueProduct;
    private Integer vatProduct;
    private BigInteger totalValueAfterVat;
    private String corporateSellAddress;
    private String corporateBuyAddress;
    private LocalDateTime createdDate;
    private List<ContractLicenseResponse> contractLicenseResponses;
    private List<ContractLicense> listContractLicenses;
    private List<LicenseCustomResponse> licenses;
}
