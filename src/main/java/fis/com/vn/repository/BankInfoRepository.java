package fis.com.vn.repository;

import fis.com.vn.model.entity.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BankInfoRepository extends JpaRepository<BankInfo, Long> {

    List<BankInfo> findByBankCode(String bankCode);

    BankInfo findBankInfoByBankCode(String bankCode);
}