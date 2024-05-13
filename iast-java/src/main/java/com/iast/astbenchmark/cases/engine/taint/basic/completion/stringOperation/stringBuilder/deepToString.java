package com.iast.astbenchmark.cases.engine.taint.basic.completion.stringOperation.stringBuilder;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.MyCommonTestUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class deepToString {

    /**
     * aTaintCase0095 传播场景-char[],byte[]操作->deepToString
     * //deepToString  的参数是Object[] *使用Byte[]
     */
    @PostMapping(value = "case0095")
    @IastTestCase(
            caseNo ="aTaintCase0095",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点传播跟踪能力->传播场景->char[],byte[]操作->deepToString",
            thisMethodTag = "aTaintCase0095",
            hasVul = true
    )
    public Map<String, Object> aTaintCase0095(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Byte[] bytes = MyCommonTestUtil.toObjects(cmd.getBytes());
            String str2 = Arrays.deepToString(bytes);
            Runtime.getRuntime().exec(str2);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
