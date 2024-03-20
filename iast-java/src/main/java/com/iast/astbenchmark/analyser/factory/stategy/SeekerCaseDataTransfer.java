package com.iast.astbenchmark.analyser.factory.stategy;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.iast.astbenchmark.analyser.bean.BaseOriginalDataBean;
import com.iast.astbenchmark.analyser.bean.consts.AnalysisCommonConsts;
import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;
import com.iast.astbenchmark.analyser.factory.CaseDataTransfer;
import com.iast.astbenchmark.analyser.factory.stategy.seeker.SeekerCollectBaseData;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SeekerCaseDataTransfer extends CaseDataTransfer {

    public VendorEnum vendor() {
        return VendorEnum.SEEKER;
    }

    public Map<String, BaseOriginalDataBean> extrapResultMap(String path) {
        /**
         * Step1 ->获取检出结果并解析；
         */
        List<SeekerCollectBaseData> logsBeans = getReportLog(path);
        /**
         * Step2 -> 抽取Tag 默认使用MethedName作为Case的tag进行标记
         */
        return convertToTagMap(logsBeans);
    }

    private Map<String, BaseOriginalDataBean> convertToTagMap(List<SeekerCollectBaseData> logsBeans) {
        if (CollectionUtils.isEmpty(logsBeans)) {
            return Maps.newHashMap();
        }
        return logsBeans.stream()
            .filter(e -> StrUtil.isNotEmpty(e.getSourceType()) && StrUtil.isNotEmpty(e.getCodeLocation()))
            .collect(Collectors.toMap(e1 -> getTagKey(e1), e2 -> e2, (k1, k2) -> k1));
    }

    private String getTagKey(SeekerCollectBaseData baseData) {
        String sinkStack = baseData.getCodeLocation();
        String url = baseData.getURL();
        if (sinkStack.contains(AnalysisCommonConsts.TAG_SPLIT)) {
            String tagData = sinkStack.split(AnalysisCommonConsts.TAG_SPLIT)[1];
            String tagData2 = tagData.substring(tagData.indexOf(".") + 1, tagData.indexOf("("));
            if (tagData2.contains("$")) {
                return tagData2.substring(tagData.indexOf("$") - 1, tagData.lastIndexOf("$") - 2);
            }
            return tagData2;
        } else if (url.contains("case00")) {
            /**
             * case0099
             */
            String tag = "aTaintCase00" + url.split("case00")[1].split("/")[0];
            if (url.endsWith("/2")) {
                tag = tag + "_2";
            }
            return tag;
        }
        return "";
    }

    private List<SeekerCollectBaseData> getReportLog(String file) {
        JSONArray array = JSONUtil.readJSONArray(FileUtil.file(file), Charset.forName("utf-8"));
        if (array == null || array.isEmpty()) {
            return Lists.newArrayList();
        }
        List<SeekerCollectBaseData> res = Lists.newArrayList();
        for (Object obj : array) {
            res.add(JSONUtil.toBean(JSONUtil.toJsonStr(obj), SeekerCollectBaseData.class));
        }
        return res;
    }
}
