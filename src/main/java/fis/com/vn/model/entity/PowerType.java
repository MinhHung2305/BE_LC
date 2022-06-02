package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "power_type")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class PowerType extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "power_type")
    private String powerType;

    @Column(name = "power_name")
    private String powerName;

    @Column(name = "purpose_use")
    private String purposeUse;

    @Column(name = "version")
    private String version;
    // Am01.10.03
    @Column(name = "power_code_fpt")
    private String powerCodeFpt;

    @Column(name = "sender_reference_number")
    private String senderReferenceNumber;

    @Column(name = "recipient_reference_number")
    private String recipientReferenceNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "sending_bank")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private BankInfo sendingBank;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiving_bank", referencedColumnName = "bank_id")
    private BankInfo receivingBank;

    @Column(name="information")
    private String information;

    @OneToMany(mappedBy = "powerType", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<HistoryPowerType> historyPowerTypes;

    @OneToMany(mappedBy = "powerType", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<StandardSwiftPowerType> standardSwiftPowerType;
}
