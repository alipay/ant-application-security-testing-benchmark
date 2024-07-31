package com.iast.astbenchmark.analyser.bean.consts;

import org.apache.commons.lang.StringUtils;

/**
 * 标记为废弃，类目树采用单独的常量类来引用
 *
 * @see com.iast.astbenchmark.analyser.Category
 */
@Deprecated
public enum CaseTypeEnum {

    T001("IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度", "污点对象完整度能力检测"),
    T002("IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度", "污点链路完整度能力检测"),
    T003("IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力", "异步跟踪能力检测"),
    T004("IAST引擎能力评估体系(JAVA)->完整度->跨进城跟踪能力", "跨进程跟踪能力检测"),
    T005("IAST引擎能力评估体系(JAVA)->准确度", "污点准确度能力检测");

    String desc;

    String tag;

    CaseTypeEnum(String tag, String desc) {
        this.desc = desc;
        this.tag = tag;
    }

    public static String getDescByTag(String data) {
        if (StringUtils.isEmpty(data)) {
            return data;
        }
        for (CaseTypeEnum caseTypeEnum : values()) {
            if (data.contains(caseTypeEnum.getTag())) {
                return caseTypeEnum.name();
            }
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
