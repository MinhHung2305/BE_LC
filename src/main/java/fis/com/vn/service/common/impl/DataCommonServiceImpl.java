package fis.com.vn.service.common.impl;

import fis.com.vn.model.entity.*;
import fis.com.vn.repository.*;
import fis.com.vn.service.common.DataCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataCommonServiceImpl implements DataCommonService {

    @Autowired
    private NationalRepository nationalRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public List<National> getAllNational() {
        return nationalRepository.findAll();
    }

    @Override
    public List<Province> getProvinceByNationalId(Long nationalId) {
        return provinceRepository.findByNationalId(nationalId);
    }

    @Override
    public List<District> getDistrictByProvinceId(Long provinceId) {
        return districtRepository.findByProvinceId(provinceId);
    }

    @Override
    public List<Ward> getWardByDistrict(Long districtId) {
        return wardRepository.findByDistricId(districtId);
    }

    @Override
    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }


}
