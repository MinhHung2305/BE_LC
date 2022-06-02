package fis.com.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import fis.com.vn.model.entity.CorporateAccount;

@Component
public interface CorporateAccountRepository extends JpaRepository<CorporateAccount, Long> {
	
	@Query("SELECT ca FROM CorporateAccount ca JOIN ca.corporate c WHERE c.corporateId = :corporateId")
    List<CorporateAccount> findByCorporateId(@Param("corporateId") Long corporateId);
}
