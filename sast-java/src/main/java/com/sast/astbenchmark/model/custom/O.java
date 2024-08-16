package com.sast.astbenchmark.model.custom;

public class O {
    private String cmd;

    public O(String cmd){
        this.cmd = cmd;
    }

    class I {
        String getCmd(){
            return O.this.cmd;
        }
    }

    public String getCmd(){
        O.I i = new I();
        return i.getCmd();
    }
}
