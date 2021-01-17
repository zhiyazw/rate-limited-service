package com.questglobal.ratelimitedservice.handler.impl;

import com.questglobal.ratelimitedservice.handler.LimitHandler;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cache the api request when exceeding rate limit, and process the api request later
 * @author Joe Ding
 */

public class CacheLimitHandler implements LimitHandler {
    /**
     * Cache the api request and process it later when exceeding rate limit
     *
     * @param request
     * @param response
     * @param handler
     * @return return true(or false) to continue(or abort) the normal api process;
     */
    @Override
    public boolean handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO: implement me in next version
        throw new NotImplementedException();
    }
}
