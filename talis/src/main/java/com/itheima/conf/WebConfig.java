package com.itheima.conf;

import com.itheima.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * 自定义的配置类
 * 用于注册拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
       //registry 专门负责注册自定义的拦截器
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login");//拦截所有
    }
}
