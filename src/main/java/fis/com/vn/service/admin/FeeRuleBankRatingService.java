package fis.com.vn.service.admin;

import fis.com.vn.model.entity.FeeRuleBankRating;
import fis.com.vn.rest.request.FeeRuleBankRatingRequest;

import java.util.List;

public interface FeeRuleBankRatingService {
    List<FeeRuleBankRating> saveAllFeeRuleBankRating(List<FeeRuleBankRatingRequest> listFullProgressionLc, Long feeRuleId);
}
