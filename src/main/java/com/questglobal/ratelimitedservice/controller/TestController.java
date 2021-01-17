package com.questglobal.ratelimitedservice.controller;

import com.questglobal.ratelimitedservice.common.result.Result;
import com.questglobal.ratelimitedservice.common.result.ResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Slf4j
@RestController
@RequestMapping("/v1/apis")
public class TestController {
    @GetMapping("/test/{id}")
    public Result test(@PathVariable("id") int id) {
        log.info("Receive request: /test/" + id);
        return ResultBuilder.success();
    }
}
