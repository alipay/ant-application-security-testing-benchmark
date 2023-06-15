package com.alipay.antbenchmark.tools;

import org.owasp.esapi.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseWriteTools {

    private static final Logger log = LoggerFactory.getLogger("ResponseWriteTools");

    public static void writeSqlToResponse(String sql, HttpServletResponse response) throws java.sql.SQLException, IOException {
        PrintWriter out = response.getWriter();
        out.write("<!DOCTYPE html>\n<html>\n<body>\n<p>");
        out.write("completed query: ");
        String message = null;
        try {
            Encoder encoder = org.owasp.esapi.ESAPI.encoder();
            message = encoder.encodeForHTML(sql);
        } catch (Exception e) {
            log.error("writeSqlToResponse error. the exception:{}", e.getMessage());
        }
        out.write(message);
        out.write("<br>\n");
        out.write("</p>\n</body>\n</html>");
    }
}
