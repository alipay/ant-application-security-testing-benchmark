package com.alipay.antbenchmark.controller.bs;

import com.alipay.antbenchmark.tools.DatabaseHelper;
import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

@Controller
@RequestMapping(value = "/sqli")
public class BS00009Controller extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger("BS00009Controller");

    @ResponseBody
    @RequestMapping(value = "/BS00009", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String[]> map = request.getParameterMap();
        String param = "";
        if (!map.isEmpty()) {
            String[] values = map.get("BS00009");
            if (values != null) {
                param = values[0];
            }
        }
        Connection con = null;
        Statement statement = null;
        try {
            String sql = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + param + "'";
            String url = "jdbc:mysql://localhost:3306/benchmarkDataBase?";
            con = DriverManager.getConnection(url, "root", "12345678");
            statement = con.createStatement();
            statement.execute(sql);
            response.getWriter().println("No results can be displayed for query: " + ESAPI.encoder().encodeForHTML(sql) + "<br>"
                    + " because the Spring execute method doesn't return results.");
        } catch (DataAccessException | SQLException e) {
            if (DatabaseHelper.hideSQLErrors) {
                response.getWriter().println("Error processing request.");
            } else {
                log.error("DataAccessException or SQLException exception:{}", e.getMessage());
            }
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException throwables) {
                log.error("the SQLException:{}", throwables.getMessage());
            }
        }
    }
}
