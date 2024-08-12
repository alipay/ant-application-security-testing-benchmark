package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.string.stringPartHasTaint.subThenConcat.string.concat;

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
import com.iast.astbenchmark.common.CommonConsts;

/**
* Introduction X
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字符串级别 -> 字符串部分存在污点 -> 截取非污点部分后再拼接污点 -> String操作 -> concat
// compose = !AccuracyTrackTaintString_StringConcat_002_F.java && AccuracyTrackTaintString_StringConcat_003_T.java && AccuracyTrackTaintString_StringConcat_001_T.java
// bind_url = /case00953
// assession information end
public class AccuracyTrackTaintString_StringConcat_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * 字符串部分存在污点->截取非污点部分后再拼接污点->String操作->concat@aTaintCase00953
     *
     * @param cmd
     * @return
     */
    @PostMapping(value = "case00953")
    @IastTestCase(caseNo = "aTaintCase00953",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->concat",
        thisMethodTag = "aTaintCase00953", hasVul = true)
    public Map<String, Object> aTaintCase00953(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "ls";
            String cmdfull = hardcode + cmd;
            String data1 = cmdfull.substring(0, hardcode.length()); // 截取到非无污点数据
            String dara2 = data1.concat(cmd); // 再拼接上污点
            Runtime.getRuntime().exec(dara2);
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
            Category.LEVEL4_字符串级别,
            //
            Category.LEVEL5_字符串部分存在污点,
            //
            "截取非污点部分后再拼接污点",
            //
            "String操作",
            //
            "concat"
            //
        };
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00953";
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
