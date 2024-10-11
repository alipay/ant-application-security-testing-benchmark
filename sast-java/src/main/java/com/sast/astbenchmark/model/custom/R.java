package com.sast.astbenchmark.model.custom;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class R implements Runnable {
    private final String cmd;
    Map<String, Object> modelMap = new HashMap<>();
    public R(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public void run() {
        try {
            Runtime.getRuntime().exec(this.cmd);
            modelMap.put("status", "success");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            modelMap.put("status", "error");
        }
    }
    public Map<String, Object> getMap(){
        return modelMap;
    }
}
