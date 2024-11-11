package com.sast.astbenchmark.case_language_maturity.completeness.asynchronous_tracing.multi_thread;

import com.sast.astbenchmark.model.custom.T;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 异步跟踪能力-多线程 Thread
 * Level 3
 * Date 2024-07-31
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->异步跟踪能力->存储型异步->多线程->Thread
// bind_url = completeness/asynchronous_tracing/multi_thread/Async_Multithreading_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/asynchronous_tracing/multi_thread")
public class Async_Multithreading_002_F {
    @PostMapping("Async_Multithreading_002_F/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        String a = "ls";
        T t = new T(a);
        t.start();
        return t.getMap();
    }
}
