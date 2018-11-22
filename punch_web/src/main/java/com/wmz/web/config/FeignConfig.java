package com.wmz.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;

import com.netflix.servo.util.Strings;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignConfig {

	/*
	 * @Bean public RequestInterceptor requestInterceptor() { return
	 * requestTemplate -> { String sessionId =
	 * RequestContextHolder.currentRequestAttributes().getSessionId(); if
	 * (!Strings.isNullOrEmpty(sessionId)) { requestTemplate.header("Cookie",
	 * "SESSION=" + sessionId); } }; }
	 */

	/*@Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
            if (!Strings.isNullOrEmpty(sessionId)) {
                requestTemplate.header("Cookie", "SESSION=" + sessionId);
            }
        };
    }*/
}
