package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.taintFromWhere.source;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintChainCompletion_FromGetCookies_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * 污点来自http headers getCookies
     *
     * @param
     * @return
     */
    @PostMapping(value = "case0045")
    @IastTestCase(caseNo = "aTaintCase0045",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点来源识别能力(source)->污点来自http header->getCookies",
        thisMethodTag = "aTaintCase0045", hasVul = true)

    public Map<String, Object> aTaintCase0045(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Cookie[] cookies = request.getCookies();
            Runtime.getRuntime().exec(String.valueOf(cookies[0].getName()));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_基础跟踪能力,
            //
            Category.LEVEL4_污点链路完整度,
            //
            Category.LEVEL5_污点来源识别能力,
            //
            "污点来自http header",
            //
            "getCookies",
            //
        };
    }

}
