package com.iast.astbenchmark.analyser.factory.stategy.dongtai;

import java.util.List;

import com.iast.astbenchmark.analyser.bean.BaseOriginalDataBean;

import lombok.Data;

@Data
public class DongTaintItemBean extends BaseOriginalDataBean {
    private long id;
    private String uri;
    private String http_method;
    private String top_stack;
    private String bottom_stack;
    private int level_id;
    private String taint_position;
    private int status_id;
    private long first_time;
    private long latest_time;
    private String strategy__vul_name;
    private String agent__language;
    private String agent__project_name;
    private String agent__server__container;
    private int agent__bind_project_id;
    private int strategy_id;
    private String level_name;
    private String server_type;
    private boolean is_header_vul;
    private List<String> header_vul_urls;
    private String status__name;
}
