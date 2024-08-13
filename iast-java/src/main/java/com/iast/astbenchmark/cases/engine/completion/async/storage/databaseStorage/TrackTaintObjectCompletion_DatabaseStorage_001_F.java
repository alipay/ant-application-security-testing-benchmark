package com.iast.astbenchmark.cases.engine.completion.async.storage.databaseStorage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.common.CommonConsts;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;

/**
* Introduction 污点通过Database存储，后续读取将其作为命令执行
* Level X  
* Date 2024-08-12
* @author CC11001100 
*/
@RestController

// assession information start
// real vulnerability = false
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 异步跟踪能力 -> 存储型异步 -> 污点通过db存储后触发
// compose = TrackTaintObjectCompletion_DatabaseStorage_002_T.java && !TrackTaintObjectCompletion_DatabaseStorage_001_F.java
// bind_url = /case00113
// assession information end
public class TrackTaintObjectCompletion_DatabaseStorage_001_F
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

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

    @Override
    public Boolean hasVul() {
        return false;
    }

    @Override
    public String description() {
        return "污点通过Database存储，后续读取将其作为命令执行";
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_异步跟踪能力,
            //
            "存储型异步",
            //
            "污点通过db存储后触发",
            //
        };
    }

    @Override
    public String caseNo() {
        return "aTaintCase00113";
    }

}
