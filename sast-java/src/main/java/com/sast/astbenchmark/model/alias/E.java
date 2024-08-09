package com.sast.astbenchmark.model.alias;

import javax.servlet.http.HttpServletRequest;

public class E {
    public HttpServletRequest req;
    public String str;

    public void read() {
        str = "";
        str = req.getParameter("cmd");
    }

    public void setF(E e) {
        e.str = "";
        e.str = req.getParameter("cmd");
    }
}