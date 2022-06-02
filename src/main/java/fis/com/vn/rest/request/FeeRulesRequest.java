package fis.com.vn.rest.request;

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
public class FeeRulesRequest {
    private Long feeRulesId;
    private Long feeId;
    private Long feeTransactionId;
    private String feeRulesCode;
    private String feeRulesName;
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

    private List<FullProgressionLc> fullProgressionLcList;
    private List<FeeRuleBankRatingRequest> listFeeRuleBankRating;

    private String feeRulesCurrency;
    private Integer feeStatus;
}
