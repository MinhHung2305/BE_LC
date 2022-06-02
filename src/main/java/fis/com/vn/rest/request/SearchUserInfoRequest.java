package fis.com.vn.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchUserInfoRequest {

	private String bankCode;
	private String userId;
	private String userName;
	private String userType;
	private String userStatus;
	private String channel;
	private String branchLevel;
	private String groupType;
	private String role;
}
