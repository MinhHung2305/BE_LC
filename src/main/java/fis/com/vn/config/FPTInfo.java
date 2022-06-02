package fis.com.vn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FPTInfo {
    @Value("${fpt.website}")
    private String fptWebsite;

    @Value("$fpt.hotline")
    private String hotline;
}
