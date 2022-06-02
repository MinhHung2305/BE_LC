package fis.com.vn.rest.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LicenseCustomResponse {
    private Long licenseId;
    private String licenseName;
    private String description;
}
