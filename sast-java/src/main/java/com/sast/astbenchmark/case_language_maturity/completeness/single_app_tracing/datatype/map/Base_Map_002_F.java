package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.map;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->字典->Map
 * Level 2
 * Date 2024-05-09
 * 注意，case中的 SinkUtil.sink 为自定义Sink，由于评测对基础类型无法直接找到sink点，因此设计出了一个自定义Sink。
 * 此评价项（evaluation item）的达成为"或"条件（参考compose），一个case是自定义case的形式，另一种case是通过String.valueof函数转化为sink点可以接受的入参类型，两个case达成一个即认为本评价项达成。具体要支持哪个可依据不同引擎的情况而定。
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典->Map
// bind_url = completeness/single_app_tracing/datatype/map/Base_Map_002_F
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/map")
public class Base_Map_002_F {
    @PostMapping("Base_Map_002_F")
    public Map<String, Object> aTaintCase0140(@RequestBody Map<String, String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        map.put("key1", "_");
        SinkUtil.sink(map);
        modelMap.put("status", "success");
        return modelMap;
    }
}
