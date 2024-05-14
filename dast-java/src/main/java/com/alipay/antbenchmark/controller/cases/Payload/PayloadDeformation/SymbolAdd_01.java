package com.alipay.antbenchmark.controller.cases.Payload.PayloadDeformation;

import com.alipay.antbenchmark.tools.Thing;
import com.alipay.antbenchmark.tools.ThingInterface;
import org.apache.commons.codec.binary.Base64;
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
// assession project = payload->payload变形->payload前后增加非字母符号
// compose = SymbolAdd_01.java
// bind_url = /xss/BS00030
// assession information end


@Controller
@RequestMapping(value = "/xss")
public class SymbolAdd_01 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @ResponseBody
    @RequestMapping(value = "/BS00030", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("BS00030");
        if (param == null) {
            param = "";
        }
        String a2196 = param;
        StringBuilder b2196 = new StringBuilder(a2196);
        b2196.append(" SafeStuff");
        b2196.replace(b2196.length() - "Chars".length(), b2196.length(), "Chars");
        java.util.HashMap<String, Object> map2196 = new java.util.HashMap<String, Object>();
        map2196.put("key2196", b2196.toString());
        String c2196 = (String) map2196.get("key2196");
        String d2196 = c2196.substring(0, c2196.length() - 1);
        String e2196 = new String(Base64.decodeBase64(
                Base64.encodeBase64(d2196.getBytes())));
        String f2196 = e2196.split(" ")[0];
        ThingInterface thing = new Thing();
        String bar = thing.doSomething(f2196);

        response.setHeader("X-XSS-Protection", "0");
        Object[] obj = {"a", "b"};
        response.getWriter().printf(java.util.Locale.US, bar, obj);
    }

}
