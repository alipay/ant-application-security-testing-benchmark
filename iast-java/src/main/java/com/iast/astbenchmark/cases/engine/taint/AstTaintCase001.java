package com.iast.astbenchmark.cases.engine.taint;

import com.iast.astbenchmark.cases.bean.SourceTestObject;
import com.iast.astbenchmark.cases.bean.SourceTestWith100Filedsbject;
import com.iast.astbenchmark.cases.bean.SourceTestWith10Filedsbject;
import com.iast.astbenchmark.cases.bean.SoureWithQueueBean;
import com.iast.astbenchmark.cases.bean.SoureWithSetBean;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean2;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean9;
import com.iast.astbenchmark.analyser.cache.CaseTag;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

/**
 * 基础跟踪能力->污点对象完整度（aTaintCase001～case0021）
 */
@RestController()
public class AstTaintCase001 {




    /**
     * 基本类型byte 作为污点对象
     *
     * @param cmd
     * @return
     */
    @GetMapping("case003")
    @CaseTag(
            caseNo ="aTaintCase003",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->byte",
            thisMethodTag = "aTaintCase003",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase003(@RequestParam byte cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 基础类型long 作为污点对象
     *
     * @param cmd
     * @return
     */
    @GetMapping("case004")
    @CaseTag(
            caseNo ="aTaintCase004",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->long",
            thisMethodTag = "aTaintCase004",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase004(@RequestParam long cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }


    @PostMapping("case005")
    @CaseTag(
            caseNo ="aTaintCase005",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->集合(集合对象全为污点)->Map元素",
            thisMethodTag = "aTaintCase005",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase005(@RequestBody Map<String, String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.isEmpty()) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd.get(cmd.keySet().iterator().next()));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 引用类型List 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case006")
    @CaseTag(
            caseNo ="aTaintCase006",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->集合(集合对象全为污点)->List元素",
            thisMethodTag = "aTaintCase006",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase006(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || CollectionUtils.isEmpty(cmd)) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd.get(0));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 引用类型queue 作为污点对象
     *
     * @param
     * @return
     */
    @PostMapping("case007")

    @CaseTag(
            caseNo ="aTaintCase007",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->集合(集合对象全为污点)->Queue元素",
            thisMethodTag = "aTaintCase007",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase007(@RequestBody SoureWithQueueBean queueBean) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(queueBean.getQueue().peek());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 引用类型Set 作为污点对象
     *
     * @param
     * @return
     */
    @PostMapping("case008")
    @CaseTag(
            caseNo ="aTaintCase008",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->集合(集合对象全为污点)->Set元素",
            thisMethodTag = "aTaintCase008",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase008(@RequestBody SoureWithSetBean setBean) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(setBean.getValue().iterator().next());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }



    /**
     * 基本数据类型的封装类型 Byte 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case009")
    @CaseTag(
            caseNo ="aTaintCase009",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->Byte",
            thisMethodTag = "aTaintCase009",
            thisMethodExpectedResult = true
    )

    public Map<String, Object> aTaintCase009(@RequestParam Byte cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }


    /**
     * 基本数据类型的封装类型 Integer 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0010")
    @CaseTag(
            caseNo ="aTaintCase0010",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->Integer",
            thisMethodTag = "aTaintCase0010",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase0010(@RequestParam Integer cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }


    /**
     * 基本数据类型的封装类型 Long 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0011")
    @CaseTag(
            caseNo ="aTaintCase0011",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->Long",
            thisMethodTag = "aTaintCase0011",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase0011(@RequestParam Long cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 基本数据类型的封装类型 Character 作为污点对象
     *
     * @param cmd 测试数据使用（0~9）
     * @return
     */
    @PostMapping("case0012")
    @CaseTag(
            caseNo ="aTaintCase0012",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->基本数据类型及其封装类型->Character",
            thisMethodTag = "aTaintCase0012",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase0012(@RequestParam Character cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 数组 String[] 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0013")
    @CaseTag(
            caseNo ="aTaintCase0013",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->数组(数组对象全为污点)->数组对象String[]",
            thisMethodTag = "aTaintCase0013",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase0013(@RequestBody String[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 数组 char[] 作为污点对象
     *
     * @param cmd [1,2]
     * @return
     */
    @PostMapping("case0014")
    @CaseTag(
            caseNo ="aTaintCase0014",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->数组(数组对象全为污点)->数组对象char[]",
            thisMethodTag = "aTaintCase0014",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase0014(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
//        if (cmd == null || cmd.length < 1) {
//            modelMap.put("status", ERROR_STR);
//            return modelMap;
        //      }
        //char[] data = {(char) cmd[0], (char) cmd[1]};
       char[] data = cmd.toCharArray();
        try {
            //java.io.PrintWriter printWriter = new PrintWriter(System.out);
            //printWriter.print(data);
           Runtime.getRuntime().exec(new String(data));
            modelMap.put("status", SUCCESS_STR);
        } catch  (IOException e)  {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 数组 byte[] 作为污点对象
     *  TODO 没有直接传入的sink,先用这个
     * @param cmd
     * @return
     */
    @PostMapping("case0015")
    @CaseTag(
            caseNo ="aTaintCase0015",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->数组(数组对象全为污点)->数组对象byte[]",
            thisMethodTag = "aTaintCase0015",
            thisMethodExpectedResult = true
    )

    public Map<String, Object> aTaintCase0015(@RequestBody byte[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(new String(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 数组 对象 作为污点对象
     *
     * @param cmd [{"a":"1"}]
     * @return
     */
    @PostMapping("case0016")
    @CaseTag(
            caseNo ="aTaintCase0016",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->数组(数组对象全为污点)->单维数组对象的元素",
            thisMethodTag = "aTaintCase0016",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase0016(@RequestBody SourceTestObject[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd[0].getCmd());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00926")
    @CaseTag(
            caseNo ="aTaintCase00926",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->数组(数组对象全为污点)->多维数组对象的元素",
            thisMethodTag = "aTaintCase00926",
            thisMethodExpectedResult = true
    )

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



    /**
     * 对象字段->单层字段->10@aTaintCase00921
     *
     * @param cmd
     * @return
     */
    @PostMapping("case00921")
    @CaseTag(
            caseNo ="aTaintCase00921",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->单层字段->10",
            thisMethodTag = "aTaintCase00921",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00921(@RequestBody SourceTestWith10Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd1());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case00921/2")
    @CaseTag(
            caseNo ="aTaintCase00921",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->单层字段->10",
            thisMethodTag = "aTaintCase00921_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00921_2(@RequestBody SourceTestWith10Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd5());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case00921/3")
    @CaseTag(
            caseNo ="aTaintCase00921",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->单层字段->10",
            thisMethodTag = "aTaintCase00921_3",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00921_3(@RequestBody SourceTestWith10Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd10());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 对象字段->单层字段->100@aTaintCase00921
     *
     * @param cmd
     * @return
     */
    @PostMapping("case00922")
    @CaseTag(
            caseNo ="aTaintCase00922",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->单层字段->100",
            thisMethodTag = "aTaintCase00922",
            thisMethodExpectedResult = true
    )

    public Map<String, Object> aTaintCase00922(@RequestBody SourceTestWith100Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd1());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case00922/2")
    @CaseTag(
            caseNo ="aTaintCase00922",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->单层字段->100",
            thisMethodTag = "aTaintCase00922_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00922_2(@RequestBody SourceTestWith100Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd50());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case00922/3")
    @CaseTag(
            caseNo ="aTaintCase00922",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->单层字段->100",
            thisMethodTag = "aTaintCase00922_3",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00922_3(@RequestBody SourceTestWith100Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd100());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00923")
    @CaseTag(
            caseNo ="aTaintCase00923",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->多层字段->3层",
            thisMethodTag = "aTaintCase00923",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00923(@RequestBody LayerBaseBean2 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda0());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00923/2")
    @CaseTag(
            caseNo ="aTaintCase00923",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->多层字段->3层",
            thisMethodTag = "aTaintCase00923_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00923_2(@RequestBody LayerBaseBean2 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda1());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00923/3")
    @CaseTag(
            caseNo ="aTaintCase00923",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->多层字段->3层",
            thisMethodTag = "aTaintCase00923_3",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00923_3(@RequestBody LayerBaseBean2 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda2());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case00924")
    @CaseTag(
            caseNo ="aTaintCase00924",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->多层字段->10层",
            thisMethodTag = "aTaintCase00924",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00924(@RequestBody LayerBaseBean9 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda0());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00924/2")
    @CaseTag(
            caseNo ="aTaintCase00924",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->多层字段->10层",
            thisMethodTag = "aTaintCase00924_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00924_2(@RequestBody LayerBaseBean9 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda1());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00924/3")
    @CaseTag(
            caseNo ="aTaintCase00924",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->多层字段->10层",
            thisMethodTag = "aTaintCase00924_3",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00924_3(@RequestBody LayerBaseBean9 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda9());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case00925")
    @CaseTag(
            caseNo ="aTaintCase00925",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->污点为父类字段",
            thisMethodTag = "aTaintCase00925",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00925(@RequestBody LayerBaseBean2 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda0());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00925/2")
    @CaseTag(
            caseNo ="aTaintCase00925",
            caseFullName = "IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点对象完整度->自定义对象->对象字段->污点为父类字段",
            thisMethodTag = "aTaintCase00925_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00925_2(@RequestBody LayerBaseBean2 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda2());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase00138 USED
     */
}
