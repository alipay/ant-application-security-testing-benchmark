package com.iast.astbenchmark.casetool.generator;

import com.iast.astbenchmark.casetool.exception.XastCaseToolException;

/**
 * @author CC11001100
 */
public class XastGeneratorException extends XastCaseToolException {

    public XastGeneratorException() {}

    public XastGeneratorException(String message) {
        super(message);
    }

    public XastGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public XastGeneratorException(Throwable cause) {
        super(cause);
    }

    public XastGeneratorException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
