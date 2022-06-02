package fis.com.vn.rest.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LcClassifyResponse {
    private Integer id;
    private String lcName;
    private String lcType;
    private String lcStatus;
}
