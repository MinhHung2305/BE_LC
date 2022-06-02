package fis.com.vn.repository;

import fis.com.vn.model.entity.FullProgressionLc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FullProgressionLcRepository extends JpaRepository<FullProgressionLc, Long> {
    @Query("Select fg from FullProgressionLc fg Join fg.feeRules f where f.feeRulesId = :feeRuleId")
    List<FullProgressionLc> getAllByFeeRule(@Param("feeRuleId") Long feeRuleId);
}
