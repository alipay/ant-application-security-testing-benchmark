package com.alipay.xast.score.util;

public enum XastSupoortToolType {
    SAST("sast"),
    IAST("iast"),
    DAST("dast");

    public String type;

    XastSupoortToolType(String type) {
        this.type=type;
    }

    public String getType(){
        return type;
    }

    public static boolean isSupportToolType(String type){
        for (XastSupoortToolType xastSupoortToolType : XastSupoortToolType.values()){
            if (xastSupoortToolType.getType().equals(type)){
                return true;
            }
        }
        return false;
    }
}
