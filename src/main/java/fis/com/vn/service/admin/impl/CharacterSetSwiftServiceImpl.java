package fis.com.vn.service.admin.impl;

import fis.com.vn.model.entity.CharacterSetSwift;
import fis.com.vn.repository.CharacterSetSwiftRepository;
import fis.com.vn.rest.mapper.CharacterSetSwiftResponseMapper;
import fis.com.vn.rest.response.CharacterSetSwiftResponse;
import fis.com.vn.service.admin.CharacterSetSwiftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class CharacterSetSwiftServiceImpl implements CharacterSetSwiftService {
    @Autowired
    CharacterSetSwiftResponseMapper characterSetSwiftResponseMapper;
    @Autowired
    CharacterSetSwiftRepository characterSetSwiftRepository;

    @Override
    public List<CharacterSetSwiftResponse> getAllCharacterSetSwift() {
        List<CharacterSetSwift> characterSetSwiftList = characterSetSwiftRepository.findAll();
        List<CharacterSetSwiftResponse> characterSetSwiftResponseList = characterSetSwiftResponseMapper.toDomain(characterSetSwiftList);
        return characterSetSwiftResponseList;
    }

    @Override
    public CharacterSetSwiftResponse getDetailCharacterSetSwift(Long id) {
        CharacterSetSwift characterSetSwift = characterSetSwiftRepository.getById(id);
        CharacterSetSwiftResponse characterSetSwiftResponse = characterSetSwiftResponseMapper.toDomain(characterSetSwift);
        return characterSetSwiftResponse;
    }

    @Override
    public List<CharacterSetSwiftResponse> searchCharacterSetSwift(String characterSet) {
        List<CharacterSetSwift> characterSetSwiftList = characterSetSwiftRepository.findAllByCharacterSet(characterSet.toLowerCase(Locale.ROOT));
        List<CharacterSetSwiftResponse> characterSetSwiftResponseList = characterSetSwiftResponseMapper.toDomain(characterSetSwiftList);
        return characterSetSwiftResponseList;
    }
}
