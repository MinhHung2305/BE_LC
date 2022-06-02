package fis.com.vn.repository;

import fis.com.vn.model.entity.ApplicationOpeningLc;
import fis.com.vn.model.entity.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public interface ApplicationOpeningLcRepository extends JpaRepository<ApplicationOpeningLc, Long> {
    @Query(value = "select sa " +
            " from ApplicationOpeningLc sa inner join BankInfo b on b.bankId = sa.bankInfo.bankId" +
            " where 1=1 " +
            " and (:proposalCodeRelease is null or upper(sa.proposalCodeRelease) like %:proposalCodeRelease% ) " +
            " and (:status is null or sa.status = :status)" +
            " and (:bankId is null or b.bankId = :bankId)" +
            " and (:corporateSellId is null or sa.corporateSell.corporateId = :corporateSellId)" +
            " and (:valueLcFrom is null or sa.valueLc >= :valueLcFrom)" +
            " and (:valueLcTo is null or sa.valueLc <= :valueLcTo)" +
            " and (sa.corporateCreate = :corporateCreate)" +
            " and (:timeFrom is null or to_char(sa.createdDate,'YYYY-MM-DD') >= :timeFrom)" +
            " and (:timeTo is null or to_char(sa.createdDate,'YYYY-MM-DD') <= :timeTo) order by sa.id desc"
    )
   List<ApplicationOpeningLc> search(@Param("proposalCodeRelease") String proposalCodeRelease, @Param("status") Integer status,
                                     @Param("timeFrom") String timeFrom, @Param("timeTo") String timeTo, @Param("bankId") Long bankId,
                                     @Param("corporateCreate") Long corporateCreate, @Param("corporateSellId") Long corporateSellId,
                                     @Param("valueLcFrom") BigInteger valueLc, @Param("valueLcTo") BigInteger valueLcTo);


   boolean existsByProposalCodeRelease(String propoalCodeRelease);
}
