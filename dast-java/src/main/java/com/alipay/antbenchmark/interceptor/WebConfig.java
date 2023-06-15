package com.alipay.antbenchmark.interceptor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * 注册拦截器
 *
 * @date: 2023/4/23
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Bean
    public CrawlerInterceptor getInterceptor() {
        return new CrawlerInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor()).addPathPatterns("/**/*");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}
