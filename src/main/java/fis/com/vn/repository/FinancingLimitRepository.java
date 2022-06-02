package fis.com.vn.repository;

import fis.com.vn.model.entity.FinancingLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancingLimitRepository extends JpaRepository<FinancingLimit, Long> {
    @Query(value = "select sa " +
            " from FinancingLimit sa inner join BankInfo b on b.bankId = sa.bankInfo.bankId" +
            " where 1=1 " +
            " and (:financingLimitCode is null or upper(sa.financingLimitCode) like %:financingLimitCode% ) " +
            " and (:status is null or sa.status = :status)" +
            " and (:bankId is null or b.bankId = :bankId)" +
            " and (sa.sponsorBank.bankId = :sponsorBankId)" +
            " and (:typeLimit is null or upper(sa.typeLimit) like %:typeLimit% )" +
            " and (:moneyType is null or upper(sa.moneyType) like %:moneyType% )" +
            " and (:dateRangeFrom is null or to_char(sa.dateRange,'YYYY-MM-DD') >= :dateRangeFrom)" +
            " and (:expirationDateTo is null or to_char(sa.expirationDate,'YYYY-MM-DD') <= :expirationDateTo)"
    )
    List<FinancingLimit> search(@Param("financingLimitCode") String financingLimitCode, @Param("typeLimit") String typeLimit,
                                     @Param("bankId") Long bankId, @Param("moneyType") String moneyType, @Param("status") Integer status,
                                @Param("dateRangeFrom") String dateRangeFrom, @Param("expirationDateTo") String expirationDateTo, @Param("sponsorBankId") Long sponsorBankId);

    @Query(value = "select sa " +
            " from FinancingLimit sa inner join BankInfo b on b.bankId = sa.bankInfo.bankId" +
            " where 1=1 " +
            " and (:financingLimitCode is null or upper(sa.financingLimitCode) like %:financingLimitCode% ) " +
            " and (:bankId is null or sa.sponsorBank.bankId = :bankId)" +
            " and (b.bankId = :releaseBankId)" +
            " and (:typeLimit is null or upper(sa.typeLimit) like %:typeLimit% )" +
            " and (:moneyType is null or upper(sa.moneyType) like %:moneyType% )" +
            " and (:dateRangeFrom is null or to_char(sa.dateRange,'YYYY-MM-DD') >= :dateRangeFrom)" +
            " and (:expirationDateTo is null or to_char(sa.expirationDate,'YYYY-MM-DD') <= :expirationDateTo)"
    )
    List<FinancingLimit> releaseBankSearch(@Param("financingLimitCode") String financingLimitCode, @Param("typeLimit") String typeLimit,
                                @Param("bankId") Long bankId, @Param("moneyType") String moneyType,
                                @Param("dateRangeFrom") String dateRangeFrom, @Param("expirationDateTo") String expirationDateTo, @Param("releaseBankId") Long releaseBankId);
}
