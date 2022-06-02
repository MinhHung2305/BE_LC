package fis.com.vn.rest.response;

import fis.com.vn.model.entity.FeeRules;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankRatingResponse {
    private Long id;
    private String rating;
    private Long feeAmount;
    private FeeRules feeRules;
    private Integer status;

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
