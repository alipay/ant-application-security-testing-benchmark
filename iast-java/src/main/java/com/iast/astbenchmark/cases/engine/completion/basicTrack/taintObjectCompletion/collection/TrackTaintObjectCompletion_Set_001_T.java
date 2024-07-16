package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintObjectCompletion.collection;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.cases.bean.SoureWithSetBean;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintObjectCompletion_Set_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * 引用类型Set 作为污点对象
     *
     * @param
     * @return
     */
    @PostMapping("case008")
    public Map<String, Object> aTaintCase008(@RequestBody SoureWithSetBean setBean) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(setBean.getValue().iterator().next());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String description() {
        return "引用类型Set 作为污点对象";
    }

    @Override
    public String caseNo() {
        return "aTaintCase008";
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
            "Set元素",
            //
        };
    }

    @Override
    public Object requestBodyBean() {
        Set<String> set = new HashSet<>();
        set.add("ls");
        set.add("key");
        SoureWithSetBean setBean = new SoureWithSetBean();
        setBean.setKey("key");
        setBean.setValue(set);
        return setBean;
    }

}
