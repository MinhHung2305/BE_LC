package fis.com.vn.rest.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.CharacterSetSwiftResponse;
import fis.com.vn.service.admin.CharacterSetSwiftService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
//@Secured({"", "fpt_management"})
@Api(tags = "Admin CharacterSet Controller")
public class CharacterSetSwiftController {
    @Autowired
    CharacterSetSwiftService characterSetSwiftService;

    @GetMapping("characterSet/getAllCharacterSetSwift")
    public ResponseEntity<BaseResponse<List<CharacterSetSwiftResponse>>> getAllCharacterSetSwift() throws LCPlatformException {
        return ResponseFactory.success(HttpStatus.OK, characterSetSwiftService.getAllCharacterSetSwift(),"Success");
    }

    @GetMapping("characterSet/getDetailCharacterSetSwift/{id}")
    public ResponseEntity<BaseResponse<CharacterSetSwiftResponse>> getDetailCharacterSetSwift(@PathVariable Long id) throws LCPlatformException {
        return ResponseFactory.success(HttpStatus.OK, characterSetSwiftService.getDetailCharacterSetSwift(id),"Success");
    }

    @GetMapping("characterSet/searchCharacterSetSwift")
    public ResponseEntity<BaseResponse<List<CharacterSetSwiftResponse>>> searchCharacterSetSwift(@RequestParam String characterSet) throws LCPlatformException {
        return ResponseFactory.success(HttpStatus.OK, characterSetSwiftService.searchCharacterSetSwift(characterSet),"Success");
    }
}
