package com.iast.astbenchmark.cases;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.CaseTag;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.MyCommonTestUtil;
import com.iast.astbenchmark.common.utils.SessionUtil;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.http.HttpRequest;

/**
 * 污点完整度->异步跟踪能力 （aTaintCase00113～aTaintCase00120） && 污点完整度->跨进程跟踪能力 （aTaintCase00121～aTaintCase00124）
 */
@RestController()
public class AstTaintCase003 {

    /**
     * aTaintCase00113 异步跟踪能力->存储型异步->污点通过db存储后触发 采用轻量级的sqlite
     */
    private Long id = 0L;

    @PostMapping(value = "case00113")
    public Map<String, Object> aTaintCase00113(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            /** 创建存储 */
            id = Db.use().insertForGeneratedKey(Entity.create("CMD").set("cmd", cmd));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (SQLException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            throw new RuntimeException(e);
        }
        return modelMap;
    }

    @PostMapping(value = "case00113/1")
    @CaseTag(caseNo = "aTaintCase00113", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力->存储型异步->污点通过db存储后触发",
        thisMethodTag = "aTaintCase00113_1", thisMethodExpectedResult = true)
    public Map<String, Object> aTaintCase00113_1(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            /** 从DB获取存储 */
            Entity res = Db.use().get(Entity.create("CMD").set("id", id));
            Runtime.getRuntime().exec(res.getStr("cmd"));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            /** 最终从DB中删除这个数据 */
            id = 0L;
            try {
                Db.use().del(Entity.create("CMD").set("cmd", cmd));
            } catch (SQLException e) {
            }
        }
        return modelMap;
    }

    /**
     * aTaintCase00114 异步跟踪能力->存储行异步->污点通过session存储后触发
     */
    @PostMapping(value = "case00114/{session}")
    public Map<String, Object> aTaintCase00114(HttpServletRequest request, @PathVariable String session) {
        Map<String, Object> modelMap = new HashMap<>();
        SessionUtil.setSession(request, "sessionKey", session);
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @PostMapping(value = "case00114/1")
    @CaseTag(caseNo = "aTaintCase00114", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力->存储型异步->污点通过session存储后触发",
        thisMethodTag = "aTaintCase00114_1", thisMethodExpectedResult = true)
    public Map<String, Object> aTaintCase00114_1(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String exec = SessionUtil.getSession(request, "sessionKey");
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase00115 异步跟踪能力->存储行异步->污点通过缓存存储后触发->本地缓存
     */
    private static Cache<String, String> cache = CacheUtil.newLFUCache(5, 6000 * 30);

    @PostMapping(value = "case00115")
    public Map<String, Object> aTaintCase00115(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        cache.put("cacheKey", cmd);
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @PostMapping(value = "case00115/1")
    @CaseTag(caseNo = "aTaintCase00115", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力->存储型异步->污点通过缓存存储后触发->本地缓存",
        thisMethodTag = "aTaintCase00115_1", thisMethodExpectedResult = true)
    public Map<String, Object> aTaintCase00115_1() {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cache.get("cacheKey"));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase00138 异步跟踪能力->存储行异步->污点通过缓存存储后触发->非本地缓存
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping(value = "case00138")
    public Map<String, Object> aTaintCase00138(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String key = "cmd_key";
        redisTemplate.opsForValue().set(key, cmd);
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @PostMapping(value = "case00138/1")
    @CaseTag(caseNo = "aTaintCase00138", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力->存储型异步->污点通过缓存存储后触发->非本地缓存",
        thisMethodTag = "aTaintCase00138_1", thisMethodExpectedResult = true)
    public Map<String, Object> aTaintCase00138_1() {
        Map<String, Object> modelMap = new HashMap<>();
        String key = "cmd_key";
        try {
            String exec = (String)redisTemplate.opsForValue().get(key);
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } finally {
            redisTemplate.delete(key);
        }
        return modelMap;
    }

    /**
     * aTaintCase00116 异步跟踪能力->存储行异->污点通过文件存储后触发->本地文件
     */
    private static String filePath = "";

    @PostMapping(value = "case00116")
    public Map<String, Object> aTaintCase00116(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        File file = FileUtil.createTempFile(FileUtil.getTmpDir());
        filePath = file.getAbsolutePath();
        FileUtil.writeString(cmd, file, "utf-8");
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @PostMapping(value = "case00116/1")
    @CaseTag(caseNo = "aTaintCase00116", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力->存储型异步->污点通过文件存储后触发->本地文件",
        thisMethodTag = "aTaintCase00116_1", thisMethodExpectedResult = true)
    public Map<String, Object> aTaintCase00116_1() {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String readexec = MyCommonTestUtil.readStrFromFile(filePath);
            Runtime.getRuntime().exec(readexec);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * TODO aTaintCase00117 异步跟踪能力->存储行异步->污点通过缓存存储后触发-> OSS
     */

    /**
     * aTaintCase00118 异步跟踪能力->存储行异步-> 支持自定义污点的存储和再次提取点
     */
    /**
     * aTaintCase00119 异步跟踪能力->多线程异步->污点的来源和触发在不同线程
     */
    @PostMapping(value = "case00119")
    @CaseTag(caseNo = "aTaintCase00119", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力->多线程异步->污点的来源和触发在不同线程",
        thisMethodTag = "aTaintCase00119", thisMethodExpectedResult = true)
    public Map<String, Object> aTaintCase00119(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Thread thread = new Thread(() -> {
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
            }
        });
        String name = thread.getName();
        thread.start();
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    /**
     * aTaintCase00120 异步跟踪能力->多线程异步->污点的来源和触发在不同线程，sink的触发由线程池中的线程触发
     */
    ThreadPoolExecutor executorForTest = ThreadUtil.newExecutor(1, 1);

    @PostMapping(value = "case00120")
    @CaseTag(caseNo = "aTaintCase00120",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力->多线程异步->污点的来源和触发在不同线程，sink的触发由线程池中的线程触发",
        thisMethodTag = "aTaintCase00120", thisMethodExpectedResult = true)
    public Map<String, Object> aTaintCase00120(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        executorForTest.execute(() -> {
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
            }
        });
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    /**
     * aTaintCase00121 跨进程跟踪能力->调用方式->sofa tr(阿里内部)->单次rpc调用触发sink
     */
    /**
     * aTaintCase00122 跨进程跟踪能力->调用方式->sofa tr(阿里内部)->跨多个应用rpc 触发sink
     */

    /**
     * aTaintCase00123 跨进程跟踪能力->调用方式->调用方式->http->单次http调用触发sink
     */
    @PostMapping(value = "case00123")
    public Map<String, Object> aTaintCase00123(@RequestParam String cmd, @RequestParam String auto_check_start_time) {
        Map<String, Object> modelMap = new HashMap<>();
        HttpRequest.post(
            "http://localhost:39100/ataint/case00123/2?cmd=" + cmd + "&auto_check_start_time=" + auto_check_start_time)
            .execute();
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @PostMapping(value = "case00123/2")
    @CaseTag(caseNo = "aTaintCase00123", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->跨进程跟踪能力->调用方式->http->跨一层进程调用",
        thisMethodTag = "aTaintCase00123_2", thisMethodExpectedResult = true)
    public Map<String, Object> aTaintCase00123_2(@RequestParam String cmd, @RequestParam String auto_check_start_time) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase00124 跨进程跟踪能力->调用方式->http->跨多个应用http触发sink
     */

    @PostMapping(value = "case00124")
    public Map<String, Object> aTaintCase00124(@RequestParam String cmd, @RequestParam String auto_check_start_time) {
        Map<String, Object> modelMap = new HashMap<>();
        HttpRequest.post(
            "http://localhost:39100/ataint/case00124/2?cmd=" + cmd + "&auto_check_start_time=" + auto_check_start_time)
            .execute();
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @PostMapping(value = "case00124/2")
    public Map<String, Object> aTaintCase00124_2(@RequestParam String cmd, @RequestParam String auto_check_start_time) {
        Map<String, Object> modelMap = new HashMap<>();
        HttpRequest.post(
            "http://localhost:39100/ataint/case00124/3?cmd=" + cmd + "&auto_check_start_time=" + auto_check_start_time)
            .execute();
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @PostMapping(value = "case00124/3")
    @CaseTag(caseNo = "aTaintCase00124", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->跨进程跟踪能力->调用方式->http->跨多层进程调用",
        thisMethodTag = "aTaintCase00124_3", thisMethodExpectedResult = true)
    public Map<String, Object> aTaintCase00124_3(@RequestParam String cmd, @RequestParam String auto_check_start_time) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * rpc跨应用，至少支持一种框架，Sofa rpc/springcloud rpc/dubbo/grpc/hsf TODO 需要多工程之间的调用，暂无case作为单独工程调用验证
     */

    @PostMapping(value = "case001241")
    @CaseTag(caseNo = "aTaintCase001241",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->跨进程跟踪能力->调用方式->rpc(至少支持一种框架：Sofa/SpringCloud/Dubbo/gRpc/HSF)->跨一层进程间调用(暂无实现)",
        thisMethodTag = "aTaintCase001241", thisMethodExpectedResult = true)
    public Map<String, Object> aTaintCase001241() {
        // TODO
        return null;
    }

    @PostMapping(value = "case001242")
    @CaseTag(caseNo = "aTaintCase001242",
        caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->跨进程跟踪能力->调用方式->rpc(至少支持一种框架：Sofa/SpringCloud/Dubbo/gRpc/HSF)->跨多层进程间调用(暂无实现)",
        thisMethodTag = "aTaintCase001242", thisMethodExpectedResult = true)
    public Map<String, Object> aTaintCase001242() {
        // TODO
        return null;
    }

}
