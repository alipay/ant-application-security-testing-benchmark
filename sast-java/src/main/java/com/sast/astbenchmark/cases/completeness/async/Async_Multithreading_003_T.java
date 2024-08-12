package com.sast.astbenchmark.cases.completeness.async;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 异步跟踪能力-多线程 Runnable
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->异步跟踪能力->存储型异步->多线程->Runnable
// compose = Async_Multithreading_003_T.java
// bind_url = completeness/async/Async_Multithreading_003_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/async")
public class Async_Multithreading_003_T {
    @PostMapping("Async_Multithreading_003_T/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        R r = new R(cmd);
        Executors.newCachedThreadPool().execute(r);
        return r.getMap();
    }
}


class R implements Runnable {
    private final String cmd;
    Map<String, Object> modelMap = new HashMap<>();
    public R(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public void run() {
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            modelMap.put("status", "error");
        }
    }
    public Map<String, Object> getMap(){
        return modelMap;
    }
}

