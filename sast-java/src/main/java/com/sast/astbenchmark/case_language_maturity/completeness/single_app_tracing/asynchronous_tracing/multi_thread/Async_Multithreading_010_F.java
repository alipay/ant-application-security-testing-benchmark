package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.asynchronous_tracing.multi_thread;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多线程
// scene introduction = Synchronized
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/Async_Multithreading_010_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/asynchronous_tracing/multi_thread")
public class Async_Multithreading_010_F {
    private String a;

    @PostMapping("Async_Multithreading_010_F/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Async_Multithreading_010_F obj = new Async_Multithreading_010_F();
        obj.a = cmd;
        Thread thread1 = new Thread(() -> {
            synchronized (obj) {
                obj.a = "ls";
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
