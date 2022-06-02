package fis.com.vn.service.bank.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.model.entity.*;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.repository.BankInfoRepository;
import fis.com.vn.repository.FinancingLimitChangeHistoryRepository;
import fis.com.vn.repository.FinancingLimitRepository;
import fis.com.vn.repository.FinancingLimitTransactionRepository;
import fis.com.vn.rest.mapper.FinancingLimitRequestMapper;
import fis.com.vn.rest.mapper.FinancingLimitResponseMapper;
import fis.com.vn.rest.request.FinancingLimitRequest;
import fis.com.vn.rest.response.FinancingLimitResponse;
import fis.com.vn.service.bank.FinancingLimitService;
import fis.com.vn.service.common.UserInfoService;
import fis.com.vn.service.notification.EmailService;
import fis.com.vn.util.Constant;
import fis.com.vn.util.ModelMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FinancingLimitServiceImpl implements FinancingLimitService {
    @Autowired
    private FinancingLimitRequestMapper financingLimitRequestMapper;

    @Autowired
    private FinancingLimitResponseMapper financingLimitResponseMapper;

    @Autowired
    private FinancingLimitRepository financingLimitRepository;

    @Autowired
    private BankInfoRepository bankInfoRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private FinancingLimitTransactionRepository financingLimitTransactionRepository;

    @Autowired
    private FinancingLimitChangeHistoryRepository financingLimitChangeHistoryRepository;

    @Override
    public FinancingLimitResponse create(String userId, FinancingLimitRequest financingLimitRequest) throws SendEmailException {
        try{
            financingLimitRequest.setStatus(1);
            FinancingLimit financingLimit = financingLimitRequestMapper.toEntity(financingLimitRequest);
            BankInfo bankInfo = null;
            Optional<BankInfo> bankInfoOptional = bankInfoRepository.findById(financingLimitRequest.getBankId());
            if(bankInfoOptional.isPresent()){
                bankInfo = bankInfoOptional.get();
            }
            UserInfoEntity userInfo = userInfoService.getUserInfo(userId);
            BankInfo sponsorbank = bankInfoRepository.findBankInfoByBankCode(userInfo.getBankCode());
            financingLimit.setSponsorBank(sponsorbank);
            financingLimit.setBankInfo(bankInfo);
            financingLimit.setNumberOfChanges(0L);
            financingLimitRepository.save(financingLimit);
            financingLimit.setFinancingLimitCode("HM" + bankInfo.getBankCode() + this.convertDate() + this.randomNumber(financingLimit.getId()));
            financingLimitRepository.save(financingLimit);
            this.saveFinancingLimitChangeHistory(financingLimit, financingLimitRequest);
            FinancingLimitResponse financingLimitResponse = financingLimitResponseMapper.toDomain(financingLimit);
            financingLimitResponse.setBankInfo(bankInfo);
            financingLimitResponse.setSponsorBank(sponsorbank);
            Email email = emailService.getEmail075(financingLimit.getFinancingLimitCode(), userInfo.getEmail());
            emailService.sendEmailWithTemplate(email);
            return financingLimitResponse;
        }
        catch (LCPlatformException e){
            log.info(e.getMessage());
        }
        return null;
    }

    public String randomNumber(Long id) {
        String convertId = id.toString();
        String number = convertId;
        switch (convertId.length()) {
            case 1:
                number = "00" + id;
                break;
            case 2:
                number = "0" + id;
                break;
            case 3:
                number = number;
                break;

        }
        return number;
    }

    public void saveFinancingLimitChangeHistory(FinancingLimit financingLimit, FinancingLimitRequest financingLimitRequest)
    {
        FinancingLimitChangeHistory financingLimitChangeHistory = new FinancingLimitChangeHistory();
        financingLimitChangeHistory.setFinancingLimit(financingLimit);
        financingLimitChangeHistory.setContractNumberLimit(financingLimitRequest.getContractNumberLimit());
        financingLimitChangeHistory.setDateRange(financingLimitRequest.getDateRange());
        financingLimitChangeHistory.setExpirationDate(financingLimitRequest.getExpirationDate());
        financingLimitChangeHistory.setTotalLimit(financingLimitRequest.getTotalLimit());
        financingLimitChangeHistory.setRequestARefund(financingLimitRequest.getRequestARefund());
        financingLimitChangeHistoryRepository.save(financingLimitChangeHistory);
    }

    @Override
    public List<FinancingLimitResponse> search(String userId, FinancingLimitRequest financingLimitRequest)
    {
        UserInfoEntity userInfo = userInfoService.getUserInfo(userId);
        BankInfo bankInfo = bankInfoRepository.findBankInfoByBankCode(userInfo.getBankCode());
        List<FinancingLimit> financingLimitList = financingLimitRepository.search(financingLimitRequest.getFinancingLimitCode(), financingLimitRequest.getTypeLimit(),
                financingLimitRequest.getBankId(), financingLimitRequest.getMoneyType(), financingLimitRequest.getStatus(), financingLimitRequest.getDateRangeFrom(), financingLimitRequest.getExpirationDateTo(), bankInfo.getBankId());
        return financingLimitResponseMapper.toDomain(financingLimitList);
    }

    @Override
    public List<FinancingLimitResponse> releaseBankSearch(String userId, FinancingLimitRequest financingLimitRequest)
    {
        UserInfoEntity userInfo = userInfoService.getUserInfo(userId);
        BankInfo bankInfo = bankInfoRepository.findBankInfoByBankCode(userInfo.getBankCode());
        List<FinancingLimit> financingLimitList = financingLimitRepository.releaseBankSearch(financingLimitRequest.getFinancingLimitCode(), financingLimitRequest.getTypeLimit(),
                financingLimitRequest.getBankId(), financingLimitRequest.getMoneyType(), financingLimitRequest.getDateRangeFrom(), financingLimitRequest.getExpirationDateTo(), bankInfo.getBankId());
        List<FinancingLimit> financingLimits = financingLimitList;
        if(financingLimitRequest.getStatusForSearchRealeaseBank() != null){
            switch (financingLimitRequest.getStatusForSearchRealeaseBank())
            {
                case Constant.STATUS_HIEU_LUC:
                    financingLimits = financingLimitList.stream().filter(x-> x.getExpirationDate().compareTo(LocalDate.now()) > 0).collect(Collectors.toList());
                    break;
                case Constant.STATUS_HET_HIEU_LUC:
                    financingLimits = financingLimitList.stream().filter(x-> x.getExpirationDate().compareTo(LocalDate.now()) < 0).collect(Collectors.toList());
                    break;
            }
        }
        return financingLimitResponseMapper.toDomain(financingLimits);
    }

    @Override
    public void delete(Long id, String userId) throws SendEmailException {
        Optional<FinancingLimit> financingLimitOptional = financingLimitRepository.findById(id);
        if(financingLimitOptional.isPresent())
        {
            FinancingLimit financingLimit = financingLimitOptional.get();
            if(financingLimit.getTotalCommitmentLimit() > 0 || financingLimit.getTotalCommitmentLimit() == null)
            {
                throw new LCPlatformException(ResponseCode.BAD_REQUEST, "financing limit can not delete");
            }
            if(financingLimit.getStatus() != 3)
            {
                throw new LCPlatformException(ResponseCode.BAD_REQUEST, "financing limit can not delete");
            }
            financingLimit.setStatus(1);
            financingLimitRepository.save(financingLimit);
            UserInfoEntity userInfo = userInfoService.getUserInfo(userId);
            Email email = emailService.getEmail081(financingLimit.getFinancingLimitCode(), userInfo.getEmail());
            emailService.sendEmailWithTemplate(email);
        }
    }

    public boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    @Override
    public FinancingLimitResponse update(String userId, FinancingLimitRequest financingLimitRequest) throws SendEmailException {
        Optional<FinancingLimit> financingLimitOptional = financingLimitRepository.findById(financingLimitRequest.getId());
        if(financingLimitOptional.isPresent())
        {
            FinancingLimit financingLimit = financingLimitOptional.get();
            int[] arrChekStatus = new int[]{2,3};
            if(!this.contains(arrChekStatus, financingLimit.getStatus()))
            {
               throw new LCPlatformException(ResponseCode.BAD_REQUEST, "Can not update");
            }
            if(financingLimit.getStatus() == 2){
                financingLimitRequest.setStatus(1);
                financingLimitRequest.setFinancingLimitCode(financingLimit.getFinancingLimitCode());
                ModelMapperUtils.mapper(financingLimitRequest, financingLimit);
                UserInfoEntity userInfo = userInfoService.getUserInfo(userId);
                BankInfo sponsorbank = bankInfoRepository.findBankInfoByBankCode(userInfo.getBankCode());
                financingLimit.setSponsorBank(sponsorbank);
               if(bankInfoRepository.findById(financingLimitRequest.getBankId()).isPresent()){
                   financingLimit.setBankInfo( bankInfoRepository.findById(financingLimitRequest.getBankId()).get());
               }
                financingLimitRepository.save(financingLimit);
            }
            if(financingLimit.getStatus() == 3)
            {
                financingLimit.setNumberOfChanges(financingLimit.getNumberOfChanges() + 1);
                financingLimit.setContractNumberLimit(financingLimitRequest.getContractNumberLimit());
                financingLimit.setDateRange(financingLimitRequest.getDateRange());
                financingLimit.setExpirationDate(financingLimitRequest.getExpirationDate());
                financingLimit.setTotalLimit(financingLimitRequest.getTotalLimit());
                financingLimit.setRequestARefund(financingLimitRequest.getRequestARefund());
                financingLimit.setStatus(1);
                financingLimitRepository.save(financingLimit);
                this.saveFinancingLimitChangeHistory(financingLimit, financingLimitRequest);
            }
            UserInfoEntity userInfo = userInfoService.getUserInfo(userId);
            Email email = emailService.getEmail075(financingLimit.getFinancingLimitCode(), userInfo.getEmail());
            emailService.sendEmailWithTemplate(email);
            return financingLimitResponseMapper.toDomain(financingLimit);
        }
        return null;
    }

    @Override
    public FinancingLimitResponse view(Long id)
    {
        Optional<FinancingLimit> financingLimitOptional = financingLimitRepository.findById(id);
        if(financingLimitOptional.isPresent()){
            FinancingLimit financingLimit = financingLimitOptional.get();
            this.totalFinancingForView(financingLimit);
            return financingLimitResponseMapper.toDomain(financingLimit);
        }
        return null;
    }

    public void totalFinancingForView(FinancingLimit financingLimit)
    {
        List<FinancingLimitTransaction> financingLimitTransactionList = financingLimitTransactionRepository.findAllByFinancingLimitTransaction(financingLimit);
        financingLimit.setTotalZoningLimit(financingLimitTransactionList.stream()
                .filter(x -> x.getZoningLimit() != null)
                .mapToLong(FinancingLimitTransaction::getZoningLimit)
                .sum());
        financingLimit.setTotalCommitmentLimit(financingLimitTransactionList.stream()
                .filter(x -> x.getCommitmentLimit() != null)
                .mapToLong(FinancingLimitTransaction::getCommitmentLimit)
                .sum());
        financingLimit.setTotalDisbursementAmount(financingLimitTransactionList.stream()
                .filter(x -> x.getDisbursementAmount() != null)
                .mapToLong(FinancingLimitTransaction::getDisbursementAmount)
                .sum());
        financingLimit.setTotalRepaymentAmount(financingLimitTransactionList.stream()
                .filter(x -> x.getRepaymentAmount() != null)
                .mapToLong(FinancingLimitTransaction::getRepaymentAmount)
                .sum());
        financingLimit.setAvailabilityLimit(financingLimit.getTotalLimit() - (financingLimit.getTotalZoningLimit() + financingLimit.getTotalCommitmentLimit() +
                financingLimit.getTotalDisbursementAmount()) + financingLimit.getTotalRepaymentAmount());
        financingLimitRepository.save(financingLimit);
    }

    public String convertDate()
    {
        String pattern = "ddMMyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

}
