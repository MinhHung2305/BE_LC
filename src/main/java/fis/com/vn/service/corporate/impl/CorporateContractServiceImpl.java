package fis.com.vn.service.corporate.impl;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import fis.com.vn.config.MinioConfig;
import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.model.entity.*;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.model.enumerate.UserPosition;
import fis.com.vn.repository.*;
import fis.com.vn.rest.mapper.*;
import fis.com.vn.rest.request.ContractLicenseRequest;
import fis.com.vn.rest.request.ContractRequest;
import fis.com.vn.rest.request.GenFileRequest;
import fis.com.vn.rest.request.SignDigitalRequest;
import fis.com.vn.rest.response.*;
import fis.com.vn.service.AsyncSendEmailService;
import fis.com.vn.service.admin.LicenseService;
import fis.com.vn.service.common.UserInfoService;
import fis.com.vn.service.corporate.ContractAddendumService;
import fis.com.vn.service.corporate.ContractLicenseService;
import fis.com.vn.service.corporate.CorporateContractService;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CorporateContractServiceImpl implements CorporateContractService {
    @Autowired
    ContractRepository contractRepository;
    @Autowired
    ContractResponseMapper contractResponseMapper;
    @Autowired
    ContractRequestMapper contractRequestMapper;
    @Autowired
    LcClassifyRepository lcClassifyRepository;
    @Autowired
    CorporateRepository corporateRepository;
    @Autowired
    LicenseService licenseService;
    @Autowired
    CommodityRepository commodityRepository;
    @Autowired
    ContractLicenseRepository contractLicenseRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ContractAddendumRepository contractAddendumRepository;
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    UserInfoResponseMapper userInfoResponseMapper;
    @Autowired
    CorporateAccountRepository corporateAccountRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    LicenseRepository licenseRepository;
    @Autowired
    TemplateService templateService;
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioService minioService;
    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    OcrService ocrService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    ContractAddendumService contractAddendumService;
    @Autowired
    ContractLicenseService contractLicenseService;
    @Autowired
    ContractLicenseResponseMapper contractLicenseResponseMapper;
    @Autowired
    LicenseResponseMapper licenseResponseMapper;
    @Autowired
    private AsyncSendEmailService asyncSendEmailService;

    @Value("${url.save.pdf}")
    public String url;

    @Value("${minio.bucket-name}")
    public String bucketName;

    @Override
    public List<ContractResponse> getAllContract() throws LCPlatformException {
        List<Contract> listContract = contractRepository.findAll(Sort.by(Sort.Direction.DESC, "lastModifiedDate"));
        List<ContractResponse> listContractRes = contractResponseMapper.toDomain(listContract);
        return listContractRes;
    }

    @Override
    public List<ContractResponse> getAllContractByCorporateBuyer(Long corporateId) throws LCPlatformException {
        List<Contract> listContract = contractRepository.findAllContractByCorporateBuyer(corporateId);
        List<ContractResponse> listContractRes = contractResponseMapper.toDomain(listContract);
        return listContractRes;
    }

    @Override
    public List<ContractResponse> getAllContractByCorporateSeller(Long corporateId) throws LCPlatformException {
        List<Contract> listContract = contractRepository.findAllContractByCorporateSeller(corporateId);
        List<ContractResponse> listContractRes = contractResponseMapper.toDomain(listContract);
        return listContractRes;
    }

    @Override
    public ContractResponse getContractById(Integer id) throws LCPlatformException {
        Contract contract = contractRepository.getById(id);
        ContractResponse contractResponse = contractResponseMapper.toDomain(contract);

        UserInfoEntity userInfoSeller = userInfoRepository.getById(contractResponse.getRepresentativeSeller().longValue());
        UserInfoResponse userInfoResponseSeller = userInfoResponseMapper.toDomain(userInfoSeller);
        contractResponse.setUserInfoSeller(userInfoResponseSeller);

        UserInfoEntity userInfoBuyer = userInfoRepository.getById(contractResponse.getRepresentativeBuyer().longValue());
        UserInfoResponse userInfoResponseBuyer = userInfoResponseMapper.toDomain(userInfoBuyer);
        contractResponse.setUserInfoBuyer(userInfoResponseBuyer);

        CorporateAccount corporateAccount = corporateAccountRepository.getById(contract.getBankAccountId().longValue());
        contractResponse.setCorporateAccount(corporateAccount);

        List<Product> listProduct = productRepository.getAllProductByContract(id);
        contractResponse.setProducts(listProduct);

//        List<License> listLicense = licenseRepository.getListLicenseByContract(id);
//        contractResponse.setListLicence(listLicense);
        List<ContractLicenseResponse> contractLicenseResponses = new ArrayList<>();
        List<ContractLicense> contractLicenseList = contractLicenseRepository.findAllByContractId(id);
        for (ContractLicense contractLicense : contractLicenseList) {
            ContractLicenseResponse contractLicenseRes = new ContractLicenseResponse();

            LicenseResponse licenseRes = licenseResponseMapper.toDomain(
                    licenseRepository.findById(contractLicense.getLicenseId()).get());
            contractLicenseRes.setLicenseResponse(licenseRes);
            contractLicenseRes.setContractLicenseId(contractLicense.getId());
            contractLicenseRes.setLicenseDescription(contractLicense.getLicenseDescription());

            contractLicenseResponses.add(contractLicenseRes);
        }
        contractResponse.setListContractLicenses(contractLicenseResponses);

        try {
            contractResponse.setUrlMinio(minioService.getObjectUrl(bucketName, contract.getUrlMinio()));
        } catch (Exception e) {
            log.error("Error get url file::{}", e.getMessage());
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }

        return contractResponse;
    }

    @Override
    @Transactional
    public ContractResponse createContract(ContractRequest contractRequest) throws LCPlatformException {
        if (contractRequest.getContractNo() != null) {
            List<Contract> checkContract = contractRepository.findContractByContractNoAndCorporateBuyer(contractRequest.getContractNo(), Long.valueOf(contractRequest.getBuyerCorporateId()));
            if (!checkContract.isEmpty() && checkContract.size() > 0) {
                log.error("contractNo exits");
                throw new LCPlatformException(ResponseCode.CONFLICT);
            }
        }
        Contract contract = contractRequestMapper.toEntity(contractRequest);

        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
        List<Contract> contractList = contractRepository.findAll();
        List<Contract> contractCreatedTodayList = contractList.stream().filter(x -> x.getCreatedDate() != null
                && x.getCreatedDate().toLocalDate().compareTo(LocalDateTime.now().toLocalDate()) == 0).collect(Collectors.toList());

        Integer countContractCreatedToday = contractCreatedTodayList.size() + 1;

        String contractCode = "HD" + DATE_FORMATTER.format(LocalDate.now()) + String.format("%03d", countContractCreatedToday);
        contract.setContractCode(contractCode);

        LcClassify lcClassify = lcClassifyRepository.getById(contractRequest.getLcId());
        if (lcClassify.getId() == null) {
            log.error("lcClassify does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setLc(lcClassify);

        Corporate sellerCorporate = corporateRepository.getById((long) contractRequest.getSellerCorporateId());
        if (sellerCorporate.getCorporateId() == null) {
            log.error("sellerCorporate does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setSellerCorporate(sellerCorporate);

        Corporate buyerCorporate = corporateRepository.getById((long) contractRequest.getBuyerCorporateId());
        if (buyerCorporate.getCorporateId() == null) {
            log.error("buyerCorporate does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setBuyerCorporate(buyerCorporate);

        // UserId of the representative.
        contract.setRepresentativeSeller(contractRequest.getRepresentativeSeller());
        contract.setRepresentativeBuyer(contractRequest.getRepresentativeBuyer());

        Commodity commodity = commodityRepository.getById(contractRequest.getCommodityId());
        if (commodity.getCommoditiesId() == null) {
            log.error("Commodities does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setCommodity(commodity);

        contract = contractRepository.save(contract);

        ContractResponse contractResponse = contractResponseMapper.toDomain(contract);

        List<Product> listProduct = productService.saveAllProduct(contractRequest.getProducts(), contract.getContractId());
        contractResponse.setProducts(listProduct);
//        List<License> licenses = licenseService.insertOrUpdateLicenseContract(contractRequest.getListLicence(), contract.getContractId());
//        contractResponse.setListLicence(licenses);

        List<ContractAddendum> contractAddendumList = contractRequest.getContractAddendum();
        for (ContractAddendum contractAddendum : contractAddendumList) {
            contractAddendum.setContract(contract);
            contractAddendumRepository.save(contractAddendum);
        }
        contractResponse.setContractAddendum(contractAddendumList);

        List<ContractLicenseResponse> contractLicenses = contractLicenseResponseMapper.toDomain(contractLicenseService.insertOrUpdateContractLicense(
                contractRequest.getListContractLicenseRequests(), contract.getContractId()));
        contractResponse.setListContractLicenses(contractLicenses);

        return contractResponse;
    }

    @Override
    @Transactional
    public ContractResponse updateContract(ContractRequest contractRequest) throws LCPlatformException {
        List<Contract> checkContract = contractRepository.findContractByContractNoAndCorporateBuyer(contractRequest.getContractNo(), Long.valueOf(contractRequest.getBuyerCorporateId()));
        for (Contract contractCheck : checkContract) {
            if (!contractCheck.getContractId().equals(contractRequest.getContractId())) {
                log.error("contractNo exits");
                throw new LCPlatformException(ResponseCode.CONFLICT);
            }
        }

        Contract contractStatus = contractRepository.getById(contractRequest.getContractId());
        if (contractStatus.getStatus() == null) {
            log.error("No editing permission");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }

        Contract contract = contractRequestMapper.toEntity(contractRequest);
        contract.setCreatedDate(contractStatus.getCreatedDate());
        contract.setCreatedBy(contractStatus.getCreatedBy());

        contract.setContractCode(contractStatus.getContractCode());
        contract.setBuyerDigitalSignature(contractStatus.getBuyerDigitalSignature());
        contract.setBuyerDigitalSigningDate(contractStatus.getBuyerDigitalSigningDate());

        LcClassify lcClassify = lcClassifyRepository.getById(contractRequest.getLcId());
        if (lcClassify.getId() == null) {
            log.error("lcClassify does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setLc(lcClassify);

        Corporate sellerCorporate = corporateRepository.getById((long) contractRequest.getSellerCorporateId());
        if (sellerCorporate.getCorporateId() == null) {
            log.error("sellerCorporate does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setSellerCorporate(sellerCorporate);

        Corporate buyerCorporate = corporateRepository.getById((long) contractRequest.getBuyerCorporateId());
        if (buyerCorporate.getCorporateId() == null) {
            log.error("buyerCorporate does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setBuyerCorporate(buyerCorporate);

        // UserId of the representative.
        contract.setRepresentativeSeller(contractRequest.getRepresentativeSeller());
        contract.setRepresentativeBuyer(contractRequest.getRepresentativeBuyer());

        Commodity commodity = commodityRepository.getById(contractRequest.getCommodityId());
        if (commodity.getCommoditiesId() == null) {
            log.error("buyerCorporate does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setCommodity(commodity);

        contract.setReasonsForRefusingTheBuyer("");

        contract = contractRepository.save(contract);

        ContractResponse contractResponse = contractResponseMapper.toDomain(contract);

        List<Product> listProduct = productService.saveAllProduct(contractRequest.getProducts(), contract.getContractId());
        contractResponse.setProducts(listProduct);

//        List<License> licenses = licenseService.insertOrUpdateLicenseContract(contractRequest.getListLicence(), contract.getContractId());
//        contractResponse.setListLicence(licenses);

        List<ContractAddendum> contractAddendumList = contractAddendumService.insertOrUpdateContractAddendum(contractRequest.getContractAddendum(), contract.getContractId());
        contractResponse.setContractAddendum(contractAddendumList);

//        List<ContractAddendum> contractAddendumList = contractRequest.getContractAddendum();
//        for (ContractAddendum contractAddendum : contractAddendumList) {
//            contractAddendum.setContract(contract);
//            contractAddendumRepository.save(contractAddendum);
//        }
//        contractResponse.setContractAddendum(contractAddendumList);

        List<ContractLicenseResponse> contractLicenses = contractLicenseResponseMapper.toDomain(contractLicenseService.insertOrUpdateContractLicense(
                contractRequest.getListContractLicenseRequests(), contract.getContractId()));
        contractResponse.setListContractLicenses(contractLicenses);

        return contractResponse;
    }

    @Override
    @Transactional
    public void deleteContractById(Integer contractId) throws LCPlatformException {
        List<Product> listProduct = productRepository.getAllProductByContract(contractId);
        productRepository.deleteAll(listProduct);

        List<ContractLicense> listContractLicense = contractLicenseRepository.findAllByContractId(contractId);
        contractLicenseRepository.deleteAll(listContractLicense);

        Contract contract = contractRepository.getById(contractId);
        contractRepository.delete(contract);
    }

    @Override
    @Transactional
    public void changeStateContract(ContractRequest contractRequest) throws LCPlatformException, SendEmailException {
        Integer status = contractRequest.getStatus();
        if (status == null || contractRequest.getContractId() == null) {
            log.error("status does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }

        Contract contract = contractRepository.getById(contractRequest.getContractId());
        if (contract.getContractNo() == null) {
            contract.setContractNo("");
        }

        List<UserInfoEntity> listUserBuyerMaker = userInfoRepository.getUserInfoSendMailMaker(contract.getBuyerCorporate().getCorporateId(), Constant.MAKER);
        List<UserInfoEntity> listUserBuyerChecker = userInfoRepository.getUserInfoSendMailChecker(contract.getBuyerCorporate().getCorporateId(), Constant.CHECKER);
        List<UserInfoEntity> listUserSellerChecker = userInfoRepository.getUserInfoSendMailChecker(contract.getSellerCorporate().getCorporateId(), Constant.CHECKER);
        List<UserInfoEntity> listUserSellerMaker = userInfoRepository.getUserInfoSendMailMaker(contract.getSellerCorporate().getCorporateId(), Constant.MAKER);

        List<UserInfoEntity> listUserBuyerMaker_Checker = new ArrayList<>();
        listUserBuyerMaker_Checker.addAll(listUserBuyerMaker);
        listUserBuyerMaker_Checker.addAll(listUserBuyerChecker);

        List<UserInfoEntity> listUserMaker = new ArrayList<>();
        listUserMaker.addAll(listUserBuyerMaker);
        listUserMaker.addAll(listUserSellerMaker);

        List<UserInfoEntity> listUserChecker = new ArrayList<>();
        listUserChecker.addAll(listUserBuyerChecker);
        listUserChecker.addAll(listUserSellerChecker);

        List<UserInfoEntity> listCheckerBuyerMakerSeller = new ArrayList<>();
        listCheckerBuyerMakerSeller.addAll(listUserBuyerChecker);
        listCheckerBuyerMakerSeller.addAll(listUserSellerMaker);

        List<Email> listEmail = new ArrayList<>();
        try {
            switch (status) {
                case 0:
                    return;
                // 1 : Waiting for buyer's digital signature.
                // send email: E17A là email tạo mới, E17B là email chỉnh sửa -- nội dung 2 email giống nhau
                // gửi cho checker bên mua
                case 1:
                    contract.setStatus(1);
                    listEmail = emailService.getEmail017A(listUserBuyerChecker, contract);
                    asyncSendEmailService.sendEmailCM04(listEmail);

//                for (Email email : listEmail) {
//                    emailService.sendEmailWithTemplate(email);
//                }
                    break;
                // 2 : Refuse to digitally sign the buyer.
//            send eamil E20 từ chối bên mua -- gửi cho maker bên mua
                case 2:
                    contract.setReasonsForRefusingTheBuyer(contractRequest.getReasonsForRefusingTheBuyer());
                    contract.setStatus(2);
                    listEmail = emailService.getEmail020(listUserBuyerMaker, contract);
                    asyncSendEmailService.sendEmailCM04(listEmail);
//                    for (Email email : listEmail) {
//                        emailService.sendEmailWithTemplate(email);
//                    }
                    break;
                // 3 : Wait for confirmation.
                // send email E19 - gửi cho maker xác nhận bên bán -- chờ xác nhận hợp đồng
                case 3:
                    if (contractRequest.getBuyerDigitalSignature() == null) {
                        log.error("BuyerDigitalSignature does not exist in DB");
                        throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                    }
                    contract.setBuyerDigitalSignature(userInfoRepository.getById(Long.valueOf(contractRequest.getBuyerDigitalSignature().getId())));
                    contract.setBuyerDigitalSigningDate(contractRequest.getBuyerDigitalSigningDate());
                    contract.setReasonsForRefusingTheBuyer(contractRequest.getReasonsForRefusingTheBuyer());
                    contract.setStatus(3);
                    listEmail = emailService.getEmail019(listUserSellerMaker, contract);
                    asyncSendEmailService.sendEmailCM04(listEmail);
//                    for (Email email : listEmail) {
//                        emailService.sendEmailWithTemplate(email);
//                    }
                    break;
                // 4 : Refused to confirm.
                // send email E21A - gửi cho maker+checker bên mua và cc cho checker bên bán-- từ chối hợp đồng
                case 4:
                    if (contractRequest.getSellerVerifier() == null) {
                        log.error("SellerVerifier does not exist in DB");
                        throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                    }
                    contract.setSellerVerifier(userInfoRepository.getById(Long.valueOf(contractRequest.getSellerVerifier().getId())));
                    contract.setSellerConfirmationDate(contractRequest.getSellerConfirmationDate());
                    contract.setReasonsForRefusingTheSeller(contractRequest.getReasonsForRefusingTheSeller());

                    contract.setStatus(4);
                    listEmail = emailService.getEmail021A(listUserBuyerMaker, listUserChecker, contract);
                    asyncSendEmailService.sendEmailCM04(listEmail);
//                listEmail.addAll(emailService.getEmail021A(listUserBuyerChecker,listUserSellerChecker, contract.getContractCode()));
//                    for (Email email : listEmail) {
//                        emailService.sendEmailWithTemplate(email);
//                    }
                    break;
                // 5 : Waiting for the seller's digital signature.
                // send email E18 - gửi cho checker ký số bên bán -- chờ ký số hợp đồng
                case 5:
                    if (contractRequest.getSellerVerifier() == null) {
                        log.error("SellerVerifier does not exist in DB");
                        throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                    }
                    contract.setSellerVerifier(userInfoRepository.getById(Long.valueOf(contractRequest.getSellerVerifier().getId())));
                    contract.setSellerConfirmationDate(contractRequest.getSellerConfirmationDate());
                    contract.setReasonsForRefusingTheSeller(contractRequest.getReasonsForRefusingTheSeller());
                    contract.setStatus(5);
//                listEmail = emailService.getEmail018(listUserBuyerMaker, contract.getContractCode());
//                listEmail.addAll(emailService.getEmail018(listUserBuyerChecker, contract.getContractCode()));
//                listEmail.addAll(emailService.getEmail018(listUserSellerChecker, contract.getContractCode()));
                    listEmail = emailService.getEmail018(listUserSellerChecker, contract);
                    asyncSendEmailService.sendEmailCM04(listEmail);

//                    for (Email email : listEmail) {
//                        emailService.sendEmailWithTemplate(email);
//                    }
                    break;
                // 6 : Refuse to sign the seller's number.
                // send email E21B - gửi cho maker bên mua va cc macker ben ban, checker ben mua-- từ chối hợp đồng
                case 6:
                    if (contractRequest.getSellerDigitalSignature() == null) {
                        log.error("SellerDigitalSignature does not exist in DB");
                        throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                    }
                    contract.setSellerDigitalSignature(userInfoRepository.getById(Long.valueOf(contractRequest.getSellerDigitalSignature().getId())));
                    contract.setSellerDigitalSigningDate(contractRequest.getSellerDigitalSigningDate());
                    contract.setReasonsForRefusingTheSeller(contractRequest.getReasonsForRefusingTheSeller());
                    contract.setStatus(6);
                    listEmail = emailService.getEmail021B(listUserBuyerMaker, listCheckerBuyerMakerSeller, contract);
//                    listEmail.addAll(emailService.getEmail021B(listUserBuyerChecker, contract.getContractCode()));
//                    listEmail.addAll(emailService.getEmail021B(listUserSellerChecker, contract.getContractCode()));
                    asyncSendEmailService.sendEmailCM04(listEmail);
//
//                    for (Email email : listEmail) {
//                        emailService.sendEmailWithTemplate(email);
//                    }
                    break;
                // 7 : Signed digital signature.
                // send email E22 - gửi cho checker ký số bên mua và cc 2 maker-- đã hoàn thành hợp đồng
                case 7:
                    if (contractRequest.getSellerDigitalSignature() == null) {
                        log.error("SellerDigitalSignature does not exist in DB");
                        throw new LCPlatformException(ResponseCode.BAD_REQUEST);
                    }
                    contract.setSellerDigitalSignature(userInfoRepository.getById(Long.valueOf(contractRequest.getSellerDigitalSignature().getId())));
                    contract.setSellerDigitalSigningDate(contractRequest.getSellerDigitalSigningDate());
                    contract.setReasonsForRefusingTheSeller(contractRequest.getReasonsForRefusingTheSeller());
                    contract.setStatus(7);
//                    listEmail = emailService.getEmail022(listUserBuyerMaker, contract.getContractCode());
//                    listEmail.addAll(emailService.getEmail022(listUserBuyerChecker, contract.getContractCode()));
//                    listEmail.addAll(emailService.getEmail022(listUserSellerChecker, contract.getContractCode()));
                    listEmail = emailService.getEmail022(listUserBuyerChecker, listUserMaker, contract);

                    asyncSendEmailService.sendEmailCM04(listEmail);
//                    for (Email email : listEmail) {
//                        emailService.sendEmailWithTemplate(email);
//                    }
                    break;
                default:
                    log.error("Status Incorrect");
                    throw new LCPlatformException(ResponseCode.BAD_REQUEST);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        if (contract.getContractNo() == "") {
            contract.setContractNo(null);
        }
        contractRepository.save(contract);
    }

    @Override
    public String generatePdfFile(String pdfFileName, ContractRequest contractRequest) throws LCPlatformException {
        Contract contract = contractRequestMapper.toEntity(contractRequest);
        LcClassify lcClassify = lcClassifyRepository.getById(contractRequest.getLcId());
        if (lcClassify.getId() == null) {
            log.error("lcClassify does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setLc(lcClassify);
        Corporate sellerCorporate = corporateRepository.getById((long) contractRequest.getSellerCorporateId());
        if (sellerCorporate.getCorporateId() == null) {
            log.error("sellerCorporate does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setSellerCorporate(sellerCorporate);
        Corporate buyerCorporate = corporateRepository.getById((long) contractRequest.getBuyerCorporateId());
        if (buyerCorporate.getCorporateId() == null) {
            log.error("buyerCorporate does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setBuyerCorporate(buyerCorporate);
        // UserId of the representative.
        contract.setRepresentativeSeller(contractRequest.getRepresentativeSeller());
        contract.setRepresentativeBuyer(contractRequest.getRepresentativeBuyer());
        Commodity commodity = commodityRepository.getById(contractRequest.getCommodityId());
        if (commodity.getCommoditiesId() == null) {
            log.error("Commodities does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setCommodity(commodity);
        contract.setStatus(0);
        ContractResponse contractResponse = contractResponseMapper.toDomain(contract);
        contractResponse.setContractCode(contract.getContractCode());
        contractResponse.setProducts(contractRequest.getProducts());
        contractResponse.setListLicence(contractRequest.getListLicence());
        contractResponse.setContractAddendum(contractRequest.getContractAddendum());
        CorporateAccount corporateAccount = corporateAccountRepository.getById(contract.getBankAccountId().longValue());
        contractResponse.setCorporateAccount(corporateAccount);

        String content = getContent(contractResponse, contractRequest);

        try {
            //genfile PDF to folder: url + pdfFileName
            exportFilePdf(pdfFileName, content);
            String fileUrl = pushFileMinIO(pdfFileName);

            return fileUrl;
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * GenfilePDF to file , base64 and url
     *
     * @param pdfFileName     : Name file PDF,
     * @param contractRequest Contract Request,
     * @return GenFileResponse
     */
    @Override
    public GenFileResponse genPdfFile(String pdfFileName, ContractRequest contractRequest) throws LCPlatformException {
        GenFileResponse genFileResponse = new GenFileResponse();
        Contract contract = contractRequestMapper.toEntity(contractRequest);

        LcClassify lcClassify = lcClassifyRepository.getById(contractRequest.getLcId());
        if (lcClassify.getId() == null) {
            log.error("lcClassify does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setLc(lcClassify);
        Corporate sellerCorporate = corporateRepository.getById((long) contractRequest.getSellerCorporateId());
        if (sellerCorporate.getCorporateId() == null) {
            log.error("sellerCorporate does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setSellerCorporate(sellerCorporate);
        Corporate buyerCorporate = corporateRepository.getById((long) contractRequest.getBuyerCorporateId());
        if (buyerCorporate.getCorporateId() == null) {
            log.error("buyerCorporate does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setBuyerCorporate(buyerCorporate);
        // UserId of the representative.
        contract.setRepresentativeSeller(contractRequest.getRepresentativeSeller());
        contract.setRepresentativeBuyer(contractRequest.getRepresentativeBuyer());
        Commodity commodity = commodityRepository.getById(contractRequest.getCommodityId());
        if (commodity.getCommoditiesId() == null) {
            log.error("Commodities does not exist in DB");
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        contract.setCommodity(commodity);
        contract.setStatus(0);
        ContractResponse contractResponse = contractResponseMapper.toDomain(contract);
        contractResponse.setProducts(contractRequest.getProducts());
        contractResponse.setListLicence(contractRequest.getListLicence());
        contractResponse.setContractAddendum(contractRequest.getContractAddendum());
        CorporateAccount corporateAccount = corporateAccountRepository.getById(contract.getBankAccountId().longValue());
        contractResponse.setCorporateAccount(corporateAccount);

        String content = getContent(contractResponse, contractRequest);

        try {
            //genfile PDF to folder: url + pdfFileName
            log.info("begin exportFilePdf:::");
            exportFilePdf(pdfFileName, content);
            log.info("end exportFilePdf:::");

            String fileUrl = pushFileMinIO(pdfFileName);
            log.info("fileUrl:::{}", fileUrl);

            File file = new File(url + pdfFileName);
            String base64String = FilesUtils.convertFileToBase64(file);

            genFileResponse.setUrlFile(fileUrl);
            genFileResponse.setBase64(base64String);
            genFileResponse.setFile(file);
            genFileResponse.setPathFileInMinio(pdfFileName);

            file.delete();
            return genFileResponse;
        } catch (DocumentException | IOException | URISyntaxException e) {
            log.info(e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Contents of the sale and purchase contract.
     *
     * @param contract : Contract Info
     * @return : content
     */
    private String getContent(ContractResponse contract, ContractRequest contractRequest) {
        Template template = templateService.getTemplateByCode(Constant.TEMPLATE_CODE_SALE_CONTRACT);
        String content = template.getTemplateContent();
        String contractCode = getContractCodeResult(contract);
        UserInfoEntity representativeSeller = userInfoRepository.getById((long) contract.getRepresentativeSeller());
        UserInfoEntity representativeBuyer = userInfoRepository.getById((long) contract.getRepresentativeBuyer());
        String productResult = getProductResult(contract);
        String paymentMethods = getPaymentMethods(contract);
        String contractAddendum = getContractAddendum(contract);
        String listLicense = getListLicenseResult(contractRequest);

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String deliveryDeadline = "";
        if (contract.getDeliveryDeadline() != null) {
            deliveryDeadline = contract.getDeliveryDeadline().format(formatters);
        }
        int day = contract.getContractEstablishmentDate().getDayOfMonth();
        int month = contract.getContractEstablishmentDate().getMonthValue();
        int year = contract.getContractEstablishmentDate().getYear();
        String contractEstablishmentDate = "ngày " + day + " tháng " + month + " năm " + year;

        LocalDate localDate = LocalDate.now();
        int dayToDay = localDate.getDayOfMonth();
        int monthToDay = localDate.getMonthValue();
        int yearToDay = localDate.getYear();
        String toDay = "ngày " + dayToDay + " tháng " + monthToDay + " năm " + yearToDay + ".";
        String pursuantLaw = getPursuantLaw(contract);
        String descriptionCommodity = replaceHtml(contract.getDescriptionCommodity()).replaceAll("\n", "<br/>");

        content = content.replace("$toDay", toDay)
                .replace("$contractCode", replaceHtml(contractCode))
                .replace("$contractNo", replaceHtml(contract.getContractNo()))
                .replace("$pursuantLaw", pursuantLaw)
                .replace("$contractEstablishmentDate", replaceHtml(contractEstablishmentDate))
                .replace("$corporateNameSeller", replaceHtml(contract.getSellerCorporate().getCorporateName()))
                .replace("$corporateAddressSeller", replaceHtml(contract.getSellerAddress()))
                .replace("$representativeSeller", replaceHtml(representativeSeller.getUserName()))
                .replace("$positionSeller", UserPosition.nameOf(representativeSeller.getPosition()).getDescription())
                .replace("$corporateNameBuyer", replaceHtml(contract.getBuyerCorporate().getCorporateName()))
                .replace("$corporateAddressBuyer", replaceHtml(contract.getBuyerAddress()))
                .replace("$representativeBuyer", replaceHtml(representativeBuyer.getUserName()))
                .replace("$positionBuyer", UserPosition.nameOf(representativeBuyer.getPosition()).getDescription())
                .replace("$descriptionCommodity", descriptionCommodity)
                .replace("$listProduct", productResult)
                .replace("$contractValueBeforeVAT", String.valueOf(contract.getContractValueBeforeVat()))
                .replace("$contractVat", String.valueOf(contract.getContractVat()))
                .replace("$contractValue$", String.valueOf(contract.getContractValue()))
                .replace("$amountReductionTolerance", contract.getAmountReductionTolerance())
                .replace("$toleranceIncreaseAmount", contract.getToleranceIncreaseAmount())
                .replace("$deliveryVehicle", replaceHtml(contract.getDeliveryVehicle()))
                .replace("$deliveryTerm", replaceHtml(contract.getDeliveryTerm()))
                .replace("$deliveryDeadline", deliveryDeadline)
                .replace("$placeDelivery", replaceHtml(contract.getPlaceDelivery()))
                .replace("$deliveryLocation", replaceHtml(contract.getDeliveryLocation()))
                .replace("$productQuality", replaceHtml(contract.getProductQuality()))
                .replace("$termsOfExchange", replaceHtml(contract.getTermsOfExchange()))
                .replace("$goodsWarranty", replaceHtml(contract.getGoodsWarranty()))
                .replace("$transferPayments", replaceHtml(contract.getTransferPayments()))
                .replace("$paymentMethods", paymentMethods)
                .replace("$lcName", replaceHtml(contract.getLc().getLcName()))
                .replace("$paymentTermLc", replaceHtml(contract.getPaymentTermLc()))
                .replace("$lcPayment", replaceHtml(contract.getLcPayment()))
                .replace("$nameLcPayment", replaceHtml(contract.getLc().getLcName()))
                .replace("$bankName", replaceHtml(contract.getCorporateAccount().getBank().getBankName()))
                .replace("$corporateAccountNumber", replaceHtml(contract.getCorporateAccount().getCorporateAccountNumber()))
                .replace("$listLicense", listLicense)
                .replace("$latePaymentInterestRate", String.valueOf(contract.getLatePaymentInterestRate()))
                .replace("$cargoInsurance", replaceHtml(contract.getCargoInsurance()))
                .replace("$obligationsBuyer", replaceHtml(contract.getObligationsBuyer()))
                .replace("$obligationsSeller", replaceHtml(contract.getObligationsSeller()))
                .replace("$regulationsPenaltiesAndContractCompensation", replaceHtml(contract.getRegulationsPenaltiesAndContractCompensation()))
                .replace("$disputeSettlementProcedures", replaceHtml(contract.getDisputeSettlementProcedures()))
                .replace("$caseOfForceMajeure", replaceHtml(contract.getCaseOfForceMajeure()))
                .replace("$validityContract", replaceHtml(contract.getValidityContract()))
                .replace("$generalTerms", replaceHtml(contract.getGeneralTerms()))
                .replace("$currency", replaceHtml(contract.getCurrency()))
                .replace("$contractAddendum", contractAddendum);
        return content;
    }

    private String replaceHtml(String data) {
        if (data == null) {
            return "";
        }
        return HtmlUtils.htmlEscape(data);
    }

    private String getContractCodeResult(ContractResponse contract) {
        String contractCodeResult = "";
        if (contract.getContractCode() == null) {
            DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
            List<Contract> contractList = contractRepository.findAll();
            List<Contract> contractCreatedTodayList = contractList.stream().filter(x -> x.getCreatedDate() != null
                    && x.getCreatedDate().toLocalDate().compareTo(LocalDateTime.now().toLocalDate()) == 0).collect(Collectors.toList());

            Integer countContractCreatedToday = contractCreatedTodayList.size() + 1;

            contractCodeResult = "HD" + DATE_FORMATTER.format(LocalDate.now()) + String.format("%03d", countContractCreatedToday);
        } else {
            contractCodeResult = contract.getContractCode();
        }
        return contractCodeResult;
    }

    /**
     * get pursuantLaw for html.
     *
     * @param contract
     * @return : String contractAddendum html.
     */
    private String getPursuantLaw(ContractResponse contract) {
        StringBuffer contractPursuantLaw = new StringBuffer();
        if (contract.getPursuantLaw() != null) {
            contractPursuantLaw.append("<ul><li>").append(replaceHtml(contract.getPursuantLaw()).replaceAll("\n", "</li><li>"))
                    .append("</li></ul>");
//            replaceHtml(contract.getPursuantLaw()).replaceAll("\n", "<br/>");
        }
        String line = contractPursuantLaw.toString();
        byte[] byteText = line.getBytes(StandardCharsets.UTF_8);
        return new String(byteText, StandardCharsets.UTF_8);
    }

    private String getPaymentMethods(ContractResponse contract) {
        String paymentMethods = "";
        switch (contract.getPaymentMethods()) {
            case 1:
                paymentMethods = Constant.PAYMENT_METHOD_1;
                break;
            case 2:
                paymentMethods = Constant.PAYMENT_METHOD_2;
                break;
            case 3:
                paymentMethods = Constant.PAYMENT_METHOD_3;
                break;
        }
        return paymentMethods;
    }

    /**
     * get Contract Addendum for html.
     *
     * @param contract
     * @return : String contractAddendum html.
     */
    private String getContractAddendum(ContractResponse contract) {
        StringBuffer contractAddendumResult = new StringBuffer();
        if (contract.getContractAddendum() != null) {
            for (int i = 1; i <= contract.getContractAddendum().size(); i++) {
                ContractAddendum contractAddendum = contract.getContractAddendum().get(i - 1);
                String addendumContentStr = replaceHtml(contractAddendum.getAddendumContent()).replaceAll("\n", "<br/>");

                contractAddendumResult.append("<div style=\"text-align: center;\">")
                        .append("<span><b>PHỤ LỤC HỢP ĐỒNG SỐ ").append(contractAddendum.getAddendumNo()).append("</b></span></div>")
                        .append("<div style=\"text-align: left;\"><b>Phụ lục số : ").append(contractAddendum.getAddendumNo()).append("</b></div>")
                        .append("<div style=\"text-align: left;\"><p>Tên phụ lục hợp đồng: ").append(replaceHtml(contractAddendum.getAddendumName())).append("</p></div>")
                        .append("<div style=\"text-align: left;\"><p>Nội dung phụ lục hợp đồng: ").append(addendumContentStr).append("</p></div>");
            }
        }
        String line = contractAddendumResult.toString();
        byte[] byteText = line.getBytes(StandardCharsets.UTF_8);
        return new String(byteText, StandardCharsets.UTF_8);
    }

    /**
     * Generate Html product.
     *
     * @param contract
     * @return : String product.
     */
    private String getProductResult(ContractResponse contract) {
        List<Product> listProduct = contract.getProducts();
        if (listProduct == null) {
            return "";
        }

        StringBuilder productResult = new StringBuilder("<table>")
                .append("<tr> <th>STT</th> <th>Mặt hàng</th> <th>Xuất sứ</th> <th>Số lượng</th> <th>Đơn vị</th> <th>Đơn giá</th><th>Thành tiền</th></tr>");
        for (int i = 1; i <= listProduct.size(); i++) {
            Product product = listProduct.get(i - 1);
            productResult.append("<tr><th>").append(i).append("</th>")
                    .append("<th>").append(replaceHtml(product.getCommodity())).append("</th>")
                    .append("<th>").append(replaceHtml(product.getOrigin())).append("</th>")
                    .append("<th>").append(product.getAmount()).append("</th>")
                    .append("<th>").append(replaceHtml(product.getUnit())).append("</th>")
                    .append("<th>").append(product.getUnitPrice()).append("</th>")
                    .append("<th>").append(product.getIntoMoney()).append("</th>")
                    .append("</tr>");
        }
        productResult.append("</table>");

        String line = productResult.toString();
        byte[] byteText = line.getBytes(StandardCharsets.UTF_8);
        return new String(byteText, StandardCharsets.UTF_8);
    }


    /**
     * Generate Html product.
     *
     * @param contractReq
     * @return : String product.
     */
    private String getListLicenseResult(ContractRequest contractReq) {
        List<ContractLicenseRequest> listLicense = contractReq.getListContractLicenseRequests();
        if (listLicense == null) {
            return "";
        }

        StringBuilder productResult = new StringBuilder("<table>")
                .append("<tr> <th>STT</th> <th>Tên chứng từ</th> <th>Mô tả chứng từ</th> </tr>");
        for (int i = 1; i <= listLicense.size(); i++) {
            ContractLicenseRequest license = listLicense.get(i - 1);
            productResult.append("<tr><th>").append(i).append("</th>")
                    .append("<th>").append(replaceHtml(license.getLicenseRequest().getLicenseName())).append("</th>")
                    .append("<th>").append(replaceHtml(license.getLicenseDescription())).append("</th>")
                    .append("</tr>");
        }
        productResult.append("</table>");

        String line = productResult.toString();
        byte[] byteText = line.getBytes(StandardCharsets.UTF_8);
        return new String(byteText, StandardCharsets.UTF_8);
    }

    /**
     * Push file to MinIO
     *
     * @param pdfFileName : File Name when save.
     * @return : File Name in MinIO.
     * @throws IOException
     */
    private String pushFileMinIO(String pdfFileName) throws IOException {
        File file = new File(url + pdfFileName);
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(pdfFileName,
                file.getName(), "text/html", IOUtils.toByteArray(input));

        String fileName = minioService.uploadFile(multipartFile);
//        File f = new File(url + pdfFileName);
//        f.delete();
        return minioService.getObjectUrl(bucketName, fileName);

    }


    /**
     * Export File to Pdf after that save to MinIO.
     *
     * @param pdfFileName : File Name when save.
     * @param content     : Html file contract.
     * @throws IOException
     * @throws DocumentException
     */
    private void exportFilePdf(final String pdfFileName, final String content) throws IOException, DocumentException, URISyntaxException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(url + pdfFileName);
            ITextRenderer renderer = new ITextRenderer();

//        URL res = getClass().getClassLoader().getResource("TimesNewRoman.ttf");
//        File file = Paths.get(res.toURI()).toFile();
//        String absolutePath = file.getAbsolutePath();
//        log.info("absolutePath:::{}", absolutePath);

//        renderer.getFontResolver().addFont(absolutePath,
//                BaseFont.IDENTITY_H,
//                BaseFont.NOT_EMBEDDED);
            log.info("begin add font:::");
            String pathFont = getClass().getClassLoader().getResource("TimesNewRoman.ttf").toString();
            log.info("pathFont:::{}", pathFont);
            renderer.getFontResolver().addFont(pathFont, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            log.info("end add font:::");

            renderer.setDocumentFromString(content);
            renderer.layout();
            renderer.createPDF(fileOutputStream, false);
            renderer.finishPDF();
            fileOutputStream.close();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public TransactionCodeResponse signInSignature(String userId, Integer contractId, String signPosition) throws LCPlatformException {
        Contract contract = contractRepository.getById(contractId);
        Integer status = contract.getStatus();
        if (status == 1) {
            signPosition = Constant.SIGN_POSITION;
        } else if (status == 5) {
            signPosition = Constant.SIGN_POSITION_SELLER;
        }
        String maGiaoDich = ocrService.getTransactionId();
        System.out.println(maGiaoDich);
        UserInfoEntity userInfo = userInfoService.getUserInfo(userId);
        UserInfoForSignDigital userInfoForSignDigital = new UserInfoForSignDigital();
        Corporate corporate = corporateRepository.getById(userInfoService.getUserInfo(userId).getCorporate().getCorporateId());
        userInfoForSignDigital.setFullName(userInfo.getUserName());
        userInfoForSignDigital.setIdentityNumber(userInfo.getIdentityNumber());
        userInfoForSignDigital.setAddress(corporate.getCorporateAddress());
        userInfoForSignDigital.setPhoneNumber(userInfo.getPhoneNumber());
        userInfoForSignDigital.setCity("Hà Nội");
        userInfoForSignDigital.setCountry("Việt Nam");
        userInfoForSignDigital.setSignPosition(signPosition);
        String urlMinio = contract.getUrlMinio();
        int findFile = urlMinio.indexOf("FileContract");
        String fileName = urlMinio.substring(findFile, findFile + 32);
        File file1 = minioService.getFileByPath(fileName, "fileSignDigitalFinal.pdf");
        userInfoForSignDigital.setContractFile(file1);
//        try (FileOutputStream fos = new FileOutputStream(contract.getUrlMinio())) {
//        try (FileOutputStream fos = new FileOutputStream(fileName)) {
//            fos.write(this.getFile(fileName));
//            File file = new File(fileName);
//            userInfoForSignDigital.setContractFile(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (FileOutputStream fos = new FileOutputStream(corporate.getFrontOfUser2DeputyIdentifyUrl())) {
            fos.write(this.getFile(corporate.getFrontOfUser2DeputyIdentifyUrl()));
            File file = new File(corporate.getFrontOfUser2DeputyIdentifyUrl());
            userInfoForSignDigital.setFilePathFrontIdentity(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fos = new FileOutputStream(corporate.getBackOfUser2DeputyIdentifyUrl())) {
            fos.write(this.getFile(corporate.getBackOfUser2DeputyIdentifyUrl()));
            File file = new File(corporate.getBackOfUser2DeputyIdentifyUrl());
            userInfoForSignDigital.setFilePahBackIdentity(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TransactionCodeResponse transactionCodeResponse = ocrService.signInSignature(userInfoForSignDigital);
        System.out.print(transactionCodeResponse);
        return transactionCodeResponse;
    }

    @Override
    public String signDigital(SignDigitalRequest signDigitalRequest, String nameFile) throws LCPlatformException, IOException {
        SignDigitalResponse signDigitalResponse = ocrService.signDigital(signDigitalRequest);
        String content = signDigitalResponse.getData();

        //lấy tên file sau khi ghi file vào resource
        String fileNameSignDigital = this.convertBase64ToFile(content, nameFile);
        //ghi file lên minio
        String fileUrl = pushFileMinIO(fileNameSignDigital);

        return fileUrl;
    }

    public String convertBase64ToFile(String base64, String fileName) throws IOException {
        byte[] decodedString = Base64.getDecoder().decode(base64);
//        fileName = this.convertDate() + "_" + fileName;
        System.out.println(new String(decodedString));
        // ghi file vào thư mục resource
        try (FileOutputStream fos = new FileOutputStream(url + fileName)) {
            fos.write(decodedString);
            File file = new File(url + fileName);
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    file.getName(), "text/plain", IOUtils.toByteArray(input));
//            this.uploadFile(multipartFile);
            file.delete();
            fos.close();
            return fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    public String convertDate() {
        String pattern = "ddMMyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);
        return todayAsString;
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

    /**
     * Contents of the sale and purchase contract.
     *
     * @param genFileRequest : Request
     * @return : content
     */
    @Override
    public GenFileResponse updateUrlFile(GenFileRequest genFileRequest) throws LCPlatformException {
        Contract contract = contractRepository.getById(genFileRequest.getContractId());
        GenFileResponse genFileResponse = new GenFileResponse();
        String nameFile = genFileRequest.getFileName();
        String urlFile = "";
        //Draff file to folder: url + filename
        File file = new File(url + nameFile);
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
            contract.setUrlMinio(nameFile);
            contract.setBase64File(genFileRequest.getBase64());
            contractRepository.save(contract);

            genFileResponse.setUrlFile(urlFile);
            genFileResponse.setFile(file);
            genFileResponse.setBase64(genFileRequest.getBase64());
            genFileResponse.setPathFileInMinio(nameFile);
        }
        File f = new File(url + nameFile);
        f.delete();
        log.info("Url::", urlFile);
        return genFileResponse;
    }

}
