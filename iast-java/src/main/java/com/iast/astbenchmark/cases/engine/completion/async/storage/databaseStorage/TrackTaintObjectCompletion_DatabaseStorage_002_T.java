package com.iast.astbenchmark.cases.engine.completion.async.storage.databaseStorage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
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
// real vulnerability = true
// assession project = IAST引擎能力评估体系(JAVA) -> 完整度 -> 异步跟踪能力 -> 存储型异步 -> 污点通过db存储后触发
// compose = TrackTaintObjectCompletion_DatabaseStorage_002_T.java && !TrackTaintObjectCompletion_DatabaseStorage_001_F.java
// bind_url = /case00113/1
// assession information end
public class TrackTaintObjectCompletion_DatabaseStorage_002_T
    implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    private Long id = 0L;

    @PostMapping(value = "case00113/1")
    @IastTestCase(caseNo = "aTaintCase00113", caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->异步跟踪能力->存储型异步->污点通过db存储后触发",
        thisMethodTag = "aTaintCase00113_1", hasVul = true)
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

    @Override
    public Boolean hasVul() {
        return true;
    }

    @Override
    public IastTestCaseDescriptor followIastTestCaseDescriptor() {
        return new TrackTaintObjectCompletion_DatabaseStorage_001_F();
    }

}
