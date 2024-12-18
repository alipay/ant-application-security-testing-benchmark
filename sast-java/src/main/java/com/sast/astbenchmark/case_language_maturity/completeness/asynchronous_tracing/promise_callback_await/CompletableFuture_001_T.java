package com.sast.astbenchmark.case_language_maturity.completeness.asynchronous_tracing.promise_callback_await;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Introduction 异步跟踪完整度-promise_callback_await-CompletableFuture
 * Level 2
 * Date 2024-12-17
 */
// evaluation information start
// real case = true
// evaluation project = 完整度->异步跟踪完整度->promise_callback_await->CompletableFuture
// bind_url = completeness/asynchronous_tracing/promise_callback_await/CompletableFuture_001_T/{cmd}
// evaluation information end
@RestController
@RequestMapping("completeness/asynchronous_tracing/promise_callback_await")
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
