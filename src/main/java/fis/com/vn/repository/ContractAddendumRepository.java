package fis.com.vn.repository;

import fis.com.vn.model.entity.ContractAddendum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractAddendumRepository extends JpaRepository<ContractAddendum, Long> {

    @Query("SELECT ca FROM ContractAddendum ca JOIN ca.contract c WHERE c.contractId = :contractId")
    public List<ContractAddendum> getAllContractAddendumByContractId(@Param("contractId") Integer contractId);
}
