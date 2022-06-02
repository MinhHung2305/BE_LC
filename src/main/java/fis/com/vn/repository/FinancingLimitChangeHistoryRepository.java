package fis.com.vn.repository;

import fis.com.vn.model.entity.FinancingLimitChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancingLimitChangeHistoryRepository extends JpaRepository<FinancingLimitChangeHistory, Long> {

    List<FinancingLimitChangeHistory> findAllByFinancingLimitId(Long financingLimitId);
}
