package com.syshlang.injection.config;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "sqlFilter", urlPatterns = "/*")
public class SqlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //在Filter里面调用自定义的HttpServletRequestWrapper
        filterChain.doFilter(new SqlHttpServletRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
    }

    @Override
    public void destroy() {

    }
}
