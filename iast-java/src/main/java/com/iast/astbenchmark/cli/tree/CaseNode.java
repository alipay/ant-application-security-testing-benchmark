package com.iast.astbenchmark.cli.tree;

import com.iast.astbenchmark.analyser.bean.CaseTargetBean;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CaseNode {

    /**
     * 基础数据
     */
    private String id;
    private String name;
    private Integer deepth;

    /**
     * 关系
     */
    private CaseNode parent;
    private List<CaseNode> children;

    /**
     *  类型
     */
    private CaseNodeType type;


    /**
     * 以上为存储结构
     * children 叶子节点数据区
     */

    private CaseTargetBean leafData;
    public String getFullName(){
        try {
            return this.getFullName(parent,name);
        }catch (Exception e){
            throw e;
        }
    }
    @Override
    public String toString() {
        if(type==null){
            return "ERROR TYPE";
        }
        switch (type){
//            case RO:
//                return  "{" +
//                        "\"type\":\"" + type +"\""+
//                        ", \"id\":" + id +
//                        ", \"name\":\"'" + name + "\""+
//                        ", \"deepth\":" + deepth +
//                        ", \"children\":" + children +
//                        '}';
            case LEAF:
                String fullName = getFullName(parent,name);
                return  "{" +
                        "\"type\":\"" + type +"\""+
                        ", \"id\":" + id +
                        ", \"name\":\"'" + fullName + "\""+
                        ", \"deepth\":" + deepth +
                        ", \"leafData\":" + leafData +
                        '}';
            default:
                return  "{" +
                        "\"type\":\"" + type +"\""+
                        ", \"id\":" + id +
                        ", \"name\":\"'" + name + "\""+
                        ", \"deepth\":" + deepth +
                        ", \"children\":" + children +
                        '}';
        }

    }
    private String getFullName(CaseNode parent,String name){
        if(parent==null){
            return name;
        }
        name = parent.getName()+"->"+name;
        if(parent.type.equals(CaseNodeType.ROOT)){
            return name;
        }else {
            return getFullName(parent.getParent(),name);
        }
    }
}
