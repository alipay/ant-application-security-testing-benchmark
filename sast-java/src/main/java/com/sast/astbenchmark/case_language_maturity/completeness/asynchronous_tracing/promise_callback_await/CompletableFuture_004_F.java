package com.sast.astbenchmark.case_language_maturity.completeness.asynchronous_tracing.promise_callback_await;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Introduction 异步跟踪完整度-promise_callback_await-CompletableFuture
 * Level 3
 * Date 2024-12-17
 */
// evaluation information start
// real case = true
// evaluation project = 完整度->异步跟踪完整度->promise_callback_await->CompletableFuture
// bind_url = completeness/asynchronous_tracing/promise_callback_await/CompletableFuture_004_F/{cmd}
// evaluation information end
@RestController
@RequestMapping("completeness/asynchronous_tracing/promise_callback_await")
public class CompletableFuture_004_F {
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);
    @PostMapping(value = "CompletableFuture_004_F/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        AtomicReference<String> result = new AtomicReference<>(cmd);
        try {
            // 使用CompletableFuture进行异步任务
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                // 异步任务开始
                try {
                    Thread.sleep(2000); // 模拟长时间任务
                    return "Task completed with input: " + result.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, executorService).thenApply(output -> {
                // 处理结果
                result.set("clean");
                return result.get();
            }).thenAccept(cleanedResult -> {
                // 最终操作
                try {
                    Runtime.getRuntime().exec(cleanedResult);
                } catch (Exception e) {
                    modelMap.put("status", "error");
                }
            });

            // 等待链式调用完成
            future.join();
            modelMap.put("status", "success");

        } catch (Exception e) {
            modelMap.put("status", "error");
        } finally {
            executorService.shutdown();
        }
        return modelMap;
    }
}
