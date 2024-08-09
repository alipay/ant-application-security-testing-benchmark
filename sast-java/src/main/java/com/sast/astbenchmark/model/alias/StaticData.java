package com.sast.astbenchmark.model.alias;

public class StaticData {
    public static B staticB1;
    public static B staticB2;

    private static void aliasStatic() {
        staticB2.attr = staticB1.attr;
    }

}