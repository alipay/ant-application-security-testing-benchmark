package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.object_sensitive;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同的类对象、结构体/联合体和字典/列表/数组
// scene introduction = 容器->Map-replace
// level = 2
// bind_url = accuracy/object_field_sensitive/object_sensitive/Map_obj_sensitive_004_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/object_field_sensitive/object_sensitive")
public class Map_obj_sensitive_004_F {
    @PostMapping("Map_obj_sensitive_004_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> m = new HashMap<>();
            m.put("key1", cmd);
            m.replace("key1", "_");
            Runtime.getRuntime().exec(m.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}