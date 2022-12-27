package com.iast.astbenchmark.analyser.bean.consts;

public enum VendorEnum {
    IAST("IAST","IAST"),
    SEEKER("SEEKER","SEEKER"),
    DONGTAI("DONGTAI","DONGTAI")
    ;
    private String code;
    private String description;
    VendorEnum(String code,String description){
        this.code =code;
        this.description=description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
