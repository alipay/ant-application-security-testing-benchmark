package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.collections;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->集合->List-set
 * Level 2
 * Date 2024-08-16
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
// bind_url = completeness/single_app_tracing/datatype/collections/Base_List_007_T
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/collections")
public class Base_List_007_T {
    @PostMapping("Base_List_007_T")
    public Map<String, Object> testcase(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add(cmd);
        list.add("_");
        list.set(1, "a");
        SinkUtil.sink(list);
        modelMap.put("status", "success");
        return modelMap;
    }
}
