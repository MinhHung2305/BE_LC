package fis.com.vn.rest.admin;

import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.CharacterSetResponse;
import fis.com.vn.service.admin.CharacterSetService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
//@Secured({ "ROLE_admin"})
@Api(tags = "Character Set")
public class CharacterSetController {
    @Autowired
    private CharacterSetService characterSetService;

    @GetMapping("/characterSet/getAll")
    //@Secured({"ROLE_"})
    public ResponseEntity<BaseResponse<List<CharacterSetResponse>>> getAll(){
        List<CharacterSetResponse> characterSetResponseList = characterSetService.findAll();
        return ResponseFactory.success(HttpStatus.OK, characterSetResponseList);
    }
}
