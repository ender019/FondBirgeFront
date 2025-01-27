package com.finance.birge.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RestServiceTest {
    @Value("${services.birge.url}")
    private String birge_url;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private RestService restService;

    private static LinkedHashMap<String,Float> resp = new LinkedHashMap<>();

    @BeforeAll
    public static void setup_data()
    {
        resp.put("AUD", 62.0633f);
        resp.put("AZN", 57.8021f);
        resp.put("AMD", 0.246293f);
        resp.put("THB", 2.90867f);
        resp.put("BYN", 28.7766f);
        resp.put("BGN", 52.2712f);
        resp.put("BRL", 16.5438f);
    }

    @Test
    public void getCoursesAllTest()
    {
        String path = "all";
        Mockito.when(restTemplate.getForObject(birge_url+path, LinkedHashMap.class)).thenReturn(resp);
        LinkedHashMap<String, Float> res = restService.getCourses(path);
        Mockito.verify(restTemplate).getForObject(birge_url+path, LinkedHashMap.class);
        assertNotNull(res);
        assertEquals(res, resp);
    }

    @Test
    public void getCoursesMoneysTest()
    {
        String path = "moneys";
        Mockito.when(restTemplate.getForObject(birge_url+path, LinkedHashMap.class)).thenReturn(resp);
        LinkedHashMap<String, Float> res = restService.getCourses(path);
        Mockito.verify(restTemplate).getForObject(birge_url+path, LinkedHashMap.class);
        assertNotNull(res);
        assertEquals(res, resp);
    }

    @Test
    public void getCoursesCryptoTest()
    {
        String path = "crypto";
        Mockito.when(restTemplate.getForObject(birge_url+path, LinkedHashMap.class)).thenReturn(resp);
        LinkedHashMap<String, Float> res = restService.getCourses(path);
        Mockito.verify(restTemplate).getForObject(birge_url+path, LinkedHashMap.class);
        assertNotNull(res);
        assertEquals(res, resp);
    }
}
