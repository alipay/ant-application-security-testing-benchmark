/*
 * 参数=多层json型SQL注入靶场
 * Payload：
 BS00132={"rs":{"BS00132": "'union select 1, 2, '3"}
 * */

package com.alipay.antbenchmark.controller.cases.ModificationAbility.UrlParam;

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

// assession information start
// real vulnerability = true
// assession project = 改包能力->url参数->value->value中的json
// compose = ValueJson_01.java
// bind_url = /sqli/BS00132
// assession information end


@Controller
@RequestMapping(value = "/sqli")
public class ValueJson_01 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("ValueJson_01");


    @ResponseBody
    @RequestMapping(value = "/BS00132", method = {RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String inputStr = request.getParameter("BS00132");
            log.info("[*] inputStr:{}", inputStr);
            Gson gson = new Gson();
            ParamSimple param = gson.fromJson(inputStr, ParamSimple.class);
            log.info("[*] param   :{}", param.toString());
            log.info("[*] rs      :{}", param.rs.toString());
            log.info("[*] BS00132   :{}", param.rs.BS00132);
            if (param.rs.BS00132 == null) {
                param.rs.BS00132 = "";
            }
            String sql = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + param.rs.BS00132 + "'";
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
        String BS00132;
    }

    public class ParamSimple {
        String id;
        RequestSimple rs;
    }
}

