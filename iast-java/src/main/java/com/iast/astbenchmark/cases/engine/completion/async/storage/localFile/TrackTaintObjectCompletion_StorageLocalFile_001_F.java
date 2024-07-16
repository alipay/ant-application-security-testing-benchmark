package com.iast.astbenchmark.cases.engine.completion.async.storage.localFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

import cn.hutool.core.io.FileUtil;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintObjectCompletion_StorageLocalFile_001_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    /**
     * aTaintCase00116 异步跟踪能力->存储行异->污点通过文件存储后触发->本地文件
     */
    public static String filePath = "";

    @PostMapping(value = "case00116")
    public Map<String, Object> aTaintCase00116(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        File file = FileUtil.createTempFile(FileUtil.getTmpDir());
        filePath = file.getAbsolutePath();
        FileUtil.writeString(cmd, file, "utf-8");
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @Override
    public Boolean hasVul() {
        return false;
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_异步跟踪能力,
            //
            "存储型异步",
            //
            "污点通过文件存储后触发",
            //
            "本地文件",
            //
        };
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00116";
    }

}
