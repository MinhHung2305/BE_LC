package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "corporate_account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class CorporateAccount extends Audit<String> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "corporate_account_id")
    private Long corporateAccountId;

    @JoinColumn(name = "corporate_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Corporate corporate;

    @JoinColumn(name = "bank_id")
    @OneToOne(fetch = FetchType.LAZY)
    private BankInfo bank;

    @Column(name = "corporate_account_number")
    private String corporateAccountNumber;

    @Column(name = "corporate_account_name")
    private String corporateAccountName;

    @Column(name = "corporate_account_type")
    private String corporateAccountType;

    @Column(name = "corporate_account_status")
    private Integer corporateAccountStatus;

}
