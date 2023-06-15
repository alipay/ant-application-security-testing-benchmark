package com.alipay.antbenchmark.filter;

import com.alipay.antbenchmark.tools.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 给扫描器扫描的时候
 *
 * @date: 2023/4/23
 */
// TODO 互联网开放此靶场时，关闭Component注释，启动鉴权机制
//@Component
public class ScanAuthFilter implements Filter, Ordered {

    private static final Logger log = LoggerFactory.getLogger("ScanAuthFilter");

    public void popCorsFilter(FilterChain filterChain) throws Exception {
        Field additionalFiltersFiled = filterChain.getClass().getDeclaredField("additionalFilters");
        additionalFiltersFiled.setAccessible(true);
        List additionalFilters = (List) additionalFiltersFiled.get(filterChain);
        for (int i = 0; i < additionalFilters.size(); i++) {
            if (additionalFilters.get(i).getClass().getName().equals("com.alipay.sofa.web.mvc.security.filter.DefaultCorsFilter")) {
                additionalFilters.remove(i);
            }
        }

    }

    public String getCookieScannerAuth(javax.servlet.http.Cookie[] theCookies) throws UnsupportedEncodingException {
        String param = "noCookieValueSupplied";
        if (theCookies != null) {
            for (javax.servlet.http.Cookie theCookie : theCookies) {
                if ("scannerauth".equals(theCookie.getName())) {
                    param = java.net.URLDecoder.decode(theCookie.getValue(), "UTF-8");
                    break;
                }
            }
        }
        return param;
    }

    /**
     * 生成
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
//        try {
//            popCorsFilter(filterChain);
//        } catch (Exception e) {
//            log.error("doFilter error. Exception :{}", e.getMessage());
//        }
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        log.info("debug:{}", uri);
        //不是API开头的地址，那就是漏洞地址
        if (!(uri.startsWith("/api/") || uri.startsWith("/antbenchmark/"))) {
            String thisKey = Utils.getAesKey();
            // 优先从header中获取关键字段
            String data = request.getHeader("scannerauth");
            // 为其他扫描器增加可认证的方法
            if (data == null || "".equals(data)) {
                data = getCookieScannerAuth(request.getCookies());
            }
            String decrypteddata = Utils.aesDecode(thisKey, data);
            //解密之后应该是2021/07/30这样的结果
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            String comparedata = dateFormat.format(date);
            //不相等
            if (!comparedata.equals(decrypteddata)) {
                response.getWriter().write("Bad scannerauth");
                return;
            }
        }

        filterChain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("ScanAuthFilter init");
    }

    @Override
    public void destroy() {
        log.info("ScanAuthFilter destroy");
    }


    @Override
    public int getOrder() {
        return 79500;
    }
}
