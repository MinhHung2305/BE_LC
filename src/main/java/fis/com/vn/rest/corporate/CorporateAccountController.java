package fis.com.vn.rest.corporate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.CorporateAccount;
import fis.com.vn.rest.mapper.CorporateAccountResponseMapper;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.CorporateAccountResponse;
import fis.com.vn.service.corporate.CorporateAccountService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/corporate")
@Slf4j
@Api(tags = "Corporate Account controller")
public class CorporateAccountController {

	@Autowired
	CorporateAccountService corporateAccountService;

	@Autowired
	CorporateAccountResponseMapper corporateAccountResponseMapper;

	@GetMapping("/account/getAll")
	public ResponseEntity<BaseResponse<List<CorporateAccountResponse>>> getAllCorporateAccount() {
		try {
			List<CorporateAccountResponse> corporateAccounResponseList = new ArrayList<CorporateAccountResponse>();
			List<CorporateAccount> corporateAccountList = corporateAccountService.findAll();

			for (CorporateAccount corporateAccount : corporateAccountList) {
				CorporateAccountResponse accountResponse = new CorporateAccountResponse();
				accountResponse = corporateAccountResponseMapper.toDomain(corporateAccount);
				accountResponse.setCorporateName(corporateAccount.getCorporate().getCorporateName());
				accountResponse.setCorporateCode(corporateAccount.getCorporate().getCorporateCode());
				accountResponse.setBankName(corporateAccount.getBank().getBankName());

				corporateAccounResponseList.add(accountResponse);
			}

			return ResponseFactory.success(HttpStatus.OK, corporateAccounResponseList,
					"GetAll Corporate Account success");
		} catch (LCPlatformException e) {
			return ResponseFactory.success(HttpStatus.CONFLICT, null, "GetAll Corporate Account fail");
		}
	}

	@GetMapping("/account/get/{id}")
	public ResponseEntity<BaseResponse<CorporateAccountResponse>> getCorporateAccountById(@PathVariable String id) {
		try {
			CorporateAccount corporateAccount = corporateAccountService.getById(id);
			
			CorporateAccountResponse corporateAccounResponse = new CorporateAccountResponse();
			corporateAccounResponse = corporateAccountResponseMapper.toDomain(corporateAccount);
			corporateAccounResponse.setCorporateName(corporateAccount.getCorporate().getCorporateName());
			corporateAccounResponse.setCorporateCode(corporateAccount.getCorporate().getCorporateCode());
			corporateAccounResponse.setBankName(corporateAccount.getBank().getBankName());

			return ResponseFactory.success(HttpStatus.OK, corporateAccounResponse, "Get Corporate Account success");
		} catch (LCPlatformException e) {
			return ResponseFactory.success(HttpStatus.OK, null, "Get Corporate Account fail");
		}
	}

}
