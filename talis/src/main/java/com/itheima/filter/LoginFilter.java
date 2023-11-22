package com.itheima.filter;


import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")//过滤所有路径
public class LoginFilter implements Filter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        //前置：强制转换为http协议的请求对象、响应对象 （转换原因：要使用子类中特有方法）
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取请求路径url
        String  uri= request.getRequestURI();
        log.info("请求路径：{}",uri);
        //判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if (uri.contains("/login")){
            chain.doFilter(request,response);//放行请求
            return;//结束当前方法的执行
        }
        //获取请求头中的令牌（token）
        String token = request.getHeader("token");
        log.info("从请求头中获取的令牌：{}",token);
        //判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(token)){
            log.info("token不存在");
            Result error = Result.error("NOT_LOGIN");
            servletResponse.getWriter().println(JSONObject.toJSON(error));
            return;

        }

        //解析token，如果解析失败，返回错误结果（未登录）
        try {
            jwtUtils.parseJWT(token);
        }catch (Exception e){
            log.info("令牌解析失败！");
            Result error = Result.error("NOT_LOGIN");
            servletResponse.getWriter().println(JSONObject.toJSON(error));
            return;
        }

        //放行
        chain.doFilter(request,response);



    }


}
