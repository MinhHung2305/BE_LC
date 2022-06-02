package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "fee_transaction")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class FeeTransactionEntity extends Audit<String>{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "fee_transaction_id")
    private Long feeTransactionId;

    @Column(name = "fee_transaction_code")
    private String feeTransactionCode;

    @Column(name = "fee_transaction_name")
    private String feeTransactionName;

    @Column(name = "fee_transaction_channel")
    private String feeTransactionChannel;

    @Column(name = "fee_transaction_status")
    private Integer feeTransactionStatus;
}
