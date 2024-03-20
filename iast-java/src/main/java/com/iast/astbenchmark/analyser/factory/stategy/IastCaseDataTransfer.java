package com.iast.astbenchmark.analyser.factory.stategy;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.iast.astbenchmark.analyser.bean.BaseOriginalDataBean;
import com.iast.astbenchmark.analyser.bean.consts.AnalysisCommonConsts;
import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;
import com.iast.astbenchmark.analyser.factory.CaseDataTransfer;
import com.iast.astbenchmark.analyser.factory.stategy.iast.IastSlsBaseMessageBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * iast iast-->获取日志文件-->根据文件逐个解析case（Log方式）
 */
@Component
public class IastCaseDataTransfer extends CaseDataTransfer {
    private final String checkFlagPre = "auto_check_start_time=";

    public VendorEnum vendor() {
        return VendorEnum.IAST;
    }

    public Map<String, BaseOriginalDataBean> extrapResultMap(String path) {

        /**
         * Step1 ->获取检出结果并解析； 指定检测结果目录 以及检测标记
         */
        List<IastSlsBaseMessageBean> logBeans =
            getReportLog(configService.getIastReportId(), configService.getIastDetectionPath());
        /**
         * Step2 -> 抽取Tag 默认使用MethedName作为Case的tag进行标记
         */
        return convertToTagMap(logBeans);

    }

    private Map<String, BaseOriginalDataBean> convertToTagMap(List<IastSlsBaseMessageBean> logBeans) {
        return logBeans.stream()
            .filter(e -> e.getDetail() != null && StrUtil.isNotEmpty(e.getDetail().getSinkStack())
                && e.getType().equals("taint"))
            .collect(Collectors.toMap(e1 -> getTagKey(e1), e2 -> e2, (k1, k2) -> k1));
    }

    private String getTagKey(IastSlsBaseMessageBean data) {
        String methodName = data.getDetail().getCustomMethod();
        if (StrUtil.isNotEmpty(methodName)) {
            if (methodName.contains("#")) {
                return methodName.split("#")[1];
            }
            return methodName;
        } else {
            String sinkStack = data.getDetail().getSinkStack();
            if (StrUtil.isNotEmpty(sinkStack) && sinkStack.contains(AnalysisCommonConsts.TAG_SPLIT)) {
                String tagData = sinkStack.split(AnalysisCommonConsts.TAG_SPLIT)[1];
                String tagData2 = tagData.substring(tagData.indexOf(".") + 1, tagData.indexOf("("));
                if (tagData2.contains("$")) {
                    return tagData2.substring(tagData.indexOf("$") - 1, tagData.lastIndexOf("$") - 2);
                }
                return tagData2;
            }
        }
        return methodName;
    }

    public List<IastSlsBaseMessageBean> getReportLog(String reportId, String filePath) {
        String autoCheckFlag = checkFlagPre + reportId;
        List<String> logList = getStringByPath(filePath, configService.getVulType(), autoCheckFlag);
        List<IastSlsBaseMessageBean> iastMbList = new ArrayList<>();
        for (String log : logList) {
            String logSplit = "LogUtil - ";
            String slsSplit = "SlsUtil - ";
            if (log.contains(logSplit)) {
                String mbStr = log.split(logSplit)[1];
                IastSlsBaseMessageBean mb = JSONUtil.toBean(mbStr, IastSlsBaseMessageBean.class);
                iastMbList.add(mb);
            } else if (log.contains(slsSplit)) {
                String mbStr = log.split(slsSplit)[1];
                IastSlsBaseMessageBean mb = JSONUtil.toBean(mbStr, IastSlsBaseMessageBean.class);
                iastMbList.add(mb);
            }
        }
        return iastMbList;
    }

    private List<String> getStringByPath(String filePath, String vulType, String autoCheckFlag) {
        List<String> lines = FileUtil.readLines(filePath, Charset.forName("utf-8"));
        if (CollectionUtils.isEmpty(lines)) {
            return Lists.newArrayList();
        }
        List<String> res = Lists.newArrayList();
        lines.stream().forEach(e -> {
            if (e.contains("type\":\"taint") && e.contains(autoCheckFlag)) {
                res.add(e);
            }
        });
        return res;
    }

}
