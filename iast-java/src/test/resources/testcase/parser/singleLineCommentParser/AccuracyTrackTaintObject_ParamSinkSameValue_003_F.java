package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.variable;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// assession information start
// real vulnerability = false
// assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 变量级别 -> sink点的值非外部可控，但与某个参数值相同
// compose =
// bind_url =
// assession information end

/**
 * @author CC11001100
 */
@RestController
public class AccuracyTrackTaintObject_ParamSinkSameValue_001_F
        implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    // assession information start
    // real vulnerability = false
    // assession project = IAST引擎能力评估体系(JAVA) -> 准确度 -> 污点对象跟踪粒度 -> 变量级别 -> sink点的值非外部可控，但与某个参数值相同
    // compose =
    // bind_url =
    // assession information end

    /**
     * ------------- 准确度---------------
     */
    /**
     * aTaintCase00125 污点对象跟踪粒度->变量级别->sink点的值非外部可控，但与某个参数值相同 这个case期望不能被检出污点
     */
    // 考虑使用这个sink点
    // private PrintWriter pw = new PrintWriter(System.out);
    // private void sink(Object obj) throws Exception{
    // pw.println(obj);
    // pw.flush();
    // }
    @PostMapping(value = "case00125")
    public Map<String, Object> aTaintCase00125(@RequestParam String cmd1,
                                               @RequestParam(defaultValue = "ls") String cmd2) {

        Map<String, Object> modelMap = new HashMap<>();
        try {
            String exec = "ls";
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }

        return modelMap;
    }

    @Override
    public String[] category() {
        return new String[]{
                //
                Category.LEVEL1_IAST_JAVA_ENGINE,
                //
                Category.LEVEL2_准确度,
                //
                Category.LEVEL3_污点对象跟踪粒度,
                //
                Category.LEVEL4_变量级别,
                //
                "sink点的值非外部可控，但与某个参数值相同",
                //
        };
    }

    @Override
    public Boolean hasVul() {
        return false;
    }

    @Override
    public String description() {
        return "sink点的值非外部可控，但与某个参数值相同 这个case期望不能被检出污点";
    }

    @Override
    public String caseNo() {
        return "aTaintCase00125";
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("cmd1", "cd /");
        paramMap.put("cmd2", "ls");
        return paramMap;
    }

}
