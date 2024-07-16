package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.specialLinkTrackingCapabilities.dependencies;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

import cn.hutool.core.util.StrUtil;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintChainCompletion_HutoolStrUtil_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /** 污点链路完整度 */
    /**
     * 特殊链路跟踪能力->三方包方法跟踪
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0022")
    @IastTestCase(caseNo = "aTaintCase0022",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->特殊链路跟踪能力->三方包方法跟踪", thisMethodTag = "aTaintCase0022",
        hasVul = true)
    public Map<String, Object> aTaintCase0022(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(StrUtil.addPrefixIfNot(cmd, "pre"));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase0022";
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_准确度,
            //
            Category.LEVEL3_污点对象跟踪粒度,
            //
            Category.LEVEL4_字符串级别,
            //
            Category.LEVEL5_字符串部分存在污点,
            //
            "截取非污点部分后再拼接污点",
            //
            "String操作",
            //
            "concat"
            //
        };
    }

}
