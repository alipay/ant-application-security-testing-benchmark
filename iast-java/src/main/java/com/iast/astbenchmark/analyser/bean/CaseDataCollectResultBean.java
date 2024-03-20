package com.iast.astbenchmark.analyser.bean;

import java.io.Serializable;
import java.util.List;

import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;

import lombok.Data;

@Data
public class CaseDataCollectResultBean implements Serializable {

    private VendorEnum vendor;
    private String reportId;
    private Long caseTime;
    /**
     * 用于存储case检出结果
     */
    private List<CaseResultbean> caseDetectionItems;

    @Override
    public String toString() {
        return "CaseDataCollectResultBean{" + "vendor=" + vendor.getDescription() + ", reportId='" + reportId + '\''
            + ", caseTime=" + caseTime + ", caseDetectionItems=" + caseDetectionItems + '}';
    }

}
