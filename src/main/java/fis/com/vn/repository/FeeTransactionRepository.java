package fis.com.vn.repository;

import fis.com.vn.model.entity.FeeTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeTransactionRepository extends JpaRepository<FeeTransactionEntity, Long> {
    @Query("SELECT ft FROM FeeTransactionEntity ft order by ft.lastModifiedDate desc ")
    List<FeeTransactionEntity> findAllFeeTransaction();

    @Query("Select ft From FeeTransactionEntity ft Where ft.feeTransactionId = :feeTransactionId")
    FeeTransactionEntity getDetailFeeTransaction(@Param("feeTransactionId") Long feeTransactionId);
}
