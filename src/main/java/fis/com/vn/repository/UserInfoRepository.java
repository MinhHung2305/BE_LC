package fis.com.vn.repository;

import fis.com.vn.model.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {

    UserInfoEntity findByEmailIgnoreCase(String email);

    Optional<UserInfoEntity> findByEmailIsIgnoreCase(String email);

    long countByEmailIsIgnoreCase(String email);

    List<UserInfoEntity> findByUserId(String userId);

    List<UserInfoEntity> findByUserCode(String userCode);

    @Query("SELECT u FROM UserInfoEntity u JOIN u.userGroupEntitys ug WHERE u.bankCode like %:bankCode% and u.userId like %:userId% and u.userName like %:userName% and "
            + " ug.channels like %:channel% and ug.branchLevel like %:branchLevel% and u.userType like %:userType% and  "
            + " ug.groupType like %:groupType% and ug.role like %:role% and u.userStatus = :userStatus")
    List<UserInfoEntity> searchUser(@Param("bankCode") String bankCode, @Param("userId") String userId, @Param("userName") String userName,
                                    @Param("userType") String userType, @Param("userStatus") String userStatus, @Param("channel") String channels,
                                    @Param("branchLevel") String branchLevel, @Param("groupType") String groupType, @Param("role") String role);

    @Query("SELECT ui FROM UserInfoEntity ui JOIN ui.corporate c WHERE c.corporateId = :corporateId order by ui.lastModifiedDate desc")
    List<UserInfoEntity> getByCorporateId(Long corporateId);

    @Query("SELECT ui FROM UserInfoEntity ui JOIN ui.corporate c WHERE c.corporateId = :corporateId order by ui.id asc")
    List<UserInfoEntity> getByCorporateIdOrderById(Long corporateId);

    @Query("SELECT ui FROM UserInfoEntity ui WHERE ui.userType = :userType order by lastModifiedDate desc")
    List<UserInfoEntity> getByUserType(String userType);

    //	@Query(value = "SELECT nextval('user_info_id_seq')",nativeQuery = true)
    @Query(value = "select max(id) from user_info", nativeQuery = true)
    Long getSequence();

    @Query("Select u From UserInfoEntity u Join Corporate c on u.corporate = c.corporateId "
            + "JOIN u.userGroupEntitys ug "
            + "Where c.corporateId = :corporateId and ug.role = :maker")
    List<UserInfoEntity> getUserInfoSendMailMaker(@Param("corporateId") Long corporateId, @Param("maker") String maker);

    @Query("Select u From UserInfoEntity u Join Corporate c on u.corporate = c.corporateId "
            + "JOIN u.userGroupEntitys ug "
            + "Where c.corporateId = :corporateId and ug.role = :checker")
    List<UserInfoEntity> getUserInfoSendMailChecker(@Param("corporateId") Long corporateId, @Param("checker") String checker);

    Optional<UserInfoEntity> findByUserCodeIsIgnoreCase(String userCode);

    boolean existsByEmail(String email);

    @Query(nativeQuery = true, value = "select * from user_info ui where ui.corporate_id = :corporateId and ui.\"position\" = :position")
    UserInfoEntity findUserAccountant(@Param("corporateId") Long corporateId, @Param("position") String position);

    @Query(nativeQuery = true, value = "select ui.* from userinfo_usergroup uu join user_info ui on ui.id = uu.userinfo_id join user_group ug on ug.id = uu.usergroup_id \n" +
            "where ug.\"role\" = 'Approver' and ui.user_type = 'BANK' and ug.bank_id = :bankId")
    List<UserInfoEntity> getApproverBank(@Param("bankId") Long bankId);
}
