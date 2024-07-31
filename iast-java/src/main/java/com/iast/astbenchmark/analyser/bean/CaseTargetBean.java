package com.iast.astbenchmark.analyser.bean;

import lombok.Data;

import java.util.List;

/**
 * case_target&case_de
 */
@Data
public class CaseTargetBean {

    /**
     * case标签
     */
    private List<String> tags;

    /**
     * 权重（1～10）
     */
    private Integer weight;
    private String caseNo;
    private String caseDesc;
    private String caseType;
    private List<CaseTargetItemBean> data;

    @Override
    public String toString() {
        return "{" +
                "tags=" + tags +
                ", weight=" + weight +
                ", caseNo='" + caseNo + '\'' +
                ", caseDesc='" + caseDesc + '\'' +
                ", caseType='" + caseType + '\'' +
                ", data=" + data +
                '}';
    }

}

