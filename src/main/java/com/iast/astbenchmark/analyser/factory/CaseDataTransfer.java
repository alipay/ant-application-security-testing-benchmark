package com.iast.astbenchmark.analyser.factory;

import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;
import com.iast.astbenchmark.analyser.bean.CaseDataCollectResultBean;

/**
 * 定制化能力：
 *
 * 对于软件厂商适配通常需要几步 拉取数据->定位Case->解析抽象->分析结果格式化
 *
 * iast iast-->获取日志文件-->根据文件逐个解析case-->
 * seeker -->获取数据接口文件/-->根据文件逐个解析case-->
 *
 * （1）case_target,用于描述case的预期结果
 *  (2) case_detection,用于获取case的检出结果
 *  (3) case_analyse 用于分析测试用例测结果状态
 */
public interface CaseDataTransfer {
     VendorEnum vendor();
     CaseDataCollectResultBean doOperation();
}
