package com.sast.astbenchmark.cases.accuracy.fieldSensitive.stringContent;

import com.sast.astbenchmark.common.utils.HttpUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-字符串中字符-字符级敏感
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->字符串中字符->字符级敏感
// compose = !StringContent_CharacterLevel_002_F.java && StringContent_CharacterLevel_001_T.java
// bind_url = accuracy/fieldSensitive/stringContent/StringContent_CharacterLevel_001_T
// assession information end
@RestController()
@RequestMapping("accuracy/fieldSensitive/stringContent")
public class StringContent_CharacterLevel_001_T {
    @PostMapping(value = "StringContent_CharacterLevel_001_T")
    public Map<String, Object> testcase(@RequestParam String domain) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String param = "?a=1";
            HttpUtil.doGet(domain + param);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
