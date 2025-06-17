package com.finance.birge.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.LinkedHashMap;

@Slf4j
@Configuration
public class CourseService {
    @Autowired
    private WebClient webClient;

    @Autowired
    private RestService restService;

    @Scheduled(fixedRate = 10000)
    public void getCourses()
    {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
        log.info("Scheduled thread: {}", Thread.currentThread().getName());
        webClient.get().uri("/crypto").retrieve().bodyToMono(LinkedHashMap.class)
                .doOnSuccess(restService::setCryptocourse)
                .subscribe(res -> log.debug("Thread: {}\nResult: {}", Thread.currentThread().getName(), res));

        webClient.get().uri("/moneys").retrieve().bodyToMono(LinkedHashMap.class)
                .doOnSuccess(restService::setMoneycourse)
                .subscribe(res -> log.debug("Thread: {}\nResult: {}", Thread.currentThread().getName(), res));

        log.debug("Result");
    }
}
