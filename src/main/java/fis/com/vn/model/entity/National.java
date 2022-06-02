package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "national")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class National extends Audit<String> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "national_id")
    private Long nationalId;

    @Column(name = "national_name")
    private String nationalName;

    @Column(name = "national_description")
    private String nationalDescription;

    @Column(name = "national_status")
    private Integer nationalStatus;
}