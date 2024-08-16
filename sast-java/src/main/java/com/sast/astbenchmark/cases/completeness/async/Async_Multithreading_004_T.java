package com.sast.astbenchmark.cases.completeness.async;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Introduction 异步跟踪能力-多线程 TimerTask
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->异步跟踪能力->存储型异步->多线程->TimerTask
// compose = Async_Multithreading_004_T.java
// bind_url = completeness/async/Async_Multithreading_004_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/async")
public class Async_Multithreading_004_T {
    @PostMapping("Async_Multithreading_004_T/{cmd}")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Timer timer = new Timer();
        TT tt = new TT();  
		timer.schedule(tt, 2000);
        return tt.getMap(); 
    }
}

class TT extends TimerTask {
    private String cmd;
    Map<String, Object> modelMap = new HashMap<>();
    @Override
    public void run() {
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
    }
    public Map<String, Object> getMap(){
        return modelMap;
    }
}

