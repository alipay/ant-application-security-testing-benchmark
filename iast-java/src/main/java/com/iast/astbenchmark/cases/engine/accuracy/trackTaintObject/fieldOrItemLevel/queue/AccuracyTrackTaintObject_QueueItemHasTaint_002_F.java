package com.iast.astbenchmark.cases.engine.accuracy.trackTaintObject.fieldOrItemLevel.queue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.cases.bean.SoureWithQueueBean;
import com.iast.astbenchmark.common.CommonConsts;

import lombok.SneakyThrows;

/**
 * @author CC11001100
 */
@RestController
public class AccuracyTrackTaintObject_QueueItemHasTaint_002_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00135/2")
    public Map<String, Object> aTaintCase00135_2(@RequestBody SoureWithQueueBean queueBean) {
        Map<String, Object> modelMap = new HashMap<>();
        Queue<String> queue = queueBean.getQueue();
        queue.add("cd /");
        queue.add("cd ~");
        queue.stream().forEach(e -> {
            if ("cd /".equals(e)) {
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
    public Boolean hasVul() {
        return false;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new AccuracyTrackTaintObject_QueueItemHasTaint_001_T();
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
