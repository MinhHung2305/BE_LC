package fis.com.vn.service.corporate.impl;

import fis.com.vn.model.entity.BankInfo;
import fis.com.vn.model.entity.Corporate;
import fis.com.vn.model.entity.CorporateAccount;
import fis.com.vn.repository.BankInfoRepository;
import fis.com.vn.repository.CorporateAccountRepository;
import fis.com.vn.rest.mapper.CorporateAccountRequestMapper;
import fis.com.vn.rest.request.CorporateAccountRequest;
import fis.com.vn.service.corporate.CorporateAccountService;
import fis.com.vn.service.corporate.CorporateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorporateAccountServiceImpl implements CorporateAccountService {

    @Autowired
    CorporateAccountRepository corporateAccountRepository;

    @Autowired
    CorporateAccountRequestMapper corporateAccountRequestMapper;

    @Autowired
    CorporateService corporateService;

    @Autowired
    BankInfoRepository bankInfoRepository;

    @Override
    public List<CorporateAccount> findAll() {
        return corporateAccountRepository.findAll();
    }

    @Override
    public CorporateAccount getById(String corporateAccountId) {
        return corporateAccountRepository.getById(Long.parseLong(corporateAccountId));
    }

    @Override
    public CorporateAccount createCorporateAccount(CorporateAccountRequest corporateAccountRequest,
                                                   String corporateId) {
        Corporate corporate = new Corporate();
        CorporateAccount corporateAccount = corporateAccountRequestMapper.toEntity(corporateAccountRequest);

        BankInfo bankInfo = bankInfoRepository.getById(Long.parseLong(corporateAccountRequest.getBankId()));
        corporate = corporateService.getById(corporateId);

        corporateAccount.setCorporate(corporate);
        corporateAccount.setBank(bankInfo);
        corporateAccount.setCorporateAccountType(corporateAccountRequest.getCorporateAccountType());
        corporateAccountRepository.save(corporateAccount);
        return corporateAccount;
    }

    @Override
    public CorporateAccount updateCorporateAccount(CorporateAccountRequest corporateAccountRequest,
                                                   String corporateId) {
        Corporate corporate = new Corporate();
        CorporateAccount corporateAccount = corporateAccountRepository.getById(corporateAccountRequest.getCorporateAccountId());

        BankInfo bankInfo = bankInfoRepository.getById(Long.parseLong(corporateAccountRequest.getBankId()));
        corporate = corporateService.getById(corporateId);

        corporateAccount.setCorporate(corporate);
        corporateAccount.setBank(bankInfo);
        corporateAccount.setCorporateAccountType(corporateAccountRequest.getCorporateAccountType());
        corporateAccountRepository.save(corporateAccount);
        return corporateAccount;
    }

    @Override
    public CorporateAccount deleteCorporateAccount(String corporateAccountId) {
        CorporateAccount corporateAccountDel = getById(corporateAccountId);
        corporateAccountRepository.delete(corporateAccountDel);
        return corporateAccountDel;
    }

    @Override
    public List<CorporateAccount> getByCorporateId(String corporateId) {
        return corporateAccountRepository.findByCorporateId(Long.parseLong(corporateId));
    }

}
