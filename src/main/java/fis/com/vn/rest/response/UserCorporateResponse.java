package fis.com.vn.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import fis.com.vn.model.entity.UserGroupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCorporateResponse {

	private static final String DATE_FORMAT = "dd/MM/yyyy";

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
	private String position;
	private List<UserGroupEntity> userGroupEntitys;
	private String imageFrontOfIdentity;
	private String imageBackOfIdentity;
	private String imagePortraitOfIdentity;
	private String imageFrontOfIdentityUrl;
	private String imageBackOfIdentityUrl;
	private String imagePortraitOfIdentityUrl;

	@JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
	private LocalDate dateOfBirth;

}
