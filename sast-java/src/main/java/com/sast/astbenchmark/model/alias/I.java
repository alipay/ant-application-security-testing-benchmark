package com.sast.astbenchmark.model.alias;

import javax.servlet.http.HttpServletRequest;

public class I {
    public HttpServletRequest req;
    public String str;
    public String str2;

    public void read() {
        str = "";
        str2 = str;
        str = req.getParameter("cmd");
    }
}