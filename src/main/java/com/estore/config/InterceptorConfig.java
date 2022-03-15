package com.estore.config;

import com.estore.interceptor.AuthorizeInterceptor;
import com.estore.interceptor.CategoryInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private CategoryInterceptor interceptor;

    @Autowired
    private AuthorizeInterceptor authorizeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");

        String[] urls = {
                "/account/change",
                "/account/logout",
                "/account/edit",
                "/order/**",
        };
        registry.addInterceptor(authorizeInterceptor).addPathPatterns(urls);
    }
}
