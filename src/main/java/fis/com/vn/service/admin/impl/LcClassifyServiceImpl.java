package fis.com.vn.service.admin.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.LcClassify;
import fis.com.vn.repository.LcClassifyRepository;
import fis.com.vn.rest.mapper.LcClassifyResponseMapper;
import fis.com.vn.rest.response.LcClassifyResponse;
import fis.com.vn.service.admin.LcClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LcClassifyServiceImpl implements LcClassifyService {
    @Autowired
    LcClassifyResponseMapper lcClassifyResponseMapper;
    @Autowired
    LcClassifyRepository lcClassifyRepository;

    @Override
    public List<LcClassifyResponse> getAllLcClassify() throws LCPlatformException {
        List<LcClassify> list = lcClassifyRepository.findAll();
        return lcClassifyResponseMapper.toDomain(list);
    }
}
