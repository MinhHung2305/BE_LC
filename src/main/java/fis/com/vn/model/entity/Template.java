package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "template")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class Template extends Audit<String> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Long templateId;

    @Column(name = "template_code")
    private String templateCode;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "template_description")
    private String templateDescription;

    @Column(name = "template_subject")
    private String templateSubject;

    @Column(name = "template_content")
    private String templateContent;

    @Column(name = "template_to")
    private String templateTo;

    @Column(name = "template_cc")
    private String templateCc;

    @Column(name = "template_bcc")
    private String templateBcc;

    @Column(name = "template_status")
    private Integer templateStatus;
}