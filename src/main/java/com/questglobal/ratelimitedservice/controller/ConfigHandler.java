package com.questglobal.ratelimitedservice.controller;

import com.questglobal.ratelimitedservice.common.result.Result;
import com.questglobal.ratelimitedservice.common.result.ResultBuilder;
import com.questglobal.ratelimitedservice.handler.ApiInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This is a controller to enable hot config changing
 * @author Joe Ding
 */

@RestController
@Slf4j
@RequestMapping("/config")
public class ConfigHandler {

    @Autowired
    ApiInterceptor apiInterceptor;

    /**
     * config limiter count for current session
     * @param limiterCount new limiter count value
     * @return
     */
    @PostMapping("/limiter/count")
    public Result configLimiterCount(@RequestBody int limiterCount) {
        log.info("Receive request: config limiter count to " + limiterCount);
        apiInterceptor.setLimiterCount(limiterCount);
        return ResultBuilder.success();
    }
}
