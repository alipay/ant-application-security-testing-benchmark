package com.sast.astbenchmark.cases.accuracy.fieldSensitive;

import com.sast.astbenchmark.common.utils.HttpUtil;
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
// bind_url = completeness/base/chain/astTaint/PropertyIsTaintOrNot_Map_002_F
// assession information end

@RestController()
@RequestMapping("accuracy/fieldSensitive/propertyIsTaintOrNot")
public class PropertyIsTaintOrNot_Map_002_F {
    @PostMapping(value = "PropertyIsTaintOrNot_Map_002_F")
    public Map<String, Object> aTaintCase024_2(@RequestParam String url) {
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
