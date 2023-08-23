package com.alipay.antbenchmark.tools.pojo;


import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "message")
public class StringMessage {
    private final String key;
    private final String msg;


    public StringMessage(String key, String msg) {
        this.key = key;
        this.msg = msg;
    }

}