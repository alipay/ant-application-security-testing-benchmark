package com.iast.astbenchmark.cases.bean;

import java.util.Set;

import lombok.Data;

@Data
public class SoureWithSetBean {
    private String key;
    private Set<String> value;
}
