package com.sast.astbenchmark.model;

import java.util.Map;

public class XCmdObject {
    private String cmd1;
    private Map<String, String> modelMap;

    public void setCmd(String cmd) {
        this.cmd1 = cmd;
    }

    public void setModelMap(Map<String, String> map) {
        this.modelMap = map;
    }

    public String getCmd() {
        return cmd1;
    }

    public Map<String, String> getModelMap() {
        return modelMap;
    }
}
