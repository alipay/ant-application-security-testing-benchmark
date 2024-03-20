package com.iast.astbenchmark.cases.bean;

import java.util.concurrent.LinkedBlockingQueue;

import lombok.Data;

@Data
public class SoureWithQueueBean {
    private String key;
    private LinkedBlockingQueue<String> queue;

}
