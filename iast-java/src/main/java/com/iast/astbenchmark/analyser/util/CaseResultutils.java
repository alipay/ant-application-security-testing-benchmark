package com.iast.astbenchmark.analyser.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.iast.astbenchmark.analyser.bean.BaseOriginalDataBean;
import com.iast.astbenchmark.analyser.bean.CaseResultbean;
import com.iast.astbenchmark.analyser.bean.CaseTargetBean;
import com.iast.astbenchmark.analyser.bean.CaseTargetItemBean;
import com.iast.astbenchmark.analyser.bean.consts.CaseResultEnum;
import com.iast.astbenchmark.analyser.bean.consts.CaseTypeEnum;
import com.iast.astbenchmark.analyser.cache.CasetargeCache;

import cn.hutool.core.util.StrUtil;

public class CaseResultutils {

    public static List<CaseResultbean> caseAnalyse(Map<String, BaseOriginalDataBean> tagMap) {
        /**
         * 将获取的结果与预期结果进行比较,得到这个Case是否成功 TODO 此处还可以考虑存储原始数据和解析数据 为展示详情打基础
         */
        List<CaseResultbean> caseResultbeans = Lists.newArrayList();
        Map<String, CaseTargetBean> caseTargetBeanMap = CasetargeCache.getAllCases();
        caseTargetBeanMap.forEach((k, targetBean) -> {
            CaseResultbean resultbean = new CaseResultbean();
            resultbean.setCaseNo(targetBean.getCaseNo());
            resultbean.setCaseDesc(targetBean.getCaseDesc());
            resultbean.setCaseTypeEnum(
                StrUtil.isEmpty(targetBean.getCaseType()) ? null : CaseTypeEnum.valueOf(targetBean.getCaseType()));
            resultbean.setResult(checkCaseResult(targetBean, tagMap));
            caseResultbeans.add(resultbean);
        });

        return caseResultbeans;
    }

    /**
     * 判断此次Case的结果
     * 
     * @param
     * @param tagMap
     * @return
     */
    private static CaseResultEnum checkCaseResult(CaseTargetBean targetBean, Map<String, BaseOriginalDataBean> tagMap) {
        List<CaseTargetItemBean> itemBeanList = targetBean.getData();
        List<CaseResultEnum> resultEnums = Lists.newArrayList();
        for (CaseTargetItemBean caseTargetItemBean : itemBeanList) {
            resultEnums.add(findAndCheckItem(caseTargetItemBean, tagMap));
        }
        return analysisResultEnumList(resultEnums);
    }

    /**
     * TODO 对于需要多组接口来确定Case结果的理解
     */
    private static CaseResultEnum analysisResultEnumList(List<CaseResultEnum> resultEnums) {
        /**
         * PT PF NT NF PF>NT>>PT>FN FN>FP>>TP>TN
         */
        Map<String, CaseResultEnum> resultEnumMap =
            resultEnums.stream().collect(Collectors.toMap(e -> e.getCode(), e -> e, (k1, k2) -> k1));
        if (resultEnumMap.containsKey(CaseResultEnum.FN.getCode())) {
            return CaseResultEnum.FN;
        } else if (resultEnumMap.containsKey(CaseResultEnum.FP.getCode())) {
            return CaseResultEnum.FP;
        } else if (resultEnumMap.containsKey(CaseResultEnum.TP.getCode())) {
            return CaseResultEnum.TP;
        } else {
            return CaseResultEnum.TN;
        }
    }

    /** 检查是否和预期值一样 **/
    private static CaseResultEnum findAndCheckItem(CaseTargetItemBean itemBean,
        Map<String, BaseOriginalDataBean> tagMap) {
        if (itemBean.getResult() && tagMap.containsKey(itemBean.getTag())) {
            return CaseResultEnum.TP;
        } else if (itemBean.getResult() && !tagMap.containsKey(itemBean.getTag())) {
            return CaseResultEnum.FN; // 漏报
        } else if (!itemBean.getResult() && tagMap.containsKey(itemBean.getTag())) {
            return CaseResultEnum.FP; // 误报
        } else {
            return CaseResultEnum.TN;
        }
    }

}
