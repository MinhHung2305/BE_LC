package fis.com.vn.service.admin;

import fis.com.vn.rest.response.CharacterSetSwiftResponse;

import java.util.List;

public interface CharacterSetSwiftService {
    List<CharacterSetSwiftResponse> getAllCharacterSetSwift();
    CharacterSetSwiftResponse getDetailCharacterSetSwift(Long id);
    List<CharacterSetSwiftResponse> searchCharacterSetSwift(String characterSet);
}
