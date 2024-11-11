package com.sast.astbenchmark.case_language_maturity.accuracy.object_sensitive.map;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-Map-replace
 * Level 3
 * Date 2024-06-28
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感->容器->Map-replace
// bind_url = accuracy/objectSensitive/baseCollection/Map_obj_sensitive_004_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/objectSensitive/baseCollection")
public class Map_obj_sensitive_004_F {
    @PostMapping("Map_obj_sensitive_004_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> m = new HashMap<>();
            m.put("key1",cmd);
            m.replace("key1","_");
            SinkUtil.sink(m);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}