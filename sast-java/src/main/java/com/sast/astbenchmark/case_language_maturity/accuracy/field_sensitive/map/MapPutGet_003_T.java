package com.sast.astbenchmark.case_language_maturity.accuracy.field_sensitive.map;

import com.sast.astbenchmark.common.utils.HttpUtil;
import com.sast.astbenchmark.model.XCmdObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-MapPutGet-scene2
 * Level 3
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->域敏感->容器->MapPutGet-scene2
// bind_url = accuracy/fieldSensitive/baseCollection/MapPutGet_003_T
// evaluation information end

@RestController()
@RequestMapping("accuracy/fieldSensitive/baseCollection")
public class MapPutGet_003_T {
    @PostMapping(value = "MapPutGet_003_T")
    public Map<String, Object> testcase(@RequestParam String url) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            Map<String, String> paramMap = new HashMap<>();
            XCmdObject xCmdObject = new XCmdObject();
            paramMap.put("url1", url);
            paramMap.put("url2", "http://localhost");
            paramMap.put("method", "GET");
            xCmdObject.setModelMap(paramMap);
            HttpUtil.doGet(xCmdObject.getModelMap().get("url1"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
