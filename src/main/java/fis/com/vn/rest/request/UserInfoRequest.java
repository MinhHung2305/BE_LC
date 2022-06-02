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
public class UserInfoRequest {

    private String id;
    private String userId;
    private String userCode;
	private String userName;
	private String password;
    private String branchLevel;
//	private String firstName;
//	private String lastName;
//	private List<String> realmRoles;
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
    private String position;
    private List<String> listUserGroupCode;
    
}
