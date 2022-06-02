package fis.com.vn.rest.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.LcClassifyResponse;
import fis.com.vn.service.admin.LcClassifyService;
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
@Api(tags = "Admin Lc Classify controller")
public class LcClassifyController {
    @Autowired
    LcClassifyService lcClassifyService;

    @GetMapping("/lcClassify/getAll")
    //@Secured({"ROLE_"})
    public ResponseEntity<BaseResponse<List<LcClassifyResponse>>> getAllLcClassify() throws LCPlatformException {
        List<LcClassifyResponse> classifyResponseList = lcClassifyService.getAllLcClassify();
        return ResponseFactory.success(HttpStatus.OK, classifyResponseList);
    }
}
