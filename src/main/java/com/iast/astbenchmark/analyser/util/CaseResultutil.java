//package com.iast.astbenchmark.analyser.util;
//
//import cn.hutool.core.util.StrUtil;
//import com.iast.astbenchmark.analyser.consts.CaseResultEnum;
//import com.iast.astbenchmark.analyser.bean.CaseDataBean;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static com.iast.astbenchmark.analyser.consts.CaseResultEnum.TN;
//import static com.iast.astbenchmark.analyser.consts.CaseResultEnum.TP;
//
//public class CaseResultutil {
//
//    public static CaseResultEnum compare(CaseStructBean target, CaseStructBean detection){
//        List<CaseDataBean> targetList =  target.getData();
//        List<CaseDataBean> detectionList = detection.getData();
//        int targetLength =targetList.size();
//        if(targetLength==1){
//            CaseDataBean tagertBean = targetList.get(0);
//             if(CollectionUtils.isEmpty(detectionList)){
//                 return TN;
//             }
//             return compare(tagertBean,detectionList.get(0));
//        }else {
//            if(CollectionUtils.isEmpty(detectionList)||detectionList.size()!=targetLength){
//                return TN;
//            }
//            Map<String,CaseDataBean> detectionMap = detectionList.stream().collect(Collectors.toMap(CaseDataBean::getTag,e->e));
//            for (CaseDataBean caseDataBean : targetList) {
//                if(TN.equals(compare(caseDataBean,detectionMap.get(caseDataBean.getTag())))){
//                    return TN;
//                }
//            }
//        }
//       return TP;
//    }
//
//    private static CaseResultEnum compare(CaseDataBean targetBean,CaseDataBean detectionBean){
//        Boolean resultSource = true;
//        Boolean resultSinks  = true;
//        Boolean resultPropagator  =true;
//        if(StrUtil.isEmpty(targetBean.getSource())&&CollectionUtils.isEmpty(targetBean.getSinks())
//        &&CollectionUtils.isEmpty(targetBean.getPropagatorImportant())&&detectionBean!=null){
//            return TP;
//        }
//        if(detectionBean==null){
//            return TN;
//        }
//        if(!StrUtil.isEmpty(targetBean.getSource())){
//            if(targetBean.getSource().equalsIgnoreCase(detectionBean.getSource())){
//                resultSource =  true;
//            }else {
//                resultSource = false;
//            }
//        }
//
//        if(!CollectionUtils.isEmpty(targetBean.getSinks())){
//            if(CollectionUtils.isEmpty(detectionBean.getSinks())){
//                resultSinks=false;
//            }
//            for (String sink : targetBean.getSinks()) {
//                if(!detectionBean.getSinks().contains(sink)){
//                    resultSinks=false;
//                }
//            }
//           // resultSinks=true;
//        }
//
//        if(!CollectionUtils.isEmpty(targetBean.getPropagatorImportant())){
//            if(CollectionUtils.isEmpty(detectionBean.getPropagatorImportant())){
//               resultPropagator = false;
//            }
//            for (String propagator : targetBean.getPropagatorImportant()) {
//                if(!detectionBean.getPropagatorImportant().contains(propagator)){
//                    resultPropagator=false;
//                }
//            }
//           // resultPropagator =true;
//        }
//
//        if(resultSource&resultSinks&resultPropagator){
//            return TP;
//        }else {
//            return TN;
//        }
//
//    }
//}
