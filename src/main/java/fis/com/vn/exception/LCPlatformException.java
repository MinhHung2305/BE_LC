package fis.com.vn.exception;

import fis.com.vn.common.Translator;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.rest.response.BaseResponse;
import lombok.Getter;

@Getter
public class LCPlatformException extends RuntimeException{
    /**
     * Value from ResponseCode
     */
    private int code;

    /**
     * Message from Response code or custom message
     */
    private String message;

    /**
     * Data return at data field on Rest api response
     */
    private Object data;

    public LCPlatformException(ResponseCode responseCode){
        this.code = responseCode.getValue();
        this.message = Translator.toLocale(String.valueOf(responseCode.getValue()));
    }

    public LCPlatformException(ResponseCode responseCode, String message){
        this.code = responseCode.getValue();
        this.message = message; //Custom message
    }

    public LCPlatformException(ResponseCode responseCode, Object data){
        this.code = responseCode.getValue();
        this.message =Translator.toLocale(String.valueOf(responseCode.getValue()));
        this.data = data;
    }

    public LCPlatformException(BaseResponse response) {
        this.code = response.getCode();
        this.message = response.getMessage();
        this.data = response.getData();
    }

    public LCPlatformException(ResponseCode responseCode, Object data, String message){
        this.code = responseCode.getValue();
        this.message = message; //Custom message
        this.data = data;
    }

    public LCPlatformException(ResponseCode responseCode, int countDown){
        this.code = responseCode.getValue();
        this.message = String.format(String.valueOf(responseCode.getValue()), countDown); //Custom message
        this.data = data;
    }

   
}