package fis.com.vn.service.admin.impl;

import fis.com.vn.rest.response.CharacterSetSwiftResponse;
import fis.com.vn.service.admin.CharacterSetSwiftService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CharacterSetSwiftServiceImplTest {
    @Autowired
    private CharacterSetSwiftService characterSetSwiftService;

    @Before
    public void setUp() {
        characterSetSwiftService = new CharacterSetSwiftServiceImpl();
    }

    @Test
    public void getAllCharacterSetSwift(){
        List<CharacterSetSwiftResponse> list = characterSetSwiftService.getAllCharacterSetSwift();
        System.out.println(list);
    }
}