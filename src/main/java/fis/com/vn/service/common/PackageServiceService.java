package fis.com.vn.service.common;

import java.util.List;

import fis.com.vn.model.entity.PackageService;

public interface PackageServiceService {
	List<PackageService> findAll();

	List<PackageService> getByFeeMethodId(String feeMethodId);
	
    PackageService getById(String packageServiceId);

}
