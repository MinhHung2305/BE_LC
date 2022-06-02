package fis.com.vn.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "package_service")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class PackageService extends Audit<String> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "package_service_id")
    private Long package_service_id;

    @Column(name = "package_code")
    private String package_code;

    @Column(name = "package_name")
    private String package_name;

    @Column(name = "package_frequency")
    private String package_frequency;

    @Column(name = "package_apply_date")
    private LocalDate package_apply_date;

    @Column(name = "package_description")
    private String package_description;

    @Column(name = "package_status")
    private Integer package_status;
    
    @JoinColumn(name = "fee_method_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private FeeMethod feeMethod;
}
