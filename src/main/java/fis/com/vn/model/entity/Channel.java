package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Table(name = "channel")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class Channel extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id", nullable = false)
    private Long id;

    @Column(name = "channel_class")
    private String channelClass;

    @Column(name = "channel_code")
    private String channelCode;

    @Column(name = "channel_name")
    private String channelName;

    @Column(name = "channel_type")
    private String channelType;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;


}