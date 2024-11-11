package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.references_pointers.alias;

import com.sast.astbenchmark.model.alias.PrimitiveData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度-链路跟踪完整度-引用和指针-别名-PrimitiveFieldAccess-scene2
 * Level 3
 * Date 2024-07-02
 */
@RestController
// evaluation information start
// real case = true
// evaluation item =  完整度->链路跟踪完整度->引用和指针->别名->对象属性->对象指针->PrimitiveFieldAccess-scene2
// bind_url = completeness/chain_tracing/references_pointers/alias/PrimitiveFieldAccess_003_T
// evaluation information end
@RequestMapping("completeness/chain_tracing/references_pointers/alias")
public class PrimitiveFieldAccess_003_T {
    @PostMapping(value = "PrimitiveFieldAccess_003_T")
    public Map<String, Object> testcase(@RequestParam int id) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            id = id + 99;
            PrimitiveData i = new PrimitiveData();
            PrimitiveData b = i;
            b.setIntData(id);
            Runtime.getRuntime().exec("cat /some/path/" + i.intData + ".png");
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}