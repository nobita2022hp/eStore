package com.estore.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Bean(name = "messageSource")
    public MessageSource getMessageSource(){
        var ms = new ReloadableResourceBundleMessageSource();
        ms.setBasenames("classpath:static/i18n/messages/account"
                ,"classpath:static/i18n/messages/layout");
        ms.setDefaultEncoding("utf-8");
        return ms;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        var inter = new LocaleChangeInterceptor();
        inter.setParamName("lang");
        registry.addInterceptor(inter).addPathPatterns("/home/language");
    }

    @Bean("localeResolver")
    public LocaleResolver getLocaleResolver(){
        var c = new CookieLocaleResolver();
        c.setCookiePath("/");
        c.setCookieMaxAge(2 * 24 * 60 * 60); // 2 ngay
        c.setDefaultLocale(new Locale("vi"));
        return c;
    }
}
