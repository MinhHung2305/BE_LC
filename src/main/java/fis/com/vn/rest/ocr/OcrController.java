package fis.com.vn.rest.ocr;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.repository.CorporateRepository;
import fis.com.vn.rest.request.OcrCorporateBusinessRequest;
import fis.com.vn.rest.request.OcrIdentifyRequest;
import fis.com.vn.rest.request.VerifyIdentifyRequest;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.rest.response.OcrCorporateResponse;
import fis.com.vn.rest.response.OcrErrorRepose;
import fis.com.vn.rest.response.OcrIdentifyResponse;
import fis.com.vn.service.ocr.OcrService;
import fis.com.vn.util.FilesUtils;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/ocr")
@Api(tags = "Ocr controller")
public class OcrController {

    @Autowired
    OcrService ocrService;

    @Autowired
    CorporateRepository corporateRepository;

    @PostMapping("/getOcrCorporateBussiness")
    public ResponseEntity<?> getOcrCorporateBussiness(
            @RequestParam("ocrCode") String ocrCode,
            @RequestParam("file") MultipartFile multipartFile, @RequestParam("tocDoOcr") String tocDoOcr) {
        try {

            File tempFile = File.createTempFile("tempFile", ".pdf");
            tempFile.deleteOnExit();
            multipartFile.transferTo(tempFile);
            OcrCorporateBusinessRequest ocrCorporateBusinessRequest = OcrCorporateBusinessRequest.builder()
                    .code(ocrCode).file((File) tempFile).tocDoOcr(tocDoOcr).build();
            OcrCorporateResponse ocrCorporate = ocrService.getOcrCorporate(ocrCorporateBusinessRequest);
            if (corporateRepository.existsByCorporateCode(ocrCorporate.getOcrCorporateBussinessResponse().getMaSoDoanhNghiep())) {
//                throw new LCPlatformException(ResponseCode.BAD_REQUEST, "corporate code has exists");
                return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, "corporate code has exists");
            }
            tempFile.delete();
            return ResponseFactory.success(HttpStatus.OK, ocrCorporate);
        } catch (LCPlatformException | IOException e) {
            e.printStackTrace();
//            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, "corporate code has exists");
        }
        return null;
    }

    @PostMapping("/getOcrCorporateBussinessUpdate")
    public ResponseEntity<?> getOcrCorporateBussinessUpdate(
            @RequestParam("ocrCode") String ocrCode, @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("tocDoOcr") String tocDoOcr, @RequestParam("corporateCode") String corporateCode) {
        try {

            File tempFile = File.createTempFile("tempFile", ".pdf");
            tempFile.deleteOnExit();
            multipartFile.transferTo(tempFile);
            OcrCorporateBusinessRequest ocrCorporateBusinessRequest = OcrCorporateBusinessRequest.builder()
                    .code(ocrCode).file((File) tempFile).tocDoOcr(tocDoOcr).build();
            OcrCorporateResponse ocrCorporate = ocrService.getOcrCorporate(ocrCorporateBusinessRequest);
            if (!ocrCorporate.getOcrCorporateBussinessResponse().getMaSoDoanhNghiep().equals(corporateCode)) {
                if (corporateRepository.existsByCorporateCode(ocrCorporate.getOcrCorporateBussinessResponse().getMaSoDoanhNghiep())) {
                    return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, "Mã doang nghiệp đã tồn tại");
                }
                return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, "Thông tin giấy đăng ký kinh doanh không khớp với mã số doanh nghiệp");
            }
            tempFile.delete();
            return ResponseFactory.success(HttpStatus.OK, ocrCorporate);
        } catch (LCPlatformException | IOException e) {
            e.printStackTrace();
//            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, "corporate code has exists");
        }
        return null;
    }

    @PostMapping("/getOcrIdentify")
    public ResponseEntity<?> getOcrIdentify(
            @RequestParam("ocrCode") String ocrCode,
            @RequestParam("fileFrontIdentify") MultipartFile multipartFileFrontIdentify,
            @RequestParam("fileBackIdentify") MultipartFile multipartFileBackIdentify) {
        try {
            File tempFile = File.createTempFile("tempFile", "");
            multipartFileFrontIdentify.transferTo(tempFile);
            String sMatTruoc = FilesUtils.convertFileToBase64(tempFile);
            tempFile.delete();

            File tempFile2 = File.createTempFile("tempFile2", "");
            multipartFileBackIdentify.transferTo(tempFile2);
            String sMatSau = FilesUtils.convertFileToBase64(tempFile2);


            OcrIdentifyRequest ocrIdentifyRequest = OcrIdentifyRequest.builder()
                    .anhMatSau(sMatSau)
                    .anhMatTruoc(sMatTruoc)
                    .maGiayTo(ocrCode).build();
            OcrIdentifyResponse ocrIdentify = ocrService.getOcrIdentify(ocrIdentifyRequest);
            if (ocrIdentify.getStatus() != null && ocrIdentify.getStatus() == 401) {
                OcrErrorRepose ocrErrorRepose = new OcrErrorRepose();
                ocrErrorRepose.setStatus(ocrIdentify.getStatus());
                ocrErrorRepose.setCodeMesage(ocrIdentify.getCodeMesage());
                ocrErrorRepose.setMessage(ocrIdentify.getMessage());
                return ResponseFactory.success(HttpStatus.OK, ocrErrorRepose);
            }
            tempFile2.delete();
            return ResponseFactory.success(HttpStatus.OK, ocrIdentify);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/verifyIndentify")
    public ResponseEntity<BaseResponse<String>> verifyIndentify(
            @RequestParam("fileFrontIdentify") MultipartFile multipartFileFrontIdentify,
            @RequestParam("filePortrait") MultipartFile multipartFilePortrait) {
        try {
            File tempFile = File.createTempFile("tempFile", "");
            tempFile.deleteOnExit();
            multipartFileFrontIdentify.transferTo(tempFile);
            String sMatTruoc = FilesUtils.convertFileToBase64(tempFile);
            tempFile.delete();

            File tempFile2 = File.createTempFile("tempFile2", "");
            multipartFilePortrait.transferTo(tempFile2);
            String sAnhKhachHang = FilesUtils.convertFileToBase64(tempFile2);
            tempFile2.delete();

            VerifyIdentifyRequest verifyIdentifyRequest = VerifyIdentifyRequest.builder()
                    .anhKhachHang(sAnhKhachHang).anhMatTruoc(sMatTruoc).build();

            String verfifyIdentify = ocrService.verfifyIdentify(verifyIdentifyRequest);
            System.out.println(verfifyIdentify);
            return ResponseFactory.success(HttpStatus.OK, verfifyIdentify);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseFactory.success(HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/getTransactionId")
    public ResponseEntity<BaseResponse<String>> getTransactionId() {
        String transactionId = ocrService.getTransactionId();
        System.out.println(transactionId);
        return ResponseFactory.success(HttpStatus.OK, transactionId);
    }
}
