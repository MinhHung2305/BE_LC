package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "application_opening_lc")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ApplicationOpeningLc extends Audit<String> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "proposal_code_release")
    private String proposalCodeRelease;
    @Basic
    @Column(name = "contract_type")
    private Integer contractType;
    @Basic
    @Column(name = "contract_code", length = 50)
    private String contractCode;
    @Basic
    @Column(name = "contract_number", length = 20)
    private String contractNumber;
    @Basic
    @Column(name = "contract_file")
    private String contractFile;
    @Basic
    @Column(name = "proposal_date")
    private Date proposalDate;
    @Basic
    @Column(name = "lc_type")
    private String lcType;

    @Basic
    @Column(name = "confirmation_instruction")
    private Integer confirmationInstruction;
    @Basic
    @Column(name = "confirming_bank_request")
    private String confirmingBankRequest;
    @Basic
    @Column(name = "money_type", length = 50)
    @NotBlank(message = "{moneyType.invalid}")
    private String moneyType;
    @Basic
    @Column(name = "value_lc")
    private BigInteger valueLc;
    @Basic
    @Column(name = "negative_tolerance")
    private Float negativeTolerance;
    @Basic
    @Column(name = "positive_tolerance")
    private Float positiveTolerance;
    @Basic
    @Column(name = "term_of_payment",length = 20)
    private Integer termOfPayment;
    @Basic
    @Column(name = "note_term_of_payment")
    private String noteTermOfPayment;
    @Basic
    @Column(name = "payment_amount", length = 255)
    private String paymentAmount;
    @Basic
    @Column(name = "due_date")
    private Date dueDate;
    @Basic
    @Column(name = "due_address", length = 255)
    private String dueAddress;
    @Basic
    @Column(name = "fee", length = 255)
    private String fee;
    @Basic
    @Column(name = "partial_shipment")
    private Boolean partialShipment;
    @Basic
    @Column(name = "transhipment")
    private Boolean transhipment;
    @Basic
    @Column(name = "place_of_receipt", length = 300)
    private String placeOfReceipt;
    @Basic
    @Column(name = "place_of_destination", length = 300)
    private String placeOfDestination;
    @Basic
    @Column(name = "port_of_departure", length = 300)
    private String portOfDeparture;
    @Basic
    @Column(name = "lastest_delivery_date", length = 300)
    private Date lastestDeliveryDate;
    @Basic
    @Column(name = "delivery_time")
    private String deliveryTime;
    @Basic
    @Column(name = "delivery_term")
    private String deliveryTerm;
    @Basic
    @Column(name = "description_of_goods")
    private String descriptionOfGoods;
    @Basic
    @Column(name = "period_for_presentation")
    private String periodForPresentation;
    @Basic
    @Column(name = "other_condition")
    private String otherCondition;
    @Basic
    @Column(name = "tt_reimbursement")
    private Boolean ttReimbursement;

    @Basic
    @Column(name = "commitment_customer")
    private String commitmentCustomer;
    @Basic
    @Column(name = "port_of_destination", length = 300)
    private String portOfDestination;
    @Basic
    @Column(name = "presentation_at")
    private String presentationAt;

    @Basic
    @Column(name = "status")
    private Integer status;

    @Basic
    @Column(name = "bank_confirm_address", length = 300)
    private String bankConfirmAddress;

    @Basic
    @Column(name = "reason_for_refusal")
    private String reasonForRefusal;

    @Basic
    @Column(name = "file_ky_so")
    private String fileKySo;

    @Basic
    @Column(name = "product_type")
    private Integer product_type;

    @Basic
    @Column(name = "filekyso_base64")
    private String fileKySoBase64;

    @Basic
    @Column(name = "corporate_sell_address")
    private String corporateSellAddress;

    @Basic
    @Column(name = "corporate_buy_address")
    private String corporateBuyAddress;

    @Basic
    @Column(name = "status_sign_digital")
    private Integer statusSignDigital;

    @Basic
    @Column(name = "total_value_product")
    private BigInteger totalValueProduct;

    @Basic
    @Column(name = "vat_product")
    private Integer vatProduct;

    @Basic
    @Column(name = "total_value_after_vat")
    private BigInteger totalValueAfterVat;

    @JoinColumn(name = "bank_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private BankInfo bankInfo;

    @JoinColumn(name = "corporate_buy_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Corporate corporateBuy;

    @JoinColumn(name = "corporate_sell_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Corporate corporateSell;

    @JoinColumn(name = "bank_confirm_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private BankInfo bankConfirm;

    @JoinColumn(name = "bank_tranfer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private BankInfo bankTranfer;

    @OneToMany(mappedBy = "applicationOpeningLc",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Product> products;

    @JoinColumn(name = "hold_account")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private CorporateAccount holdAccount;

    @JoinColumn(name = "fee_account")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private CorporateAccount feeAccount;

    @JoinColumn(name = "payment_account")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private CorporateAccount paymentAccount;

    @JoinColumn(name = "confirming_bank_id_request")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private BankInfo bankConfirmRequest;

    @JoinColumn(name = "bank_id_presentation_at")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private BankInfo bankPresentationAt;

    @Basic
    @Column(name = "corporate_create")
    private Long corporateCreate;

//    @ManyToMany
//    @JoinTable(name = "contract_license", joinColumns = @JoinColumn(name = "application_opening_lc_id"), inverseJoinColumns = @JoinColumn(name = "license_id"))
//    private List<License> licenses;

}
