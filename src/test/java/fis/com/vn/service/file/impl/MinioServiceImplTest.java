package fis.com.vn.service.file.impl;

import fis.com.vn.service.file.MinioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class MinioServiceImplTest {

    @Autowired
    MinioService minioService;
    @Test
    void getObjectUrl(){
        String url = minioService.getObjectUrl("s3-lc-dev","Test.pdf");
        System.out.println(url);
    }
}