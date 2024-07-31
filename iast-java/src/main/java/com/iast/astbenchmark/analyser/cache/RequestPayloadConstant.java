package com.iast.astbenchmark.analyser.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CC11001100
 */
public class RequestPayloadConstant {

    /**
     * 命令执行的请求提交的命令是哪个
     */
    public static final String DEFAULT_COMMAND_REQUEST_STRING_BODY = "ls";

    /**
     * 命令执行的请求提交的命令，请求体是JSON格式
     */
    public static final String DEFAULT_COMMAND_REQUEST_JSON_BODY = "{\"cmd\":\"ls\"}";

    public static final Map<String, String> DEFAULT_COMMAND_REQUEST_PARAMS = new HashMap<>();

    static {
        DEFAULT_COMMAND_REQUEST_PARAMS.put("cmd", DEFAULT_COMMAND_REQUEST_STRING_BODY);
    }

    public static final String DEFAULT_PAYLOAD_FILE_PATH = "data/ls";

}
