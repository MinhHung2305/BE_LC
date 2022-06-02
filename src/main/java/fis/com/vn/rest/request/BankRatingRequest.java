package fis.com.vn.rest.request;


import fis.com.vn.model.entity.FeeRules;
import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankRatingRequest {
    private Long id;
    private String rating;
    private Long feeAmount;
    private FeeRules feeRules;
    private Integer status;
}
