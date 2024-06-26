package com.alipay.antbenchmark.controller.cases.modificationAbility.requestBody;

import com.alipay.antbenchmark.tools.DatabaseHelper;
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
import java.sql.Statement;
import java.util.Map;

import static java.sql.Statement.RETURN_GENERATED_KEYS;


// assession information start
// real vulnerability = true
// assession project = 改包能力->requestBody->key=value->value
// compose = BodyValue_03.java
// bind_url = /sqli/BS00012
// assession information end



@Controller
@RequestMapping(value = "/sqli")
public class BodyValue_03 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BodyValue_03");

    @ResponseBody
    @RequestMapping(value = "/BS00012", method = {RequestMethod.POST})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String[]> map = request.getParameterMap();
        String param = "";
        if (!map.isEmpty()) {
            String[] values = map.get("BS00012");
            if (values != null) {
                param = values[0];
            }
        }
        String sql = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + param + "'";
        Connection con = null;
        Statement statement = null;
        try {
            String url = "jdbc:mysql://localhost:3306/benchmarkDataBase";
            con = DriverManager.getConnection(url, "root", "12345678");
            statement = con.createStatement();
            statement.execute(sql, RETURN_GENERATED_KEYS);
            String content = DatabaseHelper.collectQueryResults(statement, sql);
            DatabaseHelper.writeToResponse(content, sql, response);
            con.close();
            statement.close();
        } catch (SQLException e) {
            if (DatabaseHelper.hideSQLErrors) {
                response.getWriter().println("Error processing request.");
                return;
            } else {
                throw new ServletException(e);
            }
        }
    }
}
