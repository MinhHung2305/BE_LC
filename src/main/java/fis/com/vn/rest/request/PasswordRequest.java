package fis.com.vn.rest.request;

import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRequest {

    private String userId;
    private String userCode;
    private String passwordNew;
    private String email;
    private String phoneNumber;
}
