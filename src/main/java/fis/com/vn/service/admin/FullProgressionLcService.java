package fis.com.vn.service.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.FullProgressionLc;

import java.util.List;

public interface FullProgressionLcService {
    List<FullProgressionLc> saveAllFullProgressionLc(List<FullProgressionLc> listFullProgressionLc, Long feeRuleId) throws LCPlatformException;
}
