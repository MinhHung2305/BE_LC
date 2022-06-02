package fis.com.vn.service.admin.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.FullProgressionLc;
import fis.com.vn.repository.FeeRulesRepository;
import fis.com.vn.repository.FullProgressionLcRepository;
import fis.com.vn.service.admin.FullProgressionLcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FullProgressionLcServiceImpl implements FullProgressionLcService {
    @Autowired
    FullProgressionLcRepository fullProgressionLcRepository;

    @Autowired
    FeeRulesRepository feeRulesRepository;

    @Override
    public List<FullProgressionLc> saveAllFullProgressionLc(List<FullProgressionLc> listFullProgressionLc, Long feeRuleId) throws LCPlatformException {
        List<FullProgressionLc> listFullProgressionLcBD = fullProgressionLcRepository.getAllByFeeRule(feeRuleId);
        List<Long> idFullProgressionLcRequest = listFullProgressionLc.stream().map(fullProgressionLc -> fullProgressionLc.getId()).collect(Collectors.toList());
        List<FullProgressionLc> listFullProgressionLcNotInDB = listFullProgressionLcBD
                .stream()
                .filter(fullProgressionLc -> !idFullProgressionLcRequest.contains(fullProgressionLc.getId()))
                .collect(Collectors.toList());
        for(FullProgressionLc fullProgressionLc : listFullProgressionLcNotInDB){
            fullProgressionLcRepository.delete(fullProgressionLc);
        }
        for(FullProgressionLc fullProgressionLc : listFullProgressionLc)
        {
            fullProgressionLc.setFeeRules(feeRulesRepository.getById(feeRuleId));
            fullProgressionLcRepository.save(fullProgressionLc);
        }
        return listFullProgressionLc;
    }
}
