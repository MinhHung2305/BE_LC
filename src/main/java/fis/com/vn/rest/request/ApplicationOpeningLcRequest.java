package fis.com.vn.rest.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import fis.com.vn.model.entity.License;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ApplicationOpeningLcRequest {
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private Long id;

    @NotBlank(message = "{bankInfo.invalid}")
    private Long bankId;
    private String proposalCodeRelease;

    @NotBlank(message = "{contractType.invalid}")
    private Integer contractType;
    private String contractCode;
    private String contractNumber;
    private String contractFile;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    @NotBlank(message = "{proposalDate.invalid}")
    private Date proposalDate;

    @NotBlank(message = "{lcType.invalid}")
    private String lcType;

    @NotBlank(message = "{corporateBuy.invalid}")
    private Long corporateBuyId;

    @NotBlank(message = "{corporateSell.invalid}")
    private Long corporateSellId;

    @NotBlank(message = "{bankConfirm.invalid}")
    private Long bankConfirmId;

    private Long bankTranferId;

    @NotBlank(message = "{confirmationInstruction.invalid}")
    private Integer confirmationInstruction;
    private String confirmingBankRequest;

    @NotBlank(message = "{moneyType.invalid}")
    private String moneyType;

    @NotBlank(message = "{valueLc.invalid}")
    private BigInteger valueLc;

    @NotBlank(message = "{negativeTolerance.invalid}")
    private Float negativeTolerance;

    @NotBlank(message = "{positiveTolerance.invalid}")
    private Float positiveTolerance;

    @NotBlank(message = "{termOfPayment.invalid}")
    private Integer termOfPayment;
    private String noteTermOfPayment;
    private String paymentAmount;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    @NotBlank(message = "{dueDate.invalid}")
    private Date dueDate;

    @NotBlank(message = "{dueAddress.invalid}")
    private String dueAddress;

    @NotBlank(message = "{fee.invalid}")
    private String fee;

    @NotBlank(message = "{partialShipment.invalid}")
    private Boolean partialShipment;

    @NotBlank(message = "{transhipment.invalid}")
    private Boolean transhipment;
    private String placeOfReceipt;

    private String placeOfDestination;

    private String portOfDeparture;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private Date lastestDeliveryDate;

    private String deliveryTime;

    private String deliveryTerm;

    private String descriptionOfGoods;

    private String periodForPresentation;

    private String otherCondition;

    @NotBlank(message = "{ttReimbursement.invalid}")
    private Boolean ttReimbursement;

    private Long holdAccountId;

    private Long feeAccountId;

    private Long paymentAccountId;

    private String commitmentCustomer;
    private String portOfDestination;
    private BigDecimal totalValueProduct;
    private Integer vatProduct;
    private BigDecimal totalValueAfterVat;
    private String corporateSellAddress;
    private String corporateBuyAddress;

    @NotBlank(message = "{presentationAt.invalid}")
    private String presentationAt;

    private String bankConfirmAddress;

    private String reasonForRefusal;

    private List<ProductRequest> productsRequest = new ArrayList<>();

    @NotBlank(message = "{licenses.invalid}")
    private List<License> licenses;
    private Integer product_type;
    private Long bankIdConfirmRequest;
    private Long bankIdPresentationAt;
    private String fileKySoBase64;
    private Integer status;
    private List<ContractLicenseRequest> listContractLicenseRequests = new ArrayList<>();
    private Long corporateCreate;
}
