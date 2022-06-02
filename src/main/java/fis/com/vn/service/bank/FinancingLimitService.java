package fis.com.vn.service.bank;

import fis.com.vn.exception.SendEmailException;
import fis.com.vn.rest.request.FinancingLimitRequest;
import fis.com.vn.rest.response.FinancingLimitResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FinancingLimitService {
    FinancingLimitResponse create(String userId, FinancingLimitRequest financingLimitRequest) throws SendEmailException;
    List<FinancingLimitResponse> search(String userId, FinancingLimitRequest financingLimitRequest);
    void delete(Long id, String userId) throws SendEmailException;
    FinancingLimitResponse update(String userId, FinancingLimitRequest financingLimitRequest) throws SendEmailException;
    FinancingLimitResponse view(Long id);

    List<FinancingLimitResponse> releaseBankSearch(String userId, FinancingLimitRequest financingLimitRequest);
}
