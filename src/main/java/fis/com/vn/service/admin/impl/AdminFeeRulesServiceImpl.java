package fis.com.vn.service.admin.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.Fee;
import fis.com.vn.model.entity.FeeRuleBankRating;
import fis.com.vn.model.entity.FeeRules;
import fis.com.vn.model.entity.FeeTransactionEntity;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.repository.*;
import fis.com.vn.rest.mapper.FeeRuleBankRatingResponseMapper;
import fis.com.vn.rest.mapper.FeeRulesRequestMapper;
import fis.com.vn.rest.mapper.FeeRulesResponseMapper;
import fis.com.vn.rest.request.FeeRulesRequest;
import fis.com.vn.rest.response.FeeRuleBankRatingResponse;
import fis.com.vn.rest.response.FeeRulesResponse;
import fis.com.vn.service.admin.AdminFeeRulesService;
import fis.com.vn.service.admin.FeeRuleBankRatingService;
import fis.com.vn.service.admin.FullProgressionLcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminFeeRulesServiceImpl implements AdminFeeRulesService {
    @Autowired
    FeeRulesRepository feeRulesRepository;

    @Autowired
    FeeRepository feeRepository;

    @Autowired
    FeeTransactionRepository feeTransactionRepository;

    @Autowired
    FeeRulesRequestMapper feeRulesRequestMapper;

    @Autowired
    FeeRulesResponseMapper feeRulesResponseMapper;

    @Autowired
    FullProgressionLcRepository fullProgressionLcRepository;

    @Autowired
    FullProgressionLcService fullProgressionLcService;

    @Autowired
    FeeRuleBankRatingRepository feeRuleBankRatingRepository;

    @Autowired
    FeeRuleBankRatingResponseMapper feeRuleBankRatingResponseMapper;

    @Autowired
    FeeRuleBankRatingService feeRuleBankRatingService;

    @Override
    public List<FeeRulesResponse> getAllFeeRules() throws LCPlatformException {
        List<FeeRules> feeRulesList = feeRulesRepository.findAllFeeRules();
        if (feeRulesList.isEmpty()) {
            throw new LCPlatformException(ResponseCode.NOT_FOUND);
        }
        List<FeeRulesResponse> results = new ArrayList<>();
        for (FeeRules feeRules : feeRulesList) {
            FeeRulesResponse res = this.getDetailFeeRules(String.valueOf(feeRules.getFeeRulesId()));
            results.add(res);
        }
        return results;
    }

    @Override
    public FeeRulesResponse getDetailFeeRules(String feeRulesId) throws LCPlatformException {
        FeeRules listFeeRuleInDB = feeRulesRepository.findByFeeRulesId(Long.parseLong(feeRulesId));
        if (listFeeRuleInDB == null) {
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        FeeRules feeRules = feeRulesRepository.findByFeeRulesId(Long.parseLong(feeRulesId));
        FeeRulesResponse res = feeRulesResponseMapper.toDomain(feeRules);
        if (feeRules.getFee() != null) {
            res.setFeeId(feeRules.getFee().getFeeId());
            res.setFeeCode(feeRules.getFee().getFeeCode());
            res.setFeeFrequency(feeRules.getFee().getFeeFrequency());
            res.setFeeProcedure(feeRules.getFee().getFeeProcedure());
            res.setFeeName(feeRules.getFee().getFeeName());
        }
        if (feeRules.getFeeTransactionEntity() != null) {
            res.setFeeTransactionId(feeRules.getFeeTransactionEntity().getFeeTransactionId());
            res.setFeeTransactionCode(feeRules.getFeeTransactionEntity().getFeeTransactionCode());
            res.setFeeTransactionName(feeRules.getFeeTransactionEntity().getFeeTransactionName());
        }
        List<FeeRuleBankRating> feeRuleBankRatingList = feeRuleBankRatingRepository.findAllByFeeRule(feeRules.getFeeRulesId());
        List<FeeRuleBankRatingResponse> feeRuleBankRatingResponseList = feeRuleBankRatingResponseMapper.toDomain(feeRuleBankRatingList);
        res.setListFeeRuleBankRating(feeRuleBankRatingResponseList);
        res.setFullProgressionLcList(fullProgressionLcRepository.getAllByFeeRule(feeRules.getFeeRulesId()));

        return res;
    }

    @Override
    @Transactional
    public void createRecordFeeRules(FeeRulesRequest feeRulesRequest) throws LCPlatformException {
        checkValidateFeeRequest(feeRulesRequest);

        FeeRules feeRules = feeRulesRequestMapper.toEntity(feeRulesRequest);
        // Check fee in DB.
        Fee feeInDB = feeRepository.getDetailBankFee(feeRulesRequest.getFeeId());
        if (feeInDB != null) {
            feeRules.setFee(feeInDB);
        }
        // Check fee Transaction in DB.
        FeeTransactionEntity feeTransactionDB = feeTransactionRepository.getDetailFeeTransaction(feeRulesRequest.getFeeTransactionId());
        if (feeTransactionDB != null) {
            feeRules.setFeeTransactionEntity(feeTransactionDB);
        }
        feeRules.setListFeeRuleBankRating(null);
        feeRules.setFullProgressionLcList(null);
        feeRules = feeRulesRepository.save(feeRules);
        if (feeRulesRequest.getListFeeRuleBankRating() != null && !feeRulesRequest.getListFeeRuleBankRating().isEmpty()) {
            feeRuleBankRatingService.saveAllFeeRuleBankRating(feeRulesRequest.getListFeeRuleBankRating(), feeRules.getFeeRulesId());
        }
        if (feeRulesRequest.getFullProgressionLcList() != null && !feeRulesRequest.getFullProgressionLcList().isEmpty()) {
            fullProgressionLcService.saveAllFullProgressionLc(feeRulesRequest.getFullProgressionLcList(), feeRules.getFeeRulesId());
        }
    }

    private void checkValidateFeeRequest(FeeRulesRequest feeRulesRequest) {
        // Check request.
        if (feeRulesRequest == null) {
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        // Check Fee Rules in DB.
        FeeRules feeRuleInDB = feeRulesRepository.findByFeeRulesId(feeRulesRequest.getFeeRulesId());
        if (feeRuleInDB != null) {
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        FeeRules feeRuleByCode = feeRulesRepository.findByFeeRulesCode(feeRulesRequest.getFeeRulesCode());
        if (feeRuleByCode != null) {
            throw new LCPlatformException(ResponseCode.FEE_RULE_CODE_DUPLICATE);
        }
    }

    @Override
    @Transactional
    public void updateRecordFeeRules(FeeRulesRequest feeRulesRequest) throws LCPlatformException {
        // Check request.
        if (feeRulesRequest == null) {
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        // Check Fee Rules in DB.
        FeeRules feeRulesDB = feeRulesRepository.findByFeeRulesId(feeRulesRequest.getFeeRulesId());
        if (feeRulesDB == null) {
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        FeeRules feeRules = feeRulesRequestMapper.toEntity(feeRulesRequest);

        // Check fee in DB.
        Fee feeInDB = feeRepository.getDetailBankFee(feeRulesRequest.getFeeId());
        if (feeInDB != null) {
            feeRules.setFee(feeInDB);
        }
        // Check fee Transaction in DB.
        FeeTransactionEntity feeTransactionDB = feeTransactionRepository.getDetailFeeTransaction(feeRulesRequest.getFeeTransactionId());
        if (feeTransactionDB != null) {
            feeRules.setFeeTransactionEntity(feeTransactionDB);
        }
        feeRules.setCreatedDate(feeRulesDB.getCreatedDate());
        feeRules.setCreatedBy(feeInDB.getCreatedBy());
        feeRules.setListFeeRuleBankRating(null);
        feeRules.setFullProgressionLcList(null);
        feeRulesRepository.save(feeRules);
        if (feeRulesRequest.getListFeeRuleBankRating() != null && !feeRulesRequest.getListFeeRuleBankRating().isEmpty()) {
            feeRuleBankRatingService.saveAllFeeRuleBankRating(feeRulesRequest.getListFeeRuleBankRating(), feeRules.getFeeRulesId());
        }
        if (feeRulesRequest.getFullProgressionLcList() != null && !feeRulesRequest.getFullProgressionLcList().isEmpty()) {
            fullProgressionLcService.saveAllFullProgressionLc(feeRulesRequest.getFullProgressionLcList(), feeRules.getFeeRulesId());
        }
    }

    @Override
    public void deleteRecordFeeRules(String feeTransactionId) throws LCPlatformException {
        // Check Fee Rules in DB.
        FeeRules feeRuleInDB = feeRulesRepository.findByFeeRulesId(Long.parseLong(feeTransactionId));
        if (feeRuleInDB == null) {
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        feeRulesRepository.deleteById(Long.parseLong(feeTransactionId));
    }
}
