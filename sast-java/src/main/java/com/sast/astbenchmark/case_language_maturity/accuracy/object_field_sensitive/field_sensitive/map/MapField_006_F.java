package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.field_sensitive.map;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->对象敏感与域敏感->区分一维字典/列表/数组的不同元素->Map-keySet
 * Level 3
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item =  准确度->对象敏感与域敏感->区分一维字典/列表/数组的不同元素->Map-keySet
// bind_url = accuracy/object_field_sensitive/field_sensitive/map/MapField_006_F
// evaluation information end

@RestController()
@RequestMapping("accuracy/object_field_sensitive/field_sensitive/map")
public class MapField_006_F {
    @PostMapping(value = "MapField_006_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> map = new HashMap<>();
            map.put("key1","value1");
            SinkUtil.sink(map.keySet());
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
