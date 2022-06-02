package fis.com.vn.rest;

import fis.com.vn.model.entity.BankInfo;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.service.common.BankInfoService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bank")
@Api(tags = "BankInfo controller")
public class BankInfoController {

	@Autowired
	BankInfoService bankInfoService;

	/**
	 * get All bank information
	 * @return List<BankInfo>
	 */
	@GetMapping("/bankinfo/getAll")
	//@Secured({"ROLE_view_bank_user_management"})
	public ResponseEntity<BaseResponse<List<BankInfo>>> getAllPackageBankInfo() {
		List<BankInfo> bankInfoServiceList = bankInfoService.getAll();
		return ResponseFactory.success(HttpStatus.OK, bankInfoServiceList);
	}
}
