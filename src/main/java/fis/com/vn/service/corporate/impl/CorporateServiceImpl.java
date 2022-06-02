package fis.com.vn.service.corporate.impl;

import fis.com.vn.config.MinioConfig;
import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.model.entity.*;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.model.enumerate.UserInitiationChannel;
import fis.com.vn.repository.*;
import fis.com.vn.rest.mapper.CorporateRequestMapper;
import fis.com.vn.rest.mapper.CorporateResponseMapper;
import fis.com.vn.rest.mapper.CorporateUserRequestMapper;
import fis.com.vn.rest.mapper.UserCorporateResponseMapper;
import fis.com.vn.rest.request.CorporateAccountRequest;
import fis.com.vn.rest.request.CorporateRequest;
import fis.com.vn.rest.request.CorporateUserRequest;
import fis.com.vn.rest.response.CorporateFileImageResponse;
import fis.com.vn.rest.response.CorporateFileResponse;
import fis.com.vn.rest.response.CorporateResponse;
import fis.com.vn.rest.response.UserCorporateResponse;
import fis.com.vn.service.common.UserInfoService;
import fis.com.vn.service.corporate.CorporateAccountService;
import fis.com.vn.service.corporate.CorporateService;
import fis.com.vn.service.corporate.CorporateUserService;
import fis.com.vn.service.file.MinioService;
import fis.com.vn.util.Constant;
import fis.com.vn.util.FilesUtils;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CorporateServiceImpl implements CorporateService {

    @Autowired
    private CorporateRepository corporateRepository;

    @Autowired
    private NationalRepository nationalRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private CorporateAccountRepository corporateAccountRepository;

    @Autowired
    private BankInfoRepository bankInfoRepository;

    @Autowired
    private CorporateRequestMapper corporateRequestMapper;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    PackageServiceInfoRepository packageServiceInfoRepository;

    @Autowired
    PackageServiceRepository packageServiceRepository;

    @Autowired
    CorporateResponseMapper corporateMapper;

    @Autowired
    CorporateUserRequestMapper corporateUserRequestMapper;

    @Autowired
    UserCorporateResponseMapper userCorporateResponseMapper;

    @Autowired
    CorporateUserService corporateUserService;

    @Autowired
    CorporateAccountService corporateAccountService;


    @Value("${keycloak.realm}")
    public String keycloakRealm;

    @Autowired
    private Keycloak keycloak;

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioService minioService;

    @Override
    public List<Corporate> findAll() {
        return corporateRepository.findAll(Sort.by(Sort.Direction.DESC, "lastModifiedDate"));
    }

    @Override
    public Corporate getById(String corporateId) {
        return corporateRepository.getById(Long.parseLong(corporateId));
    }

    @Override
    public CorporateResponse getById(Long corporateId) {
        Corporate corporate = corporateRepository.getById(corporateId);
        CorporateResponse corporateRes = corporateMapper.toDomain(corporate);

        List<CorporateAccount> corporateAccountList = corporateAccountRepository.findByCorporateId(corporateId);
        corporateRes.setCorporateAccountList(corporateAccountList);
        List<PackageServiceInfo> packageServiceInfo = packageServiceInfoRepository.findByCorporate(corporate);
        String packageServiceId = "";
        if (!packageServiceInfo.isEmpty())
            packageServiceId = packageServiceInfo.get(0).getPackageService().getPackage_service_id().toString();
        corporateRes.setPackageServiceId(packageServiceId);

        List<UserInfoEntity> userInfoList = userInfoService.getByCorporateIdOrderById(corporateId.toString());
        List<UserCorporateResponse> userCorporateResponse = new ArrayList<>();

        for (UserInfoEntity userInfo : userInfoList) {
            UserCorporateResponse userCorporateRes = userCorporateResponseMapper.toDomain(userInfo);
            userCorporateRes.setImageFrontOfIdentityUrl(getUrlImg(userInfo.getImageFrontOfIdentity()));
            userCorporateRes.setImageBackOfIdentityUrl(getUrlImg(userInfo.getImageBackOfIdentity()));
            userCorporateRes.setImagePortraitOfIdentityUrl(getUrlImg(userInfo.getImagePortraitOfIdentity()));
            userCorporateResponse.add(userCorporateRes);
        }

        corporateRes.setUserInfoList(userCorporateResponse);
        corporateRes.setBusinessRegistrationCertificateUrlMinio(getUrlImg(corporate.getBusinessRegistrationCertificateUrl()));

        return corporateRes;
    }

    public String getUrlImg(String imgPath) {
        if (imgPath != null && !imgPath.equals("")) {
            String fileTypeImgBack = FilesUtils.getFileType(imgPath);
            return minioService.getObjectUrlFile(minioConfig.getBucketName(), imgPath, fileTypeImgBack);
        } else {
            return "";
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Corporate createCorporate(CorporateRequest corporateRequest) throws LCPlatformException {
//        if(corporateRepository.existsByCorporateCode(corporateRequest.getCorporateCode())){
//            throw new LCPlatformException(ResponseCode.BAD_REQUEST, "CorporateCode is exists");
//        }
        Corporate corporate = corporateRequestMapper.toEntity(corporateRequest);

        List<CorporateAccount> accountList = new ArrayList<>();
        if (corporateRequest.getCorporateAccountList() != null) {
            for (CorporateAccountRequest corporateAcc : corporateRequest.getCorporateAccountList()) {
                CorporateAccount account = new CorporateAccount();
                account.setCorporateAccountName(corporateAcc.getCorporateAccountName());
                account.setCorporateAccountType(corporateAcc.getCorporateAccountType());
                account.setCorporateAccountNumber(corporateAcc.getCorporateAccountNumber());
                account.setCorporateAccountStatus(corporateAcc.getCorporateAccountStatus());
                account.setBank(bankInfoRepository.getById(Long.parseLong(corporateAcc.getBankId())));
                account.setCorporate(corporate);
                accountList.add(account);
            }
            corporate.setCorporateAccountList(accountList);
        }


        List<UserInfoEntity> userList = new ArrayList<>();
        if (corporateRequest.getCorporateUserList() != null) {
            for (CorporateUserRequest userInfo : corporateRequest.getCorporateUserList()) {
                UserInfoEntity userInfoEntity = corporateUserRequestMapper.toEntity(userInfo);

                userInfoEntity.setUserType(Constant.USER_TYPE_CORPORATE);
                userInfoEntity.setChannelInit(UserInitiationChannel.CORPORATE.getName());
                userInfoEntity.setUserGroupEntitys(userGroupRepository.findByUserGroupCode(userInfo.getUserGroupCode()));
                userInfoEntity.setCorporate(corporate);
                userList.add(userInfoEntity);
            }
            corporate.setUserInfoList(userList);
        }


        Corporate result = corporateRepository.save(corporate);

        try {
            userInfoService.createAllCorporateUserInfo(userList);
        } catch (LCPlatformException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            //rollback and delete user created in keycloak
            RealmResource realmResource = keycloak.realm(keycloakRealm);
            UsersResource usersRessource = realmResource.users();
            for (UserInfoEntity userInfo : userList) {
                if (userInfo.getUserId() != null) {
                    UserResource userResource = usersRessource.get(userInfo.getUserId());
                    userResource.remove();
                }
            }
            throw new LCPlatformException(ResponseCode.USER_NAME_EXISTED);
        } catch (SendEmailException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String packageServiceId = corporateRequest.getPackageServiceId();
        if (packageServiceId != null) {
            PackageServiceInfo packageServiceInfo = new PackageServiceInfo();

            PackageService packageService = packageServiceRepository.getById(Long.parseLong(packageServiceId));
            packageServiceInfo.setCorporate(result);
            packageServiceInfo.setPackageService(packageService);
            packageServiceInfo.setPackageServiceInfoDescription("");
            packageServiceInfo.setPackageServiceInfoStatus(1);
            try {
                packageServiceInfoRepository.save(packageServiceInfo);
            } catch (Exception e) {
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                //rollback and delete user created in keycloak
                RealmResource realmResource = keycloak.realm(keycloakRealm);
                UsersResource usersRessource = realmResource.users();
                for (UserInfoEntity userInfo : userList) {
                    UserResource userResource = usersRessource.get(userInfo.getUserId());
                    userResource.remove();
                }
//                throw new LCPlatformException(ResponseCode.USER_NAME_EXISTED);
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new LCPlatformException(ResponseCode.BAD_REQUEST);
            }
        }
        return result;

    }

    @Transactional
    @Override
    public Corporate updateCorporate(CorporateRequest corporateRequest, String corporateId) throws LCPlatformException {
        Corporate corporate = corporateRepository.getById(Long.parseLong(corporateId));

        Corporate corporateUp = corporateRequestMapper.toEntity(corporateRequest);
        corporateUp.setCorporateId(Long.parseLong(corporateId));
        corporateUp.setCorporateCode(corporate.getCorporateCode());
        corporateUp.setCreatedBy(corporate.getCreatedBy());
        corporateUp.setCreatedDate(corporate.getCreatedDate());

        List<CorporateAccount> accountList = new ArrayList<>();
        if (corporateRequest.getCorporateAccountList() != null) {
            for (CorporateAccountRequest corporateAcc : corporateRequest.getCorporateAccountList()) {
                if (corporateAcc.getCorporateAccountId() == -1) {
                    CorporateAccount account = new CorporateAccount();
                    account.setCorporateAccountName(corporateAcc.getCorporateAccountName());
                    account.setCorporateAccountType(corporateAcc.getCorporateAccountType());
                    account.setCorporateAccountNumber(corporateAcc.getCorporateAccountNumber());
                    account.setCorporateAccountStatus(corporateAcc.getCorporateAccountStatus());
                    account.setBank(bankInfoRepository.getById(Long.parseLong(corporateAcc.getBankId())));
                    account.setCorporate(corporateUp);
                    accountList.add(account);
                } else {
                    CorporateAccount account = corporateAccountService.updateCorporateAccount(corporateAcc, corporateId);
                    accountList.add(account);
                }
            }
            corporateUp.setCorporateAccountList(accountList);
        }

        List<CorporateAccount> listCorporateAccountBD = corporateAccountRepository.findByCorporateId(Long.parseLong(corporateId));
        List<Long> idCorporateAccountRequest = corporateRequest.getCorporateAccountList().stream().map(corporateAccount ->
                corporateAccount.getCorporateAccountId()).collect(Collectors.toList());
        List<CorporateAccount> corporateAccountInDbNotReq = listCorporateAccountBD
                .stream()
                .filter(corporateAccount -> !idCorporateAccountRequest.contains(corporateAccount.getCorporateAccountId()))
                .collect(Collectors.toList());
        for(CorporateAccount corporateAccount : corporateAccountInDbNotReq){
            corporateAccountRepository.delete(corporateAccount);
        }
        
        List<UserInfoEntity> userList = new ArrayList<>();
        if (corporateRequest.getCorporateUserList() != null) {
            for (CorporateUserRequest userInfo : corporateRequest.getCorporateUserList()) {
                if (userInfo.getId() == -1) {
                    userInfo.setId(null);
                    UserInfoEntity userInfoEntity = corporateUserRequestMapper.toEntity(userInfo);

                    userInfoEntity.setUserType(Constant.USER_TYPE_CORPORATE);
                    userInfoEntity.setChannelInit(UserInitiationChannel.valueOf(2).getName());
                    userInfoEntity.setUserGroupEntitys(userGroupRepository.findByUserGroupCode(userInfo.getUserGroupCode()));
                    userInfoEntity.setCorporate(corporateUp);
                    userList.add(userInfoEntity);

                } else {
                    corporateUserService.updateUser(corporateId, userInfo.getId().toString(), userInfo);
                }
            }
            corporateUp.setUserInfoList(userList);
        }

        Corporate result = new Corporate();
        try {
            corporateUp = corporateRepository.save(corporateUp);
            result = corporateUp;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
        try {
            List<UserInfoEntity> uList = new LinkedList<UserInfoEntity>(result.getUserInfoList());
            userInfoService.createAllCorporateUserInfo(uList);

        } catch (LCPlatformException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            //rollback and delete user created in keycloak
            for (UserInfoEntity userInfo : userList) {
                RealmResource realmResource = keycloak.realm(keycloakRealm);
                UsersResource usersRessource = realmResource.users();
                UserResource userResource = usersRessource.get(userInfo.getUserId());
                userResource.remove();
            }

            throw new LCPlatformException(ResponseCode.USER_NAME_EXISTED);
        } catch (SendEmailException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String packageServiceId = corporateRequest.getPackageServiceId();
        if (packageServiceId != null) {
            PackageService packageService = packageServiceRepository.getById(Long.parseLong(packageServiceId));
            PackageServiceInfo packageServiceInfo = new PackageServiceInfo();

            List<PackageServiceInfo> packageServiceInfoList = packageServiceInfoRepository.findByCorporate(result);
            if (packageServiceInfoList.size() > 0) {
                packageServiceInfo = packageServiceInfoList.get(0);
            } else {
                packageServiceInfo.setCorporate(result);
            }
            packageServiceInfo.setPackageService(packageService);
            packageServiceInfo.setPackageServiceInfoDescription("");
            packageServiceInfo.setPackageServiceInfoStatus(1);
            try {
                packageServiceInfoRepository.save(packageServiceInfo);
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                log.info("Add pagkageService error:" + e.getMessage());
            }
        }
        return result;
    }

    @Transactional
    @Override
    public Corporate deleteCorporate(String corporateId) throws LCPlatformException {
        try {
            Corporate corporateDel = getById(corporateId);

            //get all userCorporate to listUser and delete in Keycloak
            List<UserInfoEntity> userList = new LinkedList<UserInfoEntity>(corporateDel.getUserInfoList());
            //delete pagkageService by Corporate
            List<PackageServiceInfo> packageServiceInfoList = packageServiceInfoRepository.findByCorporate(corporateDel);
            if (!packageServiceInfoList.isEmpty()) {
                PackageServiceInfo packageServiceInfo = packageServiceInfoList.get(0);
                packageServiceInfoRepository.delete(packageServiceInfo);
            }

            // delete corporate
            corporateRepository.delete(corporateDel);

            //delete all user in keycloak
            for (UserInfoEntity userInfo : userList) {
                RealmResource realmResource = keycloak.realm(keycloakRealm);
                UserResource userResource = realmResource.users().get(userInfo.getUserId());
                userResource.remove();
            }

            return corporateDel;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info("Delete pagkageService error:" + e.getMessage());
            throw new LCPlatformException(ResponseCode.BAD_REQUEST);
        }
    }

    @Override
    public CorporateFileResponse uploadFile(MultipartFile files) throws LCPlatformException {
        String filePath = null;
        String url = null;
        try {
            filePath = minioService.uploadFile(files);
            url = minioService.getObjectUrl(minioConfig.getBucketName(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CorporateFileResponse corporateFileResponse = new CorporateFileResponse();
        corporateFileResponse.setFileUrl(url);
        corporateFileResponse.setFilePath(filePath);
        return corporateFileResponse;
    }

    @Override
    public CorporateFileImageResponse uploadFileImage(MultipartFile imgFront, MultipartFile imgBack, MultipartFile imgPortrait)
            throws LCPlatformException {
        String filePathImgFront = null;
        String urlImgFront = null;

        String filePathImgBack = null;
        String urlImgBack = null;

        String filePathImgPortrait = null;
        String urlImgPortrait = null;
        try {
            filePathImgFront = minioService.uploadFile(imgFront);
            String fileTypeImgFront = FilesUtils.getFileType(imgFront.getOriginalFilename());
            urlImgFront = minioService.getObjectUrlFile(minioConfig.getBucketName(), filePathImgFront, fileTypeImgFront);

            filePathImgBack = minioService.uploadFile(imgBack);
            String fileTypeImgBack = FilesUtils.getFileType(imgBack.getOriginalFilename());
            urlImgBack = minioService.getObjectUrlFile(minioConfig.getBucketName(), filePathImgBack, fileTypeImgBack);

            filePathImgPortrait = minioService.uploadFile(imgPortrait);
            String fileTypeImgPortrait = FilesUtils.getFileType(imgPortrait.getOriginalFilename());
            urlImgPortrait = minioService.getObjectUrlFile(minioConfig.getBucketName(), filePathImgPortrait, fileTypeImgPortrait);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CorporateFileImageResponse corporateFileImageResponse = new CorporateFileImageResponse();
        corporateFileImageResponse.setFileUrlImgFront(urlImgFront);
        corporateFileImageResponse.setFilePathImgFront(filePathImgFront);

        corporateFileImageResponse.setFileUrlImgBack(urlImgBack);
        corporateFileImageResponse.setFilePathImgBack(filePathImgBack);

        corporateFileImageResponse.setFileUrlImgPortrait(urlImgPortrait);
        corporateFileImageResponse.setFilePathImgPortrait(filePathImgPortrait);
        return corporateFileImageResponse;
    }

    @Override
    public CorporateFileImageResponse uploadFileImagePP(MultipartFile imgFront, MultipartFile imgPortrait)
            throws LCPlatformException {
        String filePathImgFront = null;
        String urlImgFront = null;

        String filePathImgPortrait = null;
        String urlImgPortrait = null;
        try {
            filePathImgFront = minioService.uploadFile(imgFront);
            String fileTypeImgFront = FilesUtils.getFileType(imgFront.getOriginalFilename());
            urlImgFront = minioService.getObjectUrlFile(minioConfig.getBucketName(), filePathImgFront, fileTypeImgFront);

            filePathImgPortrait = minioService.uploadFile(imgPortrait);
            String fileTypeImgPortrait = FilesUtils.getFileType(imgPortrait.getOriginalFilename());
            urlImgPortrait = minioService.getObjectUrlFile(minioConfig.getBucketName(), filePathImgPortrait, fileTypeImgPortrait);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CorporateFileImageResponse corporateFileImageResponse = new CorporateFileImageResponse();
        corporateFileImageResponse.setFileUrlImgFront(urlImgFront);
        corporateFileImageResponse.setFilePathImgFront(filePathImgFront);

        corporateFileImageResponse.setFileUrlImgPortrait(urlImgPortrait);
        corporateFileImageResponse.setFilePathImgPortrait(filePathImgPortrait);
        return corporateFileImageResponse;
    }
}
