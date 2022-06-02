package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "standard_swift")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class StandardSwift extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "standard_swift_id")
    private Long standardSwiftId;

    @Column(name = "standard_swift_no")
    private String standardSwiftNo;

    @Column(name = "standard_swift_name")
    private String standardSwiftName;

    @Column(name = "mandatory")
    private Integer mandatory;

    @Column(name = "format")
    private String format;

    @Column(name = "max_length")
    private Integer maxLength;

    @Column(name = "description")
    private String description;

    @Column(name = "example")
    private String example;

    @OneToMany(mappedBy = "standardSwift", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<StandardSwiftPowerType> standardSwiftPowerType;
}
