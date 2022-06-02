package fis.com.vn.repository;

import fis.com.vn.model.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    @Query(nativeQuery = true, value = "select l.* from license l join contract_license cl ON cl.license_id = l.license_id where cl.application_opeing_lc_id = :applicationOpeningLcId")
    List<License> getListLicense(@Param("applicationOpeningLcId") Long applicationOpeningLcId);

    @Query("Select l From License l JOIN ContractLicense cl ON l.licenseId = cl.licenseId JOIN Contract c ON c.contractId = cl.contractId Where c.contractId = :contractId")
    List<License> getListLicenseByContract(@Param("contractId") Integer contractId);


    @Query("Select l From License l Where l.licenseName = :licenseName")
    List<License> getLicenseByLicenseName(@Param("licenseName") String licenseName);
}
