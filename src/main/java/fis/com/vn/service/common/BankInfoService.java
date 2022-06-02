package fis.com.vn.service.common;

import fis.com.vn.model.entity.BankInfo;

import java.util.List;

public interface BankInfoService {

	List<BankInfo> getAll();

	BankInfo getById(String bankInfoId);

	BankInfo getByBankCode(String bankCode);
}
