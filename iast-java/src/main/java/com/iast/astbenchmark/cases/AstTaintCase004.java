package com.iast.astbenchmark.cases;

import com.iast.astbenchmark.cases.bean.OneLayerSimpleBean;
import com.iast.astbenchmark.cases.bean.SourceTestObject;
import com.iast.astbenchmark.cases.bean.SoureWithQueueBean;
import com.iast.astbenchmark.cases.bean.SoureWithSetBean;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean99;
import com.iast.astbenchmark.analyser.cache.CaseTag;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.JDKSerializationUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 准备度（aTaintCase00125～case00137）
 */
@RestController()
public class AstTaintCase004 {
    /**
     * ------------- 准确度---------------
     */
    /**
     * aTaintCase00125 污点对象跟踪粒度->变量级别->sink点的值非外部可控，但与某个参数值相同
     * 这个case期望不能被检出污点
     */
    //    考虑使用这个sink点
    //    private PrintWriter pw = new PrintWriter(System.out);
    //    private void sink(Object obj) throws Exception{
    //        pw.println(obj);
    //        pw.flush();
    //    }
    @PostMapping(value = "case00125")
    @CaseTag(
            caseNo = "aTaintCase00125",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->变量级别->sink点的值非外部可控，但与某个参数值相同",
            thisMethodTag = "aTaintCase00125",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00125(@RequestParam String cmd1, @RequestParam(defaultValue = "ls") String cmd2) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String exec = "ls";
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00125/2")
    @CaseTag(
            caseNo = "aTaintCase00125",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->变量级别->sink点的值非外部可控，但与某个参数值相同",
            thisMethodTag = "aTaintCase00125",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00125_2(@RequestParam String cmd1, @RequestParam(defaultValue = "") String cmd2) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String exec = "";
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase00126 污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->单层简单对象部分字段为污点
     * /2为参照组，期望case被检测出，参照组不被检测出
     */
    @PostMapping(value = "case00126")
    @CaseTag(
            caseNo = "aTaintCase00126",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->单层简单对象部分字段为污点",
            thisMethodTag = "aTaintCase00126",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00126(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            OneLayerSimpleBean simpleBean = new OneLayerSimpleBean();
            simpleBean.setCmd(cmd);
            simpleBean.setCmd2("cd /");
            Runtime.getRuntime().exec(simpleBean.getCmd());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00126/2")
    @CaseTag(
            caseNo = "aTaintCase00126",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->单层简单对象部分字段为污点",
            thisMethodTag = "aTaintCase00126_2",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00126_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            OneLayerSimpleBean simpleBean = new OneLayerSimpleBean();
            simpleBean.setCmd(cmd);
            simpleBean.setCmd2("cd /");
            Runtime.getRuntime().exec(simpleBean.getCmd2());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase00127 污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->多层复杂对象部分字段为污点->污点来自父类
     */
    @PostMapping(value = "case00127")
    @CaseTag(
            caseNo = "aTaintCase00127",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->多层复杂对象部分字段为污点->污点来自父类",
            thisMethodTag = "aTaintCase00127",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00127(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            LayerBaseBean99 simpleBean = new LayerBaseBean99();
            simpleBean.setCmda0(cmd);
            simpleBean.setCmdb0("ls -a");
            simpleBean.setCmda99("cd /");
            simpleBean.setCmdb99("cd ~");
            Runtime.getRuntime().exec(simpleBean.getCmda0());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00127/2")
    @CaseTag(
            caseNo = "aTaintCase00127",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->多层复杂对象部分字段为污点->污点来自父类",
            thisMethodTag = "aTaintCase00127_2",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00127_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            LayerBaseBean99 simpleBean = new LayerBaseBean99();
            simpleBean.setCmda0(cmd);
            simpleBean.setCmdb0("ls");
            simpleBean.setCmda99("cd /");
            simpleBean.setCmdb99("cd ~");
            Runtime.getRuntime().exec(simpleBean.getCmdb0());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase00128 污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->多层复杂对象部分字段为污点->污点来自子类
     */
    @PostMapping(value = "case00128")
    @CaseTag(
            caseNo = "aTaintCase00128",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->多层复杂对象部分字段为污点->污点来当前类字段",
            thisMethodTag = "aTaintCase00128",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00128(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            LayerBaseBean99 simpleBean = new LayerBaseBean99();
            simpleBean.setCmda0("cd /");
            simpleBean.setCmdb0("ls -a");
            simpleBean.setCmda99(cmd);
            simpleBean.setCmdb99("ls");
            Runtime.getRuntime().exec(simpleBean.getCmda99());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00128/2")
    @CaseTag(
            caseNo = "aTaintCase00128",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->多层复杂对象部分字段为污点->污点来当前类字段",
            thisMethodTag = "aTaintCase00128_2",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00128_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            LayerBaseBean99 simpleBean = new LayerBaseBean99();
            simpleBean.setCmda0("cd /");
            simpleBean.setCmdb0("ls -a");
            simpleBean.setCmda99(cmd);
            simpleBean.setCmdb99("ls");
            Runtime.getRuntime().exec(simpleBean.getCmdb99());
            ;
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase00129 污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->单维数组中的部分元素为污点
     */
    @PostMapping(value = "case00129")
    @CaseTag(
            caseNo = "aTaintCase00129",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->单维数组中的部分元素为污点",
            thisMethodTag = "aTaintCase00129",
            thisMethodExpectedResult = true
    )

    public Map<String, Object> aTaintCase00129(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] strings = new String[3];
            strings[0] = "cd ~";
            strings[1] = cmd;
            strings[2] = "cd /";
            Runtime.getRuntime().exec(strings[1]);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00129/2")
    @CaseTag(
            caseNo = "aTaintCase00129",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->单维数组中的部分元素为污点",
            thisMethodTag = "aTaintCase00129",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00129_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] strings = new String[3];
            strings[0] = "cd ~";
            strings[1] = cmd;
            strings[2] = "cd /";
            Runtime.getRuntime().exec(strings[0]);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase00130 污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->多维数组中的部分元素为污点
     */
    @PostMapping(value = "case00130")
    @CaseTag(
            caseNo = "aTaintCase00130",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->多维数组中的部分元素为污点",
            thisMethodTag = "aTaintCase00130",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00130(@RequestBody String[][] strings) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //String[][] strings = new String[2][2];
            //strings[0][0] = "ls";
            //strings[0][1] = cmd;
            //strings[1][0] = "cd /";
            //strings[1][1] = "cd /home";
            strings[0][0] = "ls";
            strings[1][0] = "cd /";
            strings[1][1] = "cd /home";
            Runtime.getRuntime().exec(strings[0][1]);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00130/2")
    @CaseTag(
            caseNo = "aTaintCase00130",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->多维数组中的部分元素为污点",
            thisMethodTag = "aTaintCase00130_2",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00130_2(@RequestBody String[][] strings) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            strings[0][0] = "ls";
            strings[1][0] = "cd /";
            strings[1][1] = "cd /home";
            Runtime.getRuntime().exec(strings[0][0]);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase00131 污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->部分元素为污点，序列化后再反序列化
     */

    @PostMapping(value = "case00131")
    @CaseTag(
            caseNo = "aTaintCase00131",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->部分元素为污点，经过JDK序列化后再反序列化",
            thisMethodTag = "aTaintCase00131",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00131(@RequestBody String[][] strings) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            strings[0][0] = "ls";
            strings[1][0] = "cd /";
            strings[1][1] = "cd /home";
            byte[] bytes = JDKSerializationUtil.serialize(strings);
            String[][] res = JDKSerializationUtil.deSerialize(bytes);
            Runtime.getRuntime().exec(res[0][1]);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return modelMap;
    }

    @PostMapping(value = "case00131/2")
    @CaseTag(
            caseNo = "aTaintCase00131",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分数据元素为污点->部分元素为污点，经过JDK序列化后再反序列化",
            thisMethodTag = "aTaintCase00131_2",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00131_2(@RequestBody String[][] strings) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            strings[0][0] = "ls";
            strings[1][0] = "cd /";
            strings[1][1] = "cd /home";
            byte[] bytes = JDKSerializationUtil.serialize(strings);
            String[][] res = JDKSerializationUtil.deSerialize(bytes);
            Runtime.getRuntime().exec(res[0][0]);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return modelMap;
    }

    /**
     * aTaintCase00132 污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->List中部分元素为污点
     */
    @PostMapping(value = "case00132")
    @CaseTag(
            caseNo = "aTaintCase00132",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->List中部分元素为污点",
            thisMethodTag = "aTaintCase00132",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00132(@RequestBody List<String> stringList) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            stringList.add("cd /");
            stringList.add("cd ~");
            Runtime.getRuntime().exec(stringList.get(0));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00132/2")
    @CaseTag(
            caseNo = "aTaintCase00132",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->List中部分元素为污点",
            thisMethodTag = "aTaintCase00132_2",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00132_2(@RequestBody List<String> stringList) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            stringList.add("ls");
            stringList.add("cd ~");
            Runtime.getRuntime().exec(stringList.get(1));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase00133 污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Map中部分元素为污点
     */
    @PostMapping(value = "case00133")
    @CaseTag(
            caseNo = "aTaintCase00133",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Map中部分元素为污点",
            thisMethodTag = "aTaintCase00133",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00133(@RequestBody Map<String, String> map) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //Map<String, String> map = Maps.newHashMap();
            //map.put("1", cmd);
            map.put("2", "ls");
            map.put("3", "cd ~");
            Runtime.getRuntime().exec(map.get("1"));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00133/2")
    @CaseTag(
            caseNo = "aTaintCase00133",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Map中部分元素为污点",
            thisMethodTag = "aTaintCase00133_2",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00133_2(@RequestBody Map<String, String> map) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            map.put("2", "ls");
            map.put("3", "cd ~");
            Runtime.getRuntime().exec(map.get("2"));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase00134 污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Set中部分元素为污点
     */
    @PostMapping(value = "case00134")
    @CaseTag(
            caseNo = "aTaintCase00134",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Set中部分元素为污点",
            thisMethodTag = "aTaintCase00134",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00134(@RequestBody SoureWithSetBean setBean) {
        Map<String, Object> modelMap = new HashMap<>();
        Set<String> set = setBean.getValue();
        set.add("cd /");
        set.add("cd ~");
        set.stream().forEach(e -> {
            try {
                if ("ls".equals(e)) {
                    Runtime.getRuntime().exec(e);
                }
            } catch (IOException ex) {
            }
        });

        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @PostMapping(value = "case00134/2")
    @CaseTag(
            caseNo = "aTaintCase00134",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Set中部分元素为污点",
            thisMethodTag = "aTaintCase00134",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00134_2(@RequestBody SoureWithSetBean setBean) {
        Map<String, Object> modelMap = new HashMap<>();
        Set<String> set = setBean.getValue();
        set.add("cd /");
        set.add("cd ~");
        set.stream().forEach(e -> {
            try {
                if ("cd /".equals(e)) {
                    Runtime.getRuntime().exec(e);
                }
            } catch (IOException ex) {
            }
        });

        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    /**
     * aTaintCase00135 污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Queue中部分元素为污点
     */
    @PostMapping(value = "case00135")
    @CaseTag(
            caseNo = "aTaintCase00135",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Queue中部分元素为污点",
            thisMethodTag = "aTaintCase00135",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00135(@RequestBody SoureWithQueueBean queueBean) {
        Map<String, Object> modelMap = new HashMap<>();
        Queue<String> queue = queueBean.getQueue();
        queue.add("cd /");
        queue.add("cd ~");
        queue.stream().forEach(e -> {
            if ("ls".equals(e)) {
                try {
                    Runtime.getRuntime().exec(e);
                } catch (IOException ex) {
                }
            }
        });

        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    @PostMapping(value = "case00135/2")
    @CaseTag(
            caseNo = "aTaintCase00135",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->Queue中部分元素为污点",
            thisMethodTag = "aTaintCase00135",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00135_2(@RequestBody SoureWithQueueBean queueBean) {
        Map<String, Object> modelMap = new HashMap<>();
        Queue<String> queue = queueBean.getQueue();
        queue.add("cd /");
        queue.add("cd ~");
        queue.stream().forEach(e -> {
            if ("cd /".equals(e)) {
                try {
                    Runtime.getRuntime().exec(e);
                } catch (IOException ex) {
                }
            }
        });

        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    /**
     * aTaintCase00136 污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->集合中部分元素为污点，序列化后再反序列化
     */

    @PostMapping(value = "case00136")
    @CaseTag(
            caseNo = "aTaintCase00136",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->集合中部分元素为污点，经过JDK序列化后再反序列化",
            thisMethodTag = "aTaintCase00136",
            thisMethodExpectedResult = true
    )

    public Map<String, Object> aTaintCase00136(@RequestBody List<String> list) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //List<String> list = Lists.newArrayList();
            //list.add(cmd);
            list.add("cd /");
            list.add("cd ~");
            byte[] bytes = JDKSerializationUtil.serialize(list);
            List<String> strings = JDKSerializationUtil.deSerialize(bytes);
            Runtime.getRuntime().exec(strings.get(0));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return modelMap;
    }

    @PostMapping(value = "case00136/2")
    @CaseTag(
            caseNo = "aTaintCase00136",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分集合元素为污点->集合中部分元素为污点，经过JDK序列化后再反序列化",
            thisMethodTag = "aTaintCase00136",
            thisMethodExpectedResult = false
    )

    public Map<String, Object> aTaintCase00136_2(@RequestBody List<String> list) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //List<String> list = Lists.newArrayList();
            //list.add(cmd);
            list.add("ls");
            list.add("cd ~");
            byte[] bytes = JDKSerializationUtil.serialize(list);
            List<String> strings = JDKSerializationUtil.deSerialize(bytes);
            Runtime.getRuntime().exec(strings.get(1));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return modelMap;
    }

    ///**
    // * aTaintCase00137 污点对象跟踪粒度->字符串级别->字符串部分存在污点
    // */
    //@PostMapping(value = "case00137")
    //public Map<String, Object> aTaintCase00137(@RequestBody String url) {
    //    Map<String, Object> modelMap = new HashMap<>();
    //    try {
    //        String hardcode = "http://localhost//";
    //        String urlfull = hardcode + url;
    //        Runtime.getRuntime().exec(urlfull.substring(0, hardcode.length() - 1));
    //        modelMap.put("status", CommonConsts.SUCCESS_STR);
    //    } catch (IOException e)  {
    //        modelMap.put("status", CommonConsts.ERROR_STR);
    //    }
    //    return modelMap;
    //}
    //
    //@PostMapping(value = "case00137/2")
    //public Map<String, Object> aTaintCase00137_2(@RequestBody String url) {
    //    Map<String, Object> modelMap = new HashMap<>();
    //    try {
    //        Runtime.getRuntime().exec(url);
    //        modelMap.put("status", CommonConsts.SUCCESS_STR);
    //    } catch (IOException e)  {
    //        modelMap.put("status", CommonConsts.ERROR_STR);
    //    }
    //    return modelMap;
    //}

    @PostMapping(value = "case00940")

    @CaseTag(
            caseNo = "aTaintCase00940",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->replcace",
            thisMethodTag = "aTaintCase00940",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00940(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String urlfull = hardcode + cmd;
            String data = urlfull.replace(hardcode, "");
            Runtime.getRuntime().exec(data);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00940/2")
    @CaseTag(
            caseNo = "aTaintCase00940",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->replcace",
            thisMethodTag = "aTaintCase00940_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00940_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00940/3")
    @CaseTag(
            caseNo = "aTaintCase00940",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->replcace",
            thisMethodTag = "aTaintCase00940_3",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00940_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String cmdfull = hardcode + cmd;
            String data = cmdfull.replace(cmd, "");
            Runtime.getRuntime().exec(data);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00941")
    @CaseTag(
            caseNo = "aTaintCase00941",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->replcaceAll",
            thisMethodTag = "aTaintCase00941",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00941(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String cmdfull = hardcode + cmd + hardcode;
            String data = cmdfull.replaceAll(hardcode, "");
            Runtime.getRuntime().exec(data);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00941/2")
    @CaseTag(
            caseNo = "aTaintCase00941",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->replcaceAll",
            thisMethodTag = "aTaintCase00941_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00941_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00941/3")
    @CaseTag(
            caseNo = "aTaintCase00941",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->replcaceAll",
            thisMethodTag = "aTaintCase00941_3",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00941_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String cmdfull = hardcode + cmd + hardcode;
            String data = cmdfull.replaceAll(cmd, "");
            Runtime.getRuntime().exec(data);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00942")
    @CaseTag(
            caseNo = "aTaintCase00942",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->split",
            thisMethodTag = "aTaintCase00942",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00942(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc@";
            String cmdfull = hardcode + cmd;
            String[] data = cmdfull.split("@");
            Runtime.getRuntime().exec(data[1]);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00942/2")
    @CaseTag(
            caseNo = "aTaintCase00942",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->split",
            thisMethodTag = "aTaintCase00942_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00942_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00942/3")
    @CaseTag(
            caseNo = "aTaintCase00942",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->split",
            thisMethodTag = "aTaintCase00942_3",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00942_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc@";
            String cmdfull = hardcode + cmd;
            String[] data = cmdfull.split("@");
            Runtime.getRuntime().exec(data[0]);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00943")
    @CaseTag(
            caseNo = "aTaintCase00943",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->subSequence",
            thisMethodTag = "aTaintCase00943",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00943(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String cmdfull = hardcode + cmd;
            CharSequence data = cmdfull.subSequence(hardcode.length(), cmdfull.length());
            Runtime.getRuntime().exec(data.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00943/2")
    @CaseTag(
            caseNo = "aTaintCase00943",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->subSequence",
            thisMethodTag = "aTaintCase00943_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00943_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00943/3")
    @CaseTag(
            caseNo = "aTaintCase00943",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->subSequence",
            thisMethodTag = "aTaintCase00943_3",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00943_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String cmdfull = hardcode + cmd;
            CharSequence data = cmdfull.subSequence(0, hardcode.length());
            Runtime.getRuntime().exec(data.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00944")
    @CaseTag(
            caseNo = "aTaintCase00944",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->substring",
            thisMethodTag = "aTaintCase00944",
            thisMethodExpectedResult = true
    )

    public Map<String, Object> aTaintCase00944(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String cmdfull = hardcode + cmd;
            String data = cmdfull.substring(hardcode.length());
            Runtime.getRuntime().exec(data);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00944/2")
    @CaseTag(
            caseNo = "aTaintCase00944",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->substring",
            thisMethodTag = "aTaintCase00944_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00944_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00944/3")
    @CaseTag(
            caseNo = "aTaintCase00944",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->substring",
            thisMethodTag = "aTaintCase00944_3",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00944_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String cmdfull = hardcode + cmd;
            String data = cmdfull.substring(0, hardcode.length());
            Runtime.getRuntime().exec(data);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00945")
    @CaseTag(
            caseNo = "aTaintCase00945",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->trim",
            thisMethodTag = "aTaintCase00945",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00945(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = " ";
            String cmdfull = hardcode + cmd + hardcode;
            String data = cmdfull.trim();
            Runtime.getRuntime().exec(data);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00945/2")
    @CaseTag(
            caseNo = "aTaintCase00945",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->String操作->trim",
            thisMethodTag = "aTaintCase00945_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00945_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00946")
    @CaseTag(
            caseNo = "aTaintCase00946",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->delete",
            thisMethodTag = "aTaintCase00946",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00946(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            builder.delete(0, hardcode.length());
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00946/2")
    @CaseTag(
            caseNo = "aTaintCase00946",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->delete",
            thisMethodTag = "aTaintCase00946_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00946_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00946/3")
    @CaseTag(
            caseNo = "aTaintCase00946",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->delete",
            thisMethodTag = "aTaintCase00946_3",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00946_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            builder.delete(hardcode.length(), builder.length());
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00947")

    @CaseTag(
            caseNo = "aTaintCase00947",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->deleteCharAt",
            thisMethodTag = "aTaintCase00947",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00947(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "a";
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            builder.deleteCharAt(0);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00947/2")

    @CaseTag(
            caseNo = "aTaintCase00947",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->deleteCharAt",
            thisMethodTag = "aTaintCase00947_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00947_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00947/3")
    @CaseTag(
            caseNo = "aTaintCase00947",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->deleteCharAt",
            thisMethodTag = "aTaintCase00947_3",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00947_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "a";
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            builder.deleteCharAt(1);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00948")
    @CaseTag(
            caseNo = "aTaintCase00948",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->getChars",
            thisMethodTag = "aTaintCase00948",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00948(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "aa";
            char[] aa = new char[2];
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            builder.getChars(2, 4, aa, 0);
            Runtime.getRuntime().exec(new String(aa));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00948/2")
    @CaseTag(
            caseNo = "aTaintCase00948",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->getChars",
            thisMethodTag = "aTaintCase00948_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00948_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00948/3")
    @CaseTag(
            caseNo = "aTaintCase00948",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->getChars",
            thisMethodTag = "aTaintCase00948_3",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00948_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "aa";
            char[] aa = new char[2];
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            builder.getChars(0, 2, aa, 0);
            Runtime.getRuntime().exec(new String(aa));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00949")
    @CaseTag(
            caseNo = "aTaintCase00949",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->replace",
            thisMethodTag = "aTaintCase00949",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00949(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            builder.replace(0, builder.length(), hardcode);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00949/2")

    @CaseTag(
            caseNo = "aTaintCase00949",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->replace",
            thisMethodTag = "aTaintCase00949_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00949_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00949/3")
    @CaseTag(
            caseNo = "aTaintCase00949",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->replace",
            thisMethodTag = "aTaintCase00949_3",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00949_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            builder.replace(0, builder.length(), cmd);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00950")
    @CaseTag(
            caseNo = "aTaintCase00950",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->subSequence",
            thisMethodTag = "aTaintCase00950",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00950(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            CharSequence res = builder.subSequence(hardcode.length(), builder.length());
            Runtime.getRuntime().exec(res.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00950/2")
    @CaseTag(
            caseNo = "aTaintCase00950",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->subSequence",
            thisMethodTag = "aTaintCase00950_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00950_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00950/3")
    @CaseTag(
            caseNo = "aTaintCase00950",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->subSequence",
            thisMethodTag = "aTaintCase00950_3",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00950_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            CharSequence res = builder.subSequence(0, hardcode.length());
            Runtime.getRuntime().exec(res.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00951")
    @CaseTag(
            caseNo = "aTaintCase00951",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->substring",
            thisMethodTag = "aTaintCase00951",
            thisMethodExpectedResult = true
    )

    public Map<String, Object> aTaintCase00951(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            String res = builder.substring(hardcode.length());
            Runtime.getRuntime().exec(res);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00951/2")
    @CaseTag(
            caseNo = "aTaintCase00951",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->substring",
            thisMethodTag = "aTaintCase00951_2",
            thisMethodExpectedResult = true
    )

    public Map<String, Object> aTaintCase00951_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00951/3")
    @CaseTag(
            caseNo = "aTaintCase00951",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->StringBuilder操作->substring",
            thisMethodTag = "aTaintCase00951_3",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00951_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            StringBuilder builder = new StringBuilder(hardcode + cmd);
            String res = builder.substring(0, hardcode.length());
            Runtime.getRuntime().exec(res);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00952")
    @CaseTag(
            caseNo = "aTaintCase00952",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->char[]/byte[]操作->copyOfRange",
            thisMethodTag = "aTaintCase00952",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00952(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String aa = hardcode + cmd;
            char[] chars = aa.toCharArray();
            char[] data = Arrays.copyOfRange(chars, hardcode.length(), chars.length);
            ;
            Runtime.getRuntime().exec(new String(data));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00952/2")
    @CaseTag(
            caseNo = "aTaintCase00952",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->char[]/byte[]操作->copyOfRange",
            thisMethodTag = "aTaintCase00952_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00952_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00952/3")
    @CaseTag(
            caseNo = "aTaintCase00952",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分->char[]/byte[]操作->copyOfRange",
            thisMethodTag = "aTaintCase00952_2",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00952_3(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "ab";
            String aa = hardcode + cmd;
            char[] chars = aa.toCharArray();
            char[] data = Arrays.copyOfRange(chars, 0, hardcode.length());
            ;
            Runtime.getRuntime().exec(new String(data));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 字符串部分存在污点->截取非污点部分后再拼接污点->String操作->concat@aTaintCase00953
     *
     * @param cmd
     * @return
     */
    @PostMapping(value = "case00953")
    @CaseTag(
            caseNo = "aTaintCase00953",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->concat",
            thisMethodTag = "aTaintCase00953",
            thisMethodExpectedResult = true
    )

    public Map<String, Object> aTaintCase00953(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "ls";
            String cmdfull = hardcode + cmd;
            String data1 = cmdfull.substring(0, hardcode.length());  //截取到非无污点数据
            String dara2 = data1.concat(cmd);                      //再拼接上污点
            Runtime.getRuntime().exec(dara2);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00953/1")
    @CaseTag(
            caseNo = "aTaintCase00953",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->concat",
            thisMethodTag = "aTaintCase00953_1",
            thisMethodExpectedResult = false
    )
    public Map<String, Object> aTaintCase00953_1(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "ls";
            String cmdfull = hardcode + cmd;
            String data1 = cmdfull.substring(0, hardcode.length());
            String dara2 = data1.concat(hardcode);                      //再拼接上非污点
            Runtime.getRuntime().exec(dara2);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00953/2")
    @CaseTag(
            caseNo = "aTaintCase00953",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->concat",
            thisMethodTag = "aTaintCase00953_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00953_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00954")
    @CaseTag(
            caseNo = "aTaintCase00954",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->join",
            thisMethodTag = "aTaintCase00954",
            thisMethodExpectedResult = true
    )

    public Map<String, Object> aTaintCase00954(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "ls";
            String cmdfull = hardcode + cmd;
            String data1 = cmdfull.substring(0, hardcode.length());
            String dara2 = String.join(cmd, data1, hardcode);
            Runtime.getRuntime().exec(dara2);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00954/1")
    @CaseTag(
            caseNo = "aTaintCase00954",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->join",
            thisMethodTag = "aTaintCase00954_1",
            thisMethodExpectedResult = false
    )

    public Map<String, Object> aTaintCase00954_1(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "ls";
            String cmdfull = hardcode + cmd;
            String data1 = cmdfull.substring(0, hardcode.length());
            String dara2 = String.join(hardcode, data1, hardcode);
            Runtime.getRuntime().exec(dara2);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00954/2")
    @CaseTag(
            caseNo = "aTaintCase00954",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->join",
            thisMethodTag = "aTaintCase00954_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00954_2(@RequestParam String cmd) {
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
     * 字符串部分存在污点->截取非污点部分后再拼接污点->String操作->replace@aTaintCase00955
     *
     * @param cmd
     * @return
     */
    @PostMapping(value = "case00955")
    @CaseTag(
            caseNo = "aTaintCase00955",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->replace",
            thisMethodTag = "aTaintCase00955",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00955(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String cmdfull = hardcode + cmd;
            String data1 = cmdfull.replace(cmd, "");  //去掉污点
            String dara2 = data1 + cmd;                           //拼接污点
            Runtime.getRuntime().exec(dara2);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00955/2")
    @CaseTag(
            caseNo = "aTaintCase00955",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->replace",
            thisMethodTag = "aTaintCase00955_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00955_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00956")
    @CaseTag(
            caseNo = "aTaintCase00956",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->replaceAll",
            thisMethodTag = "aTaintCase00956",
            thisMethodExpectedResult = true
    )

    public Map<String, Object> aTaintCase00956(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String cmdfull = cmd + hardcode + cmd;
            String data1 = cmdfull.replaceAll(cmd, "");  //去掉污点
            String dara2 = data1 + cmd;                           //拼接污点
            Runtime.getRuntime().exec(dara2);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00956/2")
    @CaseTag(
            caseNo = "aTaintCase00956",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->replaceAll",
            thisMethodTag = "aTaintCase00956_2",
            thisMethodExpectedResult = true
    )

    public Map<String, Object> aTaintCase00956_2(@RequestParam String cmd) {
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
     * cmd = " "
     *
     * @param cmd
     * @return
     */
    @PostMapping(value = "case00957")
    @CaseTag(
            caseNo = "aTaintCase00957",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->trim",
            thisMethodTag = "aTaintCase00957",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00957(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "ls";
            String cmdfull = cmd + hardcode + cmd;
            String data1 = cmdfull.trim();  //去掉污点
            String dara2 = data1 + cmd;                           //拼接污点
            Runtime.getRuntime().exec(dara2);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00957/2")
    @CaseTag(
            caseNo = "aTaintCase00957",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->String操作->trim",
            thisMethodTag = "aTaintCase00957_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00957_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00958")
    @CaseTag(
            caseNo = "aTaintCase00958",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->StringBuilder操作->append",
            thisMethodTag = "aTaintCase00958",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00958(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            StringBuilder cmdfull = new StringBuilder(hardcode + cmd);
            StringBuilder data1 = cmdfull.replace(hardcode.length(), cmdfull.length(), "");
            data1.append(cmd);
            Runtime.getRuntime().exec(data1.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00958/2")
    @CaseTag(
            caseNo = "aTaintCase00958",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->StringBuilder操作->append",
            thisMethodTag = "aTaintCase00958_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00958_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00959")
    @CaseTag(
            caseNo = "aTaintCase00959",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->StringBuilder操作->replace",
            thisMethodTag = "aTaintCase00959",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00959(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            StringBuilder cmdfull = new StringBuilder(hardcode + cmd);
            StringBuilder data1 = cmdfull.replace(hardcode.length(), cmdfull.length(), "");
            data1.append(cmd);
            Runtime.getRuntime().exec(data1.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00959/2")
    @CaseTag(
            caseNo = "aTaintCase00959",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->StringBuilder操作->replace",
            thisMethodTag = "aTaintCase00959_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00959_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00960")
    @CaseTag(
            caseNo = "aTaintCase00960",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->char[]/byte[]操作->copyOfRange",
            thisMethodTag = "aTaintCase00960",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00960(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String hardcode = "abc";
            String aa = hardcode + cmd;
            char[] chars = aa.toCharArray();
            char[] data = Arrays.copyOfRange(chars, 0, hardcode.length());//非无污点部分
            //char result[] = new char[info.length + data.length];
            //System.arraycopy(info, 0, result, 0, info.length);
            //System.arraycopy(data, 0, result, info.length, data.length);
            char[] cmdChars = cmd.toCharArray();
            char[] res = new char[data.length + cmd.length()]; //将污点与非污点组合
            for (int i = 0; i < res.length; i++) {
                if (i < data.length) {
                    res[i] = data[i];
                } else {
                    res[i] = cmdChars[i - data.length];
                }
            }
            Runtime.getRuntime().exec(new String(res));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00960/2")
    @CaseTag(
            caseNo = "aTaintCase00960",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字符串级别->字符串部分存在污点->截取非污点部分后再拼接污点->char[]/byte[]操作->copyOfRange",
            thisMethodTag = "aTaintCase00960_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00960_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }

        return modelMap;
    }

    @PostMapping(value = "case00142")
    @CaseTag(
            caseNo = "aTaintCase00142",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->对象部分字段为污点，经过JDK序列化后再反序列化",
            thisMethodTag = "aTaintCase00142",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00142(@RequestBody SourceTestObject testObject) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //String str = JSON.toJSONString(testObject);
            //SourceTestObject strings1 = JSON.parseObject(str, SourceTestObject.class);
            byte[] bytes = JDKSerializationUtil.serialize(testObject);
            SourceTestObject object = JDKSerializationUtil.deSerialize(bytes);
            Runtime.getRuntime().exec(object.getCmd());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return modelMap;
    }

    @PostMapping(value = "case00142/2")
    @CaseTag(
            caseNo = "aTaintCase00142",
            caseFullName = "IAST引擎能力评估体系(JAVA)->准确度->污点对象跟踪粒度->字段/元素级别->部分字段对象为污点->对象部分字段为污点，经过JDK序列化后再反序列化",
            thisMethodTag = "aTaintCase00142_2",
            thisMethodExpectedResult = true
    )
    public Map<String, Object> aTaintCase00142_2(@RequestBody SourceTestObject testObject) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(testObject.getCmd());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
}
