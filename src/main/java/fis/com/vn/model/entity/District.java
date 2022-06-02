package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "district")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class District extends Audit<String> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Long districtId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province provinceId;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "district_description")
    private String districtDescription;

    @Column(name = "district_status")
    private Integer districtStatus;
}