package fis.com.vn.rest.response;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class SignDigitalResponse {
    private String data;
    private String message;
    private Long status;
}
