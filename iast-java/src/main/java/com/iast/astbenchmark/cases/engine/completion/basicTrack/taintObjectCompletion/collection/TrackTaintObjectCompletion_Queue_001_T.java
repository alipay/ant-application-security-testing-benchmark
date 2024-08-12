package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintObjectCompletion.collection;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.cases.bean.SoureWithQueueBean;

import lombok.SneakyThrows;

/**
* Introduction X
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点对象完整度 -> 集合(集合对象全为污点) -> Queue元素
// compose = TrackTaintObjectCompletion_Queue_001_T.java
// bind_url = /case007
// assession information end
public class TrackTaintObjectCompletion_Queue_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * 引用类型queue 作为污点对象
     *
     * @param
     * @return
     */
    @PostMapping("case007")
    public Map<String, Object> aTaintCase007(@RequestBody SoureWithQueueBean queueBean) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(queueBean.getQueue().peek());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase007";
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
            "Queue元素",
            //
        };
    }

    @SneakyThrows
    @Override
    public Object requestBodyBean() {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.put("ls");
        queue.put("cd /");
        SoureWithQueueBean queueBean = new SoureWithQueueBean();
        queueBean.setKey("key");
        queueBean.setQueue(queue);
        return queueBean;
    }

}
