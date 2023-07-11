package com.iast.astbenchmark.cli.test;

import cn.hutool.core.util.RandomUtil;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class AutoRunTest {
      public static String run(String url){
          Class<CopyTestCaseForRun> clazz = CopyTestCaseForRun.class;
          if(!StringUtils.isEmpty(url)){
             CopyTestCaseForRun.url_root = url;
          }
          Method[] methods = clazz.getDeclaredMethods();
          StringBuilder builder = new StringBuilder();
          for (Method method : methods) {
              if(method.getName().contains("aTaintCase00")){
                  before();
                  method.setAccessible(true);
                  try {
                      method.invoke(clazz.newInstance());
                  } catch (Exception e){
                      System.out.println(method.getName()+":invoke FAIL\n");
                      builder.append(method.getName()+":invoke FAIL\n");
                  }
                  builder.append(method.getName()+":invoke OK\n");
              }
          }
          System.out.println(builder);
          return builder.toString();
      }
    public static String run(String metheodName,String url){
        Class<CopyTestCaseForRun> clazz = CopyTestCaseForRun.class;
        if(!StringUtils.isEmpty(url)){
            CopyTestCaseForRun.url_root = url;
        }
        try {
            Method method = clazz.getMethod(metheodName);
            method.setAccessible(true);
            method.invoke(clazz.newInstance());
        } catch (NoSuchMethodException e) {
            return metheodName+"No Such Method";
        } catch (InvocationTargetException e ) {
            return metheodName+"InvocationTargetException";
        } catch (IllegalAccessException e) {
            return metheodName+"IllegalAccessException";
        } catch (InstantiationException e) {
            return  metheodName+"IllegalAccessException";
        }
        return metheodName+":OK\n";

    }
    private static void before(){
        try {
            TimeUnit.MILLISECONDS.sleep(RandomUtil.randomInt(8)*100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
