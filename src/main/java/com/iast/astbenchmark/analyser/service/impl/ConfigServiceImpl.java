package com.iast.astbenchmark.analyser.service.impl;

import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;
import com.iast.astbenchmark.analyser.service.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    public String getSeekerDetectionPath() {
        return seekerDetectionPath;
    }

    @Override
    public String getDetection(VendorEnum vendor) {
        switch (vendor){
            case IAST:
                return iastDetectionPath ;
            case SEEKER:
                return seekerDetectionPath;
            case DONGTAI:
                return dongtaiDetectionPath;
            case XMIRROR:
                return xmirrorDetectionPath;
        }
        return "";
    }


    @Value("${iast.reportId}")
    private String iastReportId;
    @Value("${iast.detectionPath}")
    private String iastDetectionPath;
    @Value("${iast.vulType}")
    private  String vulType;
    @Value("${seeker.detectionPath}")
    private String seekerDetectionPath;
    @Value("${dongtai.detectionPath}")
    private String dongtaiDetectionPath;
    @Value("${xmirror.detectionPath}")
    private String xmirrorDetectionPath;

    @Override
    public void doChanhge(VendorEnum vendor, String path, String checkFlag) {
       switch (vendor){
           case IAST:
               iastReportId = checkFlag;
               iastDetectionPath = path;
               break;
           case SEEKER:
               seekerDetectionPath = path;
               break;
           case DONGTAI:
               dongtaiDetectionPath=path;
               break;
          case XMIRROR:
                xmirrorDetectionPath = path;
               break;
       }
    }
}
