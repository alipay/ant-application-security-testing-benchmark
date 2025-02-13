package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.asynchronous_tracing.multi_thread;

import com.sast.astbenchmark.model.custom.R;
import java.util.Map;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 完整度->单应用跟踪完整度->并发、多线程、异步->多线程->Executors
 * Level 2
 * Date 2024-07-31
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多线程->Executors
// bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/Async_Multithreading_006_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/asynchronous_tracing/multi_thread")
public class Async_Multithreading_006_F {
    @PostMapping("Async_Multithreading_006_F/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        String a = "ls";
        R r = new R(a);
        Executors.newCachedThreadPool().execute(r);
        return r.getMap();
    }
}
