package com.iast.astbenchmark.common.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.iast.astbenchmark.cases.bean.SourceTestWithConstract01Bean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
//import sun.misc.Unsafe;

import java.io.*;
//import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 创建一些测试方法或者工具方法的支撑测试用例
 */
public class MyCommonTestUtil {

    public static boolean strEmpty(String str){
        return  "".equals(str)||str==null;
    }
    public static String traceDeepth(String data,int deepth, final  int maxDeepth){
        if(deepth>= maxDeepth){
            return data;
        }
        data = new StringBuilder().append(data).append(deepth).toString();
        deepth++;
        return traceDeepth(data,deepth,maxDeepth);
    }

    /**
     * 供反射调用
     */
    public String reflectTrace(String data){
        return data +"reflect";
    }


//    /**------------------------
//     * 构造使用Native方法
//     */
//    private static Unsafe unsafe = null;
//    private static Field getUnsafe = null;
//
//    static {
//        try {
//            getUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//            getUnsafe.setAccessible(true);
//            unsafe = (Unsafe) getUnsafe.get(null);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    private static class Data {
//        public Long getId() {
//            return id;
//        }
//
//        public void setId(Long id) {
//            this.id = id;
//        }
//
//        private Long id;
//
//    }
//    public static long unsafeForNative(long  inData) throws NoSuchFieldException {
//        Data data = new Data();
//        data.setId(inData);
//        Field id = data.getClass().getDeclaredField("id");
//        long l = unsafe.objectFieldOffset(id);
//        id.setAccessible(true);
//
//        unsafe.compareAndSwapLong(data,1L,1L,2L);
//        return data.getId();
//    }

    /**------------------------
     * 构造使用Native方法
     */


    public static String concat(String str1,String str2){
        return str1+str2;
    }
    public static SourceTestWithConstract01Bean buildTestObject(String str){
        return new SourceTestWithConstract01Bean(str);
    }
    public static Map<String,String> buildTestObject(String str1, String str2){
        Map<String,String> res =  Maps.newHashMap();
        res.put(str1,str1);
        res.put(str2,str2);
        return res;
    }
    public static String trowExMethod (String str1) throws Exception{
        throw new Exception(str1);
    }
     public static String readStrFromFile (String path){
         InputStream in =null;
         try {
             List<String> strings = Lists.newArrayList();
             in= new FileInputStream(FileUtil.file(path));
             IoUtil.readLines(in,"utf-8",strings);
             return strings.get(0);
         } catch (FileNotFoundException e) {
             return "";
         }finally {
             if(in!=null){
                 try {
                     in.close();
                 } catch (IOException e) {
                 }
             }
         }
    }
// byte[] to Byte[]
    public static  Byte[] toObjects(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];

        int i = 0;
        for (byte b : bytesPrim) bytes[i++] = b; // Autoboxing

        return bytes;
    }
}
