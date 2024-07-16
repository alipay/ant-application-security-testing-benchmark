package com.iast.astbenchmark.cases.engine.completion.async.storage.sessionStorage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.SessionUtil;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintObjectCompletion_SessionStorage_001_F implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00114 异步跟踪能力->存储行异步->污点通过session存储后触发
     */
    @PostMapping(value = "case00114/{session}")
    public Map<String, Object> aTaintCase00114(HttpServletRequest request, @PathVariable String session) {
        Map<String, Object> modelMap = new HashMap<>();
        SessionUtil.setSession(request, "sessionKey", session);
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @Override
    public String description() {
        return "通过Session存储污点，后续取出污点后将其命令执行";
    }

    @Override
    public String caseNo() {
        return "aTaintCase00114";
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
                "存储型异步",
                //
                "污点通过session存储后触发",
                //
        };
    }

}
