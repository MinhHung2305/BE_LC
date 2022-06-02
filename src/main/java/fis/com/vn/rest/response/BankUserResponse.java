package fis.com.vn.rest.response;

import fis.com.vn.model.entity.UserGroupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankUserResponse {


    private Long id;
    private String userId;
    private String userCode;
    private String userName;
    private String userType;
    private String bankCode;
    private String employeeCode;
    private String identityType;
    private String identityNumber;
    private String dateOfIdentity;
    private String issuedByIdentity;
    private String department;
    private String branch;
    private String email;
    private String phoneNumber;
    private String authentication;
    private Integer userStatus;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
    private List<String> channels;
    private String branchLevels;
    private String groupTypes;
    private String roles;
    private List<String> userGroupCodes;
    private List<UserGroupEntity> userGroupEntitys;
    private String channelInit;
    private Set<String> userGroupTypeList;
}
