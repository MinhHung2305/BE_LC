package fis.com.vn.rest.common;

import fis.com.vn.model.entity.Currency;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.service.common.DataCommonService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
@Api(tags = "Data Config controller")
public class DataConfigController {
    @Autowired
    DataCommonService dataCommonService;


    /**
     * get all Currency
     * @return list currency
     */
    @GetMapping("/currency/getAll")
    //@Secured({"ROLE_view_bank_user_management"})
    public ResponseEntity<BaseResponse<List<Currency>>> getCurrencies() {
        List<Currency> currencyList = dataCommonService.getCurrencies();
        return ResponseFactory.success(HttpStatus.OK, currencyList);
    }

}
