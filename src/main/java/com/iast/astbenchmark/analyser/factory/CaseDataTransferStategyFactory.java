package com.iast.astbenchmark.analyser.factory;

import com.iast.astbenchmark.analyser.bean.consts.VendorEnum;
import com.iast.astbenchmark.analyser.bean.CaseDataCollectResultBean;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CaseDataTransferStategyFactory {

    private  final  List<CaseDataTransfer> caseDataTransfers;
    public CaseDataTransferStategyFactory(List<CaseDataTransfer> caseDataTransfers){
        this.caseDataTransfers=caseDataTransfers;
    }
    private Map<String,CaseDataTransfer> caseDataTransferMap = Maps.newConcurrentMap();
    @PostConstruct
    public void init(){
        Optional.ofNullable(caseDataTransfers).orElseGet(ArrayList::new).forEach(
                tracer->{
                    caseDataTransferMap.put(tracer.vendor().getCode(),tracer);
                }
        );
    }

    public CaseDataCollectResultBean collect(VendorEnum vendorEnum){
       return caseDataTransferMap.get(vendorEnum.getCode()).doOperation();
    }
}
