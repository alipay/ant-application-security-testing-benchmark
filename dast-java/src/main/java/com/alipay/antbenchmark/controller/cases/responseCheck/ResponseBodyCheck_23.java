package com.alipay.antbenchmark.controller.cases.responseCheck;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// assession information start
// real vulnerability = true
// assession project = 响应检测->响应body识别
// compose = ResponseBodyCheck_23.java
// bind_url = /sensitive/BS00114
// assession information end


@Controller
@RequestMapping(value = "/sensitive")
public class ResponseBodyCheck_23 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00114", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().println(
                    "Mon Jul 26 17:10:40 CST 2021 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.\n" +
                            "java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''1''' at line 1\n" +
                            "\tat com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:120)\n" +
                            "\tat com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97)\n" +
                            "\tat com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)\n" +
                            "\tat com.mysql.cj.jdbc.StatementImpl.executeQuery(StatementImpl.java:1218)\n" +
                            "\tat com.alipay.web.home.TestCaseController.BS00091Controller.doPost(BS00091Controller.java:43)\n" +
                            "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                            "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
                            "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                            "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" +
                            "\tat com.alipay.sofa.web.mvc.core.servlet.handler.support.HandlerMethodInvoker.invokeHandlerMethod(HandlerMethodInvoker.java:154)\n" +
                            "\tat com.alipay.sofa.web.mvc.core.servlet.handler.support.AbstractMethodHandlerAdapter.invokeHandlerMethod(AbstractMethodHandlerAdapter.java:408)\n" +
                            "\tat com.alipay.sofa.web.mvc.core.servlet.handler.support.AbstractMethodHandlerAdapter.handle(AbstractMethodHandlerAdapter.java:388)\n" +
                            "\tat com.alipay.web.mvc.car.CarHandlerAdapter.handle(CarHandlerAdapter.java:83)\n" +
                            "\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040)\n" +
                            "\tat com.alipay.sofa.runtime.web.smvc.servlet.CarDispatcherServlet.doDispatch(CarDispatcherServlet.java:194)\n" +
                            "\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943)\n" +
                            "\tat com.alipay.sofa.runtime.web.smvc.servlet.CarDispatcherServlet.doService(CarDispatcherServlet.java:164)\n" +
                            "\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\n" +
                            "\tat org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\n" +
                            "\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:634)\n" +
                            "\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\n" +
                            "\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:741)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" +
                            "\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" +
                            "\tat org.springframework.boot.actuate.web.trace.servlet.HttpTraceFilter.doFilterInternal(HttpTraceFilter.java:88)\n" +
                            "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" +
                            "\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\n" +
                            "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" +
                            "\tat org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\n" +
                            "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" +
                            "\tat org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:94)\n" +
                            "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" +
                            "\tat org.springframework.boot.actuate.metrics.web.servlet.WebMvcMetricsFilter.filterAndRecordMetrics(WebMvcMetricsFilter.java:114)\n" +
                            "\tat org.springframework.boot.actuate.metrics.web.servlet.WebMvcMetricsFilter.doFilterInternal(WebMvcMetricsFilter.java:104)\n" +
                            "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" +
                            "\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\n" +
                            "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:121)\n" +
                            "\tat com.alipay.sofa.web.mvc.security.tracer.SecurityTracerFilter.doFilterInternal(SecurityTracerFilter.java:68)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AbstractBaseFilter.doFilter(AbstractBaseFilter.java:116)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:72)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AbstractBaseFilter.doFilter(AbstractBaseFilter.java:116)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.sofa.web.mvc.security.LoggingContextMDCFilter.doFilterInternal(LoggingContextMDCFilter.java:33)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AbstractBaseFilter.doFilter(AbstractBaseFilter.java:116)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat net.sf.acegisecurity.intercept.web.FilterSecurityInterceptor.invoke(FilterSecurityInterceptor.java:108)\n" +
                            "\tat net.sf.acegisecurity.intercept.web.SecurityEnforcementFilter.doFilter(SecurityEnforcementFilter.java:197)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.sofa.web.mvc.security.ContextCleanFilter.doFilter(ContextCleanFilter.java:86)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.service.security.acegi.filter.OperationEnvironmentIntegrationFilter.doFilter(OperationEnvironmentIntegrationFilter.java:85)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat net.sf.acegisecurity.providers.anonymous.AnonymousProcessingFilter.doFilter(AnonymousProcessingFilter.java:143)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat net.sf.acegisecurity.context.HttpSessionContextIntegrationFilter.doFilter(HttpSessionContextIntegrationFilter.java:220)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.sofa.web.mvc.security.filter.DefaultCorsFilter.doFilterInternal(DefaultCorsFilter.java:68)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AbstractBaseFilter.doFilter(AbstractBaseFilter.java:116)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.security.core.referer.RefererAllUriCheckFilter.doFilter(RefererAllUriCheckFilter.java:54)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.sofa.web.mvc.security.web.SafeHTTPFilter.doFilterInternal(SafeHTTPFilter.java:71)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AbstractBaseFilter.doFilter(AbstractBaseFilter.java:116)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.log.LoadTestFilter.doFilterInternal(LoadTestFilter.java:84)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AbstractBaseFilter.doFilter(AbstractBaseFilter.java:116)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.session.support.filter.DefaultSessionFilter.doFilterInternal(DefaultSessionFilter.java:78)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AbstractBaseFilter.doFilter(AbstractBaseFilter.java:116)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.log.SimpleMDCInitFilter.doFilterInternal(SimpleMDCInitFilter.java:45)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AbstractBaseFilter.doFilter(AbstractBaseFilter.java:116)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.sofarouter.SOFARouterFilter.doFilterInternal(SOFARouterFilter.java:76)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.sofarouter.SOFARouterFilter.doFilter(SOFARouterFilter.java:60)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.log.SofaTracerLogFilter.doFilter(SofaTracerLogFilter.java:165)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:94)\n" +
                            "\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayCharacterEncodingFilter.doFilterInternal(AlipayCharacterEncodingFilter.java:77)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AbstractBaseFilter.doFilter(AbstractBaseFilter.java:116)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.profiler.DefaultTimerFilter.doFilterInternal(DefaultTimerFilter.java:55)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AbstractBaseFilter.doFilter(AbstractBaseFilter.java:116)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter$VirtualFilterChain.doFilter(AlipayGlobalFilter.java:134)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayGlobalFilter.doFilter(AlipayGlobalFilter.java:76)\n" +
                            "\tat com.alipay.sofa.web.mvc.common.filter.AlipayDelegatingFilterProxy.doFilter(AlipayDelegatingFilterProxy.java:69)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n" +
                            "\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n" +
                            "\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\n" +
                            "\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n" +
                            "\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)\n" +
                            "\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139)\n" +
                            "\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\n" +
                            "\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\n" +
                            "\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\n" +
                            "\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:367)\n" +
                            "\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\n" +
                            "\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:868)\n" +
                            "\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1639)\n" +
                            "\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n" +
                            "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n" +
                            "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\n" +
                            "\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\n" +
                            "\tat java.lang.Thread.run(Thread.java:748)"
            );
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }
    }
}
