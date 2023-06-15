package com.alipay.antbenchmark.controller.bs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLClassLoader;


@Controller
@RequestMapping(value="/ssrf")
public class BS00110Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BS00110Controller");


    @ResponseBody
    @RequestMapping(value="/BS00110",method = {RequestMethod.GET, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = request.getParameter("BS00110");
        String classname=request.getParameter("classname");
        if(classname==null){
            classname="";
        }
        if(param==null){
            param="";
        }
        try {
            URLClassLoader   loader=new URLClassLoader(new java.net.URL[]{new java.net.URL(param)});
            loader.loadClass(classname);
        } catch (Exception e) {
            log.error("doPost error. Exception :{}", e.getMessage());
        }
    }
}
