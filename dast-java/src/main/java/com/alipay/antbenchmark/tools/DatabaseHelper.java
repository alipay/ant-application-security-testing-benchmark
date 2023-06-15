package com.alipay.antbenchmark.tools;

import org.apache.commons.dbcp.BasicDataSource;
import org.owasp.esapi.ESAPI;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    public static org.springframework.jdbc.core.JdbcTemplate JDBCtemplate;
    public static final boolean hideSQLErrors = false;

    static {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/benchmarkDataBase");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");
        JDBCtemplate = new org.springframework.jdbc.core.JdbcTemplate(dataSource);
    }

    public static String collectQueryResults(Statement statement, String sql) throws SQLException, IOException {
        StringBuilder resultsContent = new StringBuilder();
        try {
            ResultSet rs = statement.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= count; i++) {
                    if (i > 1) {
                        resultsContent.append(",  ");
                    }
                    String value = rs.getString(i);
                    resultsContent.append(value);
                }
            }
        } catch (Exception e) {
            return "";
        }
        return resultsContent.toString();
    }

    public static void writeToResponse(String content, String sql, HttpServletResponse response) throws SQLException, IOException {

        PrintWriter out = response.getWriter();
        out.write("<!DOCTYPE>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"utf-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>\n");

        if (content == null || "".equals(content)) {
            out.write("No results for query: " + ESAPI.encoder().encodeForHTML(sql));
            return;
        }

        out.write("Your results are:<br>\n");
        out.write(content);

        out.write("</p>\n</body>\n</html>");


    }

}
