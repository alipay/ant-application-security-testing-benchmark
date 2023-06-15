package com.alipay.antbenchmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.alipay.antbenchmark.tools.Utils.aesEncode;
import static com.alipay.antbenchmark.tools.Utils.getAesKey;

@SpringBootApplication
public class AntbenchmarkApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger("AntbenchmarkApplication");


    public static void main(String[] args) {
        try {
            SpringApplication.run(AntbenchmarkApplication.class, args);
            LOGGER.info("SpringBoot Web App Start!!!");
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            String data = dateFormat.format(date);
            String key = getAesKey();
            String serect = aesEncode(key, data);
            //生成每日的auth
            LOGGER.info("daily scannerauth: " + serect);
            //为了某些case的运行，生成bat/sh文件

            String osName = System.getProperty("os.name");
            if (osName.indexOf("Windows") != -1) {
                BufferedWriter out = new BufferedWriter(new FileWriter("insecureCmd.bat"));
                out.write("@echo off\n" +
                        "set line=%FOO%\n" +
                        "\n" +
                        "echo Your original string: \n" +
                        "%FOO%\n" +
                        "set num=0\n" +
                        ":LOOP\n" +
                        "call set tmpa=%%line:~%num%,1%%%\n" +
                        "set /a num+=1\n" +
                        "if not \"%tmpa%\" equ \"\" (\n" +
                        "set rline=%tmpa%%rline%\n" +
                        "goto LOOP\n" +
                        ")\n" +
                        "echo Your string reversed value is: %rline%");
                out.close();
            } else {
                BufferedWriter out = new BufferedWriter(new FileWriter("insecureCmd.sh"));
                out.write("#!/bin/sh\n" +
                        "\n" +
                        "echo Your original string:\n" +
                        "eval $FOO\n" +
                        "echo Your string reversed value is: \n" +
                        "echo $FOO | rev\n");
                out.close();
                //增加执行权限
                File file = new File("insecureCmd.sh");
                file.setExecutable(true);

            }
            BufferedWriter out = new BufferedWriter(new FileWriter("employees.xml"));
            out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<users>\n" +
                    "    <user>\n" +
                    "        <username>admin</username>\n" +
                    "        <password>admim123</password>\n" +
                    "        <secret>{FdW`nUOltj_f0H\"</secret>\n" +
                    "    </user>\n" +
                    "    <user>\n" +
                    "        <username>manager</username>\n" +
                    "        <password>a1b2c3d4</password>\n" +
                    "        <secret>|o*mE$c2BOI-j{LD</secret>\n" +
                    "    </user>\n" +
                    "    <user>\n" +
                    "        <username>user</username>\n" +
                    "        <password>1q2w3e4r5t</password>\n" +
                    "        <secret>G?2Qp%6-@xI|kR;E</secret>\n" +
                    "    </user>\n" +
                    "</users>");
            out.close();
        } catch (Throwable e) {
            LOGGER.error("SpringBoot Web App Start Fail!!! More logs can be found on 1) logs/sofa-runtime/common-error.log"
                    + " 2) logs/spring/spring.log 3) logs/mvc/common-error.log 4) logs/health-check/common-error.log, the exception:{}", e);
        }
    }

}
