package com.iast.astbenchmark.analyser.factory.stategy.iast;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TaintFlow {
    private String methodName;
    private String className;
    private String taintEventType;
    private String taintValue;
    private String classMethodSignature;
}
