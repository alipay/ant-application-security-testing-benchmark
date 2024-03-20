package com.iast.astbenchmark.cli.xmind;

import java.util.Map;
import java.util.stream.Collectors;

import com.iast.astbenchmark.analyser.bean.CaseDataCollectResultBean;
import com.iast.astbenchmark.analyser.bean.CaseResultbean;
import com.iast.astbenchmark.analyser.cache.CaseStuctCache;

public class XmindUtil {
    public static String export(CaseDataCollectResultBean resultBean) {
        Tutis tutis = new Tutis();
        Map<String, CaseResultbean> resultbeanMap = resultBean.getCaseDetectionItems().stream()
            .collect(Collectors.toMap(CaseResultbean::getCaseNo, e -> e, (k1, k2) -> k1));

        try {
            return tutis.exportXmind(CaseStuctCache.getRoot(), resultbeanMap,
                resultBean.getVendor().getDescription() + "_benchmark_result");
        } catch (Exception e) {
            return "思维导图导出异常";
        }
    }
}
