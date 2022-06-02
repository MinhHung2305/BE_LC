package fis.com.vn.model.entity;

import lombok.*;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Email {
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String htmlBody;
    private File fileAttachment;
}
