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
// real vulnerability = false
// assession project = 准确度->域敏感->字符串中字符->字符级敏感
// compose = !StringContent_CharacterLevel_002_F.java && StringContent_CharacterLevel_001_T.java
// bind_url = accuracy/fieldSensitive/stringContent/StringContent_CharacterLevel_002_F
// assession information end
@RestController()
@RequestMapping("accuracy/fieldSensitive/stringContent")
public class StringContent_CharacterLevel_002_F {
    @PostMapping(value = "StringContent_CharacterLevel_002_F")
    public Map<String, Object> testcase(@RequestParam String param) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String domain = "www.fixdomain.com/a";
            String url = domain + param;
            String not_taint = url.substring(0, 17);
            HttpUtil.doGet(not_taint);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
