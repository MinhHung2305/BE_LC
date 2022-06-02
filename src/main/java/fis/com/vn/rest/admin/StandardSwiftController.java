package fis.com.vn.rest.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.StandardSwiftResponse;
import fis.com.vn.service.admin.StandardSwiftService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
@Secured({"ROLE_admin"})
@Api(tags = "Admin StandardSwift controller")
public class StandardSwiftController {
    @Autowired
    StandardSwiftService standardSwiftService;

    @GetMapping("standardSwift/getAllByPowerType")
    public ResponseEntity<BaseResponse<List<StandardSwiftResponse>>> getAllByPowerType(@RequestParam Long powerTypeId) throws LCPlatformException {
        return ResponseFactory.success(HttpStatus.OK, standardSwiftService.getAllByPowerType(powerTypeId), "Success");
    }

//    @GetMapping("standardSwift/getDetailByPowerType/{id}")
//    public ResponseEntity<BaseResponse<StandardSwiftResponse>> getDetailByPowerType(@RequestParam Long powerTypeId) throws LCPlatformException {
//        return ResponseFactory.success(HttpStatus.OK, standardSwiftService.getDetailByPowerType(powerTypeId), "Success");
//    }
}
