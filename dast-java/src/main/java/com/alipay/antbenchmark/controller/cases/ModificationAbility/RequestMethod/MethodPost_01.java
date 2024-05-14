package com.alipay.antbenchmark.controller.cases.ModificationAbility.RequestMethod;

import com.alipay.antbenchmark.tools.FakeRequestHandler;
import com.alipay.antbenchmark.tools.Utils;
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

// assession information start
// real vulnerability = false
// bind_url = /cmdi/BS00018
// assession information end


@Controller
@RequestMapping(value = "/cmdi")
public class MethodPost_01 extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger("MethodPost_01");

    @ResponseBody
    @RequestMapping(value = "/BS00018", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        FakeRequestHandler scr = new FakeRequestHandler(request);
        String param = scr.getFakeValue("BS00018");
        String a1 = "";
        String a2 = "";
        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") != -1) {
            a1 = "cmd.exe";
            a2 = "/c";
        } else {
            a1 = "sh";
            a2 = "-c";
        }
        String[] args = {a1, a2, "echo " + param};
        ProcessBuilder pb = new ProcessBuilder(args);
        try {
            Process p = pb.start();
            Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            log.error("Problem executing cmdi - java.lang.ProcessBuilder(java.lang.String[]) Test Case");
            throw new ServletException(e);
        }
    }
}
