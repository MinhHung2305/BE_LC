package fis.com.vn.rest.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LicenseResponse {
    private Long licenseId;
    private String licenseName;
}
