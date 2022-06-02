package fis.com.vn.rest.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.License;
import fis.com.vn.repository.LicenseRepository;
import fis.com.vn.rest.mapper.LicenseResponseMapper;
import fis.com.vn.rest.request.LicenseRequest;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.LicenseResponse;
import fis.com.vn.service.admin.LicenseService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
//@Secured({ "ROLE_admin"})
@Api(tags = "Admin License controller")
public class LicenseController {
    @Autowired
    LicenseRepository licenseRepository;
    @Autowired
    LicenseResponseMapper licenseResponseMapper;
    @Autowired
    private LicenseService licenseService;

    @GetMapping("/license/getAll")
    //@Secured({"ROLE_"})
    public ResponseEntity<BaseResponse<List<LicenseResponse>>> getAllLicense() throws LCPlatformException{
        List<License> listLicense = licenseRepository.findAll();
        List<LicenseResponse> responseList = licenseResponseMapper.toDomain(listLicense);
        return ResponseFactory.success(HttpStatus.OK, responseList);
    }

    @PostMapping("/createLicense")
    public ResponseEntity<BaseResponse<License>> createCorporate(@Valid @RequestBody LicenseRequest licenseRequest) throws LCPlatformException {
        License license1 = licenseService.create(licenseRequest);
        return ResponseFactory.success(HttpStatus.OK, license1);
    }
}
