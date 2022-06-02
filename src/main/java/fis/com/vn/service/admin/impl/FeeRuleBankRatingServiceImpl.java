package fis.com.vn.service.admin.impl;

import fis.com.vn.model.entity.FeeRuleBankRating;
import fis.com.vn.repository.BankRatingRepository;
import fis.com.vn.repository.FeeRuleBankRatingRepository;
import fis.com.vn.repository.FeeRulesRepository;
import fis.com.vn.rest.mapper.FeeRuleBankRatingRequestMapper;
import fis.com.vn.rest.request.FeeRuleBankRatingRequest;
import fis.com.vn.service.admin.FeeRuleBankRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeeRuleBankRatingServiceImpl implements FeeRuleBankRatingService{
    @Autowired
    FeeRuleBankRatingRepository feeRuleBankRatingRepository;

    @Autowired
    FeeRulesRepository feeRulesRepository;

    @Autowired
    BankRatingRepository bankRatingRepository;

    @Autowired
    FeeRuleBankRatingRequestMapper feeRuleBankRatingRequestMapper;

    @Override
    public List<FeeRuleBankRating> saveAllFeeRuleBankRating(List<FeeRuleBankRatingRequest> listFeeRuleBankRatingRequest, Long feeRuleId) {
        List<FeeRuleBankRating> listFeeRuleBankRating = new ArrayList<>();
        for(FeeRuleBankRatingRequest feeRuleBankRatingRequest : listFeeRuleBankRatingRequest){
            FeeRuleBankRating feeRuleBankRating = feeRuleBankRatingRequestMapper.toEntity(feeRuleBankRatingRequest);
            feeRuleBankRating.setBankRating(bankRatingRepository.getById(feeRuleBankRatingRequest.getBankRatingId()));
            listFeeRuleBankRating.add(feeRuleBankRating);
        }

        List<FeeRuleBankRating> listFeeRuleBankRatingBD = feeRuleBankRatingRepository.findAllByFeeRule(feeRuleId);
        List<Long> idListFeeRuleBankRating = listFeeRuleBankRating.stream().map(feeRuleBankRating -> feeRuleBankRating.getId()).collect(Collectors.toList());
        List<FeeRuleBankRating> listFeeRuleBankRatingNotBD = listFeeRuleBankRatingBD
                .stream()
                .filter(feeRuleBankRating -> !idListFeeRuleBankRating.contains(feeRuleBankRating.getId()))
                .collect(Collectors.toList());
        for(FeeRuleBankRating feeRuleBankRating : listFeeRuleBankRatingNotBD){
            feeRuleBankRatingRepository.delete(feeRuleBankRating);
        }
        for(FeeRuleBankRating feeRuleBankRating : listFeeRuleBankRating)
        {
            feeRuleBankRating.setBankRating(bankRatingRepository.getById(feeRuleBankRating.getBankRating().getId()));
            feeRuleBankRating.setFeeRules(feeRulesRepository.getById(feeRuleId));
            feeRuleBankRatingRepository.save(feeRuleBankRating);
        }
        return listFeeRuleBankRating;
    }
}
