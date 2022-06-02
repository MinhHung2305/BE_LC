package fis.com.vn.service.admin;

import fis.com.vn.rest.response.CharacterSetResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharacterSetService {
    List<CharacterSetResponse> findAll();
}
