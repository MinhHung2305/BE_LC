package fis.com.vn.rest.corporate;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.rest.mapper.ProductRequestMapper;
import fis.com.vn.rest.mapper.UserInfoResponseMapper;
import fis.com.vn.rest.request.ContractRequest;
import fis.com.vn.rest.request.GenFileRequest;
import fis.com.vn.rest.request.SignDigitalRequest;
import fis.com.vn.rest.response.*;
import fis.com.vn.service.common.UserInfoService;
import fis.com.vn.service.corporate.CorporateContractService;
import fis.com.vn.service.corporate.ProductService;
import fis.com.vn.util.Constant;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/corporate")
@Slf4j
@Api(tags = "Corporate Contract controller")
public class CorporateContractController {
    @Autowired
    CorporateContractService corporateContractService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRequestMapper productRequestMapper;

    @Autowired
    UserInfoResponseMapper userInfoResponseMapper;

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/contract/getAll")
    //@Secured({ "ROLE_" })
    public ResponseEntity<BaseResponse<List<ContractResponse>>> getAllContractResponse() throws LCPlatformException {
        List<ContractResponse> contractResponseList = corporateContractService.getAllContract();
        return ResponseFactory.success(HttpStatus.OK, contractResponseList);
    }

    @GetMapping("/contract/getAllByCorporateBuyer")
    //@Secured({ "ROLE_" })
    public ResponseEntity<BaseResponse<List<ContractResponse>>> getAllContractByCorporate(@AuthenticationPrincipal Jwt principal) throws LCPlatformException {
        String userId = principal.getSubject();
        UserInfoResponse userInfoResponse = userInfoResponseMapper.toDomain(userInfoService.getUserLogin(userId));
        Long corporateId = userInfoResponse.getCorporate().getCorporateId();

        List<ContractResponse> contractResponseList = corporateContractService.getAllContractByCorporateBuyer(corporateId);
        return ResponseFactory.success(HttpStatus.OK, contractResponseList);
    }

    @GetMapping("/contract/getAllByCorporateSeller")
    //@Secured({ "ROLE_" })
    public ResponseEntity<BaseResponse<List<ContractResponse>>> getAllByCorporateSeller(@AuthenticationPrincipal Jwt principal) throws LCPlatformException {
        String userId = principal.getSubject();
        UserInfoResponse userInfoResponse = userInfoResponseMapper.toDomain(userInfoService.getUserLogin(userId));
        Long corporateId = userInfoResponse.getCorporate().getCorporateId();

        List<ContractResponse> contractResponseList = corporateContractService.getAllContractByCorporateSeller(corporateId);
        return ResponseFactory.success(HttpStatus.OK, contractResponseList);
    }

    @GetMapping("/contract/getContract/{id}")
    //@Secured({ "ROLE_" })
    public ResponseEntity<BaseResponse<ContractResponse>> getContractResponse(@PathVariable Integer id) throws LCPlatformException {
        ContractResponse contractResponse = corporateContractService.getContractById(id);
        return ResponseFactory.success(HttpStatus.OK, contractResponse);
    }

    @PostMapping("/contract/createContract")
    //@Secured({ "ROLE_" })
    public ResponseEntity<BaseResponse<ContractResponse>> createContract(@RequestBody ContractRequest contractRequest) throws LCPlatformException {
        ContractResponse contractResponse = corporateContractService.createContract(contractRequest);
        return ResponseFactory.success(HttpStatus.OK, contractResponse);
    }

    @PutMapping("/contract/updateContract")
    //@Secured({ "ROLE_" })
    public ResponseEntity<BaseResponse<ContractResponse>> updateContract(@RequestBody ContractRequest contractRequest) throws LCPlatformException {
        ContractResponse contractResponse = corporateContractService.updateContract(contractRequest);
        return ResponseFactory.success(HttpStatus.OK, contractResponse);
    }

    @DeleteMapping("/contract/deleteContract/{id}")
    //@Secured({ "ROLE_" })
    public void deleteContract(@PathVariable Integer id) throws LCPlatformException {
        corporateContractService.deleteContractById(id);
    }

    @PutMapping("/contract/updateDigitalSignature")
    public ResponseEntity<BaseResponse<String>> changeStateContract(@RequestBody ContractRequest contractRequest) throws LCPlatformException, SendEmailException {
        corporateContractService.changeStateContract(contractRequest);
        return ResponseFactory.success(HttpStatus.OK, null);
    }

    @PostMapping("/contract/generateFilePdf")
    public ResponseEntity<BaseResponse<GenFileResponse>> generateFilePdf(@RequestBody ContractRequest contractRequest, @RequestParam String fileName) throws LCPlatformException {
        GenFileResponse genFileResponse = corporateContractService.genPdfFile(fileName, contractRequest);
        return ResponseFactory.success(HttpStatus.OK, genFileResponse);
    }

    @PostMapping("/contract/signInSignature")
    public ResponseEntity<BaseResponse<TransactionCodeResponse>> signInSignature(@AuthenticationPrincipal Jwt principal,
                                                                                 @RequestParam String contractId) throws LCPlatformException {
        String userId = principal.getSubject();
        TransactionCodeResponse transactionCodeResponse = corporateContractService.signInSignature(userId, Integer.parseInt(contractId), Constant.SIGN_POSITION);
        return ResponseFactory.success(HttpStatus.OK, transactionCodeResponse);
    }

    @PostMapping(value = "/contract/signDigital")
    public ResponseEntity<BaseResponse<String>> signDigital(@RequestBody SignDigitalRequest signDigitalRequest, @RequestParam String nameFile) throws LCPlatformException, IOException, SendEmailException {
        String urlFileSignDigital = corporateContractService.signDigital(signDigitalRequest, nameFile);
        return ResponseFactory.success(HttpStatus.OK, urlFileSignDigital);
    }

    @PutMapping("/contract/updateUrlFile")
    public ResponseEntity<BaseResponse<GenFileResponse>> updateUrlFile(@RequestBody GenFileRequest genFileRequest) throws LCPlatformException, SendEmailException {
        GenFileResponse resFileSignDigital = corporateContractService.updateUrlFile(genFileRequest);
        return ResponseFactory.success(HttpStatus.OK, resFileSignDigital);
    }
}
