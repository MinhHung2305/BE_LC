package fis.com.vn.rest.response;

import lombok.*;

import java.io.File;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenFileResponse {
    private File file;
    private String urlFile ;
    private String base64;
    private String pathFileInMinio;
}
