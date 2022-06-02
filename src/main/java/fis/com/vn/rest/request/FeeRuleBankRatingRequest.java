package fis.com.vn.rest.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeeRuleBankRatingRequest {
    private Long id;
    private Long feeAmount;
    private Long feeRulesId;
    private Long bankRatingId;
}
