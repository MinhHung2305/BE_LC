package fis.com.vn.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import fis.com.vn.model.entity.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractResponse {
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    private Integer contractId;

    private String pursuantLaw;

    private String contractNo;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDate contractEstablishmentDate;

    private Corporate sellerCorporate;

    private Corporate buyerCorporate;

    private Integer representativeSeller;

    private Integer representativeBuyer;

    private String descriptionCommodity;

    private Commodity commodity;

    private Integer contractVat;

    private BigDecimal contractValue;

    private String currency;

    private String amountReductionTolerance;

    private String toleranceIncreaseAmount;

    private List<Product> products;

    private String deliveryVehicle;

    private String deliveryTerm;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDate deliveryDeadline;

    private String placeDelivery;

    private String deliveryLocation;

    private String productQuality;

    private String termsOfExchange;

    private String goodsWarranty;

    private Integer paymentMethods;

    private String transferPayments;

    private String lcPayment;

    private LcClassify lc;

    private String paymentTermLc;

    private Integer latePaymentInterestRate;

    private String cargoInsurance;

    private String obligationsBuyer;

    private String obligationsSeller;

    private String regulationsPenaltiesAndContractCompensation;

    private String disputeSettlementProcedures;

    private String caseOfForceMajeure;

    private String validityContract;

    private String generalTerms;

    private Integer proposalReleaseLcId;

    private String reasonsForRefusingTheSeller;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime buyerDigitalSigningDate;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime sellerConfirmationDate;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime sellerDigitalSigningDate;

    /*
        1 : Waiting for buyer's digital signature.
        2 : Refuse to digitally sign the buyer.
        3 : Wait for confirmation.
        4 : Refused to confirm.
        5 : Waiting for the seller's digital signature.
        6 : Refuse to sign the seller's number.
        7 : Signed digital signature.
     */
    private Integer status;

    private List<License> listLicence;

    private CorporateAccount corporateAccount;

    private List<ContractAddendum> contractAddendum;

    private UserInfoResponse userInfoSeller;

    private UserInfoResponse userInfoBuyer;

    private String contractCode;

    private String urlMinio;

    private String base64File;

    private UserInfoResponse buyerDigitalSignature;

    private UserInfoResponse sellerVerifier;

    private UserInfoResponse sellerDigitalSignature;

    private String sellerAddress;
    private String buyerAddress;

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    private String reasonsForRefusingTheBuyer;
    private BigDecimal contractValueBeforeVat;

    private List<ContractLicenseResponse> listContractLicenses;
}
