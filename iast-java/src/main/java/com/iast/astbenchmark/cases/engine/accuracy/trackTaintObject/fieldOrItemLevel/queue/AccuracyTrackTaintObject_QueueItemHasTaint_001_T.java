package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.queue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.cases.bean.SoureWithQueueBean;
import com.iast.astbenchmark.common.CommonConsts;

import lombok.SneakyThrows;

/**
 * @author CC11001100
 */
@RestController
public class AccuracyTrackTaintObject_QueueItemHasTaint_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00135 污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Queue中部分元素为污点
     */
    @PostMapping(value = "case00135")
    public Map<String, Object> aTaintCase00135(@RequestBody SoureWithQueueBean queueBean) {
        Map<String, Object> modelMap = new HashMap<>();
        Queue<String> queue = queueBean.getQueue();
        queue.add("cd /");
        queue.add("cd ~");
        queue.stream().forEach(e -> {
            if ("ls".equals(e)) {
                try {
                    Runtime.getRuntime().exec(e);
                } catch (IOException ex) {
                }
            }
        });

        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00135";
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
            Category.LEVEL4_字段_元素级别,
            //
            "部分集合元素为污点",
            //
            "Queue中部分元素为污点",
            //
        };
    }

    @SneakyThrows
    @Override
    public Object requestBodyBean() {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.put("ls");
        SoureWithQueueBean queueBean = new SoureWithQueueBean();
        queueBean.setKey("key");
        queueBean.setQueue(queue);
        return queueBean;
    }

}
