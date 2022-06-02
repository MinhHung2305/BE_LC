package fis.com.vn.service.ocr;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.request.OcrCorporateBusinessRequest;
import fis.com.vn.rest.request.OcrIdentifyRequest;
import fis.com.vn.rest.request.SignDigitalRequest;
import fis.com.vn.rest.request.VerifyIdentifyRequest;
import fis.com.vn.rest.response.*;


public interface OcrService {

    OcrIdentifyResponse getOcrIdentify(OcrIdentifyRequest request);

    String getTransactionId();

    String verfifyIdentify(VerifyIdentifyRequest verifyIdentifyRequest);

    OcrCorporateBussinessResponse getOcrCorporateBussiness(OcrCorporateBusinessRequest ocrCorporateBusinessRequest) throws LCPlatformException;

    OcrCorporateSignatureResponse[] getOcrCorporateSignature(OcrCorporateBusinessRequest ocrCorporateBusinessRequest) throws LCPlatformException;

    OcrCorporateResponse getOcrCorporate(OcrCorporateBusinessRequest ocrCorporateBusinessRequest) throws LCPlatformException;

    TransactionCodeResponse signInSignature(UserInfoForSignDigital userInfoForSignDigital) throws LCPlatformException;

    SignDigitalResponse signDigital(SignDigitalRequest signDigitalRequest);
}
