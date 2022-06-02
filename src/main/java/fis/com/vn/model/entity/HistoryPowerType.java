package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "history_power_type")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class HistoryPowerType extends Audit<String>{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="hostility")
    private String hostility;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "bank_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private BankInfo bankInfo;

    @Column(name="status")
    private String status;

    @Column(name="reason_for_refusal")
    private String reasonForRefusal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "power_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PowerType powerType;

}
