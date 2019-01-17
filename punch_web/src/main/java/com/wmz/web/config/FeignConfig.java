package com.wmz.web.config;

import org.springframework.context.annotation.Configuration;

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
