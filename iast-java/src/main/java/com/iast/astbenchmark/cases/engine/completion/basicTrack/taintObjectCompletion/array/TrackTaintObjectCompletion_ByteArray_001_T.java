package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintObjectCompletion.array;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.analyser.cache.RequestPayloadConstant;

/**
* Introduction 数组 byte[] 作为污点对象
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 基础跟踪能力 -> 污点对象完整度 -> 数组(数组对象全为污点) -> 数组对象byte[]
// compose = TrackTaintObjectCompletion_ByteArray_001_T.java
// bind_url = /case0015
// assession information end
public class TrackTaintObjectCompletion_ByteArray_001_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * 数组 byte[] 作为污点对象 TODO 没有直接传入的sink,先用这个
     * 
     * @param cmd
     * @return
     */
    @PostMapping("case0015")
    public Map<String, Object> aTaintCase0015(@RequestBody byte[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(new String(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase0015";
    }

    @Override
    public String description() {
        return "数组 byte[] 作为污点对象";
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
            Category.LEVEL4_污点对象完整度,
            //
            Category.LEVEL5_数组_数组对象全为污点,
            //
            "数组对象byte[]",
            //
        };
    }

    @Override
    public byte[] requestBodyBytes() {
        return RequestPayloadConstant.DEFAULT_COMMAND_REQUEST_STRING_BODY.getBytes(StandardCharsets.UTF_8);
    }

}
