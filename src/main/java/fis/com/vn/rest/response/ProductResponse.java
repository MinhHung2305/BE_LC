package fis.com.vn.rest.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String commodity;
    private String origin;
    private String unit;
    private BigDecimal amount;
    private BigDecimal unitPrice;
    private BigDecimal intoMoney;

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
