package com.alipay.xast.score.util;

public class ScoreCardFormart {
    // case 文件名
    public String fileName;

    // url
    public String url;

    // 评价项
    public String assession;

    // 评价项达成条件
    public String compose;

    // case 存在漏洞
    public String hasVul;

    // 扫描识别为漏洞
    public String foundVul;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAssession(String assession) {
        this.assession = assession;
    }

    public void setCompose(String compose) {
        this.compose = compose;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFoundVul(String foundVul) {
        this.foundVul = foundVul;
    }

    public void setHasVul(String hasVul) {
        this.hasVul = hasVul;
    }

    public String getUrl() {
        return url;
    }

    public String getAssession() {
        return assession;
    }

    public String getCompose() {
        return compose;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFoundVul() {
        return foundVul;
    }

    public String getHasVul() {
        return hasVul;
    }
}

