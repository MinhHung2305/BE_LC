package fis.com.vn.repository;

import fis.com.vn.model.entity.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    @Query("select r from Roles r where LOWER(roleType) in (LOWER(:roleType), 'root')")
    List<Roles> getRolesByRoleType(@Param("roleType") String roleType);

}
