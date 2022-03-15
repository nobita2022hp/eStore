package com.estore.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizeInterceptor implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var session = request.getSession();
        var user = session.getAttribute("user");
        if (user == null) {
            session.setAttribute("back-url", request.getRequestURI());
            response.sendRedirect("/account/login");
            return false;
        }

        return true;
    }
}
