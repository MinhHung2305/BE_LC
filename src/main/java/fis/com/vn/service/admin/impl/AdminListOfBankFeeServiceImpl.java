package fis.com.vn.service.admin.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.Fee;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.repository.FeeRepository;
import fis.com.vn.rest.mapper.FeeRequestMapper;
import fis.com.vn.rest.mapper.FeeResponseMapper;
import fis.com.vn.rest.request.FeeRequest;
import fis.com.vn.rest.response.FeeResponse;
import fis.com.vn.service.admin.AdminListOfBankFeeService;
import fis.com.vn.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AdminListOfBankFeeServiceImpl implements AdminListOfBankFeeService {
    @Autowired
    FeeRepository feeRepository;
    @Autowired
    FeeRequestMapper feeRequestMapper;
    @Autowired
    FeeResponseMapper feeResponseMapper;

    @Override
    public List<FeeResponse> getAlListOfBankFee() throws LCPlatformException {
        List<Fee> feeList = feeRepository.findAllListOfBankFee(Constant.USER_TYPE_BANK);
        return feeResponseMapper.toDomain(feeList);
    }

    @Override
    public List<FeeResponse> getAlListOfBankFee(Integer status) throws LCPlatformException {
        List<Fee> feeList = feeRepository.findAllListOfBankFee(Constant.USER_TYPE_BANK, status);
        List<FeeResponse> feeResponseList = feeResponseMapper.toDomain(feeList);
        for(FeeResponse feeResponse : feeResponseList){
            if(feeResponse.getCreatedDate() == feeResponse.getLastModifiedDate()){
                feeResponse.setLastModifiedDate(null);
                feeResponse.setLastModifiedBy(null);
            }
        }
        return feeResponseList;
    }

    @Override
    public FeeResponse getDetailBankFee(String feeId) throws LCPlatformException {
        Fee fee = feeRepository.getDetailBankFee(Long.parseLong(feeId), Constant.USER_TYPE_BANK);
        FeeResponse feeResponse = feeResponseMapper.toDomain(fee);
        return feeResponse;
    }

    @Override
    @Transactional
    public FeeResponse createRecordBankFee(FeeRequest feeRequest) throws LCPlatformException {
        Fee fee = feeRepository.save(feeRequestMapper.toEntity(feeRequest));
        return feeResponseMapper.toDomain(fee);
    }

    @Override
    @Transactional
    public FeeResponse updateRecordBankFee(FeeRequest feeRequest) throws LCPlatformException {
        Fee feeDB = feeRepository.getById(feeRequest.getFeeId());
        if (feeDB.getFeeId() == null) {
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        Fee feeSaveDB = feeRequestMapper.toEntity(feeRequest);
        feeSaveDB.setCreatedBy(feeDB.getCreatedBy());
        feeSaveDB.setCreatedDate(feeDB.getCreatedDate());

        Fee fee = feeRepository.save(feeSaveDB);
        return feeResponseMapper.toDomain(fee);
    }

    @Override
    @Transactional
    public void deleteRecordBankFee(String feeId) throws LCPlatformException {
        feeRepository.deleteById(Long.parseLong(feeId));
    }

    @Override
    public List<FeeResponse> getAllCorporateFee() throws LCPlatformException {
        List<Fee> listFee = feeRepository.findAllListOfBankFee(Constant.USER_TYPE_CORPORATE);
        return feeResponseMapper.toDomain(listFee);
    }

    @Override
    public FeeResponse getDetailCorporateFee(String feeId) throws LCPlatformException {
        Fee fee = feeRepository.getDetailBankFee(Long.parseLong(feeId), Constant.USER_TYPE_CORPORATE);
        return feeResponseMapper.toDomain(fee);
    }

    @Override
    @Transactional
    public FeeResponse createRecordCorporateFee(FeeRequest feeRequest) throws LCPlatformException {
        Fee fee = feeRequestMapper.toEntity(feeRequest);
        fee.setFeeApply(Constant.FEE_CHANNEL);
        fee.setFeeChannel(Constant.USER_TYPE_CORPORATE);

        Fee feeResult = feeRepository.save(fee);
        return feeResponseMapper.toDomain(feeResult);
    }

    @Override
    @Transactional
    public FeeResponse updateRecordCorporateFee(FeeRequest feeRequest) throws LCPlatformException {
        Fee fee = feeRequestMapper.toEntity(feeRequest);
        fee.setFeeApply(Constant.FEE_CHANNEL);
        fee.setFeeChannel(Constant.USER_TYPE_CORPORATE);

        Fee feeResult = feeRepository.save(fee);
        return feeResponseMapper.toDomain(feeResult);
    }
}
