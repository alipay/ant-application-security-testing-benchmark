package com.sast.astbenchmark.model.alias;

public class J {
    public E e;
    public E f;

    public void read() {
        e = new E();
        f = e;
        e.read();
    }
}