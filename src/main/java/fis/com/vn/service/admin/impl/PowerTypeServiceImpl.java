package fis.com.vn.service.admin.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.PowerType;
import fis.com.vn.repository.PowerTypeRepository;
import fis.com.vn.rest.mapper.PowerTypeResponseMapper;
import fis.com.vn.rest.response.PowerTypeResponse;
import fis.com.vn.service.admin.PowerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerTypeServiceImpl implements PowerTypeService {
    @Autowired
    PowerTypeRepository powerTypeRepository;
    @Autowired
    PowerTypeResponseMapper powerTypeResponseMapper;

    @Override
    public List<PowerTypeResponse> getAllPowerType() throws LCPlatformException {
        List<PowerType> powerTypeList = powerTypeRepository.findAll();
        List<PowerTypeResponse> powerTypeResponseList = powerTypeResponseMapper.toDomain(powerTypeList);
        return powerTypeResponseList;
    }

    @Override
    public PowerTypeResponse getDetailPowerType(Long id) throws LCPlatformException {
        PowerType powerType = powerTypeRepository.getById(id);
        PowerTypeResponse powerTypeResponse = powerTypeResponseMapper.toDomain(powerType);
        return powerTypeResponse;
    }
}
