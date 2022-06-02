package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "fee")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class Fee extends Audit<String> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "fee_id")
    private Long feeId;

    @Column(name = "fee_code")
    private String feeCode;

    @Column(name = "fee_name")
    private String feeName;

    @Column(name = "fee_channel")
    private String feeChannel;

    @Column(name = "fee_status")
    private Integer feeStatus;

    @Column(name = "fee_procedure")
    private String feeProcedure;

    @Column(name = "fee_frequency")
    private String feeFrequency;

    @Column(name = "fee_apply")
    private String feeApply;

    @Column(name = "fee_description")
    private String feeDescription;
}
