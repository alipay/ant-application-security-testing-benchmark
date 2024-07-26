package com.sast.astbenchmark.model.alias;

public class Inner1 {

    public class Inner2 {
        private String data;

        public void set(String v) {
            data = v;
        }
    }

    public Inner2 obj;

    public String get() {
        return obj.data;
    }
}