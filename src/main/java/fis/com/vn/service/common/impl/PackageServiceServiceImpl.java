package fis.com.vn.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.com.vn.model.entity.PackageService;
import fis.com.vn.repository.PackageServiceRepository;
import fis.com.vn.service.common.PackageServiceService;

@Service
public class PackageServiceServiceImpl implements PackageServiceService {

	@Autowired
	PackageServiceRepository PackageServiceRepository;

	@Override
	public List<PackageService> findAll() {
		return PackageServiceRepository.findAll();
	}

	@Override
	public PackageService getById(String PackageServiceId) {
		return PackageServiceRepository.getById(Long.parseLong(PackageServiceId));
	}

	@Override
	public List<PackageService> getByFeeMethodId(String feeMethodId) {
		return PackageServiceRepository.getByFeeMethodId(Long.parseLong(feeMethodId));
	}
}
