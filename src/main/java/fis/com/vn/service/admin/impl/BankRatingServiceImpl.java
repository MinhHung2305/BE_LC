package fis.com.vn.service.admin.impl;

import fis.com.vn.model.entity.BankRating;
import fis.com.vn.repository.BankRatingRepository;
import fis.com.vn.rest.mapper.BankRatingResponseMapper;
import fis.com.vn.rest.response.BankRatingResponse;
import fis.com.vn.service.admin.BankRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankRatingServiceImpl implements BankRatingService {
    @Autowired
    BankRatingRepository bankRatingRepository;
    @Autowired
    BankRatingResponseMapper bankRatingResponseMapper;

    @Override
    public List<BankRatingResponse> getAllBankRating() {
        List<BankRating> bankRatingList = bankRatingRepository.findAll();
        List<BankRatingResponse> responseList = bankRatingResponseMapper.toDomain(bankRatingList);
        return responseList;
    }
}
