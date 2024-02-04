package com.dorkytiger.hotel_manager.handler;

import com.alibaba.fastjson2.JSONObject;
import com.dorkytiger.hotel_manager.emun.Code;
import com.dorkytiger.hotel_manager.emun.Message;
import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import com.dorkytiger.hotel_manager.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
@WebFilter(urlPatterns = "/")
public class AuthHandler implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求url
        String url = request.getRequestURL().toString();
        //2.判断请求url是否包含login
        if (url.contains("login") || url.contains("register")) {
            log.info("登陆");
            filterChain.doFilter(request, response);
            return;
        }
        //3.获取请求头中的token
        String jwt = request.getHeader("token");
        //4.判断请求头是否为空
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头为空");
            ResponseEntity<Object> result = new ResponseEntity<>().fail(Code.UNAUTHORIZED.getCode(), Message.TOKEN_IS_NULL.getMessage());
            String jsonString = JSONObject.toJSONString(result);
            //响应给浏览器
            response.getWriter().write(jsonString);
            return;
        }
        //5.解析token
        try {
            JwtUtil.verifyToken(jwt);
        } catch (Exception e) {
            log.info(e.getMessage());
            ResponseEntity<Object> result = new ResponseEntity<>().fail(Code.UNAUTHORIZED.getCode(), Message.TOKEN_IS_ERROR.getMessage());
            ;
            String jsonString = JSONObject.toJSONString(result);
            System.out.println(result);
            response.getWriter().write(jsonString);
            return;
        }
        //6.放行
        log.info("放行");
        filterChain.doFilter(request, response);


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
