package fis.com.vn.rest.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.response.BankRatingResponse;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.service.admin.BankRatingService;
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
@Api(tags = "Admin bank rating controller")
public class AdminBankRating {
    @Autowired
    BankRatingService bankRatingService;

    @GetMapping("/bankRating/getAllBankRating")
    //@Secured({""})
    public ResponseEntity<BaseResponse<List<BankRatingResponse>>> getAllFeeRules() throws LCPlatformException {
        List<BankRatingResponse> bankRatingRes = bankRatingService.getAllBankRating();
        return ResponseFactory.success(HttpStatus.OK, bankRatingRes, "Get All Bank Rating Success");
    }

}
