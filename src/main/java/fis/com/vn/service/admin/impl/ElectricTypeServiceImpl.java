package fis.com.vn.service.admin.impl;

import fis.com.vn.model.entity.ElectricType;
import fis.com.vn.repository.admin.ElectricTypeRepository;
import fis.com.vn.rest.mapper.CharacterSetElectricTypeResponseMapper;
import fis.com.vn.rest.mapper.ElectricTypeResponseMapper;
import fis.com.vn.rest.response.ElectricTypeResponse;
import fis.com.vn.service.admin.ElectricTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ElectricTypeServiceImpl implements ElectricTypeService {
    @Autowired
    private ElectricTypeRepository electricTypeRepository;

    @Autowired
    private ElectricTypeResponseMapper electricTypeResponseMapper;

    @Autowired
    private CharacterSetElectricTypeResponseMapper characterSetElectricTypeResponseMapper;

    @Override
    public List<ElectricTypeResponse> findAll(){
        List<ElectricType> electricTypeList = electricTypeRepository.findAll();
        return electricTypeResponseMapper.toDomain(electricTypeList);
    }

    @Override
    public ElectricTypeResponse getElectricType(Long id){
        Optional<ElectricType> electricTypeOptional = electricTypeRepository.findById(id);
        if(electricTypeOptional.isPresent())
        {
            ElectricTypeResponse electricTypeResponse = electricTypeResponseMapper.toDomain(electricTypeOptional.get());
            electricTypeResponse.setCharacterSet1(characterSetElectricTypeResponseMapper.toDomain(electricTypeOptional.get().getCharacterSetElectricTypes().stream().filter(x -> x.getType() == 1).collect(Collectors.toList())));
            electricTypeResponse.setCharacterSet2(characterSetElectricTypeResponseMapper.toDomain(electricTypeOptional.get().getCharacterSetElectricTypes().stream().filter(x -> x.getType() == 2).collect(Collectors.toList())));
            return electricTypeResponse;
        }
        return null;
    }
}
