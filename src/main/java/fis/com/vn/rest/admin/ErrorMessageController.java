package fis.com.vn.rest.admin;

import fis.com.vn.model.entity.MessageError;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@RestController
@RequestMapping(value = "/admin", produces = "application/json;charset=UTF-8" )
//@PropertySource("classpath:messages_vi.properties")
//@PropertySource("classpath:messages_en.properties")
//@Secured({ "ROLE_admin"})
@Slf4j
@Api(tags = "Error Message Controller")
public class ErrorMessageController {

    private static String fileNameEnglish= "messages_en.properties";

    private static String fileNameVietnamese= "messages_vi.properties";
    /**
     * đọc file properties
     * @param fileName
     * @return
     */
    private InputStreamReader readerFileProperties(String fileName){
        InputStream input = null;
        input = ErrorMessageController.class.getClassLoader().getResourceAsStream(fileName);
        return new InputStreamReader(input);
    }

    /**
     * kiểm tra có phải chữ số hay không
     * @return
     * @throws
     */

    private boolean checkNumber(String errorCode){
        for(int i=0;i<errorCode.length();i++){
            if(errorCode.charAt(i)<48 || errorCode.charAt(i)>57) return false;
        }
        return true;
    }



    /**
     * lấy ra danh sách các lỗi trong properties
     * @return
     * @throws IOException
     */
    private List<MessageError> getListMess() throws IOException {
        List<MessageError> messageErrors = new ArrayList<>();
        Properties propertiesEnglish =new Properties();
        Properties propertiesVietnamese =new Properties();

        Reader readerEnglish = readerFileProperties(fileNameEnglish);
        propertiesEnglish.load(readerEnglish);

        Reader readerVietNamese = readerFileProperties(fileNameVietnamese);
        propertiesVietnamese.load(readerVietNamese);


        Set<String> codeErrors = propertiesEnglish.stringPropertyNames();
        for(String tmp: codeErrors){
            if(checkNumber(tmp)){
                int codeError = Integer.parseInt(tmp);
                String messageEnglish = propertiesEnglish.getProperty(tmp);
                String messageVietnamese = propertiesVietnamese.getProperty(tmp);
                String messageError = ResponseCode.valueOf(codeError).toString();
                String moduleCode = tmp.substring(0,1);
                messageErrors.add(new MessageError(codeError,messageError,messageEnglish,messageVietnamese,moduleCode));
            }
        }
        return messageErrors;
    }

    /**
     * api lấy danh sách các lỗi
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/getmessage")
    @ApiOperation(value = "Get Message Error")
    public ResponseEntity<BaseResponse<List<MessageError>>> getMessage() throws IOException {
        List<MessageError> messageErrors = getListMess();
        return ResponseFactory.success(HttpStatus.OK, messageErrors,"Get message error success");
    }

    /**
     * api tìm kiếm các lỗi
     * @return
     * @throws IOException
     */
    private boolean checkInfo(Integer codeError,String messageErrorCodeName, String messageEnglish, String messageVietnamese,String moduleCode, MessageError messageError){
        boolean success;
        String moduleCodeError = String.valueOf(messageError.getCodeError()).substring(0,1);

        if((codeError != null && codeError == messageError.getCodeError())
                ||(messageErrorCodeName != null && messageError.getMessageErrorName().contains(messageErrorCodeName))
                ||(messageEnglish != null && messageError.getMessageEnglish().contains(messageEnglish))
                ||(messageVietnamese != null && messageError.getMessageVietnamese().contains(messageVietnamese))
                ||(moduleCode != null && moduleCodeError.equals(moduleCode))){
            success = true;
        }
        else success = false;
        return success;
    }

    @PostMapping (value = "/searcherrormessage", consumes = "application/json;charset=UTF-8")
    @ApiOperation(value = "Search Error Message")
    public ResponseEntity<BaseResponse<List<MessageError>>> searchErrorMessage(@RequestBody MessageError messageError) throws IOException {

        List<MessageError> messageErrors = getListMess();
        List<MessageError> searchMessageErrors = new ArrayList<>();

        for(MessageError message: messageErrors){
            if(checkInfo(messageError.getCodeError(),messageError.getMessageErrorName(),messageError.getMessageEnglish(),messageError.getMessageVietnamese(),messageError.getModuleCode(),message)){
                searchMessageErrors.add(message);
            }
        }
        return ResponseFactory.success(HttpStatus.OK,searchMessageErrors,"Search message error success");
    }


}

