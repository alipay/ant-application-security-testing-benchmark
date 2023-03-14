package com.iast.astbenchmark;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.iast.astbenchmark.cases.bean.SourceTestObject;
import com.iast.astbenchmark.cases.bean.SourceTestWith100Filedsbject;
import com.iast.astbenchmark.cases.bean.SourceTestWith10Filedsbject;
import com.iast.astbenchmark.cases.bean.SoureWithQueueBean;
import com.iast.astbenchmark.cases.bean.SoureWithSetBean;
import com.iast.astbenchmark.cases.bean.big.BigParamBean;
import com.iast.astbenchmark.cases.bean.big.BigSizeBean;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean2;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean9;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class AstbenchmarkPrefTests {

    private static String url_root;
    /**
     * 此次本组Case执行时的唯一ID,用于在代理测对case数据的快速定位
     */
    private static Long caseUniqGroupId;

    @Test
    void contextLoads() {

    }

    @BeforeAll
    static void init() {
        url_root = "http://localhost:39100/";
        // caseUniqGroupId=System.currentTimeMillis();
        caseUniqGroupId = 54877081211069L;
        System.out.println("请保存此次跑case的关键字:" + caseUniqGroupId);
    }

    @BeforeEach
    void before() {
        try {
            TimeUnit.SECONDS.sleep(RandomUtil.randomInt(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void aTaintCase99001() {
        HttpResponse response = HttpRequest.post(url_root + "ataint/case99001?cmd=ls" + "&auto_check_start_time=" + caseUniqGroupId).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase99002() {
        HttpResponse response = HttpRequest.post(url_root + "ataint/case99002?cmd=ls" + "&auto_check_start_time=" + caseUniqGroupId).execute();
        System.out.println(response.body());
    }
    @Test
    void aTaintCase99003() {
        HttpResponse response = HttpRequest.post(url_root + "ataint/case99003?cmd=ls" + "&auto_check_start_time=" + caseUniqGroupId).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase99004() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/bigParam.json"), Charset.forName("utf-8"));
        BigParamBean object = JSONUtil.toBean(json, BigParamBean.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case99004" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase99005() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/bigSize.json"), Charset.forName("utf-8"));
        BigSizeBean object = JSONUtil.toBean(json, BigSizeBean.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case99005" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }



    private HttpResponse doGet(String url) {
        url = urlWithTime(url);
        try {
            HttpResponse response = HttpUtil.createGet(url).execute();
            if (response.getStatus() == 500) {
                return retryDoGet(url);
            } else {
                return response;
            }
        } catch (Exception e) {
            return retryDoGet(url);
        }
    }

    private HttpResponse retryDoGet(String url) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
        }
        HttpResponse response2 = HttpUtil.createGet(url).execute();
        return response2;
    }

    private HttpResponse doPost(String url) {
        return doPost(url, "");
    }

    private HttpResponse doPost(String url, String body) {
        url = urlWithTime(url);
        try {
            HttpResponse response = HttpUtil.createPost(url).body(body).execute();
            if (response.getStatus() == 500) {
                return retryDoPost(url, body);
            } else {
                return response;
            }
        } catch (Exception e) {
            return retryDoPost(url, body);
        }
    }

    private HttpResponse retryDoPost(String url, String body) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
        }
        HttpResponse response2 = HttpUtil.createPost(url).body(body).execute();
        return response2;
    }

    private String urlWithTime(String url) {
        if (url.contains("?")) {
            return url + "&auto_check_start_time=" + caseUniqGroupId;
        } else {
            return url + "?auto_check_start_time=" + caseUniqGroupId;
        }
    }
}
