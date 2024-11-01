package com.sast.astbenchmark.case_language_maturity.accuracy.field_sensitive.map;

import com.sast.astbenchmark.common.utils.HttpUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-MapPutGet-scene1
 * Level 3
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->域敏感->容器->MapPutGet-scene1
// compose = !MapPutGet_002_F.java && MapPutGet_001_T.java
// bind_url = accuracy/fieldSensitive/baseCollection/MapPutGet_002_F
// assession information end

@RestController()
@RequestMapping("accuracy/fieldSensitive/baseCollection")
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
