package fis.com.vn.service.corporate.impl;

import fis.com.vn.model.entity.ContractAddendum;
import fis.com.vn.repository.ContractAddendumRepository;
import fis.com.vn.repository.ContractRepository;
import fis.com.vn.service.corporate.ContractAddendumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractAddendumServiceImpl implements ContractAddendumService {

	@Autowired
	ContractRepository contractRepository;
	@Autowired
	ContractAddendumRepository contractAddendumRepository;
	
	@Override
	public List<ContractAddendum> findAll() {
		return contractAddendumRepository.findAll();
	}

	@Override
	public List<ContractAddendum> insertOrUpdateContractAddendum(List<ContractAddendum> contractAddendumList, Integer contractId) {
		List<ContractAddendum> listContractAddendumInBD = contractAddendumRepository.getAllContractAddendumByContractId(contractId);
		List<Long> idProductRequest = contractAddendumList.stream().map(contractAddendum -> contractAddendum.getAddendumNo()).collect(Collectors.toList());
		List<ContractAddendum> productNotInDb = listContractAddendumInBD
				.stream()
				.filter(contractAddendum -> !idProductRequest.contains(contractAddendum.getAddendumNo()))
				.collect(Collectors.toList());
		for(ContractAddendum contractAddendum : productNotInDb){
			contractAddendumRepository.delete(contractAddendum);
		}
		for(ContractAddendum productIU : contractAddendumList)
		{
			productIU.setContract(contractRepository.getById(contractId));
			contractAddendumRepository.save(productIU);
		}
		return contractAddendumList;
	}


}
