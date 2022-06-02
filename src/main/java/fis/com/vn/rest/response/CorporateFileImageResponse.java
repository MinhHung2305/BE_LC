package fis.com.vn.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CorporateFileImageResponse {
    String filePathImgFront;
    String fileUrlImgFront;

    String filePathImgBack;
    String fileUrlImgBack;

    String filePathImgPortrait;
    String fileUrlImgPortrait;
}
