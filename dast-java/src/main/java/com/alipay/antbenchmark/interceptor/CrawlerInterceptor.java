package com.alipay.antbenchmark.interceptor;

import com.alipay.antbenchmark.tools.CrawlerSingle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * @date: 2023/4/23
 */
public class CrawlerInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger("CrawlerInterceptor");

    static CrawlerSingle crawlerSingle;

    static {
        try {
            crawlerSingle = CrawlerSingle.getInstance();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        if (response.getStatus() != 404) {
            String uri = request.getRequestURI();
            try {
                String[] splitPath = uri.split("/");
                if (splitPath.length == 3) {
                    String lastPath = splitPath[splitPath.length - 1];
                    if (lastPath.startsWith("BS0")) {
                        int caseNumber = Integer.valueOf(lastPath.replace("BS", ""));
                        updateCounter(caseNumber);
                    }
                }
            } catch (Exception e) {
                log.error("postHandle . error in CrawlerFilter, the exception:{} ", e.toString());
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

    public static void updateCounter(int caseNumber) throws MalformedURLException, URISyntaxException {
        // 数字大于最大case编号，不做统计
        if (caseNumber > crawlerSingle.getTotal()) {
            return;
        } else {
            crawlerSingle.addCaseNum(caseNumber);
        }
        // 暂时以caseNumber区别ajax请求和html表单
        if (caseNumber == 8) {
            crawlerSingle.setSupportHtml(true);
        } else {
            crawlerSingle.setSupportAjax(true);
        }
    }
}
