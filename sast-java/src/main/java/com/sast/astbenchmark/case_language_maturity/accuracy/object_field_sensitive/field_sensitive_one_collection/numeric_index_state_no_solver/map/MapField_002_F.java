package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.field_sensitive_one_collection.numeric_index_state_no_solver.map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)->Map-Field
 * Level 3
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item =  准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// bind_url = accuracy/object_field_sensitive/field_sensitive_one_collection/numeric_index_state_no_solver/map/MapField_002_F
// evaluation information end

@RestController()
@RequestMapping("accuracy/object_field_sensitive/field_sensitive_one_collection/numeric_index_state_no_solver/map")
public class MapField_002_F {
    @PostMapping(value = "MapField_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> map = new HashMap<>();
            map.put("key1","_");
            Runtime.getRuntime().exec(map.get("key1"));
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
