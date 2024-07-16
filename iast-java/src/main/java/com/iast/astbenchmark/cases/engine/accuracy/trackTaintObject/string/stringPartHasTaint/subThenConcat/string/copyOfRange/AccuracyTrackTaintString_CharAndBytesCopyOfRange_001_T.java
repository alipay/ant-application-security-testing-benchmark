package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.string.stringPartHasTaint.subThenConcat.string.copyOfRange;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;
import com.iast.astbenchmark.common.CommonConsts;

/**
* Introduction X
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 字符串级别 -> 字符串部分存在污点 -> 截取非污点部分后再拼接污点 -> char[]/byte[]操作 -> copyOfRange
// compose = 
// bind_url = /case00960
// assession information end
public class AccuracyTrackTaintString_CharAndBytesCopyOfRange_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00960")
    @IastTestCase(caseNo = "aTaintCase00960",
        caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->char[]/byte[]操作->copyOfRange",
        thisMethodTag = "aTaintCase00960", hasVul = true)
    public Map<String, Object> aTaintCase00960(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String aa = hardcode + cmd;
            char[] chars = aa.toCharArray();
            char[] data = Arrays.copyOfRange(chars, 0, hardcode.length());// 非无污点部分
            // char result[] = new char[info.length + data.length];
            // System.arraycopy(info, 0, result, 0, info.length);
            // System.arraycopy(data, 0, result, info.length, data.length);
            char[] cmdChars = cmd.toCharArray();
            char[] res = new char[data.length + cmd.length()]; // 将污点与非污点组合
            for (int i = 0; i < res.length; i++) {
                if (i < data.length) {
                    res[i] = data[i];
                } else {
                    res[i] = cmdChars[i - data.length];
                }
            }
            Runtime.getRuntime().exec(new String(res));
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
            "char[]/byte[]操作",
            //
            "copyOfRange"
            //
        };
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String caseNo() {
        return null;
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }
}
