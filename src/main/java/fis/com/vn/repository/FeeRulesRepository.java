package fis.com.vn.repository;

import fis.com.vn.model.entity.FeeRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeRulesRepository extends JpaRepository<FeeRules, Long> {
    @Query("Select fr From FeeRules fr order by lastModifiedDate desc")
    List<FeeRules> findAllFeeRules();

    @Query("Select fr From FeeRules fr where fr.feeRulesId = :feeRulesId")
    FeeRules findByFeeRulesId(@Param("feeRulesId") Long feeRulesId);

    @Query("Select fr From FeeRules fr where fr.feeRulesCode = :feeRulesCode")
    FeeRules findByFeeRulesCode(@Param("feeRulesCode") String feeRulesCode);
}