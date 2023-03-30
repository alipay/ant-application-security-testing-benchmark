package com.iast.astbenchmark.analyser.service.impl;

import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;
import com.iast.astbenchmark.analyser.service.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Override
    public String getIastReportId() {
        return iastReportId;
    }
    @Override
    public String getIastDetectionPath() {
        return iastDetectionPath;
    }
    @Override
    public String getVulType() {
        return vulType;
    }

    @Override
    public String getDetection(VendorEnum vendor) {
        String key=vendor.getCode().toLowerCase();
        if(cacheMap.containsKey(key)){
            return cacheMap.get(key);
        }else {
            return detectionMap.get(key);
        }
    }

    @Value("${iast.reportId}")
    private String iastReportId;
    @Value("${iast.detectionPath}")
    private String iastDetectionPath;
    @Value("${iast.vulType}")
    private  String vulType;
    @Value("#{${detectionPath}}")
    private Map<String,String> detectionMap;
    private Map<String,String> cacheMap = new HashMap<>();

    @Override
    public void doChanhge(VendorEnum vendor, String path, String checkFlag) {

        String key=vendor.getCode().toLowerCase();
       switch (vendor){
           case IAST:
               iastReportId = checkFlag;
               iastDetectionPath = path;
               break;
           default:
               if(cacheMap.containsKey(key)){
                   cacheMap.replace(key,path);
               }else {
                   cacheMap.put(key,path);
               }
               break;
       }
    }
}
