package com.iast.astbenchmark.cases.bean.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


public class OrderRequest {

    public String lotteryType;

    public String phase;

    @JacksonXmlProperty(localName = "lotterytype")
    public String getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType) {
        this.lotteryType = lotteryType;
    }

    @JacksonXmlProperty(localName = "phase")
    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}