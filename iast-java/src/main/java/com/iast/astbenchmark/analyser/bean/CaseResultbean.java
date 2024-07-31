package com.iast.astbenchmark.analyser.bean;

import com.iast.astbenchmark.analyser.bean.consts.CaseResultEnum;
import com.iast.astbenchmark.analyser.bean.consts.CaseTypeEnum;
import lombok.Data;

@Data
public class CaseResultbean {

    private CaseTypeEnum caseTypeEnum;
    private String caseNo;
    private String caseDesc;

    /**
     * Case 结果
     */
    private CaseResultEnum result;
    /**
     * 用于存放case的原始数据
     */
    private String originalData;
    /**
     * 用于存储Case的分析结果（这一步暂时未适配，因为这一步和厂商强相关）
     */
    private CaseDataBean analysisData;

}
