package com.sast.astbenchmark.model.custom;

public class PS extends P {
    private String cmd2;

    public PS(String cmd, String cmd2) {
        super(cmd);
        this.cmd2 = cmd2;
    }

    public String getCmd() {
        return super.getCmd() + this.cmd2;
    }

    public String getCmd(String cmd) {
        return cmd + this.cmd2;
    }

}
