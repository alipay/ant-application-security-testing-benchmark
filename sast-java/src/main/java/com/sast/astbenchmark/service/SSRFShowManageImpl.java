package com.sast.astbenchmark.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SSRFShowManageImpl extends BaseSSRFShowManagerImpl implements SSRFShowManager {

    protected SsrfClient ssrfClient = new DefaultSsrfClient();

    @Override
    public void connectFacade(String url) {
        ssrfClient.doConnect(url);
    }

    @Override
    public void outConnectFacade(String url) {
        ssrfClient.doConnect(url);
    }

    @Override
    public void anonymousFacade(String url) {
        super.doConnect(url, new SsrfClient() {
            @Override
            protected void doConnect(String url) {
                CloseableHttpClient httpclient = HttpClients.createDefault();
                HttpGet httpget = new HttpGet(url);
                CloseableHttpResponse response = null;
                try {
                    response = (httpclient).execute(httpget);
                    if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                        HttpEntity httpEntity = response.getEntity();
                        String result = EntityUtils.toString(httpEntity, "UTF-8");
                        System.err.println(result);
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } finally {
                    try {
                        if (response != null) {
                            response.close();
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if (httpclient != null) {
                            httpclient.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void selfAnonymousFacade(String url) {
        SsrfClient ssrfClient = new SsrfClient() {
            @Override
            protected void doConnect(String url) {
                CloseableHttpClient httpclient = HttpClients.createDefault();
                HttpGet httpget = new HttpGet(url);
                CloseableHttpResponse response = null;
                try {
                    response = httpclient.execute(httpget);
                    if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                        HttpEntity httpEntity = response.getEntity();
                        String result = EntityUtils.toString(httpEntity, "UTF-8");
                        System.err.println(result);
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } finally {
                    try {
                        if (response != null) {
                            response.close();
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if (httpclient != null) {
                            httpclient.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ssrfClient.doConnect(url);
    }





}
