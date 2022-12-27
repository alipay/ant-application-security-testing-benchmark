package com.iast.astbenchmark.cli;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.iast.astbenchmark.analyser.bean.CaseDataCollectResultBean;
import com.iast.astbenchmark.analyser.bean.CaseResultbean;
import com.iast.astbenchmark.analyser.bean.consts.CaseResultEnum;
import com.iast.astbenchmark.analyser.bean.consts.CaseTypeEnum;
import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;
import com.iast.astbenchmark.analyser.cache.CaseStuctCache;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class FormatResultUtil {
    // TODO 更友好的展示
    public static String formatAll(VendorEnum vendorEnum, CaseDataCollectResultBean resultBean){

        StringBuffer buffer = new StringBuffer();
        buffer.append("\n");
        buffer.append("软件提供商:"+resultBean.getVendor().getDescription()+"\n");
        buffer.append("报告ID:"+resultBean.getReportId()+"\n");
        buffer.append("报告时间:"+DateUtil.date(resultBean.getCaseTime())+"\n");
        buffer.append("------------------------------------------------------------------------------------"+"\n");
        buffer.append("检测结果:"+totalResult(resultBean.getCaseDetectionItems())+"\n");
        buffer.append("类别概览:\n"+detail(resultBean.getCaseDetectionItems())+"\n");
        buffer.append("测试详情:\n"+detailItem(resultBean.getCaseDetectionItems())+"\n");
        return buffer.toString();
    }
    public static String compareAndForamt( CaseDataCollectResultBean resultBean1,CaseDataCollectResultBean resultBean2){

        StringBuffer buffer = new StringBuffer();
        buffer.append("\n");
        buffer.append("对比结果汇总：-----------------------------------------------------------------\n");
        buffer.append(resultBean1.getVendor().getDescription()+"(a):"+totalResult(resultBean1.getCaseDetectionItems())+"\n");
        buffer.append(resultBean2.getVendor().getDescription()+"(b):"+totalResult(resultBean2.getCaseDetectionItems())+"\n");
        buffer.append("差异详情：-----------------------------------------------------------------\n");
        buffer.append(compare(resultBean1,resultBean2));
        return buffer.toString();
    }

    private static String compare(CaseDataCollectResultBean resultBean1, CaseDataCollectResultBean resultBean2) {
        if(CollectionUtils.isEmpty(resultBean1.getCaseDetectionItems())){
            return resultBean1.getVendor()+"缺少对比结果";
        }
        if(CollectionUtils.isEmpty(resultBean2.getCaseDetectionItems())){
            return resultBean2.getVendor()+"缺少对比结果";
        }
        StringBuffer buffer = new StringBuffer();
        Map<String, CaseResultbean> resultbeanMap2 = flatResultToMap(resultBean2);
        for (CaseResultbean caseDetectionItem : resultBean1.getCaseDetectionItems()) {
            String compareres = compareBean(caseDetectionItem,resultbeanMap2.get(caseDetectionItem.getCaseNo()),resultBean1.getVendor().getDescription(),resultBean2.getVendor().getDescription());
            if(StrUtil.isNotEmpty(compareres)){
                buffer.append(compareres);
            }
        }
        return buffer.toString();
    }

    private static String compareBean(CaseResultbean bean1,CaseResultbean bean2,String vendor1,String vendor2){
        if(bean2==null){
            return CaseStuctCache.getLeafByCaseNo(bean1.getCaseNo())
                    .getFullName()+"("+vendor1+"(a):"+bean1.getResult().getDesc()+","+vendor2+"(b):"+"NULL"+")\n";
        }
        if(!bean1.getResult().equals(bean2.getResult())){
            return CaseStuctCache.getLeafByCaseNo(bean1.getCaseNo())
                    .getFullName()+"("+vendor1+"(a):"+bean1.getResult().getDesc()+","+vendor2+"(b):"+bean2.getResult().getDesc()+")\n";
        }
        return null;
    }
    private static Map<String,CaseResultbean> flatResultToMap(CaseDataCollectResultBean resultBean ){
        return  resultBean.getCaseDetectionItems().stream().collect(Collectors.toMap(CaseResultbean::getCaseNo,e->e,(k1,k2)->k1));
    }
    private static String getCaseDescption(CaseResultbean resultbean){
        return resultbean.getCaseDesc();
    }
    private static String detailItem(List<CaseResultbean> list) {
        StringBuffer buffer = new StringBuffer();
        for (CaseTypeEnum value : CaseTypeEnum.values()) {
            buffer.append(value.getDesc()+"-----------------------------------------------------------------\n");
            //.sorted((k1,k2)->{
            //                return k1.getCaseDesc().compareTo(k2.getCaseDesc());
            //            }).  去掉排序
            List<CaseResultbean> resultEnumList = list.stream().filter(e->value.equals(e.getCaseTypeEnum())).collect(Collectors.toList());
          buffer.append(formatCaseResult(resultEnumList));
        }
        return buffer.toString();
    }
    private static  String formatCaseResult(List<CaseResultbean> resultEnumList){
        StringBuffer buffer = new StringBuffer();
        for (CaseResultbean resultbean : resultEnumList) {
           // buffer.append(resultbean.getCaseDesc()+":"+resultbean.getResult().getDesc()+"\n");
            buffer.append(resultbean.getCaseNo()+"->"
                    + CaseStuctCache.getLeafByCaseNo(resultbean.getCaseNo())
                    .getFullName()
                    +":"+resultbean.getResult().getDesc()+"\n");
        }
        return buffer.toString();
    }

    public static String format(List<String> stringList){
        return stringList.toString();
    }
    private static String detail(List<CaseResultbean> list){
        StringBuffer buffer = new StringBuffer();
        for (CaseTypeEnum value : CaseTypeEnum.values()) {
            buffer.append(value.getDesc()+"\n");
            Map<CaseResultEnum,Long> res = list.stream().filter(e->value.equals(e.getCaseTypeEnum())).collect(Collectors.groupingBy(CaseResultbean::getResult,Collectors.counting()));
            buffer.append("TOTAL:"+ res.values().stream().mapToLong(e->e).sum()+" ");
            buffer.append(formatMap(res));
            buffer.append("\n");
        }
        return buffer.toString();
    }
    private static String totalResult(List<CaseResultbean> list){
        StringBuffer buffer = new StringBuffer();
        buffer.append("TOTAL:"+list.size()+ " ");
        Map<CaseResultEnum,Long> res = list.stream().collect(Collectors.groupingBy(CaseResultbean::getResult,Collectors.counting()));
        String data =  formatMap(res);
        buffer.append(data);
        return buffer.toString();
    }
    private static String formatMap(Map<CaseResultEnum,Long> res){
       StringBuffer buffer = new StringBuffer();
       AtomicReference<Long> rightSize = new AtomicReference<>(0L);
        res.forEach((k,v)->{
            if(k.equals(CaseResultEnum.TP)||CaseResultEnum.TN.equals(k)){
                rightSize.set(rightSize.get() + v);
            }else {
                buffer.append(k.getDesc()+":"+v+" ");
            }

        });
        buffer.append(CaseResultEnum.TP.getDesc()+":"+rightSize+" ");
        return buffer.toString();
    }

}
