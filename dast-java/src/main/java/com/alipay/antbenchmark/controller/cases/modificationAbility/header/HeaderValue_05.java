package com.alipay.antbenchmark.controller.cases.modificationAbility.header;

import com.alipay.antbenchmark.tools.DatabaseHelper;
import com.alipay.antbenchmark.tools.ResponseWriteTools;
import com.alipay.antbenchmark.tools.SystemConsts;
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
import java.sql.Statement;
import java.util.Enumeration;

// assession information start
// real vulnerability = true
// assession project = 改包能力->header->value
// compose = HeaderValue_05.java
// bind_url = /sqli/BS00006
// assession information end


@Controller
@RequestMapping(value = "/sqli")
public class HeaderValue_05 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("HeaderValue_05");


    @ResponseBody
    @RequestMapping(value = "/BS00006", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = "";
        Enumeration<String> headers = request.getHeaders("BS00006");
        if (headers != null && headers.hasMoreElements()) {
            param = headers.nextElement();
        }
        param = URLDecoder.decode(param, "UTF-8");
        String sql = "INSERT INTO USERS (username, password) VALUES ('foo','" + param + "')";
        Connection con = null;
        Statement statement = null;
        try {
            String url = "jdbc:mysql://localhost:3306/benchmarkDataBase?";
            con = DriverManager.getConnection(url, SystemConsts.JDBC_NAME, SystemConsts.JDBC_PASSWORD);
            statement = con.createStatement();
            statement.executeUpdate(sql);
            ResponseWriteTools.writeSqlToResponse(sql, response);
        } catch (SQLException e) {
            if (DatabaseHelper.hideSQLErrors) {
                response.getWriter().println("Error processing request.");
                return;
            } else {
                throw new ServletException(e);
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    log.error("doPost error. exception:{}", throwables.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    log.error("doPost error. exception:{}", throwables.getMessage());
                }
            }
        }
    }
}


