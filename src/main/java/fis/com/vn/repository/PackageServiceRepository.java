package fis.com.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fis.com.vn.model.entity.PackageService;

@Repository
public interface PackageServiceRepository extends JpaRepository<PackageService, Long>{
	
	@Query("SELECT ps FROM PackageService ps JOIN ps.feeMethod fm WHERE fm.feeMethodId = :feeMethodId")
	List<PackageService> getByFeeMethodId(@Param("feeMethodId") Long feeMethodId);
}
