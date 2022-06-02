package fis.com.vn.rest.corporate;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.*;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.rest.mapper.CorporateResponseMapper;
import fis.com.vn.rest.mapper.UserInfoRequestMapper;
import fis.com.vn.rest.request.CorporateAccountRequest;
import fis.com.vn.rest.request.CorporateRequest;
import fis.com.vn.rest.request.PackageServiceInfoRequest;
import fis.com.vn.rest.request.UserInfoRequest;
import fis.com.vn.rest.response.*;
import fis.com.vn.service.common.PackageServiceInfoService;
import fis.com.vn.service.common.UserGroupService;
import fis.com.vn.service.common.UserInfoService;
import fis.com.vn.service.corporate.CorporateAccountService;
import fis.com.vn.service.corporate.CorporateService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/corporate")
@Api(tags = "Corporate controller")
public class CorporateController {

    @Autowired
    private CorporateService corporateService;

    @Autowired
    private PackageServiceInfoService packageServiceInfoService;

    @Autowired
    private CorporateAccountService corporateAccountService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    UserInfoRequestMapper userInfoRequestMapper;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private CorporateResponseMapper corporateResponseMapper;

    @GetMapping("/getAll")
//    @Secured({"ROLE_corporate", "ROLE_view_corporate_group_management"})
    public ResponseEntity<BaseResponse<List<Corporate>>> getAllCorporate() {
        List<Corporate> corporateList = corporateService.findAll();
        return ResponseFactory.success(HttpStatus.OK, corporateList);
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<Corporate>> createCorporate(@Valid @RequestBody CorporateRequest corporateRequest) throws LCPlatformException {
        Corporate corporateResult = corporateService.createCorporate(corporateRequest);
        return ResponseFactory.success(HttpStatus.OK, corporateResult, ResponseCode.CREATE_SUCCESS);
    }

    @GetMapping("/get/{corporateId}")
    public ResponseEntity<BaseResponse<CorporateResponse>> getCorporateById(@PathVariable String corporateId) {
        CorporateResponse corporateRes = corporateService.getById(Long.parseLong(corporateId));
        return ResponseFactory.success(HttpStatus.OK, corporateRes);
    }

    @PutMapping("/update/{corporateId}")
    public ResponseEntity<BaseResponse<Corporate>> updateCorporate(@PathVariable String corporateId,
                                                                   @RequestBody CorporateRequest corporateRequest) throws LCPlatformException {

        Corporate corporateResult = corporateService.updateCorporate(corporateRequest, corporateId);
        return ResponseFactory.success(HttpStatus.OK, corporateResult, ResponseCode.UPDATE_SUCCESS);
    }

    @DeleteMapping("/delete/{corporateId}")
    public ResponseEntity<BaseResponse<Corporate>> deleteCorporate(@PathVariable String corporateId) {
        Corporate corporate = new Corporate();
        try {
            corporate = corporateService.deleteCorporate(corporateId);
        } catch (LCPlatformException e) {
            e.printStackTrace();
        }
        return ResponseFactory.success(HttpStatus.OK, corporate, ResponseCode.DELETE_SUCCESS);
    }

    @GetMapping("/getCorporateAccount/{corporateId}")
    public ResponseEntity<BaseResponse<List<CorporateAccount>>> getCorporateAccountByCorporateId(@PathVariable String corporateId) {
        List<CorporateAccount> corporateAccountList = corporateAccountService.getByCorporateId(corporateId);
        return ResponseFactory.success(HttpStatus.OK, corporateAccountList);
    }

    @PostMapping("/createCorporateAccount")
    public ResponseEntity<BaseResponse<CorporateAccount>> createCorporateAccount(
            @RequestBody CorporateAccountRequest corporateAccountRequest, @RequestParam String corporateId) {
        CorporateAccount corporateAccount = corporateAccountService.createCorporateAccount(corporateAccountRequest,
                corporateId);
        return ResponseFactory.success(HttpStatus.OK, corporateAccount, ResponseCode.CREATE_SUCCESS);
    }

    @DeleteMapping("/deleteCorporateAccount")
    public ResponseEntity<BaseResponse<CorporateAccount>> deleteCorporateAccount(@RequestParam String corporateAccountId) {
        CorporateAccount corporateAccount = corporateAccountService.deleteCorporateAccount(corporateAccountId);
        return ResponseFactory.success(HttpStatus.OK, corporateAccount, ResponseCode.DELETE_SUCCESS);
    }

    @PostMapping("/createPackageServiceInfo")
    ResponseEntity<BaseResponse<PackageServiceInfo>> createPackageServiceInfo(
            @RequestBody PackageServiceInfoRequest packageServiceInfoRequest) {
        PackageServiceInfo packageServiceInfo = packageServiceInfoService.create(packageServiceInfoRequest);
        return ResponseFactory.success(HttpStatus.OK, packageServiceInfo, ResponseCode.CREATE_SUCCESS);
    }

    @GetMapping("/getAllCorporateUser")
    public ResponseEntity<BaseResponse<List<UserInfoEntity>>> getAllCorporateUser(@RequestParam String corporateId) {
        List<UserInfoEntity> userInfoEntities = userInfoService.getByCorporateId(corporateId);
        return ResponseFactory.success(HttpStatus.OK, userInfoEntities);
    }

    @PostMapping("/createCorporateUser")
    public ResponseEntity<BaseResponse<UserInfoResponse>> createCorporateUser(
            @RequestBody UserInfoRequest userInfoRequest) throws LCPlatformException {
        UserInfoResponse userInfoResponse = userInfoService.createUserInfo(userInfoRequest);
        return ResponseFactory.success(HttpStatus.OK, userInfoResponse);
    }

    @GetMapping("/getAllCorporateGroup")
    public ResponseEntity<BaseResponse<List<UserGroupEntity>>> getAllCorporateGroup(@RequestParam String userType) {
        List<UserGroupEntity> userInfoEntities = userGroupService.getByUserType(userType);
        return ResponseFactory.success(HttpStatus.OK, userInfoEntities);
    }

    @GetMapping("/getAllCorporate")
//    @Secured({"ROLE_corporate", "ROLE_view_corporate_group_management"})
    public ResponseEntity<BaseResponse<List<CorporateResponse>>> getAllCorporateResponse() {
        List<Corporate> corporateList = corporateService.findAll();
        List<CorporateResponse> corporateResponseList = corporateResponseMapper.toDomain(corporateList);
        return ResponseFactory.success(HttpStatus.OK, corporateResponseList);
    }

    @PostMapping("/uploadFile")
    public CorporateFileResponse uploadFile(@RequestPart(value = "file", required = false) MultipartFile files) throws LCPlatformException {
        return corporateService.uploadFile(files);
    }

    @PostMapping("/uploadFileImage")
    public CorporateFileImageResponse uploadFileImage(@RequestPart(value = "imgFront", required = false) MultipartFile imgFront,
                                                      @RequestPart(value = "imgBack", required = false) MultipartFile imgBack,
                                                      @RequestPart(value = "imgPortrait", required = false) MultipartFile imgPortrait) throws LCPlatformException {


        return corporateService.uploadFileImage(imgFront,imgBack,imgPortrait);
    }

    @PostMapping("/uploadFileImagePP")
    public CorporateFileImageResponse uploadFileImagePP(@RequestPart(value = "imgFront", required = false) MultipartFile imgFront,
                                                      @RequestPart(value = "imgBack", required = false) MultipartFile imgBack,
                                                      @RequestPart(value = "imgPortrait", required = false) MultipartFile imgPortrait) throws LCPlatformException {


        return corporateService.uploadFileImagePP(imgFront,imgPortrait);
    }
}
