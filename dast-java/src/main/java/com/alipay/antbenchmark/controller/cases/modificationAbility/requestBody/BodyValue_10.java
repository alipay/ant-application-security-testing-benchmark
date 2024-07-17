package com.alipay.antbenchmark.controller.cases.modificationAbility.requestBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

// assession information start
// real vulnerability = true
// assession project = 改包能力->requestBody->key=value->value
// compose = BodyValue_10.java
// bind_url = /sqli/BS00076
// assession information end


@Controller
@RequestMapping(value = "/sqli")
public class BodyValue_10 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BodyValue_10");
    private static String driver = "com.mysql.jdbc.Driver";


    @ResponseBody
    @RequestMapping(value = "/BS00076", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("BS00076");
        if (param == null) {
            param = "";
        }
        String sql = "SELECT  * from USERS where USERNAME='foo' and PASSWORD= ?";
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/benchmarkDataBase?", "root", "12345678");
            st = con.prepareStatement(sql);
            try {
                Class.forName(driver);
                st.setString(1, param);
                rs = st.executeQuery();
                while (rs.next()) {
                    String resName = rs.getString("username");
                    String resPwd = rs.getString("password");
                    response.getWriter().println("name: " + resName + "\n" + "pass: " + resPwd);
                }
            } catch (Exception e) {
                response.getWriter().println(e);
            }
        } catch (SQLException e) {
            log.error("doPost error. Exception :{}", e.getMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                log.error("doPost error. Exception :{}", e.getMessage());
            }
        }
    }
}
