package com.iast.astbenchmark.cases.engine.completion.basicTrack.taintObjectCompletion.array;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.Category;
import com.iast.astbenchmark.analyser.cache.IastTestCaseDescriptor;
import com.iast.astbenchmark.analyser.cache.IastTestCasePayloadProvider;
import com.iast.astbenchmark.cases.bean.SourceTestObject;

/**
 * @author CC11001100
 */
@RestController
public class TrackTaintObjectCompletion_ObjectArrayArray_T implements IastTestCaseDescriptor, IastTestCasePayloadProvider {

    @PostMapping("case00926")
    public Map<String, Object> aTaintCase00926(@RequestBody SourceTestObject[][] cmd) {

        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }

        try {
            Runtime.getRuntime().exec(cmd[1][1].getCmd());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }

        return modelMap;
    }

    @Override
    public String caseNo() {
        return "aTaintCase00926";
    }

    @Override
    public String[] category() {
        return new String[] {
            //
            Category.LEVEL1_IAST_JAVA_ENGINE,
            //
            Category.LEVEL2_完整度,
            //
            Category.LEVEL3_基础跟踪能力,
            //
            Category.LEVEL4_污点对象完整度,
            //
            Category.LEVEL5_数组_数组对象全为污点,
            //
            "多维数组对象的元素",
            //
        };
    }

    @Override
    public Object requestBodyBean() {
        SourceTestObject object1 = new SourceTestObject();
        SourceTestObject object2 = new SourceTestObject();
        SourceTestObject object3 = new SourceTestObject();
        SourceTestObject object4 = new SourceTestObject();
        object1.setCmd("ls");
        object2.setCmd("cd ~");
        object3.setCmd("ls -a");
        object4.setCmd("cd /");
        SourceTestObject[][] objects = new SourceTestObject[2][2];
        objects[0][0] = object1;
        objects[1][1] = object2;
        objects[0][1] = object3;
        objects[1][0] = object4;
        return objects;
    }

}
