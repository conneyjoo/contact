package com.jinshun.contact.auth;

import com.jinshun.contact.constant.Environment;
import com.jinshun.contact.entity.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    private static final String LOGIN_PAGE = "/login.html";

    private static final String UNAUTHORIZED_PAGE = "/unauthorized.html";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Access access = null;

        if (handlerMethod != null) {
            access = handlerMethod.getMethodAnnotation(Access.class);

            if (access == null) {
                return true;
            }
        }

        User user = (User) request.getSession().getAttribute(Environment.CURRENT_USER_KEY);

        if (user == null) {
            response.sendRedirect(LOGIN_PAGE);
            return false;
        } else if (access != null) {
            Authorities authorities = access.authorities();

            if (authorities == Authorities.LOGIN) {
                return true;
            } else if (authorities == Authorities.AUTHORIED) {
                if (!user.getActions().contains(handlerMethod.toString())) {
                    // response.sendRedirect(UNAUTHORIZED_PAGE);
                    return true;
                }
            }
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
