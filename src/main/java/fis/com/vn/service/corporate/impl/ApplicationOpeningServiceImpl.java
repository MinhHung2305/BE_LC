package fis.com.vn.service.corporate.impl;

import com.lowagie.text.pdf.BaseFont;
import fis.com.vn.config.MinioConfig;
import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.model.entity.*;
import fis.com.vn.model.enumerate.ApplicationOpeningLcContractType;
import fis.com.vn.model.enumerate.ApplicationOpeningLcStatus;
import fis.com.vn.model.enumerate.LcType;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.repository.*;
import fis.com.vn.rest.mapper.*;
import fis.com.vn.rest.request.*;
import fis.com.vn.rest.response.*;
import fis.com.vn.service.admin.LicenseService;
import fis.com.vn.service.common.UserInfoService;
import fis.com.vn.service.corporate.ApplicationOpeningLcService;
import fis.com.vn.service.corporate.ContractLicenseService;
import fis.com.vn.service.corporate.ProductService;
import fis.com.vn.service.file.MinioService;
import fis.com.vn.service.notification.EmailService;
import fis.com.vn.service.notification.TemplateService;
import fis.com.vn.service.ocr.OcrService;
import fis.com.vn.util.Constant;
import fis.com.vn.util.FilesUtils;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class ApplicationOpeningServiceImpl implements ApplicationOpeningLcService {
    @Autowired
    private ApplicationOpeningLcRepository applicationOpeningLcRepository;

    @Autowired
    private ApplicationOpeningLcRequestMapper applicationOpeningLcRequestMapper;

    @Autowired
    private ApplicationOpeningLcResponseMapper applicationOpeningLcResponseMapper;


    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ProductRequestMapper productRequestMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ContractLicenseRepository contractLicenseRepository;

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioService minioService;

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private BankInfoRepository bankInfoRepository;

    @Autowired
    private CorporateRepository corporateRepository;

    @Autowired
    private CorporateAccountRepository corporateAccountRepository;

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private LicenseService licenseService;

    @Autowired
    private LicenseRequestMapper licenseRequestMapper;

    @Autowired
    private OcrService ocrService;

    @Autowired
    TemplateService templateService;

    @Autowired
    EmailService emailService;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    ContractLicenseService contractLicenseService;

    @Autowired
    ContractLicenseResponseMapper contractLicenseResponseMapper;

    @Autowired
    LicenseResponseMapper licenseResponseMapper;

    @Autowired
    CommodityRepository commodityRepository;


    @Override
    public List<ApplicationOpeningLcResponse> search(String userId, ApplicationOpeningLcSearchRequest applicationOpeningLcSearchRequest) {
        Corporate corporate = userInfoService.getUserLogin(userId).getCorporate();
        List<ApplicationOpeningLc> applicationOpeningLcList = applicationOpeningLcRepository.search(applicationOpeningLcSearchRequest.getProposalCodeRelease(),
                applicationOpeningLcSearchRequest.getStatus(), applicationOpeningLcSearchRequest.getTimeFrom(), applicationOpeningLcSearchRequest.getTimeTo(), applicationOpeningLcSearchRequest.getBankId(),
                corporate.getCorporateId(), applicationOpeningLcSearchRequest.getCorporateSellId(), applicationOpeningLcSearchRequest.getValueLcFrom(), applicationOpeningLcSearchRequest.getValueLcTo());
        return applicationOpeningLcResponseMapper.toDomain(applicationOpeningLcList);
    }

    @Override
    public ApplicationOpeningLcResponse create(String userId, ApplicationOpeningLcRequest applicationOpeningLcRequest, MultipartFile files) throws LCPlatformException, SendEmailException {
        if (applicationOpeningLcRequest.getProposalCodeRelease() != null && applicationOpeningLcRepository.existsByProposalCodeRelease(applicationOpeningLcRequest.getProposalCodeRelease())) {
            throw new LCPlatformException(ResponseCode.BAD_REQUEST, "ProposalCodeRelease has exits");
        }

        if(files != null){
            try {
                String filePath = FilesUtils.removeAccent(this.uploadFile(files));
                applicationOpeningLcRequest.setContractFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(applicationOpeningLcRequest.getContractType() == ApplicationOpeningLcContractType.DO_NOT_SIGN_IN_FPT_LC_PLATFORM.getValue())
        {
            applicationOpeningLcRequest.setContractCode(null);
        }
        ApplicationOpeningLc applicationOpeningLc = applicationOpeningLcRequestMapper.toEntity(applicationOpeningLcRequest);
        if(Long.parseLong(applicationOpeningLcRequest.getLcType()) == 1){
            applicationOpeningLc.setStatus(2);
        }
        if(Long.parseLong(applicationOpeningLcRequest.getLcType()) == 2){
            applicationOpeningLc.setStatus(10);
        }
        if(applicationOpeningLcRequest.getBankId() != null && bankInfoRepository.findById(applicationOpeningLcRequest.getBankId()).isPresent()){
            applicationOpeningLc.setBankInfo(bankInfoRepository.findById(applicationOpeningLcRequest.getBankId()).get());
        }
        if(applicationOpeningLcRequest.getBankIdConfirmRequest() != null && bankInfoRepository.findById(applicationOpeningLcRequest.getBankIdConfirmRequest()).isPresent()){
            applicationOpeningLc.setBankConfirmRequest(bankInfoRepository.findById(applicationOpeningLcRequest.getBankIdConfirmRequest()).get());
        }
        if(applicationOpeningLcRequest.getBankIdPresentationAt() != null && bankInfoRepository.findById(applicationOpeningLcRequest.getBankIdPresentationAt()).isPresent()){
            applicationOpeningLc.setBankPresentationAt(bankInfoRepository.findById(applicationOpeningLcRequest.getBankIdPresentationAt()).get());
        }
        if(applicationOpeningLcRequest.getCorporateBuyId() != null && corporateRepository.findById(applicationOpeningLcRequest.getCorporateBuyId()).isPresent()){
            applicationOpeningLc.setCorporateBuy(corporateRepository.findById(applicationOpeningLcRequest.getCorporateBuyId()).get());
        }
        if(applicationOpeningLcRequest.getCorporateSellId() != null && corporateRepository.findById(applicationOpeningLcRequest.getCorporateSellId()).isPresent()){
            applicationOpeningLc.setCorporateSell(corporateRepository.findById(applicationOpeningLcRequest.getCorporateSellId()).get());
        }
        if(bankInfoRepository.findById(applicationOpeningLcRequest.getBankConfirmId()).isPresent()){
            applicationOpeningLc.setBankConfirm(bankInfoRepository.findById(applicationOpeningLcRequest.getBankConfirmId()).get());
        }
        if(applicationOpeningLcRequest.getBankTranferId() != null && bankInfoRepository.findById(applicationOpeningLcRequest.getBankTranferId()).isPresent()){
            applicationOpeningLc.setBankTranfer(bankInfoRepository.findById(applicationOpeningLcRequest.getBankTranferId()).get());
        }
        if(applicationOpeningLcRequest.getHoldAccountId() != null && corporateAccountRepository.findById(applicationOpeningLcRequest.getHoldAccountId()).isPresent())
        {
            applicationOpeningLc.setHoldAccount(corporateAccountRepository.findById(applicationOpeningLcRequest.getHoldAccountId()).get());
        }
        if(applicationOpeningLcRequest.getFeeAccountId() != null && corporateAccountRepository.findById(applicationOpeningLcRequest.getFeeAccountId()).isPresent())
        {
            applicationOpeningLc.setFeeAccount(corporateAccountRepository.findById(applicationOpeningLcRequest.getFeeAccountId()).get());
        }
        if(applicationOpeningLcRequest.getPaymentAccountId() != null)
        {
            applicationOpeningLc.setPaymentAccount(corporateAccountRepository.findById(applicationOpeningLcRequest.getPaymentAccountId()).get());
        }
        applicationOpeningLc.setCorporateCreate(userInfoService.getUserLogin(userId).getCorporate().getCorporateId());
        applicationOpeningLcRepository.save(applicationOpeningLc);
        applicationOpeningLc.setProposalCodeRelease("DNPH." + userInfoService.getUserLogin(userId).getCorporate().getCorporateCode() + "." +
                this.convertDate() + "." + this.randomNumber(applicationOpeningLc.getId()));
        applicationOpeningLcRepository.save(applicationOpeningLc);
        ApplicationOpeningLcResponse applicationOpeningLcResponse = applicationOpeningLcResponseMapper.toDomain(applicationOpeningLc);
        List<Product> products = new ArrayList<>();
        List<LicenseCustomResponse> licenses = new ArrayList<>();
//        List<ContractLicense> contractLicenseList = new ArrayList<>();


        if (applicationOpeningLc.getContractType() == ApplicationOpeningLcContractType.SIGN_IN_FPT_LC_PLATFORM.getValue()) {
            Contract contract = contractRepository.findByContractCode(applicationOpeningLc.getContractCode());
            if (!applicationOpeningLcRequest.getProductsRequest().isEmpty()) {
                for (ProductRequest productRequest : applicationOpeningLcRequest.getProductsRequest()) {
                    productRequest.setId(null);
                    Product product = new Product();
                    product = productRequestMapper.toEntity(productRequest);
                    product.setContract(contract);
                    product.setApplicationOpeningLc(applicationOpeningLc);
                    productRepository.save(product);
                    products.add(product);
                }
            }
        }

        if (applicationOpeningLc.getContractType() == ApplicationOpeningLcContractType.DO_NOT_SIGN_IN_FPT_LC_PLATFORM.getValue()) {
            if(!applicationOpeningLcRequest.getProductsRequest().isEmpty()){
                for (ProductRequest productRequest: applicationOpeningLcRequest.getProductsRequest()) {
                    Product product = productRequestMapper.toEntity(productRequest);
                    product.setApplicationOpeningLc(applicationOpeningLc);
                    productRepository.save(product);
                    products.add(product);
                }
            }
//            if(applicationOpeningLcRequest.getLicenses().size() > 0){
//                for (License license : applicationOpeningLcRequest.getLicenses()) {
//                    ContractLicense contractLicense = new ContractLicense();
//                    contractLicense.setLicenseId(license.getLicenseId());
//                    contractLicense.setApplicationOpeingLcId(applicationOpeningLc.getId());
//                    contractLicenseRepository.save(contractLicense);
//                    licenses.add(license);
//                }
//            }

        }
        List<ContractLicense> contractLicenseList = contractLicenseService.insertContractLicenseForApplicationOpeningLc(applicationOpeningLcRequest.getListContractLicenseRequests(), applicationOpeningLc.getId());
//        for (ContractLicense contractLincense: contractLicenseList) {
//           License license = licenseRepository.getById(contractLincense.getLicenseId());
//           LicenseCustomResponse licenseResponse = new LicenseCustomResponse();
//           licenseResponse.setLicenseId(license.getLicenseId());
//           licenseResponse.setLicenseName(license.getLicenseName());
//           licenseResponse.setDescription(contractLincense.getLicenseDescription());
//           licenses.add(licenseResponse);
//        }
        applicationOpeningLcResponse.setProducts(products);
        applicationOpeningLcResponse.setListContractLicenses(contractLicenseList);
//        applicationOpeningLcResponse.setLicenses(licenses);
        this.generatePdfFile(applicationOpeningLcResponse, applicationOpeningLcRequest);
        UserInfoEntity userInfoOfAccountantCor = this.userInfoOfAccountantCor(userId, Constant.ACCOUNTANT);
        try{
            if(Integer.parseInt(applicationOpeningLc.getLcType()) == LcType.LC.getValue()){
                Email email = emailService.getEmail049(userInfoOfAccountantCor, applicationOpeningLc);
                emailService.sendEmailWithTemplate(email);
            }
            if(Integer.parseInt(applicationOpeningLc.getLcType()) == LcType.UPASLC.getValue()){
                Email email = emailService.getEmail035(applicationOpeningLc.getBankInfo().getBankName(), applicationOpeningLc.getCorporateBuy().getCorporateName(), userInfoOfAccountantCor.getEmail(), applicationOpeningLc.getProposalCodeRelease());
                emailService.sendEmailWithTemplate(email);
            }
        }catch (SendEmailException e)
        {
            throw new LCPlatformException(ResponseCode.BAD_REQUEST, "Corporate has no chief accountant");
        }

        return applicationOpeningLcResponse;
    }

    @Override
    public ApplicationOpeningLcResponse update(String userId, ApplicationOpeningLcRequest applicationOpeningLcRequest, MultipartFile files) throws LCPlatformException {
        Optional<ApplicationOpeningLc> applicationOpeningLcOptional = applicationOpeningLcRepository.findById(applicationOpeningLcRequest.getId());
        if (!applicationOpeningLcOptional.isPresent()) {
            return null;
        }
        ApplicationOpeningLc applicationOpeningLc = applicationOpeningLcOptional.get();
        int[] arr = new int[]{ApplicationOpeningLcStatus.INITIALIZATION.getValue(),ApplicationOpeningLcStatus.REFUSE_TO_SIGN.getValue(),ApplicationOpeningLcStatus.REFUSE_DRAFT.getValue()
                ,ApplicationOpeningLcStatus.REFUSE_SPONSOR.getValue(),ApplicationOpeningLcStatus.REFUSE_QUOTE.getValue()};
        if(!this.contains(arr, applicationOpeningLc.getStatus()))
        {
            throw new LCPlatformException(ResponseCode.CANNOT_UPDATE_LC);
        }
        int[] arrChekStatus = new int[]{ApplicationOpeningLcStatus.REFUSE_SPONSOR.getValue(),ApplicationOpeningLcStatus.REFUSE_QUOTE.getValue()};
        if(this.contains(arrChekStatus, applicationOpeningLc.getStatus()))
        {
            applicationOpeningLc.setStatus(10);
        }
        int[] arrChekStatus2 = new int[]{ApplicationOpeningLcStatus.REFUSE_TO_SIGN.getValue(), ApplicationOpeningLcStatus.REFUSE_DRAFT.getValue()};
        if(this.contains(arrChekStatus2, applicationOpeningLc.getStatus()))
        {
            if(Long.parseLong(applicationOpeningLc.getLcType()) == LcType.UPASLC.getValue()){
                throw new LCPlatformException(ResponseCode.CANNOT_UPDATE_LC_FOR_LC_UPAC);
            }
        }

        if(files != null){
            try {
                String filePath = FilesUtils.removeAccent(this.uploadFile(files));
                applicationOpeningLcRequest.setContractFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            applicationOpeningLcRequest.setContractFile(applicationOpeningLc.getContractFile());
        }

        if(applicationOpeningLcRequest.getContractType() == ApplicationOpeningLcContractType.SIGN_IN_FPT_LC_PLATFORM.getValue())
        {
            applicationOpeningLcRequest.setContractCode(null);
        }
        applicationOpeningLcRequest.setProposalCodeRelease(applicationOpeningLc.getProposalCodeRelease());
        applicationOpeningLcRequest.setStatus(2);
        applicationOpeningLcRequest.setCorporateCreate(applicationOpeningLc.getCorporateCreate());
        applicationOpeningLc = applicationOpeningLcRequestMapper.toEntity(applicationOpeningLcRequest);
        applicationOpeningLc.setBankInfo(bankInfoRepository.findById(applicationOpeningLcRequest.getBankId()).get());
        if(applicationOpeningLcRequest.getBankIdConfirmRequest() != null){
            applicationOpeningLc.setBankConfirmRequest(bankInfoRepository.findById(applicationOpeningLcRequest.getBankIdConfirmRequest()).get());
        }
        if(applicationOpeningLcRequest.getBankIdPresentationAt() != null){
            applicationOpeningLc.setBankPresentationAt(bankInfoRepository.findById(applicationOpeningLcRequest.getBankIdPresentationAt()).get());
        }
        if(applicationOpeningLcRequest.getCorporateBuyId() != null && corporateRepository.findById(applicationOpeningLcRequest.getCorporateBuyId()).isPresent()){
            applicationOpeningLc.setCorporateBuy(corporateRepository.findById(applicationOpeningLcRequest.getCorporateBuyId()).get());
        }
        if(applicationOpeningLcRequest.getCorporateSellId() != null && corporateRepository.findById(applicationOpeningLcRequest.getCorporateSellId()).isPresent()){
            applicationOpeningLc.setCorporateSell(corporateRepository.findById(applicationOpeningLcRequest.getCorporateSellId()).get());
        }
        applicationOpeningLc.setBankConfirm(bankInfoRepository.findById(applicationOpeningLcRequest.getBankConfirmId()).get());
        if(applicationOpeningLcRequest.getBankTranferId() != null && bankInfoRepository.findById(applicationOpeningLcRequest.getBankTranferId()).isPresent()){
            applicationOpeningLc.setBankTranfer(bankInfoRepository.findById(applicationOpeningLcRequest.getBankTranferId()).get());
        }
        if(applicationOpeningLcRequest.getHoldAccountId() != null)
        {
            applicationOpeningLc.setHoldAccount(corporateAccountRepository.findById(applicationOpeningLcRequest.getHoldAccountId()).get());
        }
        if(applicationOpeningLcRequest.getFeeAccountId() != null)
        {
            applicationOpeningLc.setFeeAccount(corporateAccountRepository.findById(applicationOpeningLcRequest.getFeeAccountId()).get());
        }
        if(applicationOpeningLcRequest.getPaymentAccountId() != null)
        {
            applicationOpeningLc.setPaymentAccount(corporateAccountRepository.findById(applicationOpeningLcRequest.getPaymentAccountId()).get());
        }
        applicationOpeningLcRepository.save(applicationOpeningLc);
        ApplicationOpeningLcResponse applicationOpeningLcResponse = applicationOpeningLcResponseMapper.toDomain(applicationOpeningLc);
        List<Product> products = new ArrayList<>();
        List<LicenseCustomResponse> licenses = new ArrayList<>();
        Contract contract = null;
        if (!applicationOpeningLcRequest.getProductsRequest().isEmpty()) {
            if(applicationOpeningLc.getContractType() == ApplicationOpeningLcContractType.SIGN_IN_FPT_LC_PLATFORM.getValue()){
                contract = contractRepository.findByContractCode(applicationOpeningLcRequest.getContractCode());
            }
            products = productService.insertOrUpdateProduct(productRequestMapper.toEntity(applicationOpeningLcRequest.getProductsRequest()), applicationOpeningLcRequest.getId(),
                    contract);
        }
        List<ContractLicense> contractLicenses = contractLicenseService.insertOrUpdateContractLicenseForApplicationOpeningLc(
                applicationOpeningLcRequest.getListContractLicenseRequests(), applicationOpeningLc.getId());
        for (ContractLicense contractLincense: contractLicenses) {
            License license = licenseRepository.getById(contractLincense.getLicenseId());
            LicenseCustomResponse licenseResponse = new LicenseCustomResponse();
            licenseResponse.setLicenseId(license.getLicenseId());
            licenseResponse.setLicenseName(license.getLicenseName());
            licenseResponse.setDescription(contractLincense.getLicenseDescription());
            licenses.add(licenseResponse);
        }
//        List<License> licenses = new ArrayList<>();
//        if (applicationOpeningLc.getContractType() == 2) {
//
//            if (applicationOpeningLcRequest.getProductsRequest().size() > 0) {
//                products = productService.insertOrUpdateProduct(productRequestMapper.toEntity(applicationOpeningLcRequest.getProductsRequest()),
//                        applicationOpeningLcRequest.getId());
//            }
//
//            licenses = licenseService.insertOrUpdateLicense(applicationOpeningLcRequest.getLicenses(), applicationOpeningLcRequest.getId());
//        }
        applicationOpeningLcResponse.setProducts(products);
        applicationOpeningLcResponse.setLicenses(licenses);
        this.generatePdfFile(applicationOpeningLcResponse, applicationOpeningLcRequest);
        if(this.contains(arrChekStatus2, applicationOpeningLc.getStatus()))
        {
            UserInfoEntity userInfoOfAccountantCor = this.userInfoOfAccountantCor(userId, Constant.ACCOUNTANT);
            try{
                Email email = emailService.getEmail049b(userInfoOfAccountantCor, applicationOpeningLc);
                emailService.sendEmailWithTemplate(email);
            }catch (SendEmailException e)
            {
                throw new LCPlatformException(ResponseCode.BAD_REQUEST, "Corporate has no chief accountant");
            }
        }
        return applicationOpeningLcResponse;
    }

    public UserInfoEntity userInfoOfAccountantCor(String userId, String position)
    {
        return userInfoRepository.findUserAccountant(userInfoService.getUserLogin(userId).getCorporate().getCorporateId(),position);
    }


    @Override
    public void delete(Long id) throws LCPlatformException {
        try {
            Optional<ApplicationOpeningLc> applicationOpeningLc = applicationOpeningLcRepository.findById(id);
            if (applicationOpeningLc != null) {
                if (applicationOpeningLc.get().getStatus() != 1) {
                    throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                }
                productService.deleteProduct(productRepository.findAllByApplicationOpeningLcId(id));
                List<ContractLicense> contractLicenseList = contractLicenseRepository.findAllByApplicationOpeingLcId(id);
                for (ContractLicense contractLicense : contractLicenseList) {
                    contractLicenseRepository.delete(contractLicense);
                }
                applicationOpeningLcRepository.delete(applicationOpeningLc.get());
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info("Delete ApplicationOpeningLc error:" + e.getMessage());
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }

    }

    @Override
    public ApplicationOpeningLcResponse getApplicationOpeningLc(Long id) throws LCPlatformException {
        Optional<ApplicationOpeningLc> applicationOpeningLc = applicationOpeningLcRepository.findById(id);
        if (applicationOpeningLc.isPresent()) {
            ApplicationOpeningLc applicationOpeningLc1 = applicationOpeningLc.get();
            ApplicationOpeningLcResponse applicationOpeningLcResponse = applicationOpeningLcResponseMapper.toDomain(applicationOpeningLc1);
            List<ContractLicenseResponse> contractLicenseResponses = new ArrayList<>();
            List<ContractLicense> contractLicenseList = contractLicenseRepository.findAllByApplicationOpeingLcId(id);
            for (ContractLicense contractLicense : contractLicenseList) {
                ContractLicenseResponse contractLicenseRes = new ContractLicenseResponse();

                LicenseResponse licenseRes = licenseResponseMapper.toDomain(
                        licenseRepository.findById(contractLicense.getLicenseId()).get());
                contractLicenseRes.setLicenseResponse(licenseRes);
                contractLicenseRes.setContractLicenseId(contractLicense.getId());
                contractLicenseRes.setLicenseDescription(contractLicense.getLicenseDescription());

                contractLicenseResponses.add(contractLicenseRes);
            }
            applicationOpeningLcResponse.setContractLicenseResponses(contractLicenseResponses);
//            List<License> licenses = licenseRepository.getListLicense(id);
//            applicationOpeningLcResponse.setLicenses(licenses);
            if(applicationOpeningLc1.getFileKySo() != null){
                applicationOpeningLcResponse.setUrlViewFileKySo(minioService.getObjectUrl(minioConfig.getBucketName(), applicationOpeningLc1.getFileKySo()));
            }

            if(applicationOpeningLc1.getContractFile() != null){
                applicationOpeningLcResponse.setUrlViewContractFile(minioService.getObjectUrl(minioConfig.getBucketName(), applicationOpeningLc1.getContractFile()));
            }
            return applicationOpeningLcResponse;
        }
        return null;
    }

    @Override
    public ApplicationOpeningLcResponse refuse(String userId, Long applicationOpeningLcId, String reasonForRefusal) throws LCPlatformException {
        ApplicationOpeningLc applicationOpeningLc = applicationOpeningLcRepository.findById(applicationOpeningLcId).get();
        applicationOpeningLc.setStatus(5);
        applicationOpeningLc.setReasonForRefusal(reasonForRefusal);
        applicationOpeningLcRepository.save(applicationOpeningLc);
        ApplicationOpeningLcResponse applicationOpeningLcResponse = applicationOpeningLcResponseMapper.toDomain(applicationOpeningLc);
        return applicationOpeningLcResponse;
    }

    @Override
    public ApplicationOpeningLcResponse cancel(String userId, Long applicationOpeningLcId) throws LCPlatformException {
        Optional<ApplicationOpeningLc> optionalApplicationOpeningLc = applicationOpeningLcRepository.findById(applicationOpeningLcId);
        if(optionalApplicationOpeningLc != null)
        {
            ApplicationOpeningLc applicationOpeningLc = optionalApplicationOpeningLc.get();
            int[] arr = new int[]{ApplicationOpeningLcStatus.REFUSE_TO_SIGN.getValue(),ApplicationOpeningLcStatus.REFUSE_DRAFT.getValue(),ApplicationOpeningLcStatus.REFUSE_SPONSOR.getValue()
                    ,ApplicationOpeningLcStatus.REFUSE_QUOTE.getValue()};
            if(!this.contains(arr, applicationOpeningLc.getStatus()))
            {
                throw new LCPlatformException(ResponseCode.BAD_REQUEST, "LC can't cancel");
            }
            int[] arrCheckLC = new int[]{ApplicationOpeningLcStatus.REFUSE_TO_SIGN.getValue(),ApplicationOpeningLcStatus.REFUSE_DRAFT.getValue()};
            if(this.contains(arrCheckLC, applicationOpeningLc.getStatus())){
                if(Integer.parseInt(applicationOpeningLc.getLcType()) == 2){
                    throw new LCPlatformException(ResponseCode.BAD_REQUEST, "LC can't cancel");
                }
            }
            applicationOpeningLc.setStatus(14);
            applicationOpeningLcRepository.save(applicationOpeningLc);
            ApplicationOpeningLcResponse applicationOpeningLcResponse = applicationOpeningLcResponseMapper.toDomain(applicationOpeningLc);
            return applicationOpeningLcResponse;
        }
        return null;
    }

    public boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    @Override
    public TransactionCodeResponse signInSignature(String userId, Long applicationOpeningLcId) throws LCPlatformException {
        ApplicationOpeningLc applicationOpeningLc = applicationOpeningLcRepository.findById(applicationOpeningLcId).get();
        List<UserInfoEntity> userInfoCreateLc = userInfoRepository.findByUserCode(applicationOpeningLc.getCreatedBy());
        if(userInfoCreateLc.size() > 0){
            UserInfoEntity userInfoLogin = userInfoService.getUserLogin(userId);
            if(!Objects.equals(userInfoLogin.getPosition(), Constant.POSITION_ACCOUNTANT)){
                throw new LCPlatformException(ResponseCode.BAD_REQUEST, "You do not have permission to digitally sign");
            }
            if(Objects.equals(userInfoLogin.getPosition(), Constant.POSITION_ACCOUNTANT)){
                if(!Objects.equals(userInfoLogin.getCorporate().getCorporateId(), userInfoCreateLc.get(0).getCorporate().getCorporateId())){
                    throw new LCPlatformException(ResponseCode.BAD_REQUEST, "You do not have permission to digitally sign");
                }
            }
//            try{
//                if(!(Objects.equals(userInfoLogin.getCorporate().getCorporateId(), userInfoCreateLc.get(0).getCorporate().getCorporateId()) &&
//                        Objects.equals(userInfoLogin.getPosition(), Constant.POSITION_ACCOUNTANT))){
//                    throw new LCPlatformException(ResponseCode.BAD_REQUEST, "You do not have permission to digitally sign");
//                }
//            }catch (LCPlatformException e)
//            {
//                throw new LCPlatformException(ResponseCode.BAD_REQUEST, "You do not have permission to digitally sign");
//            }
        }
        String maGiaoDich = ocrService.getTransactionId();
        System.out.println(maGiaoDich);
        UserInfoEntity userInfo = userInfoService.getUserInfo(userId);
        UserInfoForSignDigital userInfoForSignDigital = new UserInfoForSignDigital();
        Corporate corporate = corporateRepository.findById(userInfoService.getUserInfo(userId).getCorporate().getCorporateId()).get();
        userInfoForSignDigital.setFullName(userInfo.getUserName());
        userInfoForSignDigital.setIdentityNumber(userInfo.getIdentityNumber());
        userInfoForSignDigital.setAddress(corporate.getCorporateAddress());
        userInfoForSignDigital.setCity("Hà Nội");
        userInfoForSignDigital.setCountry("Việt Nam");
        userInfoForSignDigital.setSignPosition(Constant.DNPH_SIGN_POSITION);
        try (FileOutputStream fos = new FileOutputStream(applicationOpeningLc.getFileKySo())) {
            fos.write(this.getFile(applicationOpeningLc.getFileKySo()));
            File file = new File(applicationOpeningLc.getFileKySo());
            userInfoForSignDigital.setContractFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fos = new FileOutputStream(userInfo.getImageFrontOfIdentity())) {
            fos.write(this.getFile(userInfo.getImageFrontOfIdentity()));
            File file = new File(userInfo.getImageFrontOfIdentity());
            userInfoForSignDigital.setFilePathFrontIdentity(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fos = new FileOutputStream(userInfo.getImageBackOfIdentity())) {
            fos.write(this.getFile(userInfo.getImageBackOfIdentity()));
            File file = new File(userInfo.getImageBackOfIdentity());
            userInfoForSignDigital.setFilePahBackIdentity(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userInfoForSignDigital.setPhoneNumber(userInfo.getPhoneNumber());
        TransactionCodeResponse transactionCodeResponse = ocrService.signInSignature(userInfoForSignDigital);
        System.out.print(transactionCodeResponse);
        File file = new File(applicationOpeningLc.getFileKySo());
        File file2 = new File(userInfo.getImageFrontOfIdentity());
        File file3 = new File(userInfo.getImageBackOfIdentity());
        file.delete();
        file2.delete();
        file3.delete();
        return transactionCodeResponse;
    }

    @Override
    public ApplicationOpeningLcResponse signDigital(String userId, SignDigitalRequest signDigitalRequest) throws IOException, SendEmailException {
        SignDigitalResponse signDigitalResponse = ocrService.signDigital(signDigitalRequest);
        String content = signDigitalResponse.getData();
        ApplicationOpeningLc applicationOpeningLc = applicationOpeningLcRepository.findById(signDigitalRequest.getApplicationOpeningLcId()).get();
        String fileNameSignDigital = this.convertBase64ToFile(content, applicationOpeningLc.getFileKySo());
        applicationOpeningLc.setFileKySo(fileNameSignDigital);
        applicationOpeningLc.setStatus(3);
        applicationOpeningLc.setFileKySoBase64(content);
//        applicationOpeningLc.setStatusSignDigital(1);
        applicationOpeningLcRepository.save(applicationOpeningLc);
        UserInfoEntity userInfoOfAccountantCor = this.userInfoOfAccountantCor(userId, Constant.LEGAL_REPRESENTTATIVE);
        Email email = emailService.getEmail049(userInfoOfAccountantCor, applicationOpeningLc);
//        Email email = emailService.getEmail049(applicationOpeningLc.getBankInfo(), applicationOpeningLc.getCorporateBuy().getCorporateName(), applicationOpeningLc.getProposalCodeRelease());
        emailService.sendEmailWithTemplate(email);
        ApplicationOpeningLcResponse applicationOpeningLcResponse = applicationOpeningLcResponseMapper.toDomain(applicationOpeningLc);
        return applicationOpeningLcResponse;
    }

    public String convertBase64ToFile(String base64, String fileName) throws IOException {
        byte[] decodedString = Base64.getDecoder().decode(base64);
        System.out.println(new String(decodedString));
        try (FileOutputStream fos = new FileOutputStream(this.convertDate() + "_" + fileName)) {
            fos.write(decodedString);
            File file = new File(this.convertDate() + "_" + fileName);
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    file.getName(), "text/plain", IOUtils.toByteArray(input));
            this.uploadFile(multipartFile);
            file.delete();
            return this.convertDate() + "_" + fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GenFileResponse updateUrlFile(GenFileRequest genFileRequest) throws LCPlatformException, SendEmailException {
        ApplicationOpeningLc applicationOpeningLc = applicationOpeningLcRepository.getById(genFileRequest.getApplicationOpeningLcId());
        GenFileResponse genFileResponse = new GenFileResponse();
        String nameFile = genFileRequest.getFileName();
        String urlFile = "";
        //Draff file to folder: url + filename
        File file = new File(nameFile);
        try (FileOutputStream fos = new FileOutputStream(file);) {
            // To be short I use a corrupted PDF string, so make sure to use a valid one if you want to preview the PDF file
            byte[] decoder = Base64.getDecoder().decode(genFileRequest.getBase64());
            fos.write(decoder);
            urlFile = pushFileMinIO(nameFile);
            System.out.println("PDF File Saved");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!urlFile.isEmpty()) {
            // Chỉ lưu đường dẫn file trên minio, không lưu url
            applicationOpeningLc.setFileKySo(nameFile);
            applicationOpeningLc.setStatus(4);
            applicationOpeningLc.setFileKySoBase64(genFileRequest.getBase64());
            applicationOpeningLcRepository.save(applicationOpeningLc);

            genFileResponse.setUrlFile(urlFile);
            genFileResponse.setFile(file);
            genFileResponse.setBase64(genFileRequest.getBase64());
            genFileResponse.setPathFileInMinio(nameFile);
        }
        File f = new File(nameFile);
        f.delete();
        log.info("Url::", urlFile);
        List<UserInfoEntity> userInfoEntityApproverList = userInfoRepository.getApproverBank(applicationOpeningLc.getBankInfo().getBankId());
        List<UserInfoEntity> userMaker = userInfoRepository.findByUserCode(applicationOpeningLc.getCreatedBy());
        if(userInfoEntityApproverList.size() > 0 ){
            for (UserInfoEntity user: userInfoEntityApproverList) {
                Email email = emailService.getEmail051(applicationOpeningLc.getBankInfo(), applicationOpeningLc.getCorporateBuy().getCorporateName(), applicationOpeningLc.getProposalCodeRelease(), user, userMaker.get(0));
                emailService.sendEmailWithTemplate(email);
            }
        }
        return genFileResponse;
    }


    @Override
    public void generatePdfFile(ApplicationOpeningLcResponse applicationOpeningLcResponse, ApplicationOpeningLcRequest applicationOpeningLcRequest) throws LCPlatformException {
        String content = getContent(applicationOpeningLcResponse, applicationOpeningLcRequest);
        String pdfFileName = "DNPH_" + applicationOpeningLcResponse.getCorporateBuy().getCorporateCode() + "_" +
                this.randomNumber(applicationOpeningLcResponse.getId()) + "_" +this.convertDate() + ".pdf";
        try {
            exportFilePdf(pdfFileName, content);
            String fileName = pushFileMinIO(pdfFileName);
            ApplicationOpeningLc applicationOpeningLc = applicationOpeningLcRepository.findById(applicationOpeningLcResponse.getId()).get();
            applicationOpeningLc.setFileKySo(fileName);
            applicationOpeningLcRepository.save(applicationOpeningLc);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (com.lowagie.text.DocumentException e) {
            e.printStackTrace();
        }
    }

    private void exportFilePdf(final String pdfFileName, final String content) throws IOException, com.lowagie.text.DocumentException {
        FileOutputStream fileOutputStream = new FileOutputStream(pdfFileName);
        ITextRenderer renderer = new ITextRenderer();

        renderer.getFontResolver().addFont(getClass().getClassLoader().getResource("TimesNewRoman.ttf").toString(),
                BaseFont.IDENTITY_H,
                BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(content);
        renderer.layout();
        renderer.createPDF(fileOutputStream, false);
        renderer.finishPDF();
        fileOutputStream.close();
    }

    private String getProductResult(ApplicationOpeningLcResponse applicationOpeningLcResponse) {
        List<Product> listProduct = applicationOpeningLcResponse.getProducts();
        if (listProduct.size() == 0) {
            return "";
        }

        StringBuilder productResult = new StringBuilder("")
                .append("<tr><td colspan=\"2\"><table><tr><th>STT</th> <th>Mặt hàng</th> <th>Xuất sứ</th> <th>Số lượng</th> <th>Đơn vị</th> <th>Đơn giá</th><th>Thành tiền</th></tr>");
        for (int i = 1; i <= listProduct.size(); i++) {
            Product product = listProduct.get(i - 1);
            productResult.append("<tr><td>").append(i).append("</td>")
                    .append("<td>").append(product.getCommodity()).append("</td>")
                    .append("<td>").append(product.getOrigin()).append("</td>")
                    .append("<td>").append(String.format(Locale.GERMAN, "%,d",product.getAmount())).append("</td>")
                    .append("<td>").append(product.getUnit()).append("</td>")
                    .append("<td>").append(String.format(Locale.GERMAN, "%,d",product.getUnitPrice())).append("</td>")
                    .append("<td>").append(String.format(Locale.GERMAN, "%,d",product.getIntoMoney())).append("</td>")
                    .append("</tr>");
        }
        productResult.append("</table></td></tr>");

        String line = productResult.toString();
        byte[] byteText = line.getBytes(StandardCharsets.UTF_8);
        return new String(byteText, StandardCharsets.UTF_8);
    }

    private String licenseResult(ApplicationOpeningLcResponse applicationOpeningLcResponse)
    {
        List<LicenseCustomResponse> licenseList = applicationOpeningLcResponse.getLicenses();
        if (licenseList.size() == 0) {
            return "";
        }

        StringBuilder licenseResult = new StringBuilder("")
                .append("<tr><td colspan=\"2\"><table><tr><th>STT</th> <th>Tên chứng từ</th></tr>");
        for (int i = 1; i <= licenseList.size(); i++) {
            LicenseCustomResponse license = licenseList.get(i - 1);
            licenseResult.append("<tr><th>").append(i).append("</th>")
                    .append("<th>").append(license.getLicenseName()).append("</th>")
                    .append("</tr>");
        }
        licenseResult.append("</table></td></tr>");

        String line = licenseResult.toString();
        byte[] byteText = line.getBytes(StandardCharsets.UTF_8);
        return new String(byteText, StandardCharsets.UTF_8);
    }

    private String getListLicenseResult(ApplicationOpeningLcRequest contractReq) {
        List<ContractLicenseRequest> listLicense = contractReq.getListContractLicenseRequests();
        if (listLicense == null) {
            return "";
        }

        StringBuilder productResult = new StringBuilder("")
                .append("<tr><td colspan=\"2\"><table><tr><th>STT</th> <th>Tên chứng từ</th><th>Mô tả chứng từ</th> </tr>");
        for (int i = 1; i <= listLicense.size(); i++) {
            ContractLicenseRequest license = listLicense.get(i - 1);
            productResult.append("<tr><td>").append(i).append("</td>")
                    .append("<td>").append(HtmlUtils.htmlEscape(license.getLicenseRequest().getLicenseName())).append("</td>")
                    .append("<td>").append(license.getLicenseDescription() != null ? HtmlUtils.htmlEscape(license.getLicenseDescription()) : "").append("</td>")
                    .append("</tr>");
        }
        productResult.append("</table></td></tr>");

        String line = productResult.toString();
        byte[] byteText = line.getBytes(StandardCharsets.UTF_8);
        return new String(byteText, StandardCharsets.UTF_8);
    }

    private String getContent(ApplicationOpeningLcResponse applicationOpeningLcResponse, ApplicationOpeningLcRequest applicationOpeningLcRequest) {
        Template template = templateService.getTemplateByCode(Constant.TEMPLATE_CODE_APPLICATIONOPENINGLC);
        String content = template.getTemplateContent();
        String productResult = getProductResult(applicationOpeningLcResponse);
        String licenseResult = this.getListLicenseResult(applicationOpeningLcRequest);
        String confirmationInstruction = "";
        String lcType = "";
        Long aLong = Long.valueOf(applicationOpeningLcResponse.getLcType());
        if (aLong == 1) {
            lcType = LcType.LC.getName();
        } else if (aLong == 2) {
            lcType = LcType.UPASLC.getName();
        }
        switch (applicationOpeningLcResponse.getConfirmationInstruction()) {
            case 0:
                confirmationInstruction = Constant.CONFIRMATION_INSTRUCTION_1;
                break;
            case 1:
                confirmationInstruction = Constant.CONFIRMATION_INSTRUCTION_2;
                break;
            case 2:
                confirmationInstruction = Constant.CONFIRMATION_INSTRUCTION_3;
                break;
        }
        String termOfPayment = "";
        switch (applicationOpeningLcResponse.getTermOfPayment()) {
            case 1:
                termOfPayment = Constant.TERM_OF_PAYMENT_1;
                break;
            case 2:
                termOfPayment = Constant.TERM_OF_PAYMENT_2;
                break;
            case 3:
                termOfPayment = Constant.TERM_OF_PAYMENT_3;
                break;
        }
        String partialShipment = "";
        if (applicationOpeningLcResponse.getPartialShipment() == true) {
            partialShipment = Constant.PARTIAL_SHIPMENT_1;
        }
        if (applicationOpeningLcResponse.getPartialShipment() == false) {
            partialShipment = Constant.PARTIAL_SHIPMENT_2;
        }
        String transhipment = "";
        if (applicationOpeningLcResponse.getTranshipment() == true) {
            transhipment = Constant.TRANSHIPMENT_1;
        }
        if(applicationOpeningLcResponse.getTranshipment() == false){
            transhipment = Constant.TRANSHIPMENT_2;
        }
        String holdAccount = "";
        String feeAcc = "";
        String paymentAcc = "";
        if(applicationOpeningLcResponse.getHoldAccount() != null)
        {
            holdAccount = applicationOpeningLcResponse.getHoldAccount().getCorporateAccountNumber();
        }
        if(applicationOpeningLcResponse.getFeeAccount() != null)
        {
            feeAcc = applicationOpeningLcResponse.getFeeAccount().getCorporateAccountNumber();
        }
        if(applicationOpeningLcResponse.getPaymentAccount() != null)
        {
            paymentAcc = applicationOpeningLcResponse.getPaymentAccount().getCorporateAccountNumber();
        }
        String presentationAt = "";
        if(Long.parseLong(applicationOpeningLcResponse.getPresentationAt()) == 0){
            presentationAt = Constant.PRESENTATIONAT_0;
        }
        if(Long.parseLong(applicationOpeningLcResponse.getPresentationAt()) == 1){
            presentationAt = Constant.PRESENTATIONAT_1;
        }
        if(Long.parseLong(applicationOpeningLcResponse.getPresentationAt()) == 2){
            presentationAt = Constant.PRESENTATIONAT_2;
        }
        String confirmingBankRequest = "";
        if(Long.parseLong(applicationOpeningLcResponse.getConfirmingBankRequest()) == 0){
            confirmingBankRequest = Constant.CONFIRMINGBANKREQUEST_0;

        }
        if(Long.parseLong(applicationOpeningLcResponse.getConfirmingBankRequest()) == 1){
            confirmingBankRequest = Constant.CONFIRMINGBANKREQUEST_1;
        }

        String bankTranferName = "";
        if(applicationOpeningLcResponse.getBankTranfer() != null){
            bankTranferName = applicationOpeningLcResponse.getBankTranfer().getSwiftCode() + "-" + applicationOpeningLcResponse.getBankTranfer().getBankName();
        }

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String descriptionOfGood = "";
        if(applicationOpeningLcResponse.getDescriptionOfGoods() != null){
            descriptionOfGood = HtmlUtils.htmlEscape(applicationOpeningLcResponse.getDescriptionOfGoods()).replaceAll("\n", "<br/>");
        }
//
        content = content.replace("$proposalCodeRelease", applicationOpeningLcResponse.getProposalCodeRelease())
                .replace("$corporateBuy", applicationOpeningLcResponse.getCorporateBuy().getCorporateName())
                .replace("$corporateCode", applicationOpeningLcResponse.getCorporateBuy().getCorporateCode())
                .replace("$corporate_buy_address", applicationOpeningLcResponse.getCorporateBuyAddress() != null ? applicationOpeningLcResponse.getCorporateBuyAddress() : "")
                .replace("$corporateSell", applicationOpeningLcResponse.getCorporateSell().getCorporateName())
                .replace("$corporate_sell_address", applicationOpeningLcResponse.getCorporateSellAddress() != null ? applicationOpeningLcResponse.getCorporateSellAddress() : "")
                .replace("$bankConfirm", applicationOpeningLcResponse.getBankConfirm().getSwiftCode() + "-" + applicationOpeningLcResponse.getBankConfirm().getBankName())
                .replace("$bank_confirm_address", applicationOpeningLcResponse.getBankConfirmAddress() != null ? applicationOpeningLcResponse.getBankConfirmAddress() : "")
                .replace("$lcType", lcType)
                .replace("$bankTranfer", bankTranferName)
                .replace("$confirmationInstruction", confirmationInstruction)
                .replace("$confirmingBankRequest", confirmingBankRequest)
                .replace("$valueLc", applicationOpeningLcResponse.getValueLc() != null ? String.format(Locale.GERMAN,"%,d",applicationOpeningLcResponse.getValueLc()) : "")
                .replace("$moneyType", applicationOpeningLcResponse.getMoneyType() != null ? applicationOpeningLcResponse.getMoneyType() : "")
                .replace("$negativeTolerance", applicationOpeningLcResponse.getNegativeTolerance() != null ? applicationOpeningLcResponse.getNegativeTolerance().toString() : "")
                .replace("$positiveTolerance", applicationOpeningLcResponse.getPositiveTolerance() != null ? String.valueOf(applicationOpeningLcResponse.getPositiveTolerance()) : "")
                .replace("$presentationAt", presentationAt)
                .replace("$termOfPayment", termOfPayment)
                .replace("$fee", applicationOpeningLcResponse.getFee() != null ? applicationOpeningLcResponse.getFee() : "")
                .replace("$paymentAmount", applicationOpeningLcResponse.getPaymentAmount() != null ? applicationOpeningLcResponse.getPaymentAmount() : "")
                .replace("$dueDate", applicationOpeningLcResponse.getDueDate() != null ? applicationOpeningLcResponse.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatters) : "")
                .replace("$dueAddress", applicationOpeningLcResponse.getDueAddress() != null ? applicationOpeningLcResponse.getDueAddress() : "")
                .replace("$partialShipment", partialShipment)
                .replace("$placeOfReceipt", applicationOpeningLcResponse.getPlaceOfReceipt() != null ? applicationOpeningLcResponse.getPlaceOfReceipt() : "")
                .replace("$placeOfDestination", applicationOpeningLcResponse.getPlaceOfDestination() != null ? applicationOpeningLcResponse.getPlaceOfDestination() : "")
                .replace("$portOfDeparture", applicationOpeningLcResponse.getPortOfDeparture() != null ? HtmlUtils.htmlEscape(applicationOpeningLcResponse.getPortOfDeparture()) : "")
                .replace("$portOfDestination", applicationOpeningLcResponse.getPortOfDestination() != null ? HtmlUtils.htmlEscape(applicationOpeningLcResponse.getPortOfDestination()) : "")
                .replace("$lastestDeliveryDate", applicationOpeningLcResponse.getLastestDeliveryDate() != null ? applicationOpeningLcResponse.getLastestDeliveryDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatters) : "")
                .replace("$deliveryTime", applicationOpeningLcResponse.getDeliveryTime() != null ? applicationOpeningLcResponse.getDeliveryTime(): "")
                .replace("$deliveryTerm", applicationOpeningLcResponse.getDeliveryTerm() != null ? HtmlUtils.htmlEscape(applicationOpeningLcResponse.getDeliveryTerm()) : "")
                .replace("$otherCondition", applicationOpeningLcResponse.getOtherCondition() != null ? HtmlUtils.htmlEscape(applicationOpeningLcResponse.getOtherCondition()) : "")
                .replace("$descriptionOfGoods", descriptionOfGood)
                .replace("$periodForPresentation", applicationOpeningLcResponse.getPeriodForPresentation() != null ? applicationOpeningLcResponse.getPeriodForPresentation() : "")
                .replace("$ttReimbursement", applicationOpeningLcResponse.getTtReimbursement() == true ? Constant.TTREIMBURSEMENT_1 : Constant.TTREIMBURSEMENT_2)
                .replace("$holdAccount", holdAccount)
                .replace("$paymentAccount", paymentAcc)
                .replace("$thuPhi", feeAcc)
                .replace("$commitmentCustomer", applicationOpeningLcResponse.getCommitmentCustomer() != null ? HtmlUtils.htmlEscape(applicationOpeningLcResponse.getCommitmentCustomer()) : "")
                .replace("$transhipment", transhipment)
                .replace("$listProduct", productResult)
                .replace("$confirming_bank_request", applicationOpeningLcResponse.getBankConfirmRequest() != null ? applicationOpeningLcResponse.getBankConfirmRequest().getBankName() : "")
                .replace("$note_term_of_payment", applicationOpeningLcResponse.getNoteTermOfPayment() != null ? applicationOpeningLcResponse.getNoteTermOfPayment() : "")
                .replace("$bank_presentation_at", applicationOpeningLcResponse.getBankPresentationAt() != null ? applicationOpeningLcResponse.getBankPresentationAt().getBankName() : "")
                .replace("$listLicense", licenseResult)
                .replace("$bank_info",applicationOpeningLcResponse.getBankInfo().getBankName())
                .replace("$contract_number",applicationOpeningLcResponse.getContractNumber())
                .replace("$product_type",commodityRepository.getCommoditiesByCommoditiesId(applicationOpeningLcResponse.getProduct_type()).getCommoditiesName())
                .replace("$total_value_product",String.format(Locale.GERMAN,"%,d",applicationOpeningLcResponse.getTotalValueProduct()))
                .replace("$vat_product",applicationOpeningLcResponse.getVatProduct().toString())
                .replace("$total_value_after_vat",String.format(Locale.GERMAN,"%,d",applicationOpeningLcResponse.getTotalValueProduct()));;
        return content;
    }

    private String pushFileMinIO(String pdfFileName) throws IOException {
        File file = new File(pdfFileName);
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(pdfFileName,
                file.getName(), "text/html", IOUtils.toByteArray(input));

//        String fileName = this.uploadFile(multipartFile);
        String fileName = "";
        try {
            fileName = this.uploadFile(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File f = new File(pdfFileName);
        f.delete();
        return fileName;

    }

    @Override
    public void uploadImage(MultipartFile files) {
        Corporate corporate = corporateRepository.findById(Long.valueOf(386)).get();
        try {
            String imageFront = this.uploadFile(files);
            corporate.setBackOfUser2DeputyIdentifyUrl(imageFront);
            corporateRepository.save(corporate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String randomNumber(Long id) {
//        int ranNum = ThreadLocalRandom.current().nextInt(000,999);
//        System.out.println("Random number is "+ranNum);
//        return ranNum;
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

    public String convertDate() {
        String pattern = "ddMMyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);
        return todayAsString;
    }

    public String uploadFile(MultipartFile files) throws IOException {
        String fileType = FilenameUtils.getExtension(files.getOriginalFilename());
        String filePath = files.getOriginalFilename();
        byte[] file = files.getBytes();
        String fileName = files.getOriginalFilename();
        try {
            if (minioConfig.isUploadFile()) {
                if (filePath.startsWith("/")) {
                    filePath = filePath.substring(1);
                }
                createBucketIfNotExist(minioConfig.getBucketName(), false);
                minioClient.putObject(
                        PutObjectArgs.builder().bucket(minioConfig.getBucketName()).object(filePath).stream(new ByteArrayInputStream(file), file.length, -1).build());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    private void createBucketIfNotExist(String bucketName, boolean objectLock) {
        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).objectLock(objectLock).build());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getFile(String filePath) {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(minioConfig.getBucketName())
                        .object(filePath)
                        .build())) {
            if (stream == null) {
                try (InputStream streamAgain = minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(minioConfig.getBucketName())
                                .object(File.separator + "crm-orig" + File.separator + filePath)
                                .build())) {
                    return IOUtils.toByteArray(streamAgain);
                }
            }
            return IOUtils.toByteArray(stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


