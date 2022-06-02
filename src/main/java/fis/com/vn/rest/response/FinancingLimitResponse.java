package fis.com.vn.rest.response;

import fis.com.vn.model.entity.BankInfo;
import fis.com.vn.model.entity.FinancingLimitChangeHistory;
import fis.com.vn.model.entity.FinancingLimitTransaction;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class FinancingLimitResponse {
    private Long id;

    private String financingLimitCode;

    private String typeLimit;

    private String contractNumberLimit;

    private Date dateRange;

    private Date expirationDate;

    private String moneyType;

    private String descriptionOfTransactions;

    private Boolean requestARefund;

    private Long totalZoningLimit;

    private Long totalCommitmentLimit;

    private Long totalDisbursementAmount;

    private Long totalRepaymentAmount;

    private Long availabilityLimit;

    private BankInfo bankInfo;

    private Integer status;

    private Long totalLimit;

    private BankInfo sponsorBank;

    private String reasonForRefusal;

    private String reasonForChangeFinancingLimit;

    private List<FinancingLimitChangeHistory> financingLimitChangeHistoryList;

    private List<FinancingLimitTransaction> financingLimitTransactionList;
}
