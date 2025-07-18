package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.field_sensitive_muilt_collection;

import com.sast.astbenchmark.common.utils.HttpUtil;
import com.sast.astbenchmark.model.XCmdObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item =  准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
// scene introduction = MapPutGet-scene2
// level = 4
// bind_url = accuracy/object_field_sensitive/field_sensitive_muilt_collection/MapPutGet_003_T
// evaluation information end

@RestController()
@RequestMapping("accuracy/object_field_sensitive/field_sensitive/map")
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
