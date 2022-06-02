package fis.com.vn.rest.admin;

import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.ElectricTypeResponse;
import fis.com.vn.service.admin.ElectricTypeService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
//@Secured({ "ROLE_admin"})
@Api(tags = "Electric type")
public class ElectricTypeController {
    @Autowired
    private ElectricTypeService electricTypeService;

    @GetMapping("/electricType/getAll")
    //@Secured({"ROLE_"})
    public ResponseEntity<BaseResponse<List<ElectricTypeResponse>>> getAll(){
        List<ElectricTypeResponse> electricTypeResponseList = electricTypeService.findAll();
        return ResponseFactory.success(HttpStatus.OK, electricTypeResponseList);
    }

    @GetMapping("/electricType/get/{id}")
    //@Secured({"ROLE_"})
    public ResponseEntity<BaseResponse<ElectricTypeResponse>> getElectricType(@PathVariable Long id){
        ElectricTypeResponse electricTypeResponse = electricTypeService.getElectricType(id);
        return ResponseFactory.success(HttpStatus.OK, electricTypeResponse);
    }
}
