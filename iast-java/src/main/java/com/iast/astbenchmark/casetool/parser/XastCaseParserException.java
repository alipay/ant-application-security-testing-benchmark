package com.iast.astbenchmark.casetool.parser;

import com.iast.astbenchmark.casetool.exception.XastCaseToolException;

/**
 * 解析case源代码文件的时候可能会抛出的异常
 *
 * @author CC11001100
 */
public class XastCaseParserException extends XastCaseToolException {

    public XastCaseParserException() {
    }

    public XastCaseParserException(String message) {
        super(message);
    }

    public XastCaseParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public XastCaseParserException(Throwable cause) {
        super(cause);
    }

    public XastCaseParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
