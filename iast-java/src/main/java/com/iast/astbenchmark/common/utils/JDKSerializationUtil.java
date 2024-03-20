package com.iast.astbenchmark.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * JDK原生序列化Serializable
 **/
public class JDKSerializationUtil {
    /**
     * 序列化
     *
     * @param obj 待序列化对象
     * @return 二进制字节数组
     * @throws IOException
     */
    public static byte[] serialize(Object obj) throws IOException {
        // 字节输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 将对象序列化为二进制字节流
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        // 获取二进制字节数组
        byte[] bytes = byteArrayOutputStream.toByteArray();
        // 关闭流
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return bytes;
    }

    /**
     * 反序列化
     *
     * @param bytes 待反序列化二进制字节数组
     * @param <T> 反序列对象类型
     * @return 反序列对象
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> T deSerialize(byte[] bytes) throws IOException, ClassNotFoundException {
        // 字节输入流
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        // 将二进制字节流反序列化为对象
        final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        final T object = (T)objectInputStream.readObject();
        // 关闭流
        objectInputStream.close();
        byteArrayInputStream.close();
        return object;
    }
}
