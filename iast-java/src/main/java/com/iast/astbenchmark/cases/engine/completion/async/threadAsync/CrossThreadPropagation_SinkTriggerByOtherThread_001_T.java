package com.iast.astbenchmark.cases.engine.completion.async.threadAsync;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;
import com.iast.astbenchmark.common.CommonConsts;

import cn.hutool.core.thread.ThreadUtil;

/**
 * @author CC11001100
 */
@RestController
public class CrossThreadPropagation_SinkTriggerByOtherThread_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    private static final Logger log =
        LoggerFactory.getLogger(CrossThreadPropagation_SinkTriggerByOtherThread_001_T.class.getName());

    /**
     * aTaintCase00120 异步跟踪能力->多线程异步->污点的来源和触发在不同线程，sink的触发由线程池中的线程触发
     */
    private static final ThreadPoolExecutor executorForTest = ThreadUtil.newExecutor(1, 1);
    @PostMapping(value = "case00120")
    public Map<String, Object> aTaintCase00120(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        executorForTest.execute(() -> {
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                log.error("", e);
            }
        });
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00120";
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_异步跟踪能力,
            //
            Category.LEVEL4_多线程异步,
            //
            "污点的来源和触发在不同线程，sink的触发由线程池中的线程触发",
            //
        };
    }

    @Override
    public Map<String, String> params() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_PARAMS;
    }

}
