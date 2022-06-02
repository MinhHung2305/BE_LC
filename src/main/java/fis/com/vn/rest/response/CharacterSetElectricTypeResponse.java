package fis.com.vn.rest.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterSetElectricTypeResponse {
    private Long id;
    private String fieldNumber;
    private String fieldName;
    private String format;
    private String maxLength;
    private String description;
    private String sample;
    private Integer type;
    private Boolean obligatory;
}
