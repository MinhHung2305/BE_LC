package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_type", schema = "public", catalog = "lc")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class ProductType extends Audit<String>{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "product_code")
    private String productCode;
    @Basic
    @Column(name = "product_name")
    private String productName;
    @Basic
    @Column(name = "parent")
    private Long parent;
    @Basic
    @Column(name = "status")
    private Integer status;
}
