package com.alipay.antbenchmark.controller.bs;

import com.alipay.antbenchmark.tools.DatabaseHelper;
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
public class BS00065Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00065", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("BS00065");
        if (param == null) {
            param = "";
        }
        String sql = "SELECT  * from USERS where USERNAME='foo' and PASSWORD='bar' order by '" + param + "'";
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
    }
}
