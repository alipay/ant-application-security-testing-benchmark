package com.iast.astbenchmark.cases.engine.taint;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.cases.bean.OneLayerSimpleBean;
import com.iast.astbenchmark.cases.bean.SoureWithQueueBean;
import com.iast.astbenchmark.cases.bean.SoureWithSetBean;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean99;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.JDKSerializationUtil;

/**
 * 准确度（aTaintCase00125～case00137）
 */
@RestController()
public class AstTaintCase004 {

    /// **
    // * aTaintCase00137 污点对象跟踪粒度->字符串级别->字符串部分存在污点
    // */
    // @PostMapping(value = "case00137")
    // public Map<String, Object> aTaintCase00137(@RequestBody String url) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String hardcode = "http://localhost//";
    // String urlfull = hardcode + url;
    // Runtime.getRuntime().exec(urlfull.substring(0, hardcode.length() - 1));
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    // @PostMapping(value = "case00137/2")
    // public Map<String, Object> aTaintCase00137_2(@RequestBody String url) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // Runtime.getRuntime().exec(url);
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }

}
