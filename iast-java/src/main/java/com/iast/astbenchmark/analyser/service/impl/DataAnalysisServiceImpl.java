package com.iast.astbenchmark.analyser.service.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.iast.astbenchmark.analyser.bean.CaseDataCollectResultBean;
import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;
import com.iast.astbenchmark.analyser.factory.CaseDataTransferStategyFactory;
import com.iast.astbenchmark.analyser.service.DataAnalysisService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DataAnalysisServiceImpl implements DataAnalysisService {
    @Autowired
    private CaseDataTransferStategyFactory caseDataTransferStategyFactory;

    @Override
    public Long doAnalysisAndDB(VendorEnum vendorEnum) throws SQLException {
        log.debug("启动分析:" + vendorEnum.getDescription());
        CaseDataCollectResultBean resultBean = caseDataTransferStategyFactory.collect(vendorEnum);
        try {
            Long id = Db.use().insertForGeneratedKey(Entity.create("REPORT").set("VENDOR", vendorEnum.getCode())
                .set("REPORT_ID", resultBean.getReportId()).set("REPORT_DATA", JSONUtil.toJsonStr(resultBean)));
            Entity res = Db.use().get(Entity.create("REPORT").set("id", id));
            return id;
        } catch (SQLException e) {
            log.error("报告插入失败{}", e);
            throw e;
        }
    }

    @Override
    public CaseDataCollectResultBean searchResult(Long id) throws SQLException {
        Entity res = Db.use().get(Entity.create("REPORT").set("id", id));
        return parse(res);
    }

    @Override
    public CaseDataCollectResultBean searchResultbyReportId(String reportID) throws SQLException {
        Entity res = Db.use().get(Entity.create("REPORT").set("REPORT_ID", reportID));
        return parse(res);
    }

    @Override
    public List<String> getAllReportId(String vendor) {
        try {
            List<Entity> list = Lists.newArrayList();
            if (vendor.equalsIgnoreCase("all")) {
                list = Db.use().query("select REPORT_ID from REPORT");
            } else {
                list = Db.use().query("select REPORT_ID from REPORT where VENDOR=?", vendor.toUpperCase());
            }
            return list.stream().map(e -> e.getStr("REPORT_ID")).collect(Collectors.toList());
        } catch (SQLException e) {
            log.error("查询失败{}", e);
        }
        return Lists.newArrayList();
    }

    private CaseDataCollectResultBean parse(Entity res) {
        Object data = res.get("REPORT_DATA");
        JSONObject json = JSONUtil.parseObj(data);
        String vendor = json.getStr("vendor");
        CaseDataCollectResultBean collectResultBean = JSONUtil.toBean(json, CaseDataCollectResultBean.class);
        collectResultBean.setVendor(VendorEnum.valueOf(vendor));
        return collectResultBean;
    }
}
