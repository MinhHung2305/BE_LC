package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class Product extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "commodity", nullable = false, length = 200)
    private String commodity;

    @Column(name = "origin", nullable = false, length = 100)
    private String origin;

    @Column(name = "unit", nullable = false, length = 10)
    private String unit;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "into_money")
    private BigDecimal intoMoney;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @JoinColumn(name = "application_opening_lc_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ApplicationOpeningLc applicationOpeningLc;
}
