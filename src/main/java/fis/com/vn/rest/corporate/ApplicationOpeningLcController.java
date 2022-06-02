package fis.com.vn.rest.corporate;

import com.google.gson.Gson;
import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.rest.request.ApplicationOpeningLcRequest;
import fis.com.vn.rest.request.ApplicationOpeningLcSearchRequest;
import fis.com.vn.rest.request.GenFileRequest;
import fis.com.vn.rest.request.SignDigitalRequest;
import fis.com.vn.rest.response.ApplicationOpeningLcResponse;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.GenFileResponse;
import fis.com.vn.rest.response.TransactionCodeResponse;
import fis.com.vn.service.corporate.ApplicationOpeningLcService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/corporate")
@Api(tags = "Application Opening Lc Controller")
public class ApplicationOpeningLcController {
    @Autowired
    private ApplicationOpeningLcService applicationOpeningLcService;

    @PostMapping(value = "/createApplicationOpeningLc", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BaseResponse<ApplicationOpeningLcResponse>> createApplicationOpeningLc(@Valid @RequestPart(value = "file", required = false) MultipartFile files,
            @Valid @RequestParam String applicationOpeningLcRequest, @AuthenticationPrincipal Jwt principal) throws LCPlatformException, SendEmailException {
        String userId = principal.getSubject();
        Gson gson = new Gson();
        ApplicationOpeningLcRequest applicationOpeningLcRequestConvert = gson.fromJson(applicationOpeningLcRequest, ApplicationOpeningLcRequest.class);
        ApplicationOpeningLcResponse applicationOpeningLcResponse = applicationOpeningLcService.create(userId, applicationOpeningLcRequestConvert, files);
        return ResponseFactory.success(HttpStatus.OK, applicationOpeningLcResponse, ResponseCode.CREATE_SUCCESS);
    }

    @PostMapping(value = "/updateApplicationOpeningLc", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BaseResponse<ApplicationOpeningLcResponse>> updateApplicationOpeningLc(@Valid @RequestPart(value = "file", required = false) MultipartFile files,
                                                                                                 @Valid @RequestParam String applicationOpeningLcRequest, @AuthenticationPrincipal Jwt principal) throws LCPlatformException {
        String userId = principal.getSubject();
        Gson gson = new Gson();
        ApplicationOpeningLcRequest applicationOpeningLcRequestConvert = gson.fromJson(applicationOpeningLcRequest, ApplicationOpeningLcRequest.class);
        ApplicationOpeningLcResponse applicationOpeningLcResponse = applicationOpeningLcService.update(userId, applicationOpeningLcRequestConvert, files);
        return ResponseFactory.success(HttpStatus.OK, applicationOpeningLcResponse, ResponseCode.UPDATE_SUCCESS);
    }

    @PostMapping("/searchApplicationOpeningLc")
    public ResponseEntity<BaseResponse<List<ApplicationOpeningLcResponse>>> search(@AuthenticationPrincipal Jwt principal, @RequestBody ApplicationOpeningLcSearchRequest applicationOpeningLcSearchRequest) throws LCPlatformException{
        String userId = principal.getSubject();
        List<ApplicationOpeningLcResponse> applicationOpeningLcResponses = applicationOpeningLcService.search(userId, applicationOpeningLcSearchRequest);
        return ResponseFactory.success(HttpStatus.OK, applicationOpeningLcResponses, ResponseCode.CREATE_SUCCESS);
    }

    @PostMapping("/deleteApplicationOpeningLc")
    public void deleteApplicationOpeningLc(@RequestParam Long id)  throws LCPlatformException{
         applicationOpeningLcService.delete(id);
    }

    @GetMapping("/getApplicationOpeningLc/{id}")
    public ResponseEntity<BaseResponse<ApplicationOpeningLcResponse>> getApplicationOpeningLc(@PathVariable Long id) throws LCPlatformException{
        ApplicationOpeningLcResponse applicationOpeningLcResponses = applicationOpeningLcService.getApplicationOpeningLc(id);
        return ResponseFactory.success(HttpStatus.OK, applicationOpeningLcResponses);
    }

    @PostMapping(value = "/applicationOpeningLc/refuse")
    public ResponseEntity<BaseResponse<ApplicationOpeningLcResponse>> refuse(@AuthenticationPrincipal Jwt principal,
                                                                                 @RequestParam Long applicationOpeningLcId, @RequestParam String reasonForRefusal) throws LCPlatformException {
        String userId = principal.getSubject();
        ApplicationOpeningLcResponse applicationOpeningLcResponse = applicationOpeningLcService.refuse(userId,applicationOpeningLcId, reasonForRefusal);
        return ResponseFactory.success(HttpStatus.OK, applicationOpeningLcResponse, ResponseCode.REFUSE_SIGN_DIGITAL_SUCCESS);
    }

    @PostMapping(value = "/applicationOpeningLc/signInSignature")
    public ResponseEntity<BaseResponse<TransactionCodeResponse>> signInSignature(@AuthenticationPrincipal Jwt principal,
                                                                             @RequestParam Long applicationOpeningLcId) throws LCPlatformException {
        String userId = principal.getSubject();
        TransactionCodeResponse transactionCodeResponse = applicationOpeningLcService.signInSignature(userId,applicationOpeningLcId);
        return ResponseFactory.success(HttpStatus.OK, transactionCodeResponse);
    }

    @PostMapping(value = "/applicationOpeningLc/signDigital")
    public ResponseEntity<BaseResponse<ApplicationOpeningLcResponse>> signDigitale(@AuthenticationPrincipal Jwt principal, @RequestBody SignDigitalRequest signDigitalRequest) throws LCPlatformException, IOException, SendEmailException {
        String userId = principal.getSubject();
        ApplicationOpeningLcResponse applicationOpeningLcResponse = applicationOpeningLcService.signDigital(userId, signDigitalRequest);
        return ResponseFactory.success(HttpStatus.OK, applicationOpeningLcResponse, ResponseCode.SIGN_DIGITAL_ACCOUTANT_SUCCESS);
    }

    @PostMapping("/uploadImage")
    public void uploadImage(@RequestPart(value = "file", required = false) MultipartFile files)  throws LCPlatformException{
        applicationOpeningLcService.uploadImage(files);
    }

    @PostMapping("/applicationOpeningLc/updateUrlFile")
    public ResponseEntity<BaseResponse<GenFileResponse>> updateUrlFile(@RequestBody GenFileRequest genFileRequest) throws LCPlatformException, SendEmailException {
        GenFileResponse resFileSignDigital = applicationOpeningLcService.updateUrlFile(genFileRequest);
        return ResponseFactory.success(HttpStatus.OK, resFileSignDigital, ResponseCode.SIGN_DIGITAL_LEGAL_REPRESENTATIVE_SUCCESS);
    }

    @PostMapping(value = "/applicationOpeningLc/cancel")
    public ResponseEntity<BaseResponse<ApplicationOpeningLcResponse>> cancle(@AuthenticationPrincipal Jwt principal,
                                                                             @RequestParam Long applicationOpeningLcId) throws LCPlatformException {
        String userId = principal.getSubject();
        ApplicationOpeningLcResponse applicationOpeningLcResponse = applicationOpeningLcService.cancel(userId,applicationOpeningLcId);
        return ResponseFactory.success(HttpStatus.OK, applicationOpeningLcResponse, ResponseCode.CANCLE_SUCCESS);
    }
}
