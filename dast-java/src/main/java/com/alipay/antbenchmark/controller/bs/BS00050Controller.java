package com.alipay.antbenchmark.controller.bs;

import com.alipay.antbenchmark.tools.FakeRequestHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@RequestMapping(value = "/ssrf")
public class BS00050Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /*
     * 回显ssrf*/
    @ResponseBody
    @RequestMapping(value = "/BS00050", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        FakeRequestHandler scr = new FakeRequestHandler(request);
        String param = scr.getFakeValue("BS00050");
        StringBuffer responsestr = new StringBuffer();

        try {
            URL url = new URL(param);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Moxilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                responsestr.append(inputLine);
            }
            in.close();
            response.getWriter().println(responsestr.toString());
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }
    }
}
