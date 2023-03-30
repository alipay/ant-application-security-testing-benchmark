package com.iast.astbenchmark.analyser.factory.stategy.openrasp;

import com.iast.astbenchmark.analyser.bean.BaseDetectedDataBean;
import com.iast.astbenchmark.analyser.factory.stategy.openrasp.OpenraspTaintItemBean;
import lombok.Data;

import java.util.List;

@Data
public class OpenraspDataBean extends BaseDetectedDataBean  {

    private List<OpenraspTaintItemBean> data;

    private  int page;
    private int total_page;
}
