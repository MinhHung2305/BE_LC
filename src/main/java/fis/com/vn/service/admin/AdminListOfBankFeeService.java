package fis.com.vn.service.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.request.FeeRequest;
import fis.com.vn.rest.response.FeeResponse;

import java.util.List;

public interface AdminListOfBankFeeService {
    List<FeeResponse> getAlListOfBankFee() throws LCPlatformException;

    List<FeeResponse> getAlListOfBankFee(Integer status) throws LCPlatformException;

    FeeResponse getDetailBankFee(String feeId) throws LCPlatformException;

    FeeResponse createRecordBankFee(FeeRequest feeRequest) throws LCPlatformException;

    FeeResponse updateRecordBankFee(FeeRequest feeRequest) throws LCPlatformException;

    void deleteRecordBankFee(String feeId) throws LCPlatformException;

    List<FeeResponse> getAllCorporateFee() throws LCPlatformException;

    FeeResponse getDetailCorporateFee(String feeId) throws LCPlatformException;

    FeeResponse createRecordCorporateFee(FeeRequest feeRequest) throws LCPlatformException;

    FeeResponse updateRecordCorporateFee(FeeRequest feeRequest) throws LCPlatformException;
}
