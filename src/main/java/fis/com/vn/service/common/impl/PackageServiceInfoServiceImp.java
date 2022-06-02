package fis.com.vn.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.com.vn.model.entity.Corporate;
import fis.com.vn.model.entity.PackageService;
import fis.com.vn.model.entity.PackageServiceInfo;
import fis.com.vn.repository.CorporateRepository;
import fis.com.vn.repository.PackageServiceInfoRepository;
import fis.com.vn.repository.PackageServiceRepository;
import fis.com.vn.rest.request.PackageServiceInfoRequest;
import fis.com.vn.service.common.PackageServiceInfoService;

@Service
public class PackageServiceInfoServiceImp implements PackageServiceInfoService{
	@Autowired
	CorporateRepository corporateRepository;
	
	@Autowired
	PackageServiceRepository packageServiceRepository;

	@Autowired
	PackageServiceInfoRepository packageServiceInfoRepository;
	
	@Override
	public PackageServiceInfo create(PackageServiceInfoRequest packageServiceInfoRequest) {
		
		Long corporateId = Long.parseLong(packageServiceInfoRequest.getCorporateId());
		Corporate corporate = corporateRepository.getById(corporateId);

		Long packageServiceId = Long.parseLong(packageServiceInfoRequest.getPackageServiceId());
		PackageService packageService = packageServiceRepository.getById(packageServiceId);
		
		PackageServiceInfo packageServiceInfo = new PackageServiceInfo();
		packageServiceInfo.setCorporate(corporate);
		packageServiceInfo.setPackageService(packageService);
		packageServiceInfo.setPackageServiceInfoStatus(1);
		packageServiceInfo.setPackageServiceInfoDescription(packageServiceInfoRequest.getPackageServiceInfoDescription());
		
		packageServiceInfoRepository.save(packageServiceInfo);
		
		return packageServiceInfo;
	}

	
}
