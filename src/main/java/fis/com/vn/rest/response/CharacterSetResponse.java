package fis.com.vn.rest.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterSetResponse {
    private Long id;
    private String characterSet;
    private String applicableCharacters;
    private String note;
}
