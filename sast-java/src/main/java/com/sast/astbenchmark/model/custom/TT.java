package com.sast.astbenchmark.model.custom;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.util.TimerTask;

public class TT extends TimerTask {
    private String cmd;
    Map<String, Object> modelMap = new HashMap<>();
    public TT(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public void run() {
        try {
            Runtime.getRuntime().exec(this.cmd);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
    }
    public Map<String, Object> getMap(){
        return modelMap;
    }
}
