package fis.com.vn.rest.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeeResponse {
    private Long feeId;

    private String feeCode;

    private String feeName;

    private String feeChannel;

    private Integer feeStatus;

    private String feeProcedure;

    private String feeFrequency;

    private String feeApply;

    private String feeDescription;

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
