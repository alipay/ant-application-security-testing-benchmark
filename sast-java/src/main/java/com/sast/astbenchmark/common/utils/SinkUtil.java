package com.sast.astbenchmark.common.utils;


import com.fasterxml.jackson.databind.ObjectMapper;

public class SinkUtil {
  private static final ObjectMapper mapper = new ObjectMapper();

  public static void sink(Object param) {
    try {
      System.out.println(mapper.writeValueAsString(param));
    } catch (Exception e) {
      throw new RuntimeException("JSON 序列化失败", e);
    }
  }
}
