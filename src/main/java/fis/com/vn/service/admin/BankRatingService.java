package fis.com.vn.service.admin;

import fis.com.vn.rest.response.BankRatingResponse;

import java.util.List;

public interface BankRatingService {
    List<BankRatingResponse> getAllBankRating();
}
