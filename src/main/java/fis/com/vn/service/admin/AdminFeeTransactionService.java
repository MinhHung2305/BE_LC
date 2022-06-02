package fis.com.vn.service.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.FeeTransactionEntity;

import java.util.List;

public interface AdminFeeTransactionService {
    public List<FeeTransactionEntity> getAllFeeTransaction() throws LCPlatformException;
    public FeeTransactionEntity getDetailFeeTransaction(String feeTransactionId) throws LCPlatformException;
    public FeeTransactionEntity createRecordFeeTransaction(FeeTransactionEntity feeTransaction) throws LCPlatformException;
    public FeeTransactionEntity updateRecordFeeTransaction(FeeTransactionEntity feeTransaction) throws LCPlatformException;
    public void deleteRecordFeeTransaction(String feeTransactionId) throws LCPlatformException;
}
