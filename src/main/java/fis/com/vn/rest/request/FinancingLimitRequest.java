package fis.com.vn.rest.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class FinancingLimitRequest {

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    
    private Long id;

    private String financingLimitCode;

    @NotBlank(message = "{typeLimit.invalid}")
    private String typeLimit;
    
    private String contractNumberLimit;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    @NotBlank(message = "{dateRange.invalid}")
    private Date dateRange;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    @NotBlank(message = "{expirationDate.invalid}")
    private LocalDate expirationDate;

    @NotBlank(message = "{moneyType.invalid}")
    private String moneyType;
    
    private String descriptionOfTransactions;
    
    private Boolean requestARefund;

    private Integer status;

    @NotBlank(message = "{bankInfo.invalid}")
    private Long bankId;

    @NotBlank(message = "{totalLimit.invalid}")
    private Long totalLimit;

    private Long totalZoningLimit = 0L;
    private Long totalCommitmentLimit = 0L;
    private Long totalDisbursementAmount = 0L;
    private Long totalRepaymentAmount = 0L;
    private Long availabilityLimit = 0L;

    private String dateRangeFrom;
    private String expirationDateTo;
    private String createdBy;
    private LocalDateTime createdDate;
    private String statusForSearchRealeaseBank;
}
