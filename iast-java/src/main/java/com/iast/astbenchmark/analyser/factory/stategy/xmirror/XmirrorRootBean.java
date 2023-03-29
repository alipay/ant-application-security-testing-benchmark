package com.iast.astbenchmark.analyser.factory.stategy.xmirror;

import com.iast.astbenchmark.analyser.bean.BaseOriginalDataBean;

public class XmirrorRootBean extends BaseOriginalDataBean {

    private int code;
    private String      message;
    private XmirrorData data;
    private boolean     success;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setData(XmirrorData data) {
        this.data = data;
    }
    public XmirrorData getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean getSuccess() {
        return success;
    }

}