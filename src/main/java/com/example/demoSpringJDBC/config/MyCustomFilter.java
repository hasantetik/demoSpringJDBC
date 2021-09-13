package com.example.demoSpringJDBC.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
//@Order(1)
public class MyCustomFilter implements Filter
{
    @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        System.err.println("filtrede yakalanan request " + req.getRequestURI());
        chain.doFilter(request, response);
    }
}