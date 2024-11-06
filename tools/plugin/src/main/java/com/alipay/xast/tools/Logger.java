package com.alipay.xast.tools;

public interface Logger {

    void print(String message);

    void println(String message);

    void println();

    void printf(String format, Object... args);
}
