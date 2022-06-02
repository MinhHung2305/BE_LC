package fis.com.vn.repository;

import fis.com.vn.model.entity.FinancingLimit;
import fis.com.vn.model.entity.FinancingLimitTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancingLimitTransactionRepository extends JpaRepository<FinancingLimitTransaction, Long> {

    List<FinancingLimitTransaction> findAllByFinancingLimitTransaction(FinancingLimit financingLimit);
}
