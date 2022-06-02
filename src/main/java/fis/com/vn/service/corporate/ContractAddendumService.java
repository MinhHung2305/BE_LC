package fis.com.vn.service.corporate;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.ContractAddendum;

import java.util.List;

public interface ContractAddendumService {
	List<ContractAddendum> findAll() throws LCPlatformException;

	List<ContractAddendum> insertOrUpdateContractAddendum(List<ContractAddendum> contractAddendumList, Integer contractId);

}
