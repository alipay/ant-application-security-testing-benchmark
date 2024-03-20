package com.iast.astbenchmark.cases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.iast.astbenchmark.cases.bean.big.BigParamBean;
import com.iast.astbenchmark.cases.bean.big.BigSizeBean;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.longd.LongChainUtil;
import com.iast.astbenchmark.common.utils.MyCommonTestUtil2;

/**
 * 性能测试靶场 ● 超长调用链路（污点链路长度1000）有漏洞场景 ● 大污点对象（污点对象大小超过1K）有漏洞场景 ● 多污点对象（同一个请求同时跟踪100个污点对象）有漏洞场景
 */
@RestController()
public class AstPerfCase001 {
    /**
     * 1 无漏洞简单场景
     */
    @PostMapping("case99001")
    public Map<String, String> aTaintCase99001(@RequestParam String cmd) {
        Map res = Maps.newHashMap();
        new MyCommonTestUtil2().traceDeepth(cmd, 10);
        res.put("status", CommonConsts.SUCCESS_STR);
        return res;
    }

    /**
     * 有漏洞简单场景（污点链路长度<10）
     */
    @PostMapping("case99002")
    public Map<String, String> aTaintCase99002(@RequestParam String cmd) {
        Map<String, String> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            return modelMap;
        }
        try {
            new MyCommonTestUtil2().traceDeepth(cmd, 10);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 超长调用链路（污点调用链路长度50）有漏洞场景 递归
     */
    @PostMapping("case99003")
    public Map<String, String> aTaintCase99003(@RequestParam String cmd) {
        Map<String, String> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            return modelMap;
        }
        try {
            // new MyCommonTestUtil2().traceDeepth(cmd,0,50);
            new LongChainUtil().longChain150(cmd, 3);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 超长调用链路（污点跟踪链路长度200）有漏洞场景 for
     */
    @PostMapping("case99006")
    public Map<String, String> aTaintCase99006(@RequestParam String cmd) {
        Map<String, String> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            return modelMap;
        }
        try {
            new MyCommonTestUtil2().traceDeepth(cmd, 200);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 大污点对象（污点对象大小超过1K）有漏洞场景
     * 
     * @param
     * @return
     */
    @PostMapping("case99004")
    public Map<String, String> aTaintCase99004(@RequestBody BigParamBean bigParamBean) {

        Map<String, String> modelMap = new HashMap<>();
        try {
            String exec = bigParamBean.getCmd();
            new MyCommonTestUtil2().traceDeepth(exec, 10);
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 多污点对象（同一个请求同时跟踪100个污点对象）有漏洞场景
     * 
     * @param
     * @return
     */
    @PostMapping("case99005")
    public Map<String, String> aTaintCase99005(@RequestBody BigSizeBean bigSizeBean) {
        Map<String, String> modelMap = new HashMap<>();
        try {
            BigSizeBean temp = new BigSizeBean();
            for (int i = 0; i < 10; i++) {
                temp = null;
                if (bigSizeBean != null) {
                    temp = bigSizeBean;
                }
            }
            String exec = temp.toString();
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (Exception e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
