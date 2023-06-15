/*
 * 参数=json型SQL注入靶场
 * Payload：
 BS00133={"BS00133": "'union select 1, 2, '3"}
 * */

package com.alipay.antbenchmark.controller.bs;

import com.alipay.antbenchmark.tools.DatabaseHelper;
import com.google.gson.Gson;
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

@Controller
@RequestMapping(value = "/sqli")
public class BS00133Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BS00133Controller");


    @ResponseBody
    @RequestMapping(value = "/BS00133", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String inputStr = request.getParameter("BS00133");
            log.info("[*] inputStr:{}", inputStr);
            Gson gson = new Gson();
            RequestSimple param = gson.fromJson(inputStr, RequestSimple.class);
            log.info("[*] param     :{}", param.toString());
            log.info("[*] BS00133   :{}", param.BS00133);
            if (param.BS00133 == null) {
                param.BS00133 = "";
            }
            String sql = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + param.BS00133 + "'";
            response.getWriter().println(param);
            try {
                org.springframework.jdbc.support.rowset.SqlRowSet results = DatabaseHelper.JDBCtemplate.queryForRowSet(sql);
                response.getWriter().println("Your results are: ");
                while (results.next()) {
                    response.getWriter().println(org.owasp.esapi.ESAPI.encoder().encodeForHTML(results.getString("USERNAME")) + " ");
                }
            } catch (org.springframework.dao.EmptyResultDataAccessException e) {
                response.getWriter().println("No results returned for query: " + org.owasp.esapi.ESAPI.encoder().encodeForHTML(sql));
            } catch (org.springframework.dao.DataAccessException e) {
                if (DatabaseHelper.hideSQLErrors) {
                    response.getWriter().println("Error processing request.");
                } else {
                    throw new ServletException(e);
                }
            }
        } catch (Exception e) {
            log.error("doPost error. Exception :{}", e.getMessage());
        }


    }

    public class RequestSimple {
        String username;
        String password;
        String BS00133;
    }
}

