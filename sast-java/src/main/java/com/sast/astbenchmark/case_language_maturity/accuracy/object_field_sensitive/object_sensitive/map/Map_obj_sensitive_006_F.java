package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.object_sensitive.map;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->对象敏感与域敏感->区分不同的类对象、结构体/联合体和字典/列表/数组->容器->Map-remove
 * Level 2
 * Date 2024-06-28
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感->区分不同的类对象、结构体/联合体和字典/列表/数组->容器->Map-remove
// bind_url = accuracy/object_field_sensitive/object_sensitive/map/Map_obj_sensitive_006_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/object_field_sensitive/object_sensitive/map")
public class Map_obj_sensitive_006_F {
    @PostMapping("Map_obj_sensitive_006_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> m = new HashMap<>();
            m.put("key1",cmd);
            m.put("key2","_");
            m.remove("key1");
            SinkUtil.sink(m);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}