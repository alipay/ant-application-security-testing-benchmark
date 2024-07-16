package com.iast.astbenchmark.common.utils;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import cn.hutool.core.io.IoUtil;

/**
 * @author CC11001100
 */
public class FileUtil {

    /**
     * 读取类路径下的utf8格式的文本文件的内容
     * 
     * @param fileClassPathRelativePath
     * @return
     */
    public static String readClassPathFile(String fileClassPathRelativePath) {
        InputStream resourceAsStream =
            Thread.currentThread().getContextClassLoader().getResourceAsStream(fileClassPathRelativePath);
        byte[] bytes = IoUtil.readBytes(resourceAsStream);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     *
     * @param fileClassPathRelativePath
     * @return
     */
    public static String getClassPathFileAbsPath(String fileClassPathRelativePath) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(fileClassPathRelativePath);
        if (resource == null) {
            return null;
        }
        return resource.getPath();
    }

}
