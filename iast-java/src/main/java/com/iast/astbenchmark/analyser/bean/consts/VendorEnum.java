package com.iast.astbenchmark.analyser.bean.consts;

/**
 * 用于表示不同的IAST厂商
 */
public enum VendorEnum {

    /**
     * 这是哪家...
     */
    IAST("IAST", "IAST"),

    /**
     * synopsys
     */
    SEEKER("SEEKER", "SEEKER"),

    /**
     * 洞态
     */
    DONGTAI("DONGTAI", "DONGTAI"),

    /**
     * 百度的OpenRASP
     */
    OPENRASP("OPENRASP", "OPENRASP"),

    /**
     * 悬镜
     */
    XMIRROR("XMIRROR", "XMIRROR");

    // TODO 2024-07-11 15:09:00 没有蚂蚁的AntIAST嘛？

    private String code;

    private String description;

    VendorEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    // 枚举值应该是只读的，不允许外界修改
//    public void setCode(String code) {
//        this.code = code;
//    }

    public String getDescription() {
        return description;
    }

//    public void setDescription(String description) {
//        this.description = description;
//    }

}
