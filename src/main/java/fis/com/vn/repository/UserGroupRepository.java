package fis.com.vn.repository;

import fis.com.vn.model.entity.UserGroupEntity;
import fis.com.vn.model.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroupEntity, Long> {

	@Query("SELECT ug FROM UserGroupEntity ug WHERE ug.userGroupCode = :userGroupCode")
	List<UserGroupEntity> findByUserGroupCode(@Param("userGroupCode") String userGroupCode);

	@Query("Delete UserGroupEntity ug where ug.userGroupCode = :usercode")
	UserGroupEntity deleteByUserGroupCode(@Param("usercode") String userGroupCode);

	@Query("SELECT ug FROM UserGroupEntity ug WHERE ug.userGroupCode like %:usercode% and ug.userGroupName like %:username% and "
			+ " ug.channels like %:channel% and ug.userType like %:userType% and  ug.groupType like %:groupType% and  ug.role like %:role% ")
	List<UserGroupEntity> searchUserGroup(@Param("usercode") String userGroupCode,
			@Param("username") String userGroupName, @Param("channel") String channel,
			@Param("userType") String userType, @Param("groupType") String groupType, @Param("role") String role);

	@Query("SELECT ug FROM UserGroupEntity ug WHERE ug.branchLevel = :branchLevel and ug.groupType = :groupType and ug.role in ('Maker','Approver')")
	List<UserGroupEntity> searchUserGroupInUser(@Param("branchLevel") String branchLevel,
			@Param("groupType") String groupType);

	@Query("SELECT ug FROM UserGroupEntity ug WHERE ug.branchLevel = :branchLevel "
			+ " and ug.groupType = :groupType and ug.role = :role")
	List<UserGroupEntity> searchUserGroupInUser(@Param("branchLevel") String branchLevels,
			@Param("groupType") String groupTypes, @Param("role") String roles);
	
	@Query("SELECT ug FROM UserGroupEntity ug WHERE ug.role = :roleSystem")
	List<UserGroupEntity> searchGroupInUserFPT(String roleSystem);

	@Query("SELECT ug FROM UserGroupEntity ug WHERE ug.userType = :userType order by lastModifiedDate desc")
	List<UserGroupEntity> getByUserType( @Param("userType")String userType);

	@Query("SELECT ug FROM UserGroupEntity ug WHERE ug.groupType = :groupType order by lastModifiedDate desc")
	List<UserGroupEntity> getByGroupType( @Param("groupType")String groupType);

	@Query(nativeQuery = true, value = "select ug.* from userinfo_usergroup uu join user_info ui on ui.id = uu.userinfo_id join user_group ug on ug.id = uu.usergroup_id \n" +
			"where ui.id = :userId")
	List<UserGroupEntity> getByUserId(@Param("userId") Long userId);
}
