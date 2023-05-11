package com.sast.astbenchmark.service;

public class BaseSSRFShowManagerImpl {

    public void doConnect(String url, SsrfClient ssrfClient) {
        ssrfClient.doConnect(url);
    }

}
