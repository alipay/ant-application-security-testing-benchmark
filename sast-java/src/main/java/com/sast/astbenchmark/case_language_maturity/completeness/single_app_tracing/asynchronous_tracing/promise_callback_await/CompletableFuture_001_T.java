package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.asynchronous_tracing.promise_callback_await;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Introduction 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语->CompletableFuture
 * Level 2
 * Date 2024-12-17
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语
// bind_url = completeness/single_app_tracing/asynchronous_tracing/promise_callback_await/CompletableFuture_001_T/{cmd}
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/asynchronous_tracing/promise_callback_await")
public class CompletableFuture_001_T {
    @PostMapping(value = "CompletableFuture_001_T/{cmd}")
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
            Runtime.getRuntime().exec(result.get());
            future.join();
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
