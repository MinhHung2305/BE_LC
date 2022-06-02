package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "fee_rules")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class FeeRules extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_rules_id")
    private Long feeRulesId;

    @NotBlank(message = "{feeRulesCode.invalid}")
    @Column(name = "fee_rules_code")
    private String feeRulesCode;

    @NotBlank(message = "{feeRulesName.invalid}")
    @Column(name = "fee_rules_name")
    private String feeRulesName;

    @Column(name = "fee_rules_currency")
    private String feeRulesCurrency;

    @Column(name = "fee_rules_base_value")
    private String feeRulesBaseValue;

    @Column(name = "fee_rules_apply_date_from")
    private LocalDateTime feeRulesApplyDateFrom;

    @Column(name = "fee_rules_apply_date_to")
    private LocalDateTime feeRulesApplyDateTo;

    @Column(name = "fee_rules_method")
    private String feeRulesMethod;

    @Column(name = "fee_rules_description")
    private String feeRulesDescription;

    @Column(name = "fee_rules_rule")
    private String feeRulesRule;

    @Column(name = "fee_rules_value")
    private Integer feeRulesValue;

    @Column(name = "fee_rules_min_value")
    private Integer feeRulesMinValue;

    @Column(name = "fee_rules_max_value")
    private Integer feeRulesMaxValue;

    @Column(name = "fee_rules_rate")
    private Integer feeRulesRate;

    @Column(name = "fee_rules_tax_vat")
    private Boolean feeRulesTaxVat;

    @Column(name = "fee_rules_vat")
    private Integer feeRulesVat;

    @Column(name = "fee_rules_status")
    private Integer feeStatus;

    @Column(name = "fee_rules_base")
    private String feeRulesBase;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fee_id")
    private Fee fee;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "package_service_id")
    private PackageService packageService;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fee_transaction_id")
    private FeeTransactionEntity feeTransactionEntity;

    @OneToMany(mappedBy = "feeRules", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FullProgressionLc> fullProgressionLcList;

    @OneToMany(mappedBy = "feeRules", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FeeRuleBankRating> listFeeRuleBankRating;
}
