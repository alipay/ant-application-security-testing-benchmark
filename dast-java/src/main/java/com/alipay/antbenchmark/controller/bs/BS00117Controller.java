package com.alipay.antbenchmark.controller.bs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping(value = "/upload")
public class BS00117Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BS00117Controller");


    @PostMapping("/BS00117")
    @ResponseBody
    public String create(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            String fileName = file.getOriginalFilename();
            String filePath = "./" + fileName;
            File dest = new File(filePath);
            Files.copy(file.getInputStream(), dest.toPath());
            return "Upload file success : " + dest.getAbsolutePath();
        } catch (Exception e) {
            log.error("doPost error. Exception :{}", e.getMessage());
            return "Failed to upload";
        }
    }


}
