package com.iast.astbenchmark.cases.engine.taint;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.http.HttpRequest;
import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.MyCommonTestUtil;
import com.iast.astbenchmark.common.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 污点完整度->异步跟踪能力 （aTaintCase00113～aTaintCase00120） &&
 * 污点完整度->跨进程跟踪能力 （aTaintCase00121～aTaintCase00124）
 */
@RestController()
public class AstTaintCase003 {









    /**
     *  TODO aTaintCase00117 异步跟踪能力->存储行异步->污点通过缓存存储后触发-> OSS
     */




    /**
     * aTaintCase00121 跨进程跟踪能力->调用方式->sofa tr(阿里内部)->单次rpc调用触发sink
     */
    /**
     *  aTaintCase00122 跨进程跟踪能力->调用方式->sofa tr(阿里内部)->跨多个应用rpc 触发sink
     */











    /**
     * rpc跨应用，至少支持一种框架，Sofa rpc/springcloud rpc/dubbo/grpc/hsf
     * TODO 需要多工程之间的调用，暂无case作为单独工程调用验证
     */


    @PostMapping(value = "case001241")
    @IastTestCase(
            caseNo ="aTaintCase001241",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->跨进程跟踪能力->调用方式->rpc(至少支持一种框架：Sofa/SpringCloud/Dubbo/gRpc/HSF)->跨一层进程间调用(暂无实现)",
            thisMethodTag = "aTaintCase001241",
            hasVul = true
    )
    public Map<String, Object> aTaintCase001241() {
    //TODO
        return null;
    }
    @PostMapping(value = "case001242")
    @IastTestCase(
            caseNo ="aTaintCase001242",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->跨进程跟踪能力->调用方式->rpc(至少支持一种框架：Sofa/SpringCloud/Dubbo/gRpc/HSF)->跨多层进程间调用(暂无实现)",
            thisMethodTag = "aTaintCase001242",
            hasVul = true
    )
    public Map<String, Object> aTaintCase001242() {
        //TODO
        return null;
    }

}
