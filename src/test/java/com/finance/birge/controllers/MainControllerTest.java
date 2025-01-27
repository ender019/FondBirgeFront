package com.finance.birge.controllers;

import com.finance.birge.services.RestService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mockito;

import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainController.class)
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
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
    public void returnDefaultPageTest() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void returnBirgePageTest() throws Exception {
        String path = "all";
        Mockito.when(restService.getCourses(path)).thenReturn(resp);
        this.mockMvc.perform(get("/birge/" + path)).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("birge"))
                .andExpect(model().attribute("path", path))
                .andExpect(model().attribute("data_kol", resp.size()))
                .andExpect(model().attribute("data", resp));
    }

    @Test
    public void returnBoardPageTest() throws Exception {
        Mockito.when(restService.getCourses("all")).thenReturn(resp);
        this.mockMvc.perform(get("/birge/get/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("blocks/board"))
                .andExpect(model().attribute("data_kol", resp.size()))
                .andExpect(model().attribute("data", resp));;
    }
}
