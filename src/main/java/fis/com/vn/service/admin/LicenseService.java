package fis.com.vn.service.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.License;
import fis.com.vn.rest.request.LicenseRequest;

import java.util.List;

public interface LicenseService {
    License createLicense(License license) throws LCPlatformException;
    License create(LicenseRequest license) throws LCPlatformException;
    List<License> insertOrUpdateLicenseContract(List<License> licenseList, Integer contractId);
    List<License> insertOrUpdateLicense(List<License> licenseList, Long applicationOpeningLcId);
    License getLicenseByLicenseName(String licenseName);
}
