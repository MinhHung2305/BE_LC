package fis.com.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fis.com.vn.model.entity.Corporate;
import fis.com.vn.model.entity.PackageServiceInfo;

@Repository
public interface PackageServiceInfoRepository extends JpaRepository<PackageServiceInfo, Long> {
	List<PackageServiceInfo> findByCorporate(Corporate corporate);
}
