package com.iast.astbenchmark.cases;

import com.google.common.collect.Maps;
import com.iast.astbenchmark.cases.bean.big.BigParamBean;
import com.iast.astbenchmark.cases.bean.big.BigSizeBean;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.MyCommonTestUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 性能测试靶场
 * ● 超长调用链路（污点链路长度1000）有漏洞场景，占比5%
 * ● 大污点对象（污点对象大小超过1K）有漏洞场景，占比5%
 * ● 多污点对象（同一个请求同时跟踪100个污点对象）有漏洞场景，占比5%
 */
@RestController()
public class AstPerfCase001 {
    /**
     * 1 无漏洞简单场景
     */
    @PostMapping("case99001")
    public Map<String,String> aTaintCase99001(@RequestParam String cmd) {
           Map res =  Maps.newHashMap();
           res.put(cmd,cmd);
           return res;
    }

    /**
     * 有漏洞简单场景（污点链路长度<10）
     */
    @PostMapping("case99002")
    public Map<String,String> aTaintCase99002(@RequestParam String cmd) {
        Map<String,String> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            return modelMap;
        }
        try {
            /** 递归10次*/
            cmd = MyCommonTestUtil.traceDeepth(cmd, 0, 10);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 超长调用链路（污点链路长度1000）有漏洞场景，占比5%
     */
    @PostMapping("case99003")
    public Map<String,String> aTaintCase99003(@RequestParam String cmd) {
        Map<String,String> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            return modelMap;
        }
        try {
            /** 递归1000次*/
            cmd = MyCommonTestUtil.traceDeepth(cmd, 0, 1000);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 大污点对象（污点对象大小超过1K）有漏洞场景
     * @param
     * @return
     */
    @PostMapping("case99004")
    public Map<String,String> aTaintCase99004(@RequestBody BigParamBean bigParamBean) {

        Map<String,String> modelMap = new HashMap<>();
        try {
            String exec = bigParamBean.getCmd();
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;

    }

    /**
     * 多污点对象（同一个请求同时跟踪100个污点对象）有漏洞场景
     * @param
     * @return
     */
    @PostMapping("case99005")
    public Map<String,String> aTaintCase99005(@RequestBody BigSizeBean bigSizeBean) {
        Map<String,String> modelMap = new HashMap<>();
        try {
            sink(1,bigSizeBean,100);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (Exception e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    private void sink(int deep,BigSizeBean bigSizeBean,int maxDeep){
        if(deep>=maxDeep){
            return;
        }
        Method method = null;
        try {
            method = bigSizeBean.getClass().getMethod("getCmd"+deep);
            String exec = (String) method.invoke(bigSizeBean);
            Runtime.getRuntime().exec(exec);
        } catch (Exception e){
        }
        deep++;
        sink(deep,bigSizeBean,maxDeep);
    }

}
