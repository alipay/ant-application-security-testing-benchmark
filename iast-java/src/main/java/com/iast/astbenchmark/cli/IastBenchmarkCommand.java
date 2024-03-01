package com.iast.astbenchmark.cli;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.iast.astbenchmark.analyser.bean.CaseDataCollectResultBean;
import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;
import com.iast.astbenchmark.analyser.service.ConfigService;
import com.iast.astbenchmark.analyser.service.DataAnalysisService;
import com.iast.astbenchmark.analyser.util.MermindUtil;
import com.iast.astbenchmark.cli.test.AutoRunTest;
import com.iast.astbenchmark.cli.xmind.XMindReaderUtil;
import com.iast.astbenchmark.cli.xmind.XmindUtil;
import lombok.extern.slf4j.Slf4j;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@Slf4j
public class IastBenchmarkCommand {

    @Autowired
    private DataAnalysisService dataAnalysisService;
    @Autowired
    private ConfigService       configService;
    @Bean
    public PromptProvider promptProvider() {
        return () -> new AttributedString("IAST_SHELL:>",
                AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
    /**
     * 1 run Test ?
     */

    @ShellMethod("分析iast软件的跑测结果 -v :input vendor;-p :input file;-c :input checkFlag;-o :result to file")
    public String analysis(@ShellOption("-v") String vendor
            , @ShellOption(defaultValue = "", value = "-p") String path, @ShellOption(defaultValue = "", value = "-c") String checkFlag,
                           @ShellOption(defaultValue = "", value = "-o") String resultFile) {

        String checkParam = checkParam(vendor, path, checkFlag, resultFile);
        if (StrUtil.isNotEmpty(checkParam)) {
            return checkParam;
        }
        long id;
        VendorEnum vendorEnum = VendorEnum.valueOf(vendor.toUpperCase());

        if (StrUtil.isNotEmpty(path)) {
            configService.doChanhge(vendorEnum, path, checkFlag);
        }
        try {
            id = dataAnalysisService.doAnalysisAndDB(vendorEnum);
        } catch (SQLException e) {
            return "ERROR:分析失败";
        }

        try {
            CaseDataCollectResultBean resultBean = dataAnalysisService.searchResult(id);
            String res = FormatResultUtil.formatAll(vendorEnum, resultBean);
            if (StrUtil.isNotEmpty(resultFile)) {
                FileUtil.writeString(res, resultFile, Charset.forName("utf-8"));
                return "结果已写入文件" + resultFile + "请查看";
            }
            return res;
        } catch (Exception e) {
            log.error("分析异常:{}", e);
            return "ERROR:结果查询失败";
        }
    }

    @ShellMethod("查询已跑测的结果报告 -i :input reportId;-o :result to file;-l list ;-x export results（xmind,plain txt...）")
    public String search(@ShellOption(value = {"-i"}, defaultValue = "") String reportId,
                         @ShellOption(defaultValue = "", value = "-o") String resultFile
            , @ShellOption(defaultValue = "", value = "-l") String listId,
                         @ShellOption(defaultValue = "false", value = "-x") Boolean exportFlag) {
        try {
            String checkParam = checkParamSearch(reportId, resultFile, listId, exportFlag);
            if (StrUtil.isNotEmpty(checkParam)) {
                return checkParam;
            }
            if (StrUtil.isNotEmpty(reportId)) {
                CaseDataCollectResultBean resultBean = dataAnalysisService.searchResultbyReportId(reportId);
                String res = FormatResultUtil.formatAll(resultBean.getVendor(), resultBean);
                if (StrUtil.isNotEmpty(resultFile)) {
                    FileUtil.writeString(res, resultFile, Charset.forName("utf-8"));
                    return "结果已写入文件" + resultFile + "请查看";
                } else if (exportFlag) {
                    return XmindUtil.export(resultBean);
                    //导出xmind
                    //导出html
                    //导出文本
                }
                return res;
            } else if (StrUtil.isNotEmpty(listId)) {
                List<String> res = dataAnalysisService.getAllReportId(listId);
                return FormatResultUtil.format(res);
            }
        } catch (Exception e) {
            log.error("结果查询失败:{}", e);
            return "ERROR:结果查询失败";
        }
        return "请根据提示输入操作";
    }

    @ShellMethod("对比两次跑测报告的差异 -a :input reportId1;-b: input reportId2;-o:result to file;  (compare reportId1 to reportId2)")
    public String compare(@ShellOption(value = "-a") String reportId1, @ShellOption(value = "-b") String reportId2,
                          @ShellOption(defaultValue = "", value = "-o") String resultFile) {
        try {
            String checkParam = checkParamCompare(reportId1, reportId2, resultFile);
            if (StrUtil.isNotEmpty(checkParam)) {
                return checkParam;
            }

            CaseDataCollectResultBean resultBean1 = dataAnalysisService.searchResultbyReportId(reportId1);
            CaseDataCollectResultBean resultBean2 = dataAnalysisService.searchResultbyReportId(reportId2);
            String res = FormatResultUtil.compareAndForamt(resultBean1, resultBean2);
            if (StrUtil.isNotEmpty(resultFile)) {
                FileUtil.writeString(res, resultFile, Charset.forName("utf-8"));
                return "结果已写入文件" + resultFile + "请查看";
            }
            return res;
        } catch (Exception e) {
            log.error("比较异常:{}", e);
            return "ERROR:比较异常";
        }
    }

    @ShellMethod("跑测靶场 -m :input MethodName(Which is CaseTag. eg:aTaintCase001);-i: input benchmark host (eg: http://localhost:39100/)")
    public String runtest(@ShellOption(value = {"-m"}, defaultValue = "") String methodName,
                          @ShellOption(defaultValue = "", value = "-i") String url) {

        try {
            if (StringUtils.isEmpty(methodName)) {
                return AutoRunTest.run(url);
            } else {
                return AutoRunTest.run(methodName, url);
            }
        } catch (Exception e) {
            log.error("跑测异常:{}", e);
            return "ERROR:跑测异常";
        }
    }

    @ShellMethod("导出评价体系脑图(mermind格式) -o :mermind scripts to md file -x : mermind scripts from xmind file")
    public String mermind(@ShellOption(defaultValue = "", value = "-o") String resultFile,
                          @ShellOption(defaultValue = "", value = "-x") String xmindFile) {

        try {
            if(StrUtil.isNotEmpty(xmindFile)){
                if(!xmindFile.endsWith(".xmind")){
                    return "ERROR:请输入以xmind结尾的xmind文件路径";
                }
                if(!FileUtil.isFile(xmindFile)){
                    return "ERROR:请检查xmind文件是否存在";
                }
                return XMindReaderUtil.convertXmindToMermind(xmindFile);
            }else {
                if(StrUtil.isNotEmpty(resultFile)&&!resultFile.endsWith(".md")){
                    return "ERROR:请输入以md结尾的markdown文档";
                }
                String res = MermindUtil.printMermindScript();
                if(StrUtil.isNotEmpty(resultFile)){
                    FileUtil.writeString(res, resultFile, Charset.forName("utf-8"));
                    return "结果已写入文件" + resultFile + "中,请查看";
                }
                return res;
            }
        } catch (Exception e) {
            log.error("异常:{}", e);
            return "ERROR:异常";
        }
    }

    private String checkParamSearch(String reportId, String resultFile, String vendor, Boolean exportFlag) {

        if (StrUtil.isNotEmpty(reportId) && StrUtil.isNotEmpty(vendor)) {
            return "请选择输入一个操作 -i or -l";
        }
        if (StrUtil.isNotEmpty(vendor) && !"all".equalsIgnoreCase(vendor)) {
            try {
                VendorEnum.valueOf(vendor.toUpperCase());
            } catch (Exception e) {
                return "厂商不存在，请输入all或者" + Arrays.stream(VendorEnum.values()).map(v -> v.getCode()).collect(Collectors.toList());
            }
        }
        if (exportFlag && StrUtil.isEmpty(reportId)) {
            return "请指定厂商和检出报告ID";
        }

        return "";
    }

    private String checkParamCompare(String reportId1, String reportId2, String resultFile) {
        if (StrUtil.isEmpty(reportId1) && StrUtil.isEmpty(reportId2)) {
            return "请输入需要对比的报告";
        }
        return "";
    }

    private String checkParam(String vendor, String path, String checkFlag, String resultFile) {
        try {
            VendorEnum vendorEnum = VendorEnum.valueOf(vendor.toUpperCase());
            if (StrUtil.isEmpty(path) || !FileUtil.isFile(path)) {
                return vendor + "请检查输入路径:" + path;
            }

            if (vendorEnum.getCode().equals(VendorEnum.IAST)) {
                if (StrUtil.isEmpty(checkFlag)) {
                    return "请输入IAST日志标记checkFlag:" + checkFlag;
                }
            }
        } catch (Exception e) {
            return "厂商不存在，请输入:" + Arrays.stream(VendorEnum.values()).map(v -> v.getCode()).collect(Collectors.toList());
        }
        return "";
    }
    /**
     * 2 分析结果
     * iast  file check
     * seeker file
     */

    /**
     * 3 输出结果
     *  -o 输出文件
     */

}

