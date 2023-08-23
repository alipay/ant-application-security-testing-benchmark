package com.alipay.antbenchmark.controller.bs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Controller
@RequestMapping(value = "/code_injection")
public class BS00130Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @ResponseBody
    @RequestMapping(value = "/BS00130", method = {RequestMethod.GET})
    public String naive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //reflection!
        String classname = request.getParameter("classname");
        String methodname = request.getParameter("methodname");
        String argument = request.getParameter("argument");
        Class cls = Class.forName(classname);
        Constructor con = cls.getConstructor();
        Object obj = con.newInstance();
        Method m = cls.getMethod(methodname);
        m.invoke(obj, argument);
        return m.invoke(obj, argument).toString();
    }


}
