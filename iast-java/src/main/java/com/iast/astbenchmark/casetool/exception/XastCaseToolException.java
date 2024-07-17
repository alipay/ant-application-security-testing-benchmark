package com.iast.astbenchmark.casetool.exception;

import com.iast.astbenchmark.common.XastIastException;

/**
 * xAST case工具类可能会抛出的异常
 *
 * @author CC11001100
 */
public class XastCaseToolException extends XastIastException {

    public XastCaseToolException() {
    }

    public XastCaseToolException(String message) {
        super(message);
    }

    public XastCaseToolException(String message, Throwable cause) {
        super(message, cause);
    }

    public XastCaseToolException(Throwable cause) {
        super(cause);
    }

    public XastCaseToolException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
