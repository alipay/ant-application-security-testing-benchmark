package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.partFieldHasTaint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean99;
import com.iast.astbenchmark.common.CommonConsts;

/**
 * @author CC11001100
 */
@RestController
public class AccuracyTrackTaintObject_TaintFromParent_002_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00127/2")
    @IastTestCase(caseNo = "aTaintCase00127",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->多层复杂对象部分字段为污点->污点来自父类",
        thisMethodTag = "aTaintCase00127_2", hasVul = false)
    public Map<String, Object> aTaintCase00127_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            LayerBaseBean99 simpleBean = new LayerBaseBean99();
            simpleBean.setCmda0(cmd);
            simpleBean.setCmdb0("ls");
            simpleBean.setCmda99("cd /");
            simpleBean.setCmdb99("cd ~");
            // cmdb0的内容是常量
            Runtime.getRuntime().exec(simpleBean.getCmdb0());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public Boolean hasVul() {
        return false;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new AccuracyTrackTaintObject_TaintFromParent_001_T();
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
