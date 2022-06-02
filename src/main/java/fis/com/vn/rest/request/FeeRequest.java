package fis.com.vn.rest.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeeRequest {
    private Long feeId;

    @NotBlank(message = "{feeCode.invalid}")
    private String feeCode;

    @NotBlank(message = "{feeName.invalid}")
    private String feeName;

    private String feeChannel;

    @NotBlank(message = "{feeProcedure.invalid}")
    private String feeProcedure;

    private String feeFrequency;

    private String feeDescription;

    private String feeApply;

    @NotBlank(message = "{feeStatus.invalid}")
    private Integer feeStatus;
}
