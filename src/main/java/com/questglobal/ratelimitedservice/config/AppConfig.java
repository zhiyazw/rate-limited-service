package com.questglobal.ratelimitedservice.config;

import com.questglobal.ratelimitedservice.handler.ApiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * AppConfig
 * @author Joe Ding
 */
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    ApiInterceptor apiInterceptor;

    /**
     * Interceptor for api request
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor);
    }
}
