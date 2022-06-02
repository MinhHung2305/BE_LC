package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "contract_license")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ContractLicense {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "license_id")
    private Long licenseId;
    @Basic
    @Column(name = "contract_id")
    private Integer contractId;
    @Basic
    @Column(name = "application_opeing_lc_id")
    private Long applicationOpeingLcId;

    @Basic
    @Column(name = "license_description")
    private String licenseDescription;
}
