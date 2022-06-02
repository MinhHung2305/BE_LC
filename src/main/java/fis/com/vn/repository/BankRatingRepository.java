package fis.com.vn.repository;

import fis.com.vn.model.entity.BankRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRatingRepository extends JpaRepository<BankRating, Long> {
//    @Query("Select br from BankRating br Join br.feeRules f where f.feeRulesId = :feeRuleId")
//    List<BankRating> getAllByFeeRule(@Param("feeRuleId") Long feeRuleId);

    @Override
    List<BankRating> findAll();
}