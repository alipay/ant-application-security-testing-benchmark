package com.sast.astbenchmark.case_language_maturity.completeness.asynchronous_tracing.multi_thread;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Introduction 异步跟踪能力-多线程 Synchronized
 * Level 3
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->异步跟踪能力->存储型异步->多线程->Synchronized
// compose = !Async_Multithreading_010_F.java && Async_Multithreading_009_T.java
// bind_url = completeness/async/Async_Multithreading_009_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/async")
public class Async_Multithreading_009_T {
    private String a;
    @PostMapping("Async_Multithreading_009_T/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Async_Multithreading_009_T obj = new Async_Multithreading_009_T();
        
        Thread thread1 = new Thread(() -> {
            synchronized (obj) {
                obj.a = cmd;
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (obj) {
                try {
                    Runtime.getRuntime().exec(obj.a);
                    modelMap.put("status", "success");
                } catch (IOException e) {
                    modelMap.put("status", "error");
                }
            }
        });

        thread1.start();
        thread2.start();

        return modelMap;
    }
}
