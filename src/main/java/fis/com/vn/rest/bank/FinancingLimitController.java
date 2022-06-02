package fis.com.vn.rest.bank;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.rest.request.FinancingLimitRequest;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.FinancingLimitResponse;
import fis.com.vn.service.bank.FinancingLimitService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financingLimit")
@Slf4j
@Api(tags = "Financing Limit")
public class FinancingLimitController {
    @Autowired
    private FinancingLimitService financingLimitService;

    @PostMapping("/sponsorBank/create")
//    @Secured({"ROLE_create_bank_user_management"})
    public ResponseEntity<BaseResponse<FinancingLimitResponse>> create(@RequestBody FinancingLimitRequest financingLimitRequest, @AuthenticationPrincipal Jwt principal) throws LCPlatformException, SendEmailException {
        String userId = principal.getSubject();
        FinancingLimitResponse financingLimitResponse = financingLimitService.create(userId, financingLimitRequest);
        return ResponseFactory.success(HttpStatus.OK, financingLimitResponse);
    }

    @PostMapping("/sponsorBank/search")
    public ResponseEntity<BaseResponse<List<FinancingLimitResponse>>> search(@RequestBody FinancingLimitRequest financingLimitRequest,  @AuthenticationPrincipal Jwt principal) throws LCPlatformException{
        String userId = principal.getSubject();
        List<FinancingLimitResponse> financingLimitResponses = financingLimitService.search(userId, financingLimitRequest);
        return ResponseFactory.success(HttpStatus.OK, financingLimitResponses);
    }

    @PostMapping("/sponsorBank/delete")
    public void delete(@RequestParam Long id, @AuthenticationPrincipal Jwt principal) throws LCPlatformException, SendEmailException {
        String userId = principal.getSubject();
        financingLimitService.delete(id, userId);
    }

    @PostMapping("/sponsorBank/update")
//    @Secured({"ROLE_create_bank_user_management"})
    public ResponseEntity<BaseResponse<FinancingLimitResponse>> update(@RequestBody FinancingLimitRequest financingLimitRequest, @AuthenticationPrincipal Jwt principal) throws LCPlatformException, SendEmailException {
        String userId = principal.getSubject();
        FinancingLimitResponse financingLimitResponse = financingLimitService.update(userId, financingLimitRequest);
        return ResponseFactory.success(HttpStatus.OK, financingLimitResponse);
    }

    @GetMapping("/view/{id}")
//    @Secured({"ROLE_create_bank_user_management"})
    public ResponseEntity<BaseResponse<FinancingLimitResponse>> BankView(@PathVariable Long id) throws LCPlatformException{
        FinancingLimitResponse financingLimitResponse = financingLimitService.view(id);
        return ResponseFactory.success(HttpStatus.OK, financingLimitResponse);
    }

    @PostMapping("/releaseBank/search")
    public ResponseEntity<BaseResponse<List<FinancingLimitResponse>>> releaseBankSearch(@RequestBody FinancingLimitRequest financingLimitRequest,  @AuthenticationPrincipal Jwt principal) throws LCPlatformException{
        String userId = principal.getSubject();
        List<FinancingLimitResponse> financingLimitResponses = financingLimitService.releaseBankSearch(userId, financingLimitRequest);
        return ResponseFactory.success(HttpStatus.OK, financingLimitResponses);
    }
}
