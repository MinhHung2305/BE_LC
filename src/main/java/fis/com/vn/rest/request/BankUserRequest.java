package fis.com.vn.rest.request;

import lombok.*;

import java.util.List;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankUserRequest {
    private String id;
    private String userId;
    private String userCode;
    private String userName;
    private String password;
    private Boolean enable;
    private String userType;
    private String bankCode;
    private String employeeCode;
    private String identityType;
    private String identityNumber;
    private String dateOfIdentity;
    private String issuedByIdentity;
    private String branch;
    private String department;
    private String email;
    private String phoneNumber;
    private String authentication;
    private Integer userStatus;
    private String channelInit;
    private List<String> userGroupCodes;
}
