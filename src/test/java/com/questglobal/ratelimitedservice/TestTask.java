package com.questglobal.ratelimitedservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class TestTask {

    MockMvc mockMvc;

    public TestTask(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Async
    public void runTask(int id) throws Exception {
        //new RestTemplate().getForObject("http://127.0.0.1:8080/v1/apis/test/{id}", String.class, id);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/apis/test/{id}", id)).andReturn();
    }
}
