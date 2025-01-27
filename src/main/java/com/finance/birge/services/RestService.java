package com.finance.birge.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Slf4j
@Service
public class RestService {
    @Value("${services.birge.url}")
    private String birge_url;

    @Autowired
    private RestTemplate restTemplate;

    public LinkedHashMap<String,Float> getCourses(String path)
    {
        log.info("url: {}", birge_url + path);
        LinkedHashMap<String,Float> res = restTemplate.getForObject(birge_url+path, LinkedHashMap.class);
        log.debug("result: {}", res);
        return res;
    }
}
