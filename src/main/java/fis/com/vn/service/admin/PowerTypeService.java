package fis.com.vn.service.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.response.PowerTypeResponse;

import java.util.List;

public interface PowerTypeService {
    List<PowerTypeResponse> getAllPowerType() throws LCPlatformException;
    PowerTypeResponse getDetailPowerType(Long id) throws LCPlatformException;
}
