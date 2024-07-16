package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.specialLinkTrackingCapabilities.veryLong;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.MyCommonTestUtil;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintChainCompletion_VeryLong100_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping("case0023")
    public Map<String, Object> aTaintCase0023(@RequestBody String cmd) {

        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            return modelMap;
        }

        try {
            /** 递归100次 */
            cmd = MyCommonTestUtil.traceDeepth(cmd, 0, 100);
            Runtime.getRuntime().exec(cmd);
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
            Category.LEVEL5_特殊链路跟踪能力,
            //
            Category.LEVEL6_超长链路追踪_100,
            //
        };
    }

    @Override
    public String description() {
        return "特殊链路跟踪能力->超长链路追踪";
    }

    @Override
    public String requestBodyString() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_STRING_BODY;
    }

}
