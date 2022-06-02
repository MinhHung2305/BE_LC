package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserInfoEntity extends Audit<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_code")
	private String userCode;
	
	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_type")
	private String userType;

	@Column(name = "bank_code")
	private String bankCode;

	@Column(name = "employee_code")
	private String employeeCode;

	@Column(name = "identity_type")
	private String identityType;

	@Column(name = "identity_number")
	private String identityNumber;

	@Column(name = "date_of_identity")
	private String dateOfIdentity;

	@Column(name = "issued_by_identity")
	private String issuedByIdentity;

	@Column(name = "department")
	private String department;

	@Column(name = "branch")
	private String branch;
	
	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "authentication")
	private String authentication;

	@Column(name = "user_status")
	private Integer userStatus;

	@Column(name = "channel_init")
	private String channelInit;

	@Column(name = "position")
	private String position;

	@Column(name = "image_front_of_identity")
	private String imageFrontOfIdentity;

	@Column(name = "image_back_of_identity")
	private String imageBackOfIdentity;

	@Column(name = "image_portrait_of_identity")
	private String imagePortraitOfIdentity;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@ManyToMany
	@JoinTable(name = "userinfo_usergroup", joinColumns = @JoinColumn(name = "userinfo_id"), inverseJoinColumns = @JoinColumn(name = "usergroup_id"))
	@JsonIgnore
	private List<UserGroupEntity> userGroupEntitys;

    @JoinColumn(name = "corporate_id")
	@ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
	private Corporate corporate;
	
}
