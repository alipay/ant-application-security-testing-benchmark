package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.propagation.stringOperation.stringBuilder;

import java.io.IOException;
import java.util.Arrays;
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
public class TrackTaintChainCompletion_StringBuilderCopyOfRange_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase0094 传播场景-char[],byte[]操作-->copyOfRange
     */
    @PostMapping(value = "case0094")
    public Map<String, Object> aTaintCase0094(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] b1 = cmd.getBytes();
            byte[] b2 = Arrays.copyOfRange(b1, 0, 2);
            Runtime.getRuntime().exec(new String(b2));
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
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_基础跟踪能力,
            //
            Category.LEVEL4_污点链路完整度,
            //
            Category.LEVEL5_污点传播跟踪能力,
            //
            Category.LEVEL6_传播场景,
            //
            "char[],byte[]操作",
            //
            "copyOfRange",
            //
        };
    }

    @Override
    public String requestBodyString() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_STRING_BODY;
    }

}
