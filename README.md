### 背景
    交互式应用安全测试(Interactive Application Security Testing，简称IAST) 是一种将检测探针植入应用程序内部，在程序运行时采集各种信息进行漏洞检测的技术。
Ant Benchmark初始目的在于打造一套客观公正的评价标准,用于衡量IAST软件真实的安全能力；在同一个靶场下不同软件之间安全能力进行一个横向比较，可以直观的给出软件之间的能力差异；当前这套标准旨在评价IAST软件的基础引擎能力，如果您有任何好的想法，欢迎与我们团队联系。
### 项目结构
#### analyser:
分析器，用于解析转换不同的IAST软件扫描结果
#### cases:
靶场用例
#### cli:
提供报告生成，报告查询，结果比对等功能；你可以使用help命令查看具体支持哪些命令：
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

### 快速入门
    您可以使用AstbenchmarkApplicationTests 驱动靶场用例；
    如果您有新的软件需要接入靶场，您可以在analyser中实现数据转换；
    您可以在交互命令行中输入help查看所需命令：
```
My Commands
search: -i :input reportId;-o :result to file;-l list ;-x export results（xmind,plain txt...）
compare: -a :input reportId1;-b: input reportId2;-o:result to file;  (compare reportId1 to reportId2)
analysis: -v :input vendor;-p :input file;-c :input checkFlag;-o :result to file
runtest: -m :input MethodName(Which is CaseTag. eg:aTaintCase001);-i: input benchmark host (eg: http://localhost:39100/)
```
### License
This project is licensed under the Apache License 2.0
