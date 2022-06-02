package fis.com.vn.rest.request;

import lombok.*;

import java.math.BigDecimal;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
    private Long id;
    private String commodity;
    private String origin;
    private String unit;
    private BigDecimal amount;
    private BigDecimal unitPrice;
    private BigDecimal intoMoney;
    private Integer contractId;
}
