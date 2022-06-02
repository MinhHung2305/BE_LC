package fis.com.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fis.com.vn.model.entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
	
	@Query("SELECT p FROM Province p JOIN p.national n WHERE n.nationalId = :nationalId")
    List<Province> findByNationalId(@Param("nationalId")Long nationalId);
}
