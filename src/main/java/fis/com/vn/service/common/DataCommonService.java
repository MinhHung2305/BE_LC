package fis.com.vn.service.common;

import fis.com.vn.model.entity.*;

import java.util.List;

public interface DataCommonService {

    public List<National> getAllNational();
    public List<Province> getProvinceByNationalId(Long nationalId);
    public List<District> getDistrictByProvinceId(Long provinceId);
    public List<Ward> getWardByDistrict(Long districtId);
    public List<Currency> getCurrencies();
}
