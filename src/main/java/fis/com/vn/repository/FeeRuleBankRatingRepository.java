package fis.com.vn.repository;

import fis.com.vn.model.entity.FeeRuleBankRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeRuleBankRatingRepository extends JpaRepository<FeeRuleBankRating, Long> {
    @Query("Select frbt From FeeRuleBankRating frbt Join frbt.feeRules fr Where fr.feeRulesId = :feeRulesId ")
    List<FeeRuleBankRating> findAllByFeeRule(@Param("feeRulesId") Long feeRulesId);
}
