### 背景
      目前工业界和学术界均无安全能力的测试评价标准，都是通过在若干样本集上的测试结果对应用安全工具进行评价的。样本集的设计
    没有标准，差异很大，无法全面客观的反映全貌，且评价结果只能得出一个总体的检出率、遗漏率和误报率数据，无法直观反映安全能
    力的优势和不足。
      为了能全面而客观的对安全能力进行评价，蚂蚁安全团队从2021年开始探索建立安全能力的评价体系。通过应用安全评价体系项目，
    希望可以衡量出应用安全产品的技术优劣，指引应用安全产品的发展方向，并可辅助用于商业化安全产品采购的技术选型，详见wiki。
      项目主要包括两部分，一部分是分语言的评价体系，另一部分是基于评价体系的Benchmark。此项目一期首先发布的是Java 
    IAST引擎能力评价体系V1.0和Benchmark。如果您有任何好的想法，欢迎与我们团队联系 
[【nodejs评价体系传送门】](https://github.com/alipay/ant-application-security-testing-benchmark-nodejs)
### 项目结构
```
├── dast-java(dast工程目录）
├── floder-img(静态资源)
├── iast-java(iast工程目录）
└── sast-java(sast工程目录）
```
### 操作说明
[《dast操作说明》](https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/dast-java/README.md)

[《iast操作说明》](https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/iast-java/README.md)

[《sast操作说明》](https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/sast-java/README.md)

### 联系我们
#### 微信
![Image text](https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/Iwechat.JPG)

### License
This project is licensed under the Apache License 2.0
