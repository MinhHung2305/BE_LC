package fis.com.vn.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "package_service_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class PackageServiceInfo extends Audit<String> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "package_service_info_id")
    private Long packageServiceInfoId;

    @ManyToOne
    @JoinColumn(name = "package_service_id")
    private PackageService packageService;

    @ManyToOne
    @JoinColumn(name = "corporate_id")
    private Corporate corporate;

    @Column(name = "package_service_info_description")
    private String packageServiceInfoDescription;

    @Column(name = "package_service_info_status")
    private Integer packageServiceInfoStatus;


}
