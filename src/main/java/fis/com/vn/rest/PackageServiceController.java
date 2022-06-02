package fis.com.vn.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fis.com.vn.model.entity.PackageService;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.service.common.PackageServiceService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/package-service")
@Api(tags = "PackageService controller")
public class PackageServiceController {
	
	@Autowired
	PackageServiceService packageServiceService;
	
	@GetMapping("/getAll")
	public ResponseEntity<BaseResponse<List<PackageService>>> getAllPackageService() {
		List<PackageService> PackageServiceList = packageServiceService.findAll();
		return ResponseFactory.success(HttpStatus.OK, PackageServiceList);
	}
	
	@GetMapping("/get/{packageServiceId}")
	ResponseEntity<BaseResponse<PackageService>> getPackageServiceById(@PathVariable String packageServiceId) {
		PackageService PackageService = packageServiceService.getById(packageServiceId);
		return ResponseFactory.success(HttpStatus.OK, PackageService);
	}
	
	@GetMapping("/getByFeeMethodId")
	public ResponseEntity<BaseResponse<List<PackageService>>> getByFeeMethodId(@RequestParam String feeMethodId) {
		List<PackageService> PackageServiceList = packageServiceService.getByFeeMethodId(feeMethodId);
		return ResponseFactory.success(HttpStatus.OK, PackageServiceList);
	}
}
