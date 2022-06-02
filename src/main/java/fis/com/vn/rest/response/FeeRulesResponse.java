package fis.com.vn.rest.response;

import fis.com.vn.model.entity.FullProgressionLc;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeeRulesResponse {
    private Long feeRulesId;
    private Long feeId;
    private String feeCode;
    private String feeName;
    private Long feeTransactionId;
    private String feeTransactionCode;
    private String feeTransactionName;
    private String feeRulesCode;
    private String feeRulesName;
    private String feeProcedure;
    private String feeFrequency;
    private String feeRulesBase;
    private String feeRulesBaseValue;
    private LocalDateTime feeRulesApplyDateFrom;
    private LocalDateTime feeRulesApplyDateTo;
    private String feeRulesDescription;
    private String feeRulesMethod;
    private String feeRulesRule;
    private Integer feeRulesValue;
    private Integer feeRulesRate;
    private Integer feeRulesMinValue;
    private Integer feeRulesMaxValue;
    private Boolean feeRulesTaxVat;
    private Integer feeRulesVat;

    private String feeRulesCurrency;
    private Integer feeStatus;

    private List<FullProgressionLc> fullProgressionLcList;
    private List<FeeRuleBankRatingResponse> listFeeRuleBankRating;

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
