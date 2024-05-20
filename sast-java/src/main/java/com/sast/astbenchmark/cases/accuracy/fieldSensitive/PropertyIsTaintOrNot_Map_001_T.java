package com.sast.astbenchmark.cases.accuracy.fieldSensitive;

import com.sast.astbenchmark.common.utils.HttpUtil;
import com.sast.astbenchmark.common.utils.CmdUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感->对象部分属性为污点
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->对象部分属性为污点->Map
// compose = !PropertyIsTaintOrNot_Map_002_F.java && PropertyIsTaintOrNot_Map_001_T.java
// bind_url = accuracy/fieldSensitive/PropertyIsTaintOrNot_Map_001_T
// assession information end

@RestController()
@RequestMapping("accuracy/fieldSensitive")
public class PropertyIsTaintOrNot_Map_001_T {
    @PostMapping(value = "PropertyIsTaintOrNot_Map_001_T")
    public Map<String, Object> aTaintCase024(@RequestParam String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("url", url);
            paramMap.put("method", "GET");
            HttpUtil.doGet(paramMap.get("url"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
