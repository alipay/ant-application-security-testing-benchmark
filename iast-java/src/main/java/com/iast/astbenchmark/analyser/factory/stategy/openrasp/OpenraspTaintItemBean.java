package com.iast.astbenchmark.analyser.factory.stategy.openrasp;

import com.iast.astbenchmark.analyser.bean.BaseOriginalDataBean;
import lombok.Data;

@Data
public class OpenraspTaintItemBean extends BaseOriginalDataBean {
    private String id;
    private String url;
    private String path;
    private String request_method;
    private String attack_type;

}
