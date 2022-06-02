package fis.com.vn.rest.admin;

import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.CommodityResponse;
import fis.com.vn.service.admin.CommodityService;
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
@Api(tags = "Admin Commodity controller")
public class CommodityController {
    @Autowired
    CommodityService commodityService;

    @GetMapping("/commodity/getAll")
    //@Secured({"ROLE_"})
    public ResponseEntity<BaseResponse<List<CommodityResponse>>> getAllCommodity(){
        List<CommodityResponse> commodityResponses = commodityService.getAllCommodity();
        return ResponseFactory.success(HttpStatus.OK, commodityResponses);
    }
}
