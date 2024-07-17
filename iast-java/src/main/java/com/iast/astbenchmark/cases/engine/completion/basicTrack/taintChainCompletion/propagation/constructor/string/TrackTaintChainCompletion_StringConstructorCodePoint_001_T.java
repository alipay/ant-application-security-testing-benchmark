package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintChainCompletion.propagation.constructor.string;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

/**
* Introduction X
* Level X  
* Date 2024-07-16
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点链路完整度 -> 污点传播跟踪能力 -> 传播场景 -> String操作 -> 构造方法 -> String(int[] codePoints, int offset, int count)
// compose = 
// bind_url = /case00145
// assession information end
public class TrackTaintChainCompletion_StringConstructorCodePoint_001_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * @param codePoints
     * @return
     */
    @PostMapping(value = "case00145")
    @IastTestCase(caseNo = "aTaintCase00145",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点传播跟踪能力->传播场景->String操作->构造方法->String(int[] codePoints, int offset, int count)",
        thisMethodTag = "aTaintCase00145", hasVul = true)
    public Map<java.lang.String, Object> aTaintCase00145(@RequestBody int[] codePoints) {
        Map<java.lang.String, Object> modelMap = new HashMap<>();
        try {
            // String testCode = UnicodeUtil.toUnicode(cmd);
            // int[] codePoints = new int[cmd.length()];
            // int cpCount = cmd.codePointCount(0, cmd.length());
            // for(int index = 0; index < cpCount; ++index) {
            // //这里的i是字符的位置
            // int i = cmd.offsetByCodePoints(0, index);
            // int codepoint = cmd.codePointAt(i);
            // codePoints[i]=codepoint;
            // //108 105
            // }
            Runtime.getRuntime().exec(new java.lang.String(codePoints, 0, 2));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public java.lang.String[] category() {
        return new java.lang.String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_基础跟踪能力,
            //
            Category.LEVEL4_污点链路完整度,
            //
            Category.LEVEL5_污点传播跟踪能力,
            //
            Category.LEVEL6_传播场景,
            //
            Category.LEVEL7_String,
            //
            "构造方法",
            //
            "String(int[] codePoints, int offset, int count)",
            //
        };
    }

    @Override
    public java.lang.String caseNo() {
        return "aTaintCase00145";
    }

}
