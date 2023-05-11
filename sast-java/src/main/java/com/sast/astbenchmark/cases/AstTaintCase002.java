package com.sast.astbenchmark.cases;

import com.google.common.collect.Queues;
import com.sast.astbenchmark.common.utils.HttpUtil;
import com.sast.astbenchmark.model.CmdObject;
import com.sast.astbenchmark.model.XCmdObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class AstTaintCase002 {


    /**
     *  对象级别->sink点的值外部可控
     *  case应该被检出
     */
    @PostMapping(value = "case021")
    public Map<String, Object> aTaintCase021(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     *  对象级别->sink点的值非外部可控
     *  case不应被检出
     */
    @PostMapping(value = "case021-2")
    public Map<String, Object> aTaintCase021_2(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String exec = "ls";
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     *  对象级别->sink点的值非外部可控 被静态变量覆盖
     *  case不应被检出
     */
    @PostMapping(value = "case021-3")
    public Map<String, Object> aTaintCase021_3(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd = "ls";
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 字段/元素级别->对象字段->对象元素
     * case应该被检出
     */
    @PostMapping(value = "case022")
    public Map<String, Object> aTaintCase022(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            CmdObject simpleBean = new CmdObject();
            simpleBean.setCmd(cmd);
            simpleBean.setCmd2("cd /");
            Runtime.getRuntime().exec(simpleBean.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 字段/元素级别->对象字段->对象元素
     * case不应被检出
     */
    @PostMapping(value = "case022-2")
    public Map<String, Object> aTaintCase022_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            CmdObject cmdObject = new CmdObject();
            cmdObject.setCmd(cmd);
            cmdObject.setCmd2("cd /");
            Runtime.getRuntime().exec(cmdObject.getCmd2());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 字符级别
     * case应该被检出
     * @param url
     * @return
     */
    @PostMapping(value = "case023")
    public Map<String, Object> aTaintCase023(@RequestParam String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            HttpUtil.doGet(url+"/api/test.json");
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 字符级别
     * case不应被检出
     * @param path
     * @return
     */
    @PostMapping(value = "case023-2")
    public Map<String, Object> aTaintCase023_2(@RequestParam String path) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            HttpUtil.doGet("https://www.test.com/api"+path);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 元素级别->Map
     * case应该被检出
     * @param url
     * @return
     */
    @PostMapping(value = "case024")
    public Map<String, Object> aTaintCase024(@RequestParam String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("url", url);
            paramMap.put("method", "GET");
            HttpUtil.doGet(paramMap.get("url"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 元素级别->Map
     * case不应被检出
     * @param url
     * @return
     */
    @PostMapping(value = "case024-2")
    public Map<String, Object> aTaintCase024_2(@RequestParam String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("url", url);
            paramMap.put("method", "GET");
            HttpUtil.doGet(paramMap.get("method"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 字段/元素级别->集合元素->Queue中部分元素为污点+lambda
     * case应该被检出
     */
    @PostMapping(value = "case025")
    public Map<String, Object> aTaintCase025(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Queue<String> queue = Queues.newArrayDeque();
            queue.add(cmd);
            queue.add("cd /");
            queue.add("cd ~");
            queue.stream().forEach(e->{
                if(cmd.equals(e)){
                    try {
                        Runtime.getRuntime().exec(e);
                    } catch (IOException ex) {
                    }
                }
            });

            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 字段/元素级别->集合元素->Queue中部分元素为污点+lambda
     *  case不应被检出
     * @param cmd
     * @return
     */
    @PostMapping(value = "case025-2")
    public Map<String, Object> aTaintCase025_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Queue<String> queue = Queues.newArrayDeque();
            queue.add(cmd);
            queue.add("cd /");
            queue.add("cd ~");
            queue.stream().forEach(e->{
                if("cd /".equals(e)){
                    try {
                        Runtime.getRuntime().exec(e);
                    } catch (IOException ex) {
                    }
                }
            });

            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 元素级别->多层Map
     * case应该被检出
     * @param url
     * @return
     */
    @PostMapping(value = "case026")
    public Map<String, Object> aTaintCase026(@RequestParam String url) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            Map<String, String> paramMap = new HashMap<>();
            XCmdObject xCmdObject = new XCmdObject();
            paramMap.put("url", url);
            paramMap.put("method", "GET");
            xCmdObject.setModelMap(paramMap);
            HttpUtil.doGet(xCmdObject.getModelMap().get("url"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 元素级别->多层Map
     * case不应被检出
     * @param url
     * @return
     */
    @PostMapping(value = "case026-2")
    public Map<String, Object> aTaintCase026_2(@RequestParam String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> paramMap = new HashMap<>();
            XCmdObject xCmdObject = new XCmdObject();
            paramMap.put("url", url);
            paramMap.put("method", "GET");
            xCmdObject.setCmd("test");
            xCmdObject.setModelMap(paramMap);
            HttpUtil.doGet(xCmdObject.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }


}
