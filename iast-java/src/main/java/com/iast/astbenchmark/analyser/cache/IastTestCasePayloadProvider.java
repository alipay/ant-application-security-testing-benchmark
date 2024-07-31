package com.iast.astbenchmark.analyser.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;

/**
 *
 * iast相关的case是需要发送请求的，可以实现这个接口来配置请求应该如何发
 *
 * @author CC11001100
 */
public interface IastTestCasePayloadProvider {

    // -------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 是否生效，如果想屏蔽掉某个case的测试请求，覆写此方法返回false即可
     *
     * @return
     */
    default boolean enable(){
        return true;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 如果不手动指定的话，默认会从当前case类的路由注解上获取拼接，这也是推荐的方式，手动维护容易产生不一致
     *
     * @return
     */
    default String requestPath() {
        return null;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 请求时携带的请求头
     *
     * @return
     */
    default Map<String, String> requestHeaders() {
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("User-Agent", "xAST-IAST");
        return headersMap;
    }

    /**
     * 发送请求的时候使用的方法，如果返回null的话会根据路由映射使用的是@GetMapping还是@PostMapping来决定使用啥方法来请求
     *
     * @return
     */
    default HttpMethod requestMethod() {
        return null;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 请求时携带的参数
     *
     * @return
     */
    default Map<String, String> params() {
        return null;
    }

    /**
     * 请求时携带的query string
     *
     * @return
     */
    default String queryString() {
        return null;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 如果是提交的表单参数的话，可以通过这个方法指定
     *
     * @return
     */
    default Map<String, String> requestBodyForm() {
        return null;
    }

    /**
     * 如果请求体是字符串的话，则通过这个方法指定
     *
     * @return
     */
    default String requestBodyString() {
        return null;
    }

    /**
     * 请求体是一个bean
     *
     * @return
     */
    default Object requestBodyBean() {
        return null;
    }

    /**
     * 如果请求体是字符数组的话，则通过这个方法来指定
     *
     * @return
     */
    default byte[] requestBodyBytes() {
        return null;
    }

    /**
     * 请求体是一个文件，文件的路径
     * 
     * @return
     */
    default String requestBodyFilePath() {
        return null;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------

}
