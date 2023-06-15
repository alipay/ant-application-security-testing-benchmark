package com.alipay.antbenchmark.controller.bs;

import com.alipay.antbenchmark.tools.DatabaseHelper;
import com.alipay.antbenchmark.tools.ResponseWriteTools;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Controller
@RequestMapping(value = "/sqli")
public class BS00014Controller extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger("BS00014Controller");

    @ResponseBody
    @RequestMapping(value = "/BS00014", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("BS00014");
        if (param == null) {
            param = "";
        }
        String sql = "INSERT INTO USERS (username, password) VALUES ('foo','" + param + "')";
        Connection con = null;
        Statement statement = null;
        try {
            String url = "jdbc:mysql://localhost:3306/benchmarkDataBase?";
            con = DriverManager.getConnection(url, "root", "12345678");
            statement = con.createStatement();
            statement.executeUpdate(sql, new int[]{1, 2});
            ResponseWriteTools.writeSqlToResponse(sql, response);
        } catch (java.sql.SQLException e) {
            if (DatabaseHelper.hideSQLErrors) {
                response.getWriter().println("Error processing request.");
                return;
            } else {
                throw new ServletException(e);
            }
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                log.error("doPost error. Exception :{}", e.getMessage());
            }
        }
    }
}
