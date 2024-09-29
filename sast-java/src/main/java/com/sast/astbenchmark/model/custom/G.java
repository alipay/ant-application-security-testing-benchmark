package com.sast.astbenchmark.model.custom;

public class G<T> {
    private T cmd;

    public G(T cmd) {
        this.cmd = cmd;
    }

    public T getCmd() {
        return this.cmd;
    }
}