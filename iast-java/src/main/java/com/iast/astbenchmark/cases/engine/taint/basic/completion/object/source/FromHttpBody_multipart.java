package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.source;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class FromHttpBody_multipart {

    /**
     * 污点来自http body  multipart/form-data Part
     *
     * @param
     * @return
     */
    @PostMapping(value = "case0035")
    @IastTestCase(
            caseNo ="aTaintCase0035",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点来源识别能力(source)->污点来自http body->multipart/form-data->getPart",
            thisMethodTag = "aTaintCase0035",
            hasVul = true
    )
    public Map<String, Object> aTaintCase0035(@RequestParam MultipartFile file, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //            String cmd = request.getPart("file").getSubmittedFileName();
            Runtime.getRuntime().exec(request.getPart("file").getSubmittedFileName());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        return modelMap;
    }

}
