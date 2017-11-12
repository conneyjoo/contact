package com.jinshun.contact.auth;

import com.jinshun.contact.controller.sys.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
@WebFilter
public class AuthFilter extends AuthInterceptor implements Filter {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    public static List<String> passUriList = new LinkedList<String>();

    static {
        passUriList.add("login.html");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();

        try {
            if (passURI(uri) || preHandle(request, response, null)) {
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private boolean passURI(String uri) {
        if (!uri.endsWith(".html")) return true;

        for (String passUri : passUriList)
            if (uri.endsWith(passUri))
                return true;

        return false;
    }

    @Override
    public void destroy() {

    }
}
