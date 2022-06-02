package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "financing_limit", schema = "public", catalog = "lc")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class FinancingLimit extends Audit<String>{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "type_limit")
    private String typeLimit;
    @Basic
    @Column(name = "contract_number_limit")
    private String contractNumberLimit;
    @Basic
    @Column(name = "date_range")
    private Date dateRange;
    @Basic
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @Basic
    @Column(name = "money_type")
    private String moneyType;
    @Basic
    @Column(name = "description_of_transactions")
    private String descriptionOfTransactions;
    @Basic
    @Column(name = "request_a_refund")
    private Boolean requestARefund;
    @Basic
    @Column(name = "total_zoning_limit")
    private Long totalZoningLimit = 0L;
    @Basic
    @Column(name = "total_commitment_limit")
    private Long totalCommitmentLimit = 0L;
    @Basic
    @Column(name = "total_disbursement_amount")
    private Long totalDisbursementAmount = 0L;
    @Basic
    @Column(name = "total_repayment_amount")
    private Long totalRepaymentAmount = 0L;
    @Basic
    @Column(name = "availability_limit")
    private Long availabilityLimit = 0L;

    @Basic
    @Column(name = "financing_limit_code")
    private String financingLimitCode;

    @Basic
    @Column(name = "total_limit")
    private Long totalLimit;

    @Basic
    @Column(name = "number_of_changes")
    private Long numberOfChanges;

    @Basic
    @Column(name = "status")
    private Integer status;

    @Basic
    @Column(name = "reason_for_refusal")
    private String reasonForRefusal;

    @Basic
    @Column(name = "reason_for_change_financing_limit")
    private String reasonForChangeFinancingLimit;

    @JoinColumn(name = "bank_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private BankInfo bankInfo;

    @JoinColumn(name = "sponsor_bank_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private BankInfo sponsorBank;

    @OneToMany(mappedBy = "financingLimit",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<FinancingLimitChangeHistory> financingLimitChangeHistoryList;

    @OneToMany(mappedBy = "financingLimitTransaction",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<FinancingLimitTransaction> financingLimitTransactionList = new ArrayList<>();

}
