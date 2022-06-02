package fis.com.vn.rest.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import fis.com.vn.model.entity.ContractAddendum;
import fis.com.vn.model.entity.ContractLicense;
import fis.com.vn.model.entity.License;
import fis.com.vn.model.entity.Product;
import lombok.*;

import javax.validation.constraints.NotBlank;
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
public class ContractRequest {
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    private Integer contractId;

    private String pursuantLaw;

    @NotBlank(message = "{contractNo.invalid}")
    private String contractNo;

    private String contractCode;

    @NotBlank(message = "{contractEstablishmentDate.invalid}")
    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDate contractEstablishmentDate;

    @NotBlank(message = "{sellerCorporateId.invalid}")
    private Integer sellerCorporateId;

    @NotBlank(message = "{representativeSeller.invalid}")
    private Integer representativeSeller;

    @NotBlank(message = "{buyerCorporateId.invalid}")
    private Integer buyerCorporateId;

    @NotBlank(message = "{representativeBuyer.invalid}")
    private Integer representativeBuyer;

    private String descriptionCommodity;

    @NotBlank(message = "{commodityId.invalid}")
    private Integer commodityId;

    @NotBlank(message = "{commodityId.invalid}")
    private Integer contractVat;

    private BigDecimal contractValue;

    @NotBlank(message = "{currency.invalid}")
    private String currency;

    private String amountReductionTolerance;

    private String toleranceIncreaseAmount;

    @NotBlank(message = "{products.invalid}")
    private List<Product> products;

    private String deliveryVehicle;

    private String deliveryTerm;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDate deliveryDeadline;

    @NotBlank(message = "{placeDelivery.invalid}")
    private String placeDelivery;

    @NotBlank(message = "{deliveryLocation.invalid}")
    private String deliveryLocation;

    private String productQuality;

    private String termsOfExchange;

    private String goodsWarranty;

    @NotBlank(message = "{paymentMethods.invalid}")
    private Integer paymentMethods;

    @NotBlank(message = "{transferPayments.invalid}")
    private String transferPayments;

    @NotBlank(message = "{lcPayment.invalid}")
    private String lcPayment;

    @NotBlank(message = "{lcId.invalid}")
    private Integer lcId;

    @NotBlank(message = "{paymentTermLc.invalid}")
    private String paymentTermLc;

    private Integer latePaymentInterestRate;

    private String cargoInsurance;

    private String obligationsBuyer;

    private String obligationsSeller;

    private String regulationsPenaltiesAndContractCompensation;

    private String disputeSettlementProcedures;

    private String caseOfForceMajeure;

    private String validityContract;

    @NotBlank(message = "{generalTerms.invalid}")
    private String generalTerms;

    private Integer proposalReleaseLcId;

    private String reasonsForRefusingTheSeller;

    @JsonFormat(pattern = DATE_TIME_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime buyerDigitalSigningDate;

    @JsonFormat(pattern = DATE_TIME_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime sellerConfirmationDate;

    @JsonFormat(pattern = DATE_TIME_FORMAT, shape = JsonFormat.Shape.STRING)
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

    private String urlMinio;

    private String base64File;

    @NotBlank(message = "{listLicence.invalid}")
    private List<License> listLicence;

    @NotBlank(message = "{bankAccountId.invalid}")
    private Integer bankAccountId;

    @NotBlank(message = "{contractAddendum.invalid}")
    private List<ContractAddendum> contractAddendum;

    private List<ContractLicense> listContractLicense;

    private UserInfoRequest buyerDigitalSignature;

    private UserInfoRequest sellerVerifier;

    private UserInfoRequest sellerDigitalSignature;

    private String sellerAddress;
    private String buyerAddress;
    private String reasonsForRefusingTheBuyer;
    private BigDecimal contractValueBeforeVat;
    private List<ContractLicenseRequest> listContractLicenseRequests;

}
