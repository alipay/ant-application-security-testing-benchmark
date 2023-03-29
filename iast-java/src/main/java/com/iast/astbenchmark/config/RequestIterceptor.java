package com.iast.astbenchmark.config;//package com.iast.astbenchmark.config;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Enumeration;
//
//@Component
//public class RequestIterceptor extends HandlerInterceptorAdapter {
//
//    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
////        threadLocal.set(System.currentTimeMillis());
////        log.info("Request uri = [{}], method is: [{}]", request.getRequestURI(), request.getMethod());
////        log.info("Request header is : [{}]", parseRequestHeaders(request));
////        log.info("Request param is : [{}]", parseParams(request));
////        request.getQueryString()
////
////        if (request instanceof RequestCustomWrapper) {
////            RequestCustomWrapper requestCustomWrapper = (RequestCustomWrapper) request;
////            byte[] body = requestCustomWrapper.getBody();
////            log.info("Request body is : [{}]", new String(body));
////        }
//
//        return super.preHandle(request, response, handler);
//    }
//
//    public static String parseParams (HttpServletRequest request) {
//        StringBuilder stringBuilder = new StringBuilder();
//        Enumeration<String> parameterNames = request.getParameterNames();
//        while (parameterNames.hasMoreElements()) {
//            String name = parameterNames.nextElement();
//            request.getParameter(name);
//            stringBuilder.append(name).append("=").append(";");
//        }
//        return stringBuilder.toString();
//    }
//
//    public static String parseRequestHeaders (HttpServletRequest request) {
//        StringBuilder stringBuilder = new StringBuilder();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String name  = headerNames.nextElement();
//            String value = request.getHeader(name);
//            stringBuilder.append(name).append("=").append(value).append(";");
//        }
//        return stringBuilder.toString();
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        super.afterCompletion(request, response, handler, ex);
//    }
//}
//
