package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "full_progression_lc")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class FullProgressionLc extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="full_progression_lc_code")
    private String fullProgressionLcCode;

    @Column(name = "value_to")
    private Long valueTo;

    @Column(name = "value_from")
    private Long valueFrom;

    @Column(name = "fee_amount")
    private Long feeAmount;

    @Column(name = "fee_rate")
    private float feeRate;

    @Column(name = "fee_min_value")
    private Long feeMinValue;

    @Column(name = "fee_max_value")
    private Long feeMaxValue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "fee_rules_id")
    private FeeRules feeRules;
}
