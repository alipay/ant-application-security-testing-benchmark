package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.source;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class GetReader {

    /**
     * 污点来自http body  getReader
     * 测试用例创建文件
     *
     * @param /Users/curry/IdeaProjects/astbenchmark/src/main/resources/data/ls
     * @return
     */
    @PostMapping(value = "case0041")
    @IastTestCase(
            caseNo ="aTaintCase0041",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点来源识别能力(source)->污点来自http body->getReader",
            thisMethodTag = "aTaintCase0041",
            hasVul = true
    )
    public Map<String, Object> aTaintCase0041(@RequestBody String[] cmd, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            int len = request.getContentLength();
            // ServletInputStream in = request.getInputStream();
            String cmdStr = "";
            if (len != -1) {
                BufferedReader reader = request.getReader();
                String str;
                while ((str = reader.readLine()) != null) {
                    cmdStr += str;
                }
                reader.close();
            }
            Runtime.getRuntime().exec(cmdStr);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
