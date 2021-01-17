package com.questglobal.ratelimitedservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Test application
 * @author Joe Ding
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAsync
public class RateLimitedServiceApplicationTests {

    @Autowired
    WebApplicationContext webApplicationContext;

    TestTask testTask;

    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        testTask = new TestTask(mockMvc);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void mockRequest() throws Exception {
        for(int i=0; i < 10; i++) {
            testTask.runTask(i);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
