package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.string.stringPartHasTaint.subNotTaint.stringBuilder.deleteCharAt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;
import com.iast.astbenchmark.common.CommonConsts;

/**
 * @author CC11001100
 */
@RestController
public class AccuracyTrackTaintStringBuilder_DeleteCharAt_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00947")
    public Map<String, Object> aTaintCase00947(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "a";
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            builder.deleteCharAt(0);
            Runtime.getRuntime().exec(builder.toString());
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
            "字符串部分存在污点",
            //
            "截取非污点部分",
            //
            "StringBuilder操作",
            //
            "deleteCharAt",
            //
        };
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00947";
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
