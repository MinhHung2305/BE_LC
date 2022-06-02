package fis.com.vn.rest.request;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VerifyIdentifyRequest {
    private String anhMatTruoc;
    private String anhKhachHang;
}