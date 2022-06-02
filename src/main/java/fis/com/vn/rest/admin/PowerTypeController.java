package fis.com.vn.rest.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.PowerTypeResponse;
import fis.com.vn.service.admin.PowerTypeService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
@Secured({ "ROLE_admin"})
@Api(tags = "Admin PowerType controller")
public class PowerTypeController {
    @Autowired
    PowerTypeService powerTypeService;

    @GetMapping("powerType/getAllPowerType")
    public ResponseEntity<BaseResponse<List<PowerTypeResponse>>> getAllPowerType() throws LCPlatformException {
        return ResponseFactory.success(HttpStatus.OK, powerTypeService.getAllPowerType(),"Success");
    }

    @GetMapping("powerType/getDetailPowerType/{id}")
    public ResponseEntity<BaseResponse<PowerTypeResponse>> getDetailPowerType(@PathVariable Long id) throws LCPlatformException {
        return ResponseFactory.success(HttpStatus.OK, powerTypeService.getDetailPowerType(id),"Success");
    }
}
