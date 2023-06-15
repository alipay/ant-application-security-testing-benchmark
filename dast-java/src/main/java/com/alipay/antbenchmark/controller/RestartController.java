package com.alipay.antbenchmark.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.antbenchmark.tools.CrawlerSingle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

/**
 * 爬虫计数重制接口
 */
@Controller
@RequestMapping(value = "/")
public class RestartController extends HttpServlet {

    @RequestMapping(value = "/antbenchmark/__restart", method = {RequestMethod.GET})
    public void restartCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, URISyntaxException {
        CrawlerSingle instance = CrawlerSingle.getInstance();
        instance.setSupportAjax(false);
        instance.setSupportHtml(false);
        instance.clearNowCount();
        PrintWriter out = response.getWriter();
        out.write("爬取页面数量归零，可以重新发起扫描");
    }

    @ResponseBody
    @RequestMapping(value = "/antbenchmark/getData", method = {RequestMethod.GET})
    public void getNowCount(HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        CrawlerSingle instance = CrawlerSingle.getInstance();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nowCount", instance.getNowCount());
        jsonObject.put("total", instance.getTotal());
        jsonObject.put("supportAjax", instance.isSupportAjax());
        jsonObject.put("supportHtml", instance.isSupportHtml());
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toJSONString());
    }
}
