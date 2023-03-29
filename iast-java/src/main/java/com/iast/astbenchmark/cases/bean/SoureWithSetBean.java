package com.iast.astbenchmark.cases.bean;

import lombok.Data;

import java.util.Set;

@Data
public class SoureWithSetBean {
    private String      key;
    private Set<String> value;
}
