package com.iast.astbenchmark.analyser.factory.stategy.iast;

import com.iast.astbenchmark.analyser.bean.BaseOriginalDataBean;
import lombok.Data;

@Data
public class IastSlsBaseMessageBean extends BaseOriginalDataBean {
    private String hostName;
    private String appName;
    private ATaintTraceBaseBean detail;
    private String env;
    private String type;
    private String url;
    private String logDateTime;
}
