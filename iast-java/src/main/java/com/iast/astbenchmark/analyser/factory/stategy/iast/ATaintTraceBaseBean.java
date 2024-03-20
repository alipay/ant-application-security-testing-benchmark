package com.iast.astbenchmark.analyser.factory.stategy.iast;

import java.util.List;

import com.iast.astbenchmark.analyser.bean.BaseDetectedDataBean;

import lombok.Data;
import lombok.ToString;

/**
 * 基础跟踪Bean
 */
@Data
@ToString
public class ATaintTraceBaseBean extends BaseDetectedDataBean {
    private String traceId;
    private String protocolType;
    private List<TaintFlow> taintFlow;
    private String networkTrafficHash;
    private String uuid;
    private String sinkStack;
    private String taintType;
    private String customMethod;
}
