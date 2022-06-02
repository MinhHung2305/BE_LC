package fis.com.vn.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RestTemplateOcrInterceptor implements ClientHttpRequestInterceptor {

    @Value("${clients.ekyc.token}")
    private String token;

    @Value("${clients.ekyc.code}")
    private String code;

    @Value("${clients.ekyc.acceptLanguage}")
    private String acceptLanguage ;


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("token", "56c2f48a-3339-4081-9ddd-586062b9063a");
        request.getHeaders().add("code", "LCTEST");
        request.getHeaders().add("Accept-Language", "vi");
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;

    }

    private void logRequest(HttpRequest request, byte[] body) {
        if (log.isDebugEnabled())
        {
            log.info("===========================request begin================================================");
            log.info("URI         : {}", request.getURI());
            log.info("Method      : {}", request.getMethod());
            log.info("Headers     : {}", request.getHeaders());
            log.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
            log.info("==========================request end================================================");
        }
    }

    private void logResponse(ClientHttpResponse response) throws IOException
    {
        if (log.isDebugEnabled())
        {
            log.info("============================response begin==========================================");
            log.info("Status code  : {}", response.getStatusCode());
            log.info("Status text  : {}", response.getStatusText());
            log.info("Headers      : {}", response.getHeaders());
            log.info("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
            log.info("=======================response end=================================================");
        }
    }
}
