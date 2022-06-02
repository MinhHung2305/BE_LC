package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "fee_method")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class FeeMethod extends Audit<String> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "fee_method_id")
    private Long feeMethodId;

    @Column(name = "fee_method_code")
    private String feeMethodCode;

    @Column(name = "fee_method_name")
    private String feeName;

    @Column(name = "fee_method_description")
    private String feeChannel;

    @Column(name = "fee_method_status")
    private Integer feeMethodStatus;
}
