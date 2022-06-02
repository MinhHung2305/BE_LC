package fis.com.vn.service.admin.impl;

import fis.com.vn.model.entity.StandardSwift;
import fis.com.vn.repository.StandardSwiftRepository;
import fis.com.vn.rest.mapper.StandardSwiftResponseMapper;
import fis.com.vn.rest.response.StandardSwiftResponse;
import fis.com.vn.service.admin.StandardSwiftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StandardSwiftServiceImpl implements StandardSwiftService {
    @Autowired
    StandardSwiftRepository standardSwiftRepository;
    @Autowired
    StandardSwiftResponseMapper standardSwiftResponseMapper;

    @Override
    public List<StandardSwiftResponse> getAllByPowerType(Long powerTypeId) {
        List<StandardSwift> StandardSwiftList = standardSwiftRepository.findAllByPowerType(powerTypeId);
        List<StandardSwiftResponse> StandardSwiftResponseList = standardSwiftResponseMapper.toDomain(StandardSwiftList);
        return StandardSwiftResponseList;
    }
}
