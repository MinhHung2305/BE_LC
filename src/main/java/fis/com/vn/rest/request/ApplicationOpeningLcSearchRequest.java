package fis.com.vn.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationOpeningLcSearchRequest {
    private Long bankId;
    private String proposalCodeRelease;
    private Integer status;
    private String timeFrom;
    private String timeTo;
    private Long corporateSellId;
    private BigInteger valueLcFrom;
    private BigInteger valueLcTo;
}
