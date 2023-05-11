package com.sast.astbenchmark.service;

public interface SSRFShowManager {
    void connectFacade(String url);

    void outConnectFacade(String url);

    void anonymousFacade(String url);

    void selfAnonymousFacade(String url);
}
