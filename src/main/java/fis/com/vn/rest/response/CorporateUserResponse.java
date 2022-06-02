package fis.com.vn.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorporateUserResponse {
	
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    
	private Long id;
	private Long corporateId;
	private String corporateCode;
	private String corporateName;
	private String userId;
	private String userCode;
	private String userName;
	private String userType;
	private String email;
	private String phoneNumber;
	private Integer userStatus;
	private String userGroupCode;
	private String userGroupName;
	private String channelInit;
	private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
	private String password;

	@JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
	private LocalDate dateOfBirth;
	
}
