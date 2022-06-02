package fis.com.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fis.com.vn.model.entity.Ward;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
	
	@Query("SELECT w FROM Ward w JOIN w.district d WHERE d.districtId = :districtId")
    List<Ward> findByDistricId(@Param("districtId") Long districtId);
}
