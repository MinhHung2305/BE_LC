package fis.com.vn.rest.response;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TransactionCodeResponse {
    private String maKy;
    private String agreementUUID;
    private String codeTransaction;
}
