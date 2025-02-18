package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.asynchronous_tracing.multi_thread;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 完整度->单应用跟踪完整度->并发、多线程、异步->多线程->Runnable
 * Level 2
 * Date 2024-07-31
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多线程
// bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/Async_Multithreading_003_T/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/asynchronous_tracing/multi_thread")
public class Async_Multithreading_003_T {
    @PostMapping("Async_Multithreading_003_T/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Runtime.getRuntime().exec(cmd);
                    modelMap.put("status", "success");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    modelMap.put("status", "success");
                }
            }
        }).start();
        return modelMap;
    }
}
