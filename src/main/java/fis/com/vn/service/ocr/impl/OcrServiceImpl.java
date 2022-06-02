package fis.com.vn.service.ocr.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.request.OcrCorporateBusinessRequest;
import fis.com.vn.rest.request.OcrIdentifyRequest;
import fis.com.vn.rest.request.SignDigitalRequest;
import fis.com.vn.rest.request.VerifyIdentifyRequest;
import fis.com.vn.rest.response.*;
import fis.com.vn.service.ocr.OcrService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;


@Service
public class OcrServiceImpl implements OcrService {

    @Value("${clients.ekyc.transactionId.url}")
    private String transactionIdUrl;

    @Value("${clients.ekyc.ocrIdentify.url}")
    private String ocrIdentifyUrl;

    @Value("${clients.ekyc.verifyIdentify.url}")
    private String verifyIdentifyUrl;

    @Value("${clients.ekyc.ocrTemplate.url}")
    private String ocrTemplateUrl;

    @Value("${clients.ekyc.checkSignature.url}")
    private String checkSignatureUrl;

    @Value("${clients.ekyc.signInSignature.url}")
    private String signInSignatureUrl;

    @Value("${clients.ekyc.signDigital.url}")
    private String signDigitalUrl;

    @Value("${clients.ekyc.token}")
    private String token;

    @Value("${clients.ekyc.code}")
    private String code;

    public static final String MULTIPART_FORM_DATA_VALUE = "multipart/form-data";

    @Autowired
    @Qualifier("ocr")
    RestTemplate restTemplate;

