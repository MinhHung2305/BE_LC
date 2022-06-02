package fis.com.vn.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardSwiftResponse {
    private Long standardSwiftId;
    private String standardSwiftNo;
    private String standardSwiftName;
    private Integer mandatory;
    private String format;
    private Integer maxLength;
    private String description;
    private String example;

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
