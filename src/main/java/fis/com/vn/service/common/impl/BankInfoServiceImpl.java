package fis.com.vn.service.common.impl;

import fis.com.vn.model.entity.BankInfo;
import fis.com.vn.repository.BankInfoRepository;
import fis.com.vn.service.common.BankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankInfoServiceImpl implements BankInfoService {

	@Autowired
	BankInfoRepository bankInfoRepository;

	@Override
	public List<BankInfo> getAll() {
		return bankInfoRepository.findAll();
	}

	@Override
	public BankInfo getById(String bankInfoId) {
		return bankInfoRepository.getById(Long.parseLong(bankInfoId));
	}

	@Override
	public BankInfo getByBankCode(String bankCode) {
		return bankInfoRepository.findBankInfoByBankCode(bankCode);
	}
}
