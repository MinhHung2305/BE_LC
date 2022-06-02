package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ward")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class Ward extends Audit<String> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ward_id")
    private Long wardId;

    @OneToOne
    @JoinColumn(name = "district_id")
    private District district;

    @Column(name = "ward_name")
    private String wardName;

    @Column(name = "ward_description")
    private String wardDescription;

    @Column(name = "ward_status")
    private Integer wardStatus;
}