package fis.com.vn.repository;

import fis.com.vn.model.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    @Query("Update Contract c Set c.status = :status where c.contractId = :contractId")
    List<Contract> updateStatus(@Param("status") Integer status, @Param("contractId") Integer contractId);

    @Query("Select c From Contract c where c.contractNo = :contractNo")
    List<Contract> findContractByContractNo(@Param("contractNo") String contractNo);

    Contract findByContractCode(String contractCode);

    @Query("Select c From Contract c where c.buyerCorporate.corporateId = :corporateId order by lastModifiedDate desc")
    List<Contract> findAllContractByCorporateBuyer(@Param("corporateId") Long corporateId);

    @Query("Select c From Contract c where c.sellerCorporate.corporateId = :corporateId order by lastModifiedDate desc")
    List<Contract> findAllContractByCorporateSeller(@Param("corporateId") Long corporateId);

    @Query("Select c From Contract c inner join Corporate c2 on c.buyerCorporate.corporateId = c2.corporateId where c.contractNo = :contractNo and c2.corporateId =:buyerCorporateId")
    List<Contract> findContractByContractNoAndCorporateBuyer(@Param("contractNo") String contractNo, @Param("buyerCorporateId") Long buyerCorporateId);

}
