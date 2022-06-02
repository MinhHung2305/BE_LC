package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lc_classify")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class LcClassify extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lc_id", nullable = false)
    private Integer id;

    @Column(name = "lc_name", nullable = false, length = 30)
    private String lcName;

    @Column(name = "lc_type", nullable = false, length = 30)
    private String lcType;

    @Column(name = "lc_status", length = 10)
    private String lcStatus;

    @OneToMany(mappedBy = "lc", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Contract> contracts;
}