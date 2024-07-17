package com.iast.astbenchmark.analyser.bean;

import lombok.Data;

@Data
public class CaseTargetItemBean {

    private String tag;
    /**
     * 是否期待检出
     **/
    private Boolean result;

}
