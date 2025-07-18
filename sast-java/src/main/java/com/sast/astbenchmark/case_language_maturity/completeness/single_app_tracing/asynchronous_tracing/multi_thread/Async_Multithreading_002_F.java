package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.asynchronous_tracing.multi_thread;

import com.sast.astbenchmark.model.custom.T;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多线程
// scene introduction = Thread
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/Async_Multithreading_002_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/asynchronous_tracing/multi_thread")
public class Async_Multithreading_002_F {
    @PostMapping("Async_Multithreading_002_F/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        String a = "ls";
        T t = new T(a);
        t.start();
        return t.getMap();
    }
}
