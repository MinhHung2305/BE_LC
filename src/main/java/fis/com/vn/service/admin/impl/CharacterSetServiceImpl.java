package fis.com.vn.service.admin.impl;

import fis.com.vn.model.entity.CharacterSet;
import fis.com.vn.repository.admin.CharacterSetRepository;
import fis.com.vn.rest.mapper.CharacterSetResponseMapper;
import fis.com.vn.rest.response.CharacterSetResponse;
import fis.com.vn.service.admin.CharacterSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterSetServiceImpl implements CharacterSetService {
    @Autowired
    private CharacterSetRepository characterSetRepository;

    @Autowired
    private CharacterSetResponseMapper characterSetResponseMapper;

    @Override
    public List<CharacterSetResponse> findAll(){
        List<CharacterSet> characterSetList = characterSetRepository.findAll();
        List<CharacterSetResponse> characterSetResponseList = characterSetResponseMapper.toDomain(characterSetList);
        return characterSetResponseList;
    }
}
