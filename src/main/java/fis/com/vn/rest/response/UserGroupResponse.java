package fis.com.vn.rest.response;

import fis.com.vn.model.entity.BankInfo;
import fis.com.vn.model.enumerate.RolesInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupResponse {

    private String groupId;

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

    private String rolesDescription;

    private RoleResponse roleResponse;

    public String getRolesDescription() {
        return RolesInfo.valueOf(this.role.toUpperCase()).getDescription();
    }

    private BankInfo bankInfo;

}
