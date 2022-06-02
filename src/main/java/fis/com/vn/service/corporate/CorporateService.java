package fis.com.vn.service.corporate;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.Corporate;
import fis.com.vn.rest.request.CorporateRequest;
import fis.com.vn.rest.response.CorporateFileImageResponse;
import fis.com.vn.rest.response.CorporateFileResponse;
import fis.com.vn.rest.response.CorporateResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CorporateService {
    List<Corporate> findAll();

    CorporateResponse getById(Long corporateId);

    Corporate getById(String corporateId);
    
    Corporate createCorporate(CorporateRequest corporateRequest) throws LCPlatformException;
    
    Corporate updateCorporate(CorporateRequest corporateRequest, String corporateId) throws LCPlatformException;

	Corporate deleteCorporate(String corporateId) throws LCPlatformException;

    CorporateFileResponse uploadFile(MultipartFile files) throws LCPlatformException;

    CorporateFileImageResponse uploadFileImage(MultipartFile imgFront, MultipartFile imgBack, MultipartFile imgPortrait) throws LCPlatformException;

    CorporateFileImageResponse uploadFileImagePP(MultipartFile imgFront, MultipartFile imgPortrait) throws LCPlatformException;
}
