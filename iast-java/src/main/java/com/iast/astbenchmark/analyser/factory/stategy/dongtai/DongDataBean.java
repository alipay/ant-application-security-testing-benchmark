package com.iast.astbenchmark.analyser.factory.stategy.dongtai;

import com.iast.astbenchmark.analyser.bean.BaseDetectedDataBean;
import lombok.Data;

import java.util.List;

@Data
public class DongDataBean extends BaseDetectedDataBean {
    private List<DongTaintItemBean> messages;
}
