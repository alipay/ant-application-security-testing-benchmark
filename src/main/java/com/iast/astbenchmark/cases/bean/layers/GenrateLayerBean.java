package com.iast.astbenchmark.cases.bean.layers;

import cn.hutool.core.io.FileUtil;

import java.nio.charset.Charset;

public class GenrateLayerBean {

    public static void main(String[] args) {
        for (int i = 5; i < 100; i++) {
            String className = "LayerBaseBean"+i;
            String parentClassName = "LayerBaseBean"+(i-1);
            String m1 = "private String  cmda"+i+";";
            String m2 ="private String  cmdb"+i+";";
            String file = "package com.iast.astbenchmark.cases.bean.layers;\n" +
                    "\n" +
                    "import lombok.Data;\n" +
                    "\n" +
                    "@Data\n" +
                    "public class "+ className +" extends "+parentClassName+"{\n" +
                    "    "+m1+"\n" +
                    "    "+m2+"\n" +
                    "}";
            FileUtil.writeString(file,FileUtil.file("/Users/curry/IdeaProjects/antcode/astbenchmark/src/main/java/com/iast/astbenchmark/cases/bean/layers/"+className+".java"), Charset.forName("utf-8"));
        }

    }
}
