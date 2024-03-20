package com.iast.astbenchmark.analyser.factory.stategy.seeker;

import com.iast.astbenchmark.analyser.bean.BaseDetectedDataBean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SeekerTaintTraceBaseBean extends BaseDetectedDataBean {
    private String URL;
    private String SourceName;
    private String SourceType;
    private String CodeLocation;
}
