package fis.com.vn.rest.corporate;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.Product;
import fis.com.vn.repository.ProductRepository;
import fis.com.vn.rest.mapper.ProductResponseMapper;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.ProductResponse;
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
@RequestMapping("/product")
@Slf4j
//@Secured({ "ROLE_admin"})
@Api(tags = "Admin License controller")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductResponseMapper productResponseMapper;

    @GetMapping("/getAll")
    //@Secured({"ROLE_"})
    public ResponseEntity<BaseResponse<List<ProductResponse>>> getAll() throws LCPlatformException {
        List<Product> productList = productRepository.findAll();
        List<ProductResponse> productResponseList = productResponseMapper.toDomain(productList);
        return ResponseFactory.success(HttpStatus.OK, productResponseList);
    }
}
