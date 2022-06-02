package fis.com.vn.rest.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ElectricTypeResponse {
    private Long id;
    private String electricType;
    private String electricName;
    private String proposal;
    private String swiftVersion;
    private LocalDateTime createdDate;
    private List<CharacterSetElectricTypeResponse> characterSet1;
    private List<CharacterSetElectricTypeResponse> characterSet2;
}
