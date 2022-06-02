package fis.com.vn.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    private static final Logger logger = LoggerFactory.getLogger(AsyncConfig.class);

    @Bean("threadPoolTaskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Async-");
        return executor;
    }

//    public static void main(String[] args) {
//        SpringApplication.run(AsyncConfig.class, args);
//    }

//    @Override
//    public void run(String...args) throws Exception {
//        // Start the clock
//        long start = System.currentTimeMillis();
////        userInfoService.createAllCorporateUserInfo();
//        // Kick of multiple, asynchronous lookups
////       gitHubLookupService.findUser(99L);
////        CompletableFuture < User > page2 = gitHubLookupService.findUser("CloudFoundry");
////        CompletableFuture < User > page3 = gitHubLookupService.findUser("Spring-Projects");
////        CompletableFuture < User > page4 = gitHubLookupService.findUser("RameshMF");
////        // Wait until they are all done
////        CompletableFuture.allOf(page1, page2, page3, page4).join();
//
//        // Print results, including elapsed time
//        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
////        logger.info("--> " + page1.get());
////        logger.info("--> " + page2.get());
////        logger.info("--> " + page3.get());
////        logger.info("--> " + page4.get());
//    }
}
