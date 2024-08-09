package com.sast.astbenchmark.model.alias;

public class Inner1b {

    public class Inner2b {
        public String data;

        public void set(String v) {
            obj.data = v;
        }

        public String get() {
            return obj.data;
        }

        public String getParent() {
            return parentData;
        }
    }

    public Inner2b obj;
    public String parentData;

    public String get() {
        return obj.data;
    }
}