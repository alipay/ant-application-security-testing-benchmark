package com.sast.astbenchmark.model.alias;

public class B {
    public A attr;

    public B() {
        attr = new A();
    }

    public void setAttr(A attr) {
        this.attr = attr;
    }
}