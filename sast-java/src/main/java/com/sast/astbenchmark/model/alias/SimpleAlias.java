package com.sast.astbenchmark.model.alias;

public class SimpleAlias {
    private B b1;
    private B b2;

    public void foo(B b1, B b2) {
        this.b1 = b1;
        this.b2 = b2;
    }

    public A bar(A a) {
        this.b1.attr = a;
        return this.b2.attr;
    }
}



