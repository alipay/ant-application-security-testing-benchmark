package com.sast.astbenchmark.model.alias;

public class H {
    public I i;

    public void read() {
        i = new I();
        i.read();
    }
}