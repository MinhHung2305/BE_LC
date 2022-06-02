package fis.com.vn.rest.request;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class LicenseRequest {
    private Long licenseId;
    private String licenseName;
}
