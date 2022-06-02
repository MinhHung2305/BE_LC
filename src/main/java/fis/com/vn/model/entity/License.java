package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "license")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class License extends Audit<String> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "license_id")
    private Long licenseId;
    @Basic
    @Column(name = "license_name")
    private String licenseName;
}
