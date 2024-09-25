package com.sast.astbenchmark.cases.completeness.async;

import com.sast.astbenchmark.model.custom.T;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 异步跟踪能力-多线程 Thread
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->异步跟踪能力->存储型异步->多线程->Thread
// compose = !Async_Multithreading_002_F.java && Async_Multithreading_001_T.java
// bind_url = completeness/async/Async_Multithreading_002_F/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/async")
public class Async_Multithreading_002_F {
    @PostMapping("Async_Multithreading_002_F/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        String a = "ls";
        T t = new T(a);
        t.start();
        return t.getMap();
    }
}
