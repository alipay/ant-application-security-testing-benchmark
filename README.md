### 背景
    目前工业界和学术界均无安全能力的测试评价标准，都是通过在若干样本集上的测试结果对应用安全工具进行评价的。样本集的设计没有标准，差异很大，无法全面客观的反映全貌，且评价结果只能得出一个总体的检出率、遗漏率和误报率数据，无法直观反映安全能力的优势和不足。
    为了能全面而客观的对安全能力进行评价，蚂蚁安全团队从2021年开始探索建立安全能力的评价体系。通过应用安全评价体系项目，希望可以衡量出应用安全产品的技术优劣，指引应用安全产品的发展方向，并可辅助用于商业化安全产品采购的技术选型，详见wiki。
    项目主要包括两部分，一部分是分语言的评价体系，另一部分是基于评价体系的Benchmark。项目一期首先发布的是Java IAST引擎能力评价体系V1.0和Benchmark。如果您有任何好的想法，欢迎与我们团队联系。
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
