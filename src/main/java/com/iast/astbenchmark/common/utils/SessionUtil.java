package com.iast.astbenchmark.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

        public static void setSession(HttpServletRequest request,String SessionName,String SessionValue) {
            HttpSession session = request.getSession();
            session.setAttribute(SessionName,SessionValue);
        }

        public static String getSession(HttpServletRequest request, String sessionName) {
            HttpSession session = request.getSession();
            String sessionValue = (String) session.getAttribute(sessionName);
            return sessionValue;
        }
       public static void clearSession(HttpServletRequest request){
           HttpSession session = request.getSession();
           session.invalidate();
       }

}
