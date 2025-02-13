package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.primitives;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型->long
 * Level 2
 * Date 2024-05-09
 * 注意，case中的 SinkUtil.sink 为自定义Sink，由于评测对基础类型无法直接找到sink点，因此设计出了一个自定义Sink。
 * 此评价项（evaluation item）的达成为"或"条件（参考compose），一个case是自定义case的形式，另一种case是通过String.valueof函数转化为sink点可以接受的入参类型，两个case达成一个即认为本评价项达成。具体要支持哪个可依据不同引擎的情况而定。
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型->Long
// bind_url = completeness/base/object/javaNative/Base_Long_004_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Long_004_F {
    @PostMapping("Base_Long_004_F/{cmd}")
    public Map<String, Object> aTaintCase0146(@PathVariable Long cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Long l = 123L;
        SinkUtil.sink(l);
        modelMap.put("status", "success");
        return modelMap;
    }
}
