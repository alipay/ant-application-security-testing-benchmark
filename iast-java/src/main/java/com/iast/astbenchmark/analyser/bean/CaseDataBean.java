package com.iast.astbenchmark.analyser.bean;

import java.util.List;

import com.iast.astbenchmark.analyser.bean.consts.CaseTypeEnum;

import lombok.Data;

@Data
public class CaseDataBean {
    private String caseNo;
    private String caseDesc;
    private CaseTypeEnum caseTypeEnum;
    /**
     *
     * sinks propagators 也有同样的问题 ？ （原因： 对于iast以及seeker 对于source的描述不尽相同（如下），两者无法直接比较，或许应该考虑对source明确一个怎样的标准？ * { *
     * "methodName":"getParameter", * "className":"javax.servlet.ServletRequestWrapper", * "taintEventType":"sources", *
     * "sourceType":"http", * "taintValue":"jps", *
     * "classMethodSignature":"javax.servlet.ServletRequestWrapper#getParameter#java.lang.String" * }
     *
     * * "URL": "/ataint/case00112", * "SourceName": "cmd", * "SourceType": "Parameter", * "CodeLocation":
     * "com.iast.astbenchmark.cases.AstTaintCase002.aTaintCase00112():1553",
     */
    private String source;
    private List<String> sinks;
    private List<String> propagators;
}
