package fis.com.vn.rest.response;

import fis.com.vn.model.entity.BankRating;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FeeRuleBankRatingResponse {
    private Long id;
    private Long feeAmount;
    private BankRating bankRating;
}
