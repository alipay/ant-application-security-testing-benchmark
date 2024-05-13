package com.iast.astbenchmark.cases.engine.taint.basic.completion.object.source;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.cases.bean.xml.TicketRequest;
import com.iast.astbenchmark.common.CommonConsts;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class FromHttpBody_xml {

    /**
     * 污点来自http body  *xml  contentye==xml
     *
     * @param /Users/curry/IdeaProjects/astbenchmark/src/main/resources/data/case0034.xml
     * @return
     */
    @PostMapping(value = "/case0034", consumes = {MediaType.APPLICATION_XML_VALUE}, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    @IastTestCase(
            caseNo ="aTaintCase0034",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->污点来源识别能力(source)->污点来自http body->xml/getInputStream",
            thisMethodTag = "aTaintCase0034",
            hasVul = true
    )
    public Map<String, Object> aTaintCase0034(@RequestBody TicketRequest ticketRequest) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(ticketRequest.getPhase());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
