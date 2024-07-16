package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.partFieldHasTaint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
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
public class AccuracyTrackTaintObject_TaintFromSelf_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00128 污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->多层复杂对象部分字段为污点->污点来自子类
     */
    @PostMapping(value = "case00128")
    @IastTestCase(caseNo = "aTaintCase00128",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->多层复杂对象部分字段为污点->污点来当前类字段",
        thisMethodTag = "aTaintCase00128", hasVul = true)
    public Map<String, Object> aTaintCase00128(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            LayerBaseBean99 simpleBean = new LayerBaseBean99();
            simpleBean.setCmda0("cd /");
            simpleBean.setCmdb0("ls -a");
            simpleBean.setCmda99(cmd);
            simpleBean.setCmdb99("ls");
            Runtime.getRuntime().exec(simpleBean.getCmda99());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_准确度,
            //
            Category.LEVEL3_污点对象跟踪粒度,
            //
            Category.LEVEL4_字段_元素级别,
            //
            Category.LEVEL5_部分字段对象为污点,
            //
            "多层复杂对象部分字段为污点",
            // ,
            "污点来当前类字段",
            //
        };
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00128";
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
