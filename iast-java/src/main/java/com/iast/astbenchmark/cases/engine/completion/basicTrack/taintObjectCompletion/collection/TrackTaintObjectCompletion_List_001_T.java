package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintObjectCompletion.collection;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintObjectCompletion_List_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * 引用类型List 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case006")
    public Map<String, Object> aTaintCase006(@RequestBody List<String> cmd) {

        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || CollectionUtils.isEmpty(cmd)) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }

        try {
            Runtime.getRuntime().exec(cmd.get(0));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }

        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase006";
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
            Category.LEVEL5_集合_集合对象全为污点,
            //
            "List元素",
            //
        };
    }

    @Override
    public Object requestBodyBean() {
        return Collections.singletonList(RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_STRING_BODY);
    }

}
