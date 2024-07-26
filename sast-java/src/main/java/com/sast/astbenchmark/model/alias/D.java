package com.sast.astbenchmark.model.alias;

public class D {
    public E e;

    public void read() {
        e = new E();
        e.read();
    }

    public void setF(E e) {
        this.e = e;
        this.e.setF(e);
    }
}