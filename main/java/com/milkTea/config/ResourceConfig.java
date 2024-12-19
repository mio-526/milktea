package com.milkTea.config;

import com.milkTea.interceptor.CustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String path;

    // 白名单
    private String[] whiteList = new String[]{"/profile/**","/api/login","/api/logout","/api/register"};

    @Autowired
    private CustomInterceptor customInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/profile/**").addResourceLocations("file:" + path + "/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor).excludePathPatterns(whiteList).addPathPatterns("/**");
    }
}
