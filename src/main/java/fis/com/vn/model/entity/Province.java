package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "province")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class Province extends Audit<String> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "province_id")
    private Long provinceId;

    @OneToOne
    @JoinColumn(name = "national_id")
    private National national;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "province_description")
    private String provinceDescription;

    @Column(name = "province_status")
    private Integer provinceStatus;
}