package com.iast.astbenchmark.cases.engine.completion.async.storage.localFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.MyCommonTestUtil;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintObjectCompletion_StorageLocalFile_002_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping(value = "case00116/1")
    @IastTestCase(caseNo = "aTaintCase00116",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力->存储型异步->污点通过文件存储后触发->本地文件", thisMethodTag = "aTaintCase00116_1",
        hasVul = true)
    public Map<String, Object> aTaintCase00116_1() {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String readexec =
                MyCommonTestUtil.readStrFromFile(TrackTaintObjectCompletion_StorageLocalFile_001_F.filePath);
            Runtime.getRuntime().exec(readexec);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @Override
    public Boolean hasVul() {
        return true;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new TrackTaintObjectCompletion_StorageLocalFile_001_F();
    }

}
