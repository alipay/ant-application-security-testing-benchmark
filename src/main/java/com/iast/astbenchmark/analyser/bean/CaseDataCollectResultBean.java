package com.iast.astbenchmark.analyser.bean;

import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
        return "CaseDataCollectResultBean{" +
                "vendor=" + vendor.getDescription() +
                ", reportId='" + reportId + '\'' +
                ", caseTime=" + caseTime +
                ", caseDetectionItems=" + caseDetectionItems +
                '}';
    }

}
