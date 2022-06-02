package fis.com.vn.service.corporate;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.rest.request.ApplicationOpeningLcRequest;
import fis.com.vn.rest.request.ApplicationOpeningLcSearchRequest;
import fis.com.vn.rest.request.GenFileRequest;
import fis.com.vn.rest.request.SignDigitalRequest;
import fis.com.vn.rest.response.ApplicationOpeningLcResponse;
import fis.com.vn.rest.response.GenFileResponse;
import fis.com.vn.rest.response.TransactionCodeResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ApplicationOpeningLcService {
    ApplicationOpeningLcResponse create(String userId, ApplicationOpeningLcRequest applicationOpeningLcRequest, MultipartFile files) throws LCPlatformException, SendEmailException;

    List<ApplicationOpeningLcResponse> search(String userId, ApplicationOpeningLcSearchRequest applicationOpeningLcSearchRequest) throws LCPlatformException;

    void delete(Long id) throws LCPlatformException;

    ApplicationOpeningLcResponse getApplicationOpeningLc(Long id) throws LCPlatformException;

    ApplicationOpeningLcResponse update(String userId, ApplicationOpeningLcRequest applicationOpeningLcRequest, MultipartFile files) throws LCPlatformException;

    TransactionCodeResponse signInSignature(String userId, Long applicationOpeningLcId) throws LCPlatformException;

    ApplicationOpeningLcResponse refuse(String userId, Long applicationOpeningLcId, String reasonForRefusal) throws LCPlatformException;

    ApplicationOpeningLcResponse signDigital(String userId, SignDigitalRequest signDigitalRequest) throws IOException, SendEmailException;

    void uploadImage(MultipartFile files) throws LCPlatformException;

    void generatePdfFile(ApplicationOpeningLcResponse applicationOpeningLcResponse, ApplicationOpeningLcRequest applicationOpeningLcRequest) throws LCPlatformException;

    GenFileResponse updateUrlFile (GenFileRequest genFileRequest) throws LCPlatformException, SendEmailException;

    ApplicationOpeningLcResponse cancel(String userId, Long applicationOpeningLcId) throws LCPlatformException;
}
