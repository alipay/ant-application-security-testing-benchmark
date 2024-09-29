package com.sast.astbenchmark.model.custom;

public abstract class ABS {
    protected String cmd;
    public void setCmd(String cmd){
        this.cmd = cmd;
    }
    public abstract String getCmd();
}
