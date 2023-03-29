package com.iast.astbenchmark.cases.bean.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


public class OrderRequest {

    public String lotteryType;



    @JacksonXmlProperty(localName = "lotterytype")
    public String getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType) {
        this.lotteryType = lotteryType;
    }



}