package com.iast.astbenchmark.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.iast.astbenchmark.config.wapper.MultiReadHttpServletRequest;

public class MyFilter implements Filter {

    private final Set<String> FILTER_PATHS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("/case0041")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        ServletRequest requestWrapper = null;

        if (request instanceof HttpServletRequest) {
            HttpServletRequest servletRequest = (HttpServletRequest)request;
            String requestURI = servletRequest.getRequestURI();
            if (requestURI.contains("case0041")) {
                requestWrapper = new MultiReadHttpServletRequest(servletRequest);
                // 获取请求中的流如何，将取出来的字符串，再次转换成流，然后把它放入到新request对象中。
                // 在chain.doFiler方法中传递新的request对象
                chain.doFilter(requestWrapper, response);
            }
            // else if(requestURI.contains("case00901")||requestURI.contains("case00902")){
            // requestWrapper=new StringProcessHttpServletRequest(servletRequest);
            // chain.doFilter(requestWrapper,response);
            // }
            else {
                chain.doFilter(servletRequest, response);
            }
        }
    }
}