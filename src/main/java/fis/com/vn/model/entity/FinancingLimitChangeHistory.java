package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "financing_limit_change_history", schema = "public", catalog = "lc")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class FinancingLimitChangeHistory extends Audit<String>{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "contract_number_limit")
    private String contractNumberLimit;
    @Basic
    @Column(name = "date_range")
    private Date dateRange;
    @Basic
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @Basic
    @Column(name = "total_limit")
    private Long totalLimit;
    @Basic
    @Column(name = "request_a_refund")
    private Boolean requestARefund;

    @JoinColumn(name = "financing_limit_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private FinancingLimit financingLimit;

}
