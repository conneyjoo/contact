package com.jinshun.contact.interceptor;

import com.jinshun.contact.constant.Environment;
import com.jinshun.contact.controller.common.Access;
import com.jinshun.contact.entity.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    private static final String LOGIN_URI = "/login.html";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Access access = handlerMethod.getMethodAnnotation(Access.class);
        User user = (User) request.getSession().getAttribute(Environment.LOGIN_USER_KEY);

        if (access == null) {
            return true;
        }

        if (user == null) {
            response.sendRedirect(LOGIN_URI);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
