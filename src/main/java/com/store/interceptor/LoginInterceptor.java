package com.store.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测是否登录，即session中是否有uid
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器
     * @return true为放行，false则拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute("uid");
        System.out.println(obj);
        if (obj == null) {
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
