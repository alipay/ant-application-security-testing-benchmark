package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.asynchronous;

import com.sast.astbenchmark.model.custom.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.Executors;

/**
 * Introduction 异步跟踪能力-多线程 Executors
 * Level 4
 * Date 2024-07-31
 */
// evaluation information start
// real case = false
// evaluation project = 准确度->流敏感->异步->存储型异步->多线程->Executors
// bind_url = accuracy/flow_sensitive/asynchronous/Async_Multithreading_006_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("accuracy/flow_sensitive/asynchronous")
public class Async_Multithreading_006_F {
    @PostMapping("Async_Multithreading_006_F/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        String a = "ls";
        R r = new R(a);
        Executors.newCachedThreadPool().execute(r);
        return r.getMap();
    }
}
