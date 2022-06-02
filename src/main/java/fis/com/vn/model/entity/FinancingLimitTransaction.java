package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "financing_limit_transaction", schema = "public", catalog = "lc")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class FinancingLimitTransaction extends Audit<String>{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "sponsor_offer_code")
    private String sponsorOfferCode;
    @Basic
    @Column(name = "lc_number")
    private String lcNumber;
    @Basic
    @Column(name = "revised_sponsorship_request_code_lc")
    private String revisedSponsorshipRequestCodeLc;
    @Basic
    @Column(name = "modification_lc_code")
    private String modificationLcCode;
    @Basic
    @Column(name = "transaction_code_presented")
    private String transactionCodePresented;
    @Basic
    @Column(name = "status_transaction_presented")
    private String statusTransactionPresented;
    @Basic
    @Column(name = "period")
    private String period;
    @Basic
    @Column(name = "financing_date")
    private Date financingDate;
    @Basic
    @Column(name = "loan_maturity_date")
    private Date loanMaturityDate;
    @Basic
    @Column(name = "zoning_limit")
    private Long zoningLimit;
    @Basic
    @Column(name = "commitment_limit")
    private Long commitmentLimit;
    @Basic
    @Column(name = "disbursement_amount")
    private Long disbursementAmount;
    @Basic
    @Column(name = "repayment_amount")
    private Long repaymentAmount;

    @JoinColumn(name = "financing_limit_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private FinancingLimit financingLimitTransaction;
}
