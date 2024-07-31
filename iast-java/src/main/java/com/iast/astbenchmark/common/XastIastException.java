package com.iast.astbenchmark.common;

/**
 *
 * xAST IAST模块都继承这个异常
 *
 * @author CC11001100
 */
public class XastIastException extends Exception {

    public XastIastException() {
    }

    public XastIastException(String message) {
        super(message);
    }

    public XastIastException(String message, Throwable cause) {
        super(message, cause);
    }

    public XastIastException(Throwable cause) {
        super(cause);
    }

    public XastIastException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
