package com.alipay.antbenchmark.controller.bs;

import com.alipay.antbenchmark.tools.DatabaseHelper;
import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

@Controller
@RequestMapping(value = "/sqli")
public class BS00010Controller extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger("BS00010Controller");

    @ResponseBody
    @RequestMapping(value = "/BS00010", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String[]> map = request.getParameterMap();
        String param = "";
        if (!map.isEmpty()) {
            String[] values = map.get("BS00010");
            if (values != null) {
                param = values[0];
            }
        }
        String sql = "SELECT  * from USERS where USERNAME='foo' and PASSWORD='" + param + "'";
        Connection con = null;
        Statement statement = null;
        ResultSet results = null;
        try {
            String url = "jdbc:mysql://localhost:3306/benchmarkDataBase?";
            con = DriverManager.getConnection(url, "root", "12345678");
            statement = con.createStatement();
            results = statement.executeQuery(sql);
            response.getWriter().println("Your results are: ");
            while (results.next()) {
                response.getWriter().println(ESAPI.encoder().encodeForHTML(results.getString("USERNAME")) + " ");
            }
        } catch (EmptyResultDataAccessException e) {
            response.getWriter().println("No results returned for query: " + ESAPI.encoder().encodeForHTML(sql));
        } catch (DataAccessException e) {
            if (DatabaseHelper.hideSQLErrors) {
                response.getWriter().println("Error processing request.");
            } else {
                log.error("doPost error. DataAccessException :{}", e.getMessage());
            }
        } catch (SQLException throwables) {
            log.error("doPost error. SQLException :{}", throwables.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
                if (results != null) {
                    results.close();
                }
            } catch (Exception e) {
                log.error("doPost error. Exception :{}", e.getMessage());
            }
        }
    }
}
