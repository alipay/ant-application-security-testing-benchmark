package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.field_sensitive.map;

import com.sast.astbenchmark.common.utils.HttpUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->对象敏感与域敏感->区分一维字典/列表/数组的不同元素->MapPutGet-scene1
 * Level 3
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item =  准确度->对象敏感与域敏感->区分一维字典/列表/数组的不同元素->MapPutGet-scene1
// bind_url = accuracy/object_field_sensitive/field_sensitive/map/MapPutGet_002_F
// evaluation information end

@RestController()
@RequestMapping("accuracy/object_field_sensitive/field_sensitive/map")
public class MapPutGet_002_F {
    @PostMapping(value = "MapPutGet_002_F")
    public Map<String, Object> testcase(@RequestParam String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("url", url);
            paramMap.put("method", "GET");
            HttpUtil.doGet(paramMap.get("method"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
