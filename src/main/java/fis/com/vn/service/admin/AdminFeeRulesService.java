package fis.com.vn.service.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.request.FeeRulesRequest;
import fis.com.vn.rest.response.FeeRulesResponse;

import java.util.List;

public interface AdminFeeRulesService {
    public List<FeeRulesResponse> getAllFeeRules() throws LCPlatformException;
    public FeeRulesResponse getDetailFeeRules(String feeRulesId) throws LCPlatformException;
    public void createRecordFeeRules(FeeRulesRequest feeRulesRequest) throws LCPlatformException;
    public void updateRecordFeeRules(FeeRulesRequest feeRulesRequest) throws LCPlatformException;
    public void deleteRecordFeeRules(String feeTransactionId) throws LCPlatformException;
}
