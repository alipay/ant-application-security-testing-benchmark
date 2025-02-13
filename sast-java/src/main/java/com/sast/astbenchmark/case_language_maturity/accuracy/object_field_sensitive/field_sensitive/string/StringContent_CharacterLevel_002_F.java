//package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.field_sensitive.string;
//
//import com.sast.astbenchmark.common.utils.HttpUtil;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Introduction 域敏感-字符串中字符-字符级敏感
// * Level 4
// * Date 2024-05-09
// */
//// evaluation information start
//// real case = false
//// evaluation item =  准确度->对象敏感与域敏感->域敏感->字符串中字符->字符级敏感
//// bind_url = accuracy/fieldSensitive/stringContent/StringContent_CharacterLevel_002_F
//// evaluation information end
//@RestController()
//@RequestMapping("accuracy/fieldSensitive/stringContent")
//public class StringContent_CharacterLevel_002_F {
//    @PostMapping(value = "StringContent_CharacterLevel_002_F")
//    public Map<String, Object> testcase(@RequestParam String param) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            String domain = "www.fixdomain.com/a";
//            String url = domain + param;
//            String not_taint = url.substring(0, 17);
//            HttpUtil.doGet(not_taint);
//            modelMap.put("status", "success");
//        } catch (Exception e) {
//            modelMap.put("status", "error");
//        }
//        return modelMap;
//    }
//}
