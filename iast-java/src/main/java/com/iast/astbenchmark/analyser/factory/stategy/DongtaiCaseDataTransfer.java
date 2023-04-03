package com.iast.astbenchmark.analyser.factory.stategy;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.iast.astbenchmark.analyser.bean.BaseOriginalDataBean;
import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;
import com.iast.astbenchmark.analyser.factory.CaseDataTransfer;
import com.iast.astbenchmark.analyser.factory.stategy.dongtai.DongResultBean;
import com.iast.astbenchmark.analyser.factory.stategy.dongtai.DongTaintItemBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DongtaiCaseDataTransfer extends CaseDataTransfer {

    public VendorEnum vendor() {
        return VendorEnum.DONGTAI;
    }

    public Map<String, BaseOriginalDataBean> extrapResultMap(String path) {
        /**
         *  Step1 ->获取检出结果并解析；
         *  指定检测结果目录 以及检测标记
         */
        List<DongTaintItemBean> taintItemBeans = getReportLogArray(path);
        /**
         *  Step2 -> 抽取Tag
         *  默认使用MethedName作为Case的tag进行标记
         */
        return convertToTagMap(taintItemBeans);
    }

    private Map<String, BaseOriginalDataBean> convertToTagMap(List<DongTaintItemBean> logsBeans) {
        if (CollectionUtils.isEmpty(logsBeans)) {
            return Maps.newHashMap();
        }
        return logsBeans.stream().filter(e -> StrUtil.isNotEmpty(e.getBottom_stack())
                        && StrUtil.isNotEmpty(e.getTop_stack()) && StrUtil.isNotEmpty(e.getUri()))
                .collect(Collectors.toMap(e1 -> getTagKey(e1), e2 -> e2, (k1, k2) -> k1));
    }

    private String getTagKey(DongTaintItemBean baseData) {
        String url = baseData.getUri();
        if (url.contains("case00")) {
            String tag = "aTaintCase00" + url.split("case00")[1].split("/")[0];
            if (url.endsWith("/2")) {
                tag = tag + "_2";
            } else if (url.endsWith("/1")) {
                tag = tag + "_1";
            } else if (url.endsWith("/3")) {
                tag = tag + "_3";
            } else if (url.endsWith("/4")) {
                tag = tag + "_4";
            } else if (url.endsWith("/5")) {
                tag = tag + "_5";
            } else if (url.endsWith("/6")) {
                tag = tag + "_6";
            } else if (url.endsWith("/7")) {
                tag = tag + "_7";
            }
            return tag;
        }
        return "";
    }

    private List<DongTaintItemBean> getReportLog(String path) {
        JSONObject json = JSONUtil.readJSONObject(FileUtil.file(path), Charset.forName("utf-8"));
        DongResultBean resultBean = JSONUtil.toBean(json, DongResultBean.class);
        return resultBean.getData().getMessages();
    }

    private List<DongTaintItemBean> getReportLogArray(String path) {
        List<DongTaintItemBean> itemBeans = Lists.newArrayList();
        JSONArray jsonArray = JSONUtil.readJSONArray(FileUtil.file(path), Charset.forName("utf-8"));
        for (Object o : jsonArray) {
            DongResultBean resultBean = JSONUtil.toBean(JSONUtil.toJsonStr(o), DongResultBean.class);
            itemBeans.addAll(resultBean.getData().getMessages());
        }
        return itemBeans;
    }
}
