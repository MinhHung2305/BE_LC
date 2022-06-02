package fis.com.vn.rest.request;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractAddendumRequest {
    private Long addendumNo;
    private String addendumName;
    private String addendumContent;
}