    @Override
    public OcrIdentifyResponse getOcrIdentify(OcrIdentifyRequest ocrIdentifyRequest) {
        String transactionId = getTransactionId();
        if (transactionId != null) {
            HttpHeaders headers = getHttpHeaders(transactionId);
            HttpEntity<OcrIdentifyRequest> requestHttp = new HttpEntity<>(ocrIdentifyRequest, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(ocrIdentifyUrl, requestHttp, String.class);
            OcrIdentifyResponse ocrIdentifyResponse = null;
            try {
                ObjectMapper mapper = new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                JsonNode root = mapper.readTree(response.getBody());
                if (response.getStatusCodeValue() == 200) {
                    if(root.get("status").asInt() == 401){
                        return new OcrIdentifyResponse(root.get("status").asInt(), root.get("codeMesage").asInt(),
                                root.get("message").toString());
                    }
                    ocrIdentifyResponse = mapper.readValue(root.get("data").toString(), OcrIdentifyResponse.class);
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return ocrIdentifyResponse;
        }
        return null;
    }

    private HttpHeaders getHttpHeaders(String transactionId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("code_transaction", transactionId);
        return headers;
    }

    @Override
    public String getTransactionId() {
        ResponseEntity<String> response = restTemplate.getForEntity(transactionIdUrl, String.class);
        String transactionId = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            transactionId = root.get("data").asText();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return transactionId;
    }

    @Override
    public String verfifyIdentify(VerifyIdentifyRequest verifyIdentifyRequest) {
        String transactionId = getTransactionId();
        if (transactionId != null) {
            HttpHeaders headers = getHttpHeaders(getTransactionId());
            HttpEntity<VerifyIdentifyRequest> requestHttp = new HttpEntity<>(verifyIdentifyRequest, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(verifyIdentifyUrl, requestHttp, String.class);
            try {
                if (response.getStatusCodeValue() == 200) {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode root = mapper.readTree(response.getBody());
                    return root.get("message").asText();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }


        }
        return null;
    }

    @Override
    public OcrCorporateBussinessResponse getOcrCorporateBussiness(OcrCorporateBusinessRequest ocrCorporateBusinessRequest) throws LCPlatformException {
        String transactionId = getTransactionId();
        if (transactionId != null) {
            try {
                HttpHeaders headers = getHttpHeaders(transactionId);
                headers.set(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);


                Resource resource = new FileSystemResource(ocrCorporateBusinessRequest.getFile());
                MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
                form.set("file", new FileSystemResource(ocrCorporateBusinessRequest.getFile()));
                form.set("code", ocrCorporateBusinessRequest.getCode());
                form.set("tocdoOcr", ocrCorporateBusinessRequest.getTocDoOcr());


                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(form, headers);
                HttpEntity<OcrCorporateBusinessRequest> requestHttp = new HttpEntity<>(ocrCorporateBusinessRequest, headers);
                MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
                mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED, MediaType.MULTIPART_FORM_DATA));
                restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
                ResponseEntity<String> response = restTemplate.postForEntity(ocrTemplateUrl, requestEntity, String.class);

                ObjectMapper mapper = new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                JsonNode root = mapper.readTree(response.getBody());
                if (root.get("status").asInt() == 200) {
                    return mapper.readValue(root.get("data").toString(), OcrCorporateBussinessResponse.class);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public OcrCorporateSignatureResponse[] getOcrCorporateSignature(OcrCorporateBusinessRequest ocrCorporateBusinessRequest) throws LCPlatformException {
        String transactionId = getTransactionId();
        if (transactionId != null) {
            try {
                HttpHeaders headers = getHttpHeaders(transactionId);
                headers.set(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);

                MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
                form.set("file", new FileSystemResource(ocrCorporateBusinessRequest.getFile()));

                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(form, headers);
                MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
                mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED, MediaType.MULTIPART_FORM_DATA));
                restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
                ResponseEntity<String> response = restTemplate.postForEntity(checkSignatureUrl, requestEntity, String.class);

                ObjectMapper mapper = new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                JsonNode root = mapper.readTree(response.getBody());
                if (root.get("status").asInt() == 200) {
                    return mapper.readValue(root.get("data").toString(), OcrCorporateSignatureResponse[].class);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public OcrCorporateResponse getOcrCorporate(OcrCorporateBusinessRequest ocrCorporateBusinessRequest) throws LCPlatformException {
        OcrCorporateSignatureResponse[] ocrCorporateSignature = getOcrCorporateSignature(ocrCorporateBusinessRequest);
        if(ocrCorporateSignature != null && ocrCorporateSignature.length > 0) {
            OcrCorporateBussinessResponse ocrCorporateBussinessResponse = getOcrCorporateBussiness(ocrCorporateBusinessRequest);
            if(ocrCorporateBussinessResponse != null) {
               return OcrCorporateResponse.builder().ocrCorporateBussinessResponse(ocrCorporateBussinessResponse)
                        .ocrCorporateSignatureResponse(ocrCorporateSignature).build();
            }
        }
        return null;
    }

    @Override
    public TransactionCodeResponse signInSignature(UserInfoForSignDigital userInfoForSignDigital) throws LCPlatformException
    {
        String transactionId = getTransactionId();
        if (transactionId != null) {
            try {
                HttpHeaders headers = getHttpHeaders(transactionId);
                headers.set(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);

                MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
                form.set("file", new FileSystemResource(userInfoForSignDigital.getContractFile()));
                form.set("anhMatTruoc", new FileSystemResource(userInfoForSignDigital.getFilePathFrontIdentity()));
                form.set("anhMatSau", new FileSystemResource(userInfoForSignDigital.getFilePahBackIdentity()));
                form.set("hoVaTen", userInfoForSignDigital.getFullName());
                form.set("soCmt", userInfoForSignDigital.getIdentityNumber());
                form.set("diaChi", userInfoForSignDigital.getAddress());
                form.set("thanhPho", userInfoForSignDigital.getCity());
                form.set("quocGia", userInfoForSignDigital.getCountry());
                form.set("viTriKy", userInfoForSignDigital.getSignPosition());
                form.set("soDienThoai", userInfoForSignDigital.getPhoneNumber());

                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(form, headers);
                MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
                mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED, MediaType.MULTIPART_FORM_DATA));
                restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
                ResponseEntity<String> response = restTemplate.postForEntity(signInSignatureUrl, requestEntity, String.class);

                ObjectMapper mapper = new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                JsonNode root = mapper.readTree(response.getBody());
                if (root.get("status").asInt() == 200) {
                    mapper.readValue(root.get("data").toString(), TransactionCodeResponse.class);
                    TransactionCodeResponse transactionCodeResponse = new TransactionCodeResponse();
                    transactionCodeResponse.setMaKy(root.get("data").get("maKy").asText());
                    transactionCodeResponse.setAgreementUUID(root.get("data").get("agreementUUID").asText());
                    transactionCodeResponse.setCodeTransaction(transactionId);
                    return transactionCodeResponse;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public SignDigitalResponse signDigital(SignDigitalRequest signDigitalRequest)
    {
        if (signDigitalRequest.getCodeTransaction() != null) {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("maKy", signDigitalRequest.getMaKy())
                    .addFormDataPart("agreementUUID", signDigitalRequest.getAgreementUUID())
                    .addFormDataPart("otp", signDigitalRequest.getOtp())
                    .build();

            Request request = new Request.Builder()
                    .url(this.signDigitalUrl)
                    .addHeader("token", token)
                    .addHeader("code",  code)
                    .addHeader("Accept-Language", "vi")
                    .addHeader("code_transaction", signDigitalRequest.getCodeTransaction())
                    .post(requestBody)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String body =  response.body().string();
                System.out.println(body);
                Gson gson = new Gson();
                SignDigitalResponse signDigitalResponse = gson.fromJson(body, SignDigitalResponse.class);
                return signDigitalResponse;
            } catch (IOException e) {
                e.printStackTrace();
            }
//            HttpHeaders headers = getHttpHeaders(signDigitalRequest.getCodeTransaction());
//            HttpEntity<SignDigitalRequest> requestHttp = new HttpEntity<>(signDigitalRequest, headers);
//            ResponseEntity<String> response = restTemplate.postForEntity(signDigitalUrl, requestHttp, String.class);
//            SignDigitalResponse signDigitalResponse = null;
//            try {
//                ObjectMapper mapper = new ObjectMapper()
//                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//                mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
//                JsonNode root = mapper.readTree(response.getBody());
//                if (response.getStatusCodeValue() == 200) {
//                    mapper.readValue(root.get("data").toString(), OcrIdentifyResponse.class);
//                    signDigitalResponse.setFileBase64(root.get("data").asText());
//                }
//
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//            return signDigitalResponse;
        }
        return null;
    }

}
