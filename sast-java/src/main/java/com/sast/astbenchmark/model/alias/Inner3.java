package com.sast.astbenchmark.model.alias;

public class Inner3 {

    public class Inner2b {

        public Inner2 foo;

    }

    private String data;

    public class Inner2 {

        public void set(String p) {
            data = p;
        }

    }

    public Inner2b obj2;

    public String get() {
        return data;
    }
}