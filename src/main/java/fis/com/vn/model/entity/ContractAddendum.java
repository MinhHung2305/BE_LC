package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "contract_addendum")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class ContractAddendum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="addendum_no")
    private Long addendumNo;

    @Column(name="addendum_name")
    private String addendumName;

    @Column(name="addendum_content")
    private String addendumContent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "contract_id")
    private Contract contract;
}
