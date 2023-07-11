### 背景
    目前工业界和学术界均无安全能力的测试评价标准，都是通过在若干样本集上的测试结果对应用安全工具进行评价的。样本集的设计没有标准，差异很大，无法全面客观的反映全貌，且评价结果只能得出一个总体的检出率、遗漏率和误报率数据，无法直观反映安全能力的优势和不足。
    为了能全面而客观的对安全能力进行评价，蚂蚁安全团队从2021年开始探索建立安全能力的评价体系。通过应用安全评价体系项目，希望可以衡量出应用安全产品的技术优劣，指引应用安全产品的发展方向，并可辅助用于商业化安全产品采购的技术选型，详见wiki。
    项目主要包括两部分，一部分是分语言的评价体系，另一部分是基于评价体系的Benchmark。项目一期首先发布的是Java IAST引擎能力评价体系V1.0和Benchmark。如果您有任何好的想法，欢迎与我们团队联系。
### 快速入门
- #### 一 靶场测试
- - ##### 工程启动
    项目使用到的中间件有sqlite&redis,您可以在application&db.setting修改链接信息
    项目通过AstbenchmarkApplication作为springboot项目启动
    使用-Dspring.shell.interactive.enabled=false/true来关闭/开启项目的shell交互功能
    根据待测IAST厂商相关提示加入iastagent
- - ##### 驱动靶场
    项目启动后您可以直接运行AstbenchmarkApplicationTests驱动靶场用例
- - ##### 交互命令
    项目中内置了(靶场跑测,结果分析,报告搜索,报告对比)等命令,您可以在项目启动后输入help查看
- - - ###### --跑测结果分析
    对于厂商的跑测结果收集,通常从页面或者后台日志手动获取;当页面结果有多个时将结果构造成jsonArray([response1,response2]);日志使用接口上标记的时间戳检索

    ```分析命令 analysis: -v :input vendor;-p :input file;-c :input checkFlag;-o :result to file```

    命令示例: analysis -v IAST -p /var/data/testresult.log -o /var/data/report.txt
- - - ###### --跑测报告搜索
    ```查询命令 search: -i :input reportId;-o :result to file;-l list ;-x export results（xmind,plain txt...）```

    命令示例:search -l all （查询所有报告的ID） 
        search -i reportId （查询指定报告详情）
- #### 二 性能测试
   [《IAST性能与稳定性测试方案》](https://github.com/alipay/ant-application-security-testing-benchmark/wiki/IAST%E6%80%A7%E8%83%BD%E4%B8%8E%E7%A8%B3%E5%AE%9A%E6%80%A7%E6%B5%8B%E8%AF%95%E6%96%B9%E6%A1%88)



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
##### 分析器-用于解析转换不同的IAST软件扫描结果;
    如果您有新的软件需要适配,可以通过下面的步骤开始：
    1.在VendorEnum枚举中增加您的软件
    2.在com.iast.astbenchmark.analyser.factory.stategy包中实现CaseDataTransfer接口
    3.注意实现过程中需要对扫描结果里面分析出'aTaintCase00xxx'这样的tagKey进行标记，您可以参考已经实现的CaseDataTransfer
    4.您可以通过交互命令行对您的结果进行调试
    5.对于厂商的跑测结果收集,通常从页面或者后台日志手动获取;页面结果有多个时将结果构造成jsonArray;日志使用接口上标记的时间戳检索
#### cases:
##### 靶场用例
依据[《引擎能力评价体系》](https://github.com/alipay/ant-application-security-testing-benchmark/wiki/Java-IAST%E5%BC%95%E6%93%8E%E8%83%BD%E5%8A%9B%E8%AF%84%E4%BB%B7%E4%BD%93%E7%B3%BB)

    这里存放了所有靶场case的实现，您可以评论留下您的宝贵意见或建议
#### cli:
    提供报告生成，报告查询，结果比对等功能；你可以使用help命令查看具体支持哪些命令：
```
My Commands
search: -i :input reportId;-o :result to file;-l list ;-x export results（xmind,plain txt...）
   报告搜索功能 egg:search -i reportId || search -l all
compare: -a :input reportId1;-b: input reportId2;-o:result to file;  (compare reportId1 to reportId2)
   报告对比功能 egg: compare -a reportId1 -b reportId2
analysis: -v :input vendor;-p :input file;-c :input checkFlag;-o :result to file
   跑测结果分析 egg: analysis -v IAST -p /var/data/testresult.log -o /var/data/report.txt
runtest: -m :input MethodName(Which is CaseTag. eg:aTaintCase001);-i: input benchmark host (eg: http://localhost:39100/)
   靶场用例跑测 egg: runtest || runtest -m aTaintCase001
```

    靶场提供一套完整的性能测试方案,旨在判断日常工作模式下和极端场景下IAST的性能情况
### License
This project is licensed under the Apache License 2.0
