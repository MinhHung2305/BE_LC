package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bank_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class BankInfo extends Audit<String> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "swift_code")
    private String swiftCode;

    @Column(name = "bank_short_name")
    private String bankShortName;

    @Column(name = "bank_phone_number")
    private String bankPhoneNumber;

    @Column(name = "bank_fax_number")
    private String bankFaxNumber;

    @Column(name = "bank_email_address")
    private String bankEmailAddress;

    @Column(name = "bank_address")
    private String bankAddress;

    @Column(name = "bank_status")
    private Integer bankStatus;

}