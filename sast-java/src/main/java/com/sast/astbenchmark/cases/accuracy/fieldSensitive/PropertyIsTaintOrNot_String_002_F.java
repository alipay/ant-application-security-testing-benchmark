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
// assession project = 准确度->域敏感->对象部分属性为污点->字符串
// compose = !PropertyIsTaintOrNot_String_002_F.java && PropertyIsTaintOrNot_String_001_T.java
// bind_url = completeness/base/chain/astTaint/PropertyIsTaintOrNot_String_002_F
// assession information end
@RestController()
@RequestMapping("accuracy/fieldSensitive/propertyIsTaintOrNot")
public class PropertyIsTaintOrNot_String_002_F {
    @PostMapping(value = "PropertyIsTaintOrNot_String_002_F-2")
    public Map<String, Object> aTaintCase023_2(@RequestParam String path) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            HttpUtil.doGet("https://www.test.com/api"+path);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
