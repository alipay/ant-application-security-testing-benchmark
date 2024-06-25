package com.alipay.antbenchmark.controller.cases.modificationAbility.urlParam;

import com.alipay.antbenchmark.tools.DatabaseHelper;
import com.alipay.antbenchmark.tools.FakeRequestHandler;
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
import java.sql.SQLException;

// assession information start
// real vulnerability = false
// bind_url = /sqli/BS00044
// assession information end


@Controller
@RequestMapping(value = "/sqli")
public class UrlValue_06 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("UrlValue_06");

    @ResponseBody
    @RequestMapping(value = "/BS00044", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        FakeRequestHandler scr = new FakeRequestHandler(request);
        String param = scr.getFakeValue("BS00044");
        String bar = "alsosafe";
        if (param != null) {
            java.util.List<String> valuesList = new java.util.ArrayList<String>();
            valuesList.add("safe");
            valuesList.add(param);
            valuesList.add("moresafe");
            valuesList.remove(0);
            bar = valuesList.get(1);
        }
        String sql = "SELECT * from USERS where USERNAME=? and PASSWORD='" + bar + "'";
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/benchmarkDataBase?";
            connection = DriverManager.getConnection(url, "root", "12345678");
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "foo");
            statement.execute();
            String content = DatabaseHelper.collectQueryResults(statement, sql);
            DatabaseHelper.writeToResponse(content, sql, response);
            statement.close();
        } catch (SQLException e) {
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
