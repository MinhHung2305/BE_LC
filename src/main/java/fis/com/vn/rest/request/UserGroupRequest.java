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
public class UserGroupRequest {

    private Long id ;

    private int groupId;

    private String userGroupCode;

    private String userGroupName;

    private String channels;

    private String userType;

    private String groupType;

    private String branchLevel;

    private String branch;

    private String department;

    private String role;

    private int status;

    private String description;

    private String permission;

    private List<String> permissionList;

    private String bankId;
}
