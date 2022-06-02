package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "character_set_electric_type", schema = "public", catalog = "lc")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class CharacterSetElectricType extends Audit<String>{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "field_number")
    private String fieldNumber;
    @Basic
    @Column(name = "field_name")
    private String fieldName;
    @Basic
    @Column(name = "format")
    private String format;
    @Basic
    @Column(name = "max_length")
    private String maxLength;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "sample")
    private String sample;
    @Basic
    @Column(name = "type")
    private Integer type;
    @Basic
    @Column(name = "obligatory")
    private Boolean obligatory;
    @JoinColumn(name = "electric_type_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ElectricType electricType;
}
