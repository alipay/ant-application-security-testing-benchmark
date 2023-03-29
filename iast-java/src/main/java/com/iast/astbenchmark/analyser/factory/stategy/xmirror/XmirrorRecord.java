package com.iast.astbenchmark.analyser.factory.stategy.xmirror;

import com.iast.astbenchmark.analyser.bean.BaseDetectedDataBean;
import com.iast.astbenchmark.analyser.bean.BaseOriginalDataBean;

import java.util.Date;

public class XmirrorRecord extends BaseOriginalDataBean {

    private String vulResultId;
    private int    securityLevelId;
    private String vulName;
    private String vulSerial;
    private String vulUrl;
    private String vulMidTypeId;
    private String iastParam;
    private int    detectEngineId;
    private int    status;
    private String iastActiveVerify;
    private Date   createDate;
    private Date   firstCreateDate;
    private String mergeCount;
    private String assignUserId;
    private int mergeVulCount;
    public void setVulResultId(String vulResultId) {
        this.vulResultId = vulResultId;
    }
    public String getVulResultId() {
        return vulResultId;
    }

    public void setSecurityLevelId(int securityLevelId) {
        this.securityLevelId = securityLevelId;
    }
    public int getSecurityLevelId() {
        return securityLevelId;
    }

    public void setVulName(String vulName) {
        this.vulName = vulName;
    }
    public String getVulName() {
        return vulName;
    }

    public void setVulSerial(String vulSerial) {
        this.vulSerial = vulSerial;
    }
    public String getVulSerial() {
        return vulSerial;
    }

    public void setVulUrl(String vulUrl) {
        this.vulUrl = vulUrl;
    }
    public String getVulUrl() {
        return vulUrl;
    }

    public void setVulMidTypeId(String vulMidTypeId) {
        this.vulMidTypeId = vulMidTypeId;
    }
    public String getVulMidTypeId() {
        return vulMidTypeId;
    }

    public void setIastParam(String iastParam) {
        this.iastParam = iastParam;
    }
    public String getIastParam() {
        return iastParam;
    }

    public void setDetectEngineId(int detectEngineId) {
        this.detectEngineId = detectEngineId;
    }
    public int getDetectEngineId() {
        return detectEngineId;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public void setIastActiveVerify(String iastActiveVerify) {
        this.iastActiveVerify = iastActiveVerify;
    }
    public String getIastActiveVerify() {
        return iastActiveVerify;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setFirstCreateDate(Date firstCreateDate) {
        this.firstCreateDate = firstCreateDate;
    }
    public Date getFirstCreateDate() {
        return firstCreateDate;
    }

    public void setMergeCount(String mergeCount) {
        this.mergeCount = mergeCount;
    }
    public String getMergeCount() {
        return mergeCount;
    }

    public void setAssignUserId(String assignUserId) {
        this.assignUserId = assignUserId;
    }
    public String getAssignUserId() {
        return assignUserId;
    }

    public void setMergeVulCount(int mergeVulCount) {
        this.mergeVulCount = mergeVulCount;
    }
    public int getMergeVulCount() {
        return mergeVulCount;
    }

}