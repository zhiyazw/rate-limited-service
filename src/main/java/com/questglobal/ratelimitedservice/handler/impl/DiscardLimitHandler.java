package com.questglobal.ratelimitedservice.handler.impl;

import com.questglobal.ratelimitedservice.handler.LimitHandler;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Discard the api request when exceeding rate limit
 * @author Joe Ding
 */

@Slf4j
public class DiscardLimitHandler implements LimitHandler {
    /**
     * Handle the case of api exceeding rate limit
     *
     * @param request
     * @param response
     * @param handler
     * @return simply return false to abort the normal api process and discard this api request;
     */
    @Override
    public boolean handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.warn("Discard request: " + request.getRequestURI());
        return false;
    }
}
