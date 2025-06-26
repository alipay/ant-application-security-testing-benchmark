package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.asynchronous_tracing.multi_thread;

import com.sast.astbenchmark.model.custom.TT;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Timer;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->并发、多线程、异步->多线程
// scene introduction = TimerTask
// level = 2
// bind_url = completeness/single_app_tracing/asynchronous_tracing/multi_thread/Async_Multithreading_007_T/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/asynchronous_tracing/multi_thread")
public class Async_Multithreading_007_T {
    @PostMapping("Async_Multithreading_007_T/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Timer timer = new Timer();
        TT tt = new TT(cmd);
        timer.schedule(tt, 2000);
        return tt.getMap();
    }
}
