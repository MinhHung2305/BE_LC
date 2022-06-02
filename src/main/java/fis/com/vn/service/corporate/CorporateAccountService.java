package fis.com.vn.service.corporate;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.CorporateAccount;
import fis.com.vn.rest.request.CorporateAccountRequest;

import java.util.List;

public interface CorporateAccountService {
	List<CorporateAccount> findAll() throws LCPlatformException;

	List<CorporateAccount> getByCorporateId(String corporateId);
	
    CorporateAccount getById(String corporateAccountId) throws LCPlatformException;

    CorporateAccount createCorporateAccount(CorporateAccountRequest corporateAccountRequest, String corporateId);

    CorporateAccount updateCorporateAccount(CorporateAccountRequest corporateAccountRequest, String corporateId);

    CorporateAccount deleteCorporateAccount(String corporateAccountId);

}
