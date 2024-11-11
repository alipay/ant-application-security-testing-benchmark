package com.sast.astbenchmark.case_language_maturity.accuracy.field_sensitive.map;

import com.sast.astbenchmark.model.XCmdObject;
import com.sast.astbenchmark.common.utils.HttpUtil;
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
// real case = false
// evaluation item = 准确度->域敏感->容器->MapPutGet-scene2
// bind_url = accuracy/field_sensitive/map/MapPutGet_004_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/field_sensitive/map")
public class MapPutGet_004_F {
    @PostMapping(value = "MapPutGet_004_F")
    public Map<String, Object> testcase(@RequestParam String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> paramMap = new HashMap<>();
            XCmdObject xCmdObject = new XCmdObject();
            paramMap.put("url1", url);
            paramMap.put("url2", "http://localhost");
            paramMap.put("method", "GET");
            xCmdObject.setModelMap(paramMap);
            HttpUtil.doGet(xCmdObject.getModelMap().get("url2"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

}
