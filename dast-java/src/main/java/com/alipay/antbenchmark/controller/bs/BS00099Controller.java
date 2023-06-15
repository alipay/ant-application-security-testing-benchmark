package com.alipay.antbenchmark.controller.bs;

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
import java.sql.ResultSet;
import java.sql.Statement;


@Controller
@RequestMapping(value = "/sqli")
public class BS00099Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BS00099Controller");


    @ResponseBody
    @RequestMapping(value = "/BS00099", method = {RequestMethod.GET, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("BS00099");
        if (param == null) {
            param = "";
        }
        //直接执行SQL
        //时间盲注测试！
        String sql = param;
        Connection con = null;
        Statement statement = null;
        ResultSet results = null;
        try {
            String url = "jdbc:mysql://localhost:3306/benchmarkDataBase?";
            con = DriverManager.getConnection(url, "root", "12345678");
            statement = con.createStatement();
            results = statement.executeQuery(sql);
            //全部都200，时间盲注，请
            while (results.next()) {
                response.setStatus(200);
                return;
            }
            response.setStatus(200);
            return;
        } catch (Exception e) {
            response.setStatus(200);
            return;
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
