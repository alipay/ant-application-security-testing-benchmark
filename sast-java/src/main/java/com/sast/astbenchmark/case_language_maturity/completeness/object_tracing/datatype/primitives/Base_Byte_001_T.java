package com.sast.astbenchmark.case_language_maturity.completeness.object_tracing.datatype.primitives;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * Introduction 对象中的简单类型对象，byte作为污点
 * Level 2
 * Date 2024-05-09
 * 注意，case中的 SinkUtil.sink 为自定义Sink，由于评测对基础类型无法直接找到sink点，因此设计出了一个自定义Sink。
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->byte
// bind_url = completeness/object_tracing/datatype/primitives/Base_Byte_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/object_tracing/datatype/primitives")
public class Base_Byte_001_T {
    @GetMapping("Base_Byte_001_T/{cmd}")
    public Map<String, Object> aTaintCase0138(@PathVariable byte cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        SinkUtil.sink(cmd);
        modelMap.put("status", "success");
        return modelMap;
    }
}
