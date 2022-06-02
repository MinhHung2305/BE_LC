package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "electric_type", schema = "public", catalog = "lc")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class ElectricType extends Audit<String> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "electric_type")
    private String electricType;
    @Basic
    @Column(name = "electric_name")
    private String electricName;
    @Basic
    @Column(name = "proposal")
    private String proposal;
    @Basic
    @Column(name = "swift_version")
    private String swiftVersion;
    @OneToMany(mappedBy = "electricType",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<CharacterSetElectricType> characterSetElectricTypes;
}
