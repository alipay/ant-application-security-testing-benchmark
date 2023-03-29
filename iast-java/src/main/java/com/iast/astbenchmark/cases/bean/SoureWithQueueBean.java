package com.iast.astbenchmark.cases.bean;

import lombok.Data;
import java.util.concurrent.LinkedBlockingQueue;

@Data
public class SoureWithQueueBean {
    private String                      key;
    private LinkedBlockingQueue<String> queue;

}
