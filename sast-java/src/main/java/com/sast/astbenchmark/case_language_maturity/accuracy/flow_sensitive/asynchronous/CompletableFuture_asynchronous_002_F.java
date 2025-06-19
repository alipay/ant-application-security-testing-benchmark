package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.asynchronous;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->异步执行
// scene introduction = CompletableFuture
// level = 4
// bind_url = accuracy/flow_sensitive/asynchronous/CompletableFuture_asynchronous_002_F
// evaluation information end
@RestController
@RequestMapping("accuracy/flow_sensitive/asynchronous")
public class CompletableFuture_asynchronous_002_F {
    @PostMapping(value = "CompletableFuture_asynchronous_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            AtomicReference<String> result = new AtomicReference<>(cmd);
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                // 异步任务
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            future.thenRun(() -> {
                try {
                    result.set("clean");
                } catch (Exception e) {
                    modelMap.put("status", "error");
                }
            });
            future.join();
            Runtime.getRuntime().exec(result.get());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
