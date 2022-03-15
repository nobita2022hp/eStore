package com.estore.interceptor;

import com.estore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CategoryInterceptor implements AsyncHandlerInterceptor {

    @Autowired
    private CategoryRepository repository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        var categories = repository.findAll();
        request.setAttribute("cates", categories);
    }
}
