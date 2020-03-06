package com.xiaoman.config;

import com.xiaoman.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class markingConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludePath={"/login.html","/login","/regist","/regist.html","/css/**","/images/**","/js/**","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"};
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePath);
    }


}
