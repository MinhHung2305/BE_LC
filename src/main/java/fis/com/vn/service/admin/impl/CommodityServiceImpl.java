package fis.com.vn.service.admin.impl;

import fis.com.vn.model.entity.Commodity;
import fis.com.vn.repository.CommodityRepository;
import fis.com.vn.rest.mapper.CommodityResponseMapper;
import fis.com.vn.rest.response.CommodityResponse;
import fis.com.vn.service.admin.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    CommodityRepository commodityRepository;
    @Autowired
    CommodityResponseMapper commodityResponseMapper;

    @Override
    public List<CommodityResponse> getAllCommodity() {
        List<Commodity> commodityList = commodityRepository.findAll();
        List<CommodityResponse> responseList = commodityResponseMapper.toDomain(commodityList);
        return responseList;
    }
}
