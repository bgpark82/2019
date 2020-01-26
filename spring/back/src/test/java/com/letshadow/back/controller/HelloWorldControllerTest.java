package com.letshadow.back.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class HelloWorldControllerTest {

    @Autowired
    private HelloWorldController helloWorldController;
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .alwaysDo(print())
                .build();
    }


    @Test
    public void hello(){
        System.out.println(helloWorldController.hello());
        assertThat(helloWorldController.hello()).isEqualTo("hello");
    }

    @Test
    public void mockHello() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(helloWorldController).build();
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("hello"));
    }

    @Test
    public void mocktest() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(helloWorldController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello"));
    }

    @Test
    void helloException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/exception"))
        .andExpect(status().isInternalServerError())
        .andExpect(jsonPath("$.code").value(500))
        .andExpect(jsonPath("$.message").value("알 수 없는 서버 오류가 발생하였습니다"));
    }

}