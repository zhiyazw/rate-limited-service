package com.questglobal.ratelimitedservice.handler;

import com.questglobal.ratelimitedservice.handler.impl.DiscardLimitHandler;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ApiInterceptor
 * @author Joe Ding
 */

@Configuration
@Slf4j
public class ApiInterceptor extends HandlerInterceptorAdapter {

    /**
     * Max count of api requests in a single period
     */
    @Value("${service.limiter.count}")
    private Integer limiterCount;

    /**
     * Recieved requests number in current period
     */
    private Integer requestCountOfCurrentPeriod = 0;

    private LimitHandler limitHandler = new DiscardLimitHandler();

    /**
     * Change limiter count for current session
     * @param limiterCount new limiter count
     */
    public void setLimiterCount(int limiterCount) {
        synchronized (this.limitHandler) {
            log.info("Change limiter count from " + this.limiterCount + " to " + limiterCount);
            this.limiterCount = limiterCount;
        }
    }

    /**
     * Intercept each api, if fail to request api using, a LimitHandler will response to decide how to handle it
     * @param request
     * @param response
     * @param handler
     * @return Return true to go on process the api, false to abort
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String currentLoad = null;
        synchronized (requestCountOfCurrentPeriod) {
            synchronized (limiterCount) {
                currentLoad = requestCountOfCurrentPeriod + "/" + limiterCount;
            }
        }
        log.info("Intercept request: " + request.getRequestURI() + ", current load " + currentLoad);

        boolean ret = false;
        if(!requestApiUsing()) {
            ret = limitHandler.handle(request, response, handler);
        } else {
            ret = true;
        }
        if(ret) {
            log.info("Continue request: " + request.getRequestURI());
        } else {
            log.warn("Abort request: " + request.getRequestURI());
        }
        return ret;
    }

    @Scheduled(fixedRateString = "${service.limiter.period}")
    private void resetCounterTask() {
        synchronized(requestCountOfCurrentPeriod) {
            requestCountOfCurrentPeriod = 0;
        }
    }

    private boolean requestApiUsing() {
        synchronized (requestCountOfCurrentPeriod) {
            synchronized (limiterCount) {
                if(requestCountOfCurrentPeriod < limiterCount) {
                    requestCountOfCurrentPeriod++;
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
