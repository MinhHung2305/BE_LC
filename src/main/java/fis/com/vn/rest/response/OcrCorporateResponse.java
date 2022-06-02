package fis.com.vn.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OcrCorporateResponse {
    private OcrCorporateSignatureResponse[] ocrCorporateSignatureResponse;
    private OcrCorporateBussinessResponse ocrCorporateBussinessResponse;
}
