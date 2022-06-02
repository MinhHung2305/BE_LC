package fis.com.vn.repository;

import fis.com.vn.model.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {
    @Query("Select f From Fee f Where f.feeChannel = :feeChannel order by f.lastModifiedDate desc ")
    List<Fee> findAllListOfBankFee(@Param("feeChannel") String feeChannel);

    @Query("Select f From Fee f Where f.feeChannel = :feeChannel and f.feeStatus = :status order by f.lastModifiedDate desc ")
    List<Fee> findAllListOfBankFee(@Param("feeChannel") String feeChannel, @Param("status") Integer status);

    @Query("Select f From Fee f Where f.feeChannel = :feeChannel and f.feeId = :feeId")
    Fee getDetailBankFee(@Param("feeId") Long feeId, @Param("feeChannel") String feeChannel);

    @Query("Select f From Fee f Where f.feeId = :feeId")
    Fee getDetailBankFee(@Param("feeId") Long feeId);
}
