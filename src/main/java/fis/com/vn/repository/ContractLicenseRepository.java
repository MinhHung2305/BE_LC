package fis.com.vn.repository;

import fis.com.vn.model.entity.ContractLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractLicenseRepository extends JpaRepository<ContractLicense, Long> {
    @Query("SELECT cl FROM ContractLicense cl WHERE cl.contractId = :contractId and cl.applicationOpeingLcId is null")
    List<ContractLicense> findAllByContractId(@Param("contractId") Integer contractId);

    List<ContractLicense> findAllByApplicationOpeingLcId(Long applicationOpeningLcId);
}
