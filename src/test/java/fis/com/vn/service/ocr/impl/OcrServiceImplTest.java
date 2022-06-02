package fis.com.vn.service.ocr.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.request.OcrCorporateBusinessRequest;
import fis.com.vn.rest.request.OcrIdentifyRequest;
import fis.com.vn.rest.request.VerifyIdentifyRequest;
import fis.com.vn.rest.response.OcrCorporateBussinessResponse;
import fis.com.vn.rest.response.OcrCorporateResponse;
import fis.com.vn.rest.response.OcrCorporateSignatureResponse;
import fis.com.vn.rest.response.OcrIdentifyResponse;
import fis.com.vn.service.ocr.OcrService;
import fis.com.vn.util.FilesUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.net.URISyntaxException;

@SpringBootTest
@ActiveProfiles("local")
class OcrServiceTest {
    @Autowired
    OcrService ocrService;


    @Test
    public void getOcrIdentify() {

        try {
            String matSau = "cmnd_1.jpg";
            String matTruoc = "cmnd_2.jpg";
            ClassLoader classLoader = getClass().getClassLoader();
            File inputFileMatTruoc = new File(classLoader.getResource(matTruoc).getFile());
            File inputFileMatSau = new File(classLoader.getResource(matSau).getFile());
            String sMatTruoc = FilesUtils.convertFileToBase64(inputFileMatTruoc);
            String sMatSau = FilesUtils.convertFileToBase64(inputFileMatSau);
            OcrIdentifyRequest ocrIdentifyRequest = OcrIdentifyRequest.builder()
                    .anhMatSau(sMatSau)
                    .anhMatTruoc(sMatTruoc)
                    .maGiayTo("cmtnd").build();
            OcrIdentifyResponse ocrIdentify = ocrService.getOcrIdentify(ocrIdentifyRequest);
            System.out.println(ocrIdentify);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void getOcrCorporateBussiness() {

        try {

            String gpdkkd = "dkdn.pdf";
            ClassLoader classLoader = getClass().getClassLoader();
            File gpdkkdFile = new File(classLoader.getResource(gpdkkd).toURI());
            OcrCorporateBusinessRequest ocrCorporateBusinessRequest = OcrCorporateBusinessRequest.builder()
                    .code("gpdkdn").file(gpdkkdFile).tocDoOcr("1").build();


            OcrCorporateBussinessResponse ocrCorporateBussiness = ocrService.getOcrCorporateBussiness(ocrCorporateBusinessRequest);
            System.out.println(ocrCorporateBussiness);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void getOcrCorporateSignature() {

        try {

            String gpdkkd = "dkdn.pdf";
            ClassLoader classLoader = getClass().getClassLoader();
            File gpdkkdFile = new File(classLoader.getResource(gpdkkd).toURI());
            OcrCorporateBusinessRequest ocrCorporateBusinessRequest = OcrCorporateBusinessRequest.builder()
                    .code("gpdkdn").file(gpdkkdFile).tocDoOcr("1").build();


            OcrCorporateSignatureResponse[] ocrCorporateSignature = ocrService.getOcrCorporateSignature(ocrCorporateBusinessRequest);
            System.out.println(ocrCorporateSignature);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void verifyIndentify() {

        try {
            String anhKhachHang = "chandung.jpg";
            String matTruoc = "cmnd_2.jpg";
            ClassLoader classLoader = getClass().getClassLoader();
            File inputFileMatTruoc = new File(classLoader.getResource(matTruoc).getFile());
            File inputFileAnhKhachHang = new File(classLoader.getResource(anhKhachHang).getFile());
            String sMatTruoc = FilesUtils.convertFileToBase64(inputFileMatTruoc);
            String sAnhKhachHang = FilesUtils.convertFileToBase64(inputFileAnhKhachHang);
            VerifyIdentifyRequest verifyIdentifyRequest = VerifyIdentifyRequest.builder()
                    .anhKhachHang(sAnhKhachHang).anhMatTruoc(sMatTruoc).build();

            String verfifyIdentify = ocrService.verfifyIdentify(verifyIdentifyRequest);
            System.out.println(verfifyIdentify);
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }

    @Test
    public void getTransactionId() {

        String transactionId = ocrService.getTransactionId();
        System.out.println(transactionId);

    }
    @Test
    public void getOcrCorporate() throws LCPlatformException, URISyntaxException {
        String gpdkkd = "dkdn.pdf";
        ClassLoader classLoader = getClass().getClassLoader();
        File gpdkkdFile = new File(classLoader.getResource(gpdkkd).toURI());
        OcrCorporateBusinessRequest ocrCorporateBusinessRequest = OcrCorporateBusinessRequest.builder()
                .code("gpdkdn").file(gpdkkdFile).tocDoOcr("1").build();
        OcrCorporateResponse ocrCorporate = ocrService.getOcrCorporate(ocrCorporateBusinessRequest);
        System.out.println(ocrCorporate);
    }
}