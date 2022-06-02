package fis.com.vn.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserGroupSearchRespone {

    private Long id;
    private String userGroupCode;
    private String userGroupName;
    private String branchLevel;
	private String channels;
    private String userType;
    private String groupType;
    private String role;
    
}
