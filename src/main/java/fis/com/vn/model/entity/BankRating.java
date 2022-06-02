package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bank_rating")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class BankRating extends Audit<String> {
    //Todo Hiện tại bảng hang ngân hàng trong Am01.13.03 chưa thực hiện
    // nên đang fix các field cần sử dụng.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bank_rating_code")
    private String bankRatingCode;

    @Column(name = "rating")
    private String rating;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "bankRating", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FeeRuleBankRating> listFeeRuleBankRating;
}
