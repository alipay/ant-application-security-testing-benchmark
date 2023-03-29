package com.iast.astbenchmark.cases;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.iast.astbenchmark.analyser.service.DataAnalysisService;
import com.iast.astbenchmark.analyser.bean.CaseDataCollectResultBean;
import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;
import com.iast.astbenchmark.analyser.factory.stategy.IastCaseDataTransfer;
import com.iast.astbenchmark.analyser.factory.stategy.SeekerCaseDataTransfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * 自测用，可删除
 */
@RestController
@Slf4j
public class TestController {
    @Autowired
    IastCaseDataTransfer iastCaseDataTransfer;
    @PostMapping(value = "iast/strategy")
    public void test001() {
        iastCaseDataTransfer.doOperation();
    }
    @Autowired
    SeekerCaseDataTransfer seekerCaseDataTransfer;
    @PostMapping(value = "seeker/strategy")
    public void test002() {
        seekerCaseDataTransfer.doOperation();
    }
    @Autowired
    DataAnalysisService dataAnalysisService;
    @PostMapping(value = "analysis/strategy/{vendor}")
    public void test003(@PathVariable String vendor) {
        VendorEnum vendorEnum = VendorEnum.valueOf(vendor);
        if(vendorEnum==null){
            return;
        }
        try {
            dataAnalysisService.doAnalysisAndDB(vendorEnum);
        } catch (SQLException e) {
            log.error("分析存储报告异常");
        }
    }

    @PostMapping(value = "analysis/{id}")
    public void test003(@PathVariable Long id) {
        Entity res = null;
        try {
            res = Db.use().get(Entity.create("REPORT").set("id",id));
            Object data = res.get("REPORT_DATA");
            JSONObject json = JSONUtil.parseObj(data);
            String vendor = json.getStr("vendor");
            CaseDataCollectResultBean collectResultBean = JSONUtil.toBean(json,CaseDataCollectResultBean.class);
            collectResultBean.setVendor(VendorEnum.valueOf(vendor));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(res);
    }
}
