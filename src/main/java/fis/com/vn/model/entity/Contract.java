package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "contract", indexes = {
        @Index(name = "contract_no", columnList = "contract_no", unique = true)
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class Contract extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id", nullable = false)
    private Integer contractId;

    @Column(name = "pursuant_law", length = 500)
    private String pursuantLaw;

    @Column(name = "contract_no", nullable = false, length = 20)
    private String contractNo;

    @Column(name = "contract_establishment_date", nullable = false)
    private LocalDate contractEstablishmentDate;

    @Column(name = "description_commodity", length = 500)
    private String descriptionCommodity;

    @Column(name = "contract_value_before_vat")
    private BigDecimal contractValueBeforeVat;

    @Column(name = "contract_vat")
    private Integer contractVat;

    @Column(name = "contract_value")
    private BigDecimal contractValue;

    @Column(name = "currency", length = 20)
    private String currency;

    @Column(name = "amount_reduction_tolerance", length = 2)
    private String amountReductionTolerance;

    @Column(name = "tolerance_increase_amount", length = 2)
    private String toleranceIncreaseAmount;

    @Column(name = "delivery_vehicle", length = 500)
    private String deliveryVehicle;

    @Column(name = "delivery_term", length = 300)
    private String deliveryTerm;

    @Column(name = "delivery_deadline")
    private LocalDate deliveryDeadline;

    @Column(name = "place_delivery", length = 100)
    private String placeDelivery;

    @Column(name = "delivery_location", length = 100)
    private String deliveryLocation;

    @Column(name = "product_quality", length = 300)
    private String productQuality;

    @Column(name = "terms_of_exchange", length = 300)
    private String termsOfExchange;

    @Column(name = "goods_warranty", length = 300)
    private String goodsWarranty;

    @Column(name = "payment_methods")
    private Integer paymentMethods;

    @Column(name = "transfer_payments", length = 100)
    private String transferPayments;

    @Column(name = "lc_payment", length = 100)
    private String lcPayment;

    @Column(name = "payment_term_lc", length = 100)
    private String paymentTermLc;

    @Column(name = "late_payment_interest_rate")
    private Integer latePaymentInterestRate;

    @Column(name = "cargo_insurance", length = 500)
    private String cargoInsurance;

    @Column(name = "obligations_buyer", length = 1000)
    private String obligationsBuyer;

    @Column(name = "obligations_seller", length = 1000)
    private String obligationsSeller;

    @Column(name = "regulations_penalties_and_contract_compensation", length = 1000)
    private String regulationsPenaltiesAndContractCompensation;

    @Column(name = "dispute_settlement_procedures", length = 1000)
    private String disputeSettlementProcedures;

    @Column(name = "case_of_force_majeure", length = 1000)
    private String caseOfForceMajeure;

    @Column(name = "validity_contract", length = 1000)
    private String validityContract;

    @Column(name = "general_terms", length = 1000)
    private String generalTerms;

    @Column(name = "representative_seller")
    private Integer representativeSeller;

    @Column(name = "representative_buyer")
    private Integer representativeBuyer;

    /*
        1 : Waiting for buyer's digital signature.
        2 : Refuse to digitally sign the buyer.
        3 : Wait for confirmation.
        4 : Refused to confirm.
        5 : Waiting for the seller's digital signature.
        6 : Refuse to sign the seller's number.
        7 : Signed digital signature.
     */
    @Column(name = "status")
    private Integer status;

    @Column(name = "buyer_digital_signing_date")
    private LocalDateTime buyerDigitalSigningDate;

    @Column(name = "seller_confirmation_date")
    private LocalDateTime sellerConfirmationDate;

    @Column(name = "seller_digital_signing_date")
    private LocalDateTime sellerDigitalSigningDate;

    @Column(name = "reasons_for_refusing_the_buyer")
    private String reasonsForRefusingTheBuyer;

    @Column(name = "reasons_for_refusing_the_seller")
    private String reasonsForRefusingTheSeller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "lc_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private LcClassify lc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "fee_rules_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FeeRules feeRules;

    @Column(name = "proposal_release_lc_id")
    private Integer proposalReleaseLcId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "seller_corporate_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Corporate sellerCorporate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "buyer_corporate_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Corporate buyerCorporate;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "commodity_id")
    private Commodity commodity;

    @OneToMany(mappedBy = "contract", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ContractAddendum> contractAddendum;

    @Column(name = "bank_account_id")
    private Integer bankAccountId;

    @Column(name = "url_minio")
    private String urlMinio;

    @Column(name = "contract_code")
    private String contractCode;

    @Column(name = "base64_file")
    private String base64File;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "buyer_digital_signature")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserInfoEntity buyerDigitalSignature;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "seller_verifier")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserInfoEntity sellerVerifier;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "seller_digital_signature")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserInfoEntity sellerDigitalSignature;

    @Column(name = "seller_address")
    private String sellerAddress;

    @Column(name = "buyer_address")
    private String buyerAddress;

}