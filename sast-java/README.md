### 背景
    目前工业界和学术界均无安全能力的测试评价标准，都是通过在若干样本集上的测试结果对应用安全工具进行评价的。样本集的设计没有标准，差异很大，无法全面客观的反映全貌，且评价结果只能得出一个总体的检出率、遗漏率和误报率数据，无法直观反映安全能力的优势和不足。
    为了能全面而客观的对安全能力进行评价，蚂蚁安全团队从2021年开始探索建立安全能力的评价体系。通过应用安全评价体系项目，希望可以衡量出应用安全产品的技术优劣，指引应用安全产品的发展方向，并可辅助用于商业化安全产品采购的技术选型，详见wiki。
    项目主要包括两部分，一部分是分语言的评价体系，另一部分是基于评价体系的Benchmark。本bundle下发布的是Java SAST引擎能力评价体系V1.0和Benchmark。如果您有任何好的想法，欢迎与我们团队联系。
### 项目结构
```
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── sast
    │   │           └── astbenchmark
    │   │               ├── cases   
      
```
### 快速开始
#### 测试流程简介
SAST引擎能力测评包括三步：</br>
（1）准备前置条件</br>
（2）测试目标产品/引擎扫描SAST靶场</br>
（3）自动化获取评价体系测试结果 或 自主分析靶场测试结果</br>
#### 确认前置条件
（1）确认被测的SAST产品配置了以下sink点规则
```
Runtime.getRuntime().exec
org.apache.http.impl.client.CloseableHttpClient#execute
java.sql.Statement#executeQuery
SinkUtil#sink (本评价体系靶场自定义)
```
#### 自动化获取评价体系测试结果
（1）安装maven插件xastutils-maven-plugin</br>
进入xastutils目录
```
cd xast-utils
```
执行安装命令
```
mvn install
```
（2）尝试生成xast统一评价结果</br>
运行命令
```
mvn com.alipay.xast:xastutils-maven-plugin:create-scorecard -DdirectoryPath=xast靶场绝对路径 -DresultFile=工具扫描结果绝对路径
```
修改需要指定的两个参数并运行
例如
```
mvn com.alipay.xast:xastutils-maven-plugin:create-scorecard -DdirectoryPath=/Users/admin/javaProject/ant-application-security-testing-benchmark -DresultFile=/Users/admin/Downloads/Benchmark_1.2-pmd-v5.2.3-11.xml
```
如果xast-utils中没有扫描工具生成的结果文件的处理类，即Reader，可以编写对应的Reader类放到xast-utils中（欢迎将编写的Reader类提交到我们的仓库中），再运行上述命令
或者也可以手动填写靶场中的样本csv文件，修改其中的【被工具识别为漏洞】列，注意不要修改文件名最后的工具类型（sast/iast/dast）填写完成后，运行如下命令
```
mvn com.alipay.xast:xastutils-maven-plugin:create-scorecard -DdirectoryPath=靶场的绝对路径 -DcsvFile=人工填写后的csv文件绝对路径
```
例如：
```
mvn com.alipay.xast:xastutils-maven-plugin:create-scorecard -DdirectoryPath=/Users/admin/javaProject/ant-application-security-testing-benchmark -DcsvFile=/Users/admin/javaProject/ant-application-security-testing-benchmark/results/xAST_v_1.0.0_pmd_v5.2.3_sast.csv
```
运行成功后，会生成csv表和html文件，其中csv表记录了扫描工具在每个靶场case上的识别情况，html文件为最终的评价结果，使用浏览器打开html文件，根据html文件中的各项数值了解扫描工具在xast评价体系中的表现。
### License
This project is licensed under the Apache License 2.0
