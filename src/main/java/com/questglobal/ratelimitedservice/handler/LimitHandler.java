package com.questglobal.ratelimitedservice.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LimitHandler - Handle the case of api exceeding rate limit
 * @author Joe Ding
 */
public interface LimitHandler {
    /**
     * Handle the case of api exceeding rate limit
     * @param request
     * @param response
     * @param handler
     * @return return true(or false) to continue(or abort) the normal api process;
     */
    public boolean handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
