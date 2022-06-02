package fis.com.vn.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user_group")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class UserGroupEntity extends Audit<String> {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;

	@Column(name = "group_id")
	private String groupId;

	@Column(name = "user_group_code")
    private String userGroupCode;
	
	@Column(name = "user_group_name")
	private String userGroupName;
	
	@Column(name = "channels")
	private String channels;
	
	@Column(name = "user_type")
	private String userType;
	
	@Column(name = "group_type")
	private String groupType;
	
	@Column(name = "branch_level")
	private String branchLevel;

	@Column(name = "branch")
	private String branch;

	@Column(name = "department")
	private String department;

	@Column(name = "role")
	private String role;
	
	@Column(name = "user_group_status")
	private int status;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "permission")
	private String permission;

	@JoinColumn(name = "bank_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private BankInfo bankInfo;
}
