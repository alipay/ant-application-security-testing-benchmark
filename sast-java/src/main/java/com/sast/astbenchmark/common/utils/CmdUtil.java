package com.sast.astbenchmark.common.utils;

public class CmdUtil {

    public static void run(String cmd) {
        try {
            Runtime.getRuntime().exec(cmd);
        }catch (Exception e) {
            return;
        }
    }

    public static String getCmdWithContextSensitive(int x, String cmd) {
        String res;
        if (x > 0) {
            res = cmd;
        }
        else {
            res = "ls";
        }
        return res;
    }
}
