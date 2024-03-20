package com.iast.astbenchmark.analyser.factory.stategy.openrasp;

import java.util.List;

import com.iast.astbenchmark.analyser.bean.BaseDetectedDataBean;

import lombok.Data;

@Data
public class OpenraspDataBean extends BaseDetectedDataBean {

    private List<OpenraspTaintItemBean> data;

    private int page;
    private int total_page;
}
