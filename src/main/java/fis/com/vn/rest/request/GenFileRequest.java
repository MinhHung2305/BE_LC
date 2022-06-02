package fis.com.vn.rest.request;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenFileRequest {
    private Integer contractId;
    private Long applicationOpeningLcId;
    private String fileName;
    private String urlFile ;
    private String base64;
}
