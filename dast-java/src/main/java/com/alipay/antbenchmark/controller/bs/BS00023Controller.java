package com.alipay.antbenchmark.controller.bs;

import com.alipay.antbenchmark.tools.DatabaseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/sqli")
public class BS00023Controller extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger("BS00023Controller");

    @ResponseBody
    @RequestMapping(value = "/BS00023", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] theCookies = request.getCookies();
        String param = "noCookieValueSupplied";
        if (theCookies != null) {
            for (Cookie theCookie : theCookies) {
                if ("BS00023".equals(theCookie.getName())) {
                    param = URLDecoder.decode(theCookie.getValue(), "UTF-8");
                    break;
                }
            }
        }
        String bar = "safe!";
        HashMap<String, Object> map72344 = new HashMap<String, Object>();
        map72344.put("keyA-72344", "a-Value");
        map72344.put("keyB-72344", param);
        map72344.put("keyC", "another-Value");
        bar = (String) map72344.get("keyB-72344");

        String sql = "SELECT * from USERS where USERNAME=? and PASSWORD='" + bar + "'";
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/benchmarkDataBase?";
            connection = DriverManager.getConnection(url, "root", "12345678");
            java.sql.PreparedStatement statement = connection.prepareStatement(sql,
                    java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY,
                    java.sql.ResultSet.CLOSE_CURSORS_AT_COMMIT);
            statement.setString(1, "foo");
            statement.execute();
            String content = DatabaseHelper.collectQueryResults(statement, sql);
            DatabaseHelper.writeToResponse(content, sql, response);
            statement.close();
        } catch (java.sql.SQLException e) {
            if (DatabaseHelper.hideSQLErrors) {
                response.getWriter().println("Error processing request.");
                return;
            } else {
                throw new ServletException(e);
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                log.error("doPost error. Exception :{}", e.getMessage());
            }
        }
    }

}
