package com.iast.astbenchmark.analyser.bean.consts;

public enum CaseTypeEnum {
    T001("污点对象完整度能力检测"),
    T002("污点链路完整度能力检测"),
    T003("异步跟踪能力检测"),
    T004("跨进程跟踪能力检测"),
    T005("污点准确度能力检测");
    String desc;
    CaseTypeEnum(String desc){
        this.desc=desc;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
