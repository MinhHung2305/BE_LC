package fis.com.vn.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(value= {"handler","hibernateLazyInitializer","FieldHandler"})
public class OcrCorporateBusinessRequest {
    private String code;
    private File file;
    private String tocDoOcr;
}
