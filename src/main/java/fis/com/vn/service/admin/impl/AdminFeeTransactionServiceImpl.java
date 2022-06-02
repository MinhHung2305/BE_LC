package fis.com.vn.service.admin.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.FeeTransactionEntity;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.repository.FeeTransactionRepository;
import fis.com.vn.service.admin.AdminFeeTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminFeeTransactionServiceImpl implements AdminFeeTransactionService {
    @Autowired
    FeeTransactionRepository feeTransactionRepository;

    @Override
    public List<FeeTransactionEntity> getAllFeeTransaction() throws LCPlatformException {
        List<FeeTransactionEntity> feeTransactionEntityList = feeTransactionRepository.findAllFeeTransaction();
        List<FeeTransactionEntity> feeResult = new ArrayList<>();
        for (FeeTransactionEntity feeTransaction : feeTransactionEntityList) {
            if (feeTransaction.getCreatedDate() != null && feeTransaction.getLastModifiedDate() != null && feeTransaction.getCreatedDate().equals(feeTransaction.getLastModifiedDate())){
                feeTransaction.setLastModifiedDate(null);
                feeTransaction.setLastModifiedBy(null);
            }
            feeResult.add(feeTransaction);
        }
        return feeResult;
    }

    @Override
    public FeeTransactionEntity getDetailFeeTransaction(String feeTransactionId) throws LCPlatformException {
        FeeTransactionEntity feeTransaction = feeTransactionRepository.getDetailFeeTransaction(Long.parseLong(feeTransactionId));
        if (feeTransaction == null) {
            throw new LCPlatformException(ResponseCode.BAD_REQUEST, "Fee Transaction does not exist");
        }
        return feeTransactionRepository.getDetailFeeTransaction(Long.parseLong(feeTransactionId));
    }

    @Override
    public FeeTransactionEntity createRecordFeeTransaction(FeeTransactionEntity feeTransaction) throws LCPlatformException {
        FeeTransactionEntity feeTransactionDB = feeTransactionRepository.getDetailFeeTransaction(feeTransaction.getFeeTransactionId());
        if (feeTransactionDB != null) {
            throw new LCPlatformException(ResponseCode.BAD_REQUEST, "Fee Transaction already exist");
        }
        return feeTransactionRepository.save(feeTransaction);
    }

    @Override
    public FeeTransactionEntity updateRecordFeeTransaction(FeeTransactionEntity feeTransaction) throws LCPlatformException {
        FeeTransactionEntity feeTransactionDB = feeTransactionRepository.getDetailFeeTransaction(feeTransaction.getFeeTransactionId());
        if (feeTransactionDB == null) {
            throw new LCPlatformException(ResponseCode.BAD_REQUEST, "Fee Transaction does not exist");
        }
        feeTransaction.setCreatedBy(feeTransactionDB.getCreatedBy());
        feeTransaction.setCreatedDate(feeTransactionDB.getCreatedDate());
        return feeTransactionRepository.save(feeTransaction);
    }

    @Override
    public void deleteRecordFeeTransaction(String feeTransactionId) throws LCPlatformException {
        FeeTransactionEntity feeTransactionDB = feeTransactionRepository.getDetailFeeTransaction(Long.parseLong(feeTransactionId));
        if (feeTransactionDB == null) {
            throw new LCPlatformException(ResponseCode.BAD_REQUEST, "Fee Transaction does not exist");
        }
        feeTransactionRepository.deleteById(Long.parseLong(feeTransactionId));
    }
}
