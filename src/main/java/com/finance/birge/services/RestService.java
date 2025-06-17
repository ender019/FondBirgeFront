package com.finance.birge.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Slf4j
@Service
public class RestService {
    private LinkedHashMap<String, Float> moneycourse;
    private LinkedHashMap<String, Float> cryptocourse;

    public synchronized void setCryptocourse(LinkedHashMap<String, Float> cryptocourse) {
        this.cryptocourse = cryptocourse;
    }

    public synchronized void setMoneycourse(LinkedHashMap<String, Float> moneycourse) {
        this.moneycourse = moneycourse;
    }

    public LinkedHashMap<String,Float> getCourses(String path)
    {
        log.info("Path: {}", path);
        log.info(Thread.currentThread().getName());
        LinkedHashMap<String, Float> res;
        if(path.equals("money")) res = moneycourse;
        else if(path.equals("crypto")) res = cryptocourse;
        else {
            res = new LinkedHashMap<>(moneycourse);
            res.putAll(cryptocourse);
            return res;
        }
        log.debug("Result: {}", res);
        return res;
    }
}
