package com.iast.astbenchmark.analyser.service;

import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;

public interface ConfigService {
    String getIastReportId();

    String getIastDetectionPath();

    String getVulType();
    String getDetection(VendorEnum vendorEnum);

    void doChanhge(VendorEnum vendor, String path, String checkFlag);
}
