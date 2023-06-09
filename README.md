### 背景
    目前工业界和学术界均无安全能力的测试评价标准，都是通过在若干样本集上的测试结果对应用安全工具进行评价的。样本集的设计没有标准，差异很大，无法全面客观的反映全貌，且评价结果只能得出一个总体的检出率、遗漏率和误报率数据，无法直观反映安全能力的优势和不足。
    为了能全面而客观的对安全能力进行评价，蚂蚁安全团队从2021年开始探索建立安全能力的评价体系。通过应用安全评价体系项目，希望可以衡量出应用安全产品的技术优劣，指引应用安全产品的发展方向，并可辅助用于商业化安全产品采购的技术选型，详见wiki。
    项目主要包括两部分，一部分是分语言的评价体系，另一部分是基于评价体系的Benchmark。此项目一期首先发布的是Java IAST引擎能力评价体系V1.0和Benchmark。如果您有任何好的想法，欢迎与我们团队联系
### 项目结构
```
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── iast
    │   │           └── astbenchmark
    │   │               ├── analyser   
    │   │               ├── cases
    │   │               ├── cli      
```
#### analyser:
    分析器，用于解析转换不同的IAST软件扫描结果
    如果您有新的软件需要适配，可以通过以下几个步骤：
    1.在VendorEnum枚举中增加您的软件
    2.在com.iast.astbenchmark.analyser.factory.stategy包中实现CaseDataTransfer接口
    3.注意实现过程中需要对扫描结果里面分析出'aTaintCase00xxx'这样的tagKey进行标记，您可以参考已经实现的CaseDataTransfer
    4.最后您可以通过交互命令行对您的结果进行调试
#### cases:
靶场用例，依据
[《引擎能力评价体系》](https://github.com/alipay/ant-application-security-testing-benchmark/wiki/Java-IAST%E5%BC%95%E6%93%8E%E8%83%BD%E5%8A%9B%E8%AF%84%E4%BB%B7%E4%BD%93%E7%B3%BB) 
持续更新中，您也可以评论留下您的宝贵意见或建议
#### cli:
    提供报告生成，报告查询，结果比对等功能；你可以使用help命令查看具体支持哪些命令：

### 快速入门
    项目通过AstbenchmarkApplication作为springboot项目启动，
    您可以增加-Dspring.shell.interactive.enabled=false/true来关闭/开启项目的shell交互功能
    项目启动后您可以使用AstbenchmarkApplicationTests驱动靶场用例；
    项目为您提供了交互命令行，输入help查看所需命令：
```
My Commands
search: -i :input reportId;-o :result to file;-l list ;-x export results（xmind,plain txt...）
   报告搜索功能 egg:search -i reportId || search -l all
compare: -a :input reportId1;-b: input reportId2;-o:result to file;  (compare reportId1 to reportId2)
   报告对比功能 egg: compare -a reportId1 -b reportId2
analysis: -v :input vendor;-p :input file;-c :input checkFlag;-o :result to file
   跑测结果分析 egg: analysis -v IAST -p /var/data/testresult.log 
runtest: -m :input MethodName(Which is CaseTag. eg:aTaintCase001);-i: input benchmark host (eg: http://localhost:39100/)
   跑测用例命令行 egg: runtest -m aTaintCase001
```
### 联系我们
    钉钉
    [[[![ddq]([https://github.com/alipay/ant-application-security-testing-benchmark/assets/121414736/74153170-f7cf-4a15-a0ad-21276321bdc6](https://github.com/alipay/ant-application-security-testing-benchmark/tree/main/floder-img))](https://github.com/alipay/ant-application-security-testing-benchmark/tree/main/floder-img)](https://github.com/alipay/ant-application-security-testing-benchmark/tree/main/floder-img)](https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/dingTalk.png)

### License
This project is licensed under the Apache License 2.0
