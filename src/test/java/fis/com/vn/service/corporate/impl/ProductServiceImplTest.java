package fis.com.vn.service.corporate.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.service.corporate.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class ProductServiceImplTest {
    @Autowired
    ProductService productService;

    @Test
    void getAllProductByContract() throws LCPlatformException {
//        List<ProductResponse> list = productService.getAllProductByContract(5);
//        Assertions.assertTrue(list.size() >= 0);
    }

    @Test
    void saveAllProduct() {

    }

    @Test
    void deleteAllProduct() {
    }
}