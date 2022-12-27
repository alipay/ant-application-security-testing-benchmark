package com.iast.astbenchmark.analyser.bean.consts;

/**
 * TP（True Positives）： 真的正样本 = 【正样本 被正确分为 正样本】
 *
 * TN（True Negatives）： 真的负样本 = 【负样本 被正确分为 负样本】
 *
 * FP（False Positives）： 假的正样本 = 【负样本 被错误分为 正样本】
 *
 * FN（False Negatives）：假的负样本 = 【正样本 被错误分为 负样本】
 */
public enum CaseResultEnum {
//    TP("TP", "真的正样本"),
//    TN("TN","真的负样本"),
//
//    FP("FP","假的正样本"),
//    FN("FN","假的负样本")
    TP("TP", "通过"),
    TN("TN","通过"),

    FP("FP","误报"),
    FN("FN","漏报")
    ;

    private String code;
    private String desc;
    CaseResultEnum(String code,String desc){
        this.code = code;
        this.desc =desc;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
