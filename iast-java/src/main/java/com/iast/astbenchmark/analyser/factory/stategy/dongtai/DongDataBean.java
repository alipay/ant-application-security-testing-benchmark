package com.iast.astbenchmark.analyser.factory.stategy.dongtai;

import java.util.List;

import com.iast.astbenchmark.analyser.bean.BaseDetectedDataBean;

import lombok.Data;

@Data
public class DongDataBean extends BaseDetectedDataBean {
    private List<DongTaintItemBean> messages;
}
