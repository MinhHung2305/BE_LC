package fis.com.vn.rest.request;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OcrIdentifyRequest {
    private String anhMatTruoc;
    private String anhMatSau;
    private String maGiayTo;
}
