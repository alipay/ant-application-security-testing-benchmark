package com.iast.astbenchmark.cases.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class SourceTestObject implements Serializable {
    private static final Long serialVersionUID = 9123456L;
    private  String cmd;
    private String cmd2;
}
