package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.asynchronous_tracing.promise_callback_await;

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
 * Introduction 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语->CompletableFuture
 * Level 2
 * Date 2024-12-17
 */
// evaluation information start
// real case = true
// evaluation project = 完整度->单应用跟踪完整度->并发、多线程、异步->同步原语->CompletableFuture
// bind_url = completeness/asynchronous_tracing/promise_callback_await/CompletableFuture_003_T/{cmd}
// evaluation information end
@RestController
@RequestMapping("completeness/asynchronous_tracing/promise_callback_await")
public class CompletableFuture_003_T {
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);
    @PostMapping(value = "CompletableFuture_003_T/{cmd}")
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
                return result.get();
            }).thenAccept(notCleanedResult -> {
                // 最终操作
                try {
                    Runtime.getRuntime().exec(notCleanedResult);
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
