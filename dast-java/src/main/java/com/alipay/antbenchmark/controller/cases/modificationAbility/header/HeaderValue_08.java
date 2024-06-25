package com.alipay.antbenchmark.controller.cases.modificationAbility.header;

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
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// assession information start
// real vulnerability = true
// assession project = 改包能力->header->value
// compose = HeaderValue_08.java
// bind_url = /sqli/BS00026
// assession information end


@Controller
@RequestMapping(value = "/sqli")
public class HeaderValue_08 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("HeaderValue_08");

    @ResponseBody
    @RequestMapping(value = "/BS00026", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = "";
        if (request.getHeader("BS00026") != null) {
            param = request.getHeader("BS00026");
        }
        param = URLDecoder.decode(param, "UTF-8");
        String bar;
        String guess = "ABC";
        char switchTarget = guess.charAt(2);

        switch (switchTarget) {
            case 'A':
                bar = param;
                break;
            case 'B':
                bar = "bobs_your_uncle";
                break;
            case 'C':
            case 'D':
                bar = param;
                break;
            default:
                bar = "bobs_your_uncle";
                break;
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
