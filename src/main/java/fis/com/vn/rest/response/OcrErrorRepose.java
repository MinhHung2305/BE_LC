package fis.com.vn.rest.response;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OcrErrorRepose {
    Integer status;
    Integer codeMesage;
    String message;
}
