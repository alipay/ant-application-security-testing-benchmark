package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintObjectCompletion.basicAndUnbox;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintObjectCompletion_LongUnbox_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * 基本数据类型的封装类型 Long 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0011")
    public Map<String, Object> aTaintCase0011(@RequestParam Long cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase0011";
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_基础跟踪能力,
            //
            Category.LEVEL4_污点对象完整度,
            //
            Category.LEVEL5_基本数据类型及其封装类型,
            //
            "Long",
            //
        };
    }

    @Override
    public String queryString() {
        return "cmd=10086";
    }

}
