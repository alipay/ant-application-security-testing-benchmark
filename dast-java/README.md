# DAST引擎能力评价体系设计
蚂蚁黑盒扫描器靶场是一款配合蚂蚁黑盒扫描引擎评价体系食用的黑盒靶场。我们借鉴了[OWASP Benchmark](https://github.com/OWASP-Benchmark/BenchmarkJava)靶场的设计思路，对接口管理进行了增强，此靶场具备以下增强功能
- 在/index.html和/index路径下，提供靶场全部接口信息，并内置了对接口的监控，自动统计爬虫对HTML表单和Ajax请求的爬取能力
- 根据蚂蚁黑盒评价体系，提供了丰富的接口以测评黑盒扫描引擎各项能力
- 提供了扫描引擎评价卡自动生成能力，只需将扫描结果输入相应文件即可生成扫描引擎能力评价卡和规则评价卡（[使用浏览器打开此示例HTML文件](src/main/resources/callscanner/report-2023-05-16-14-11-45.html)）

## 快速开始
### 测试流程简介
DAST引擎能力测评包括四步：

（1）准备前置条件

（2）启动Benchmark

（3）使用黑盒扫描器对靶场进行扫描

（4）获取测试结果

### 准备前置条件
#### 安装数据库
靶场运行需要准备mysql数据 5.7.x版本
- [安装包地址](https://downloads.mysql.com/archives/community/)，推荐在靶场运行服务器安装mysql，可减少配置修改量
- 创建数据库
```
create database benchmarkDataBase charset='utf8';
```
- 创建数据表
```
切换数据库
use benchmarkDataBase;

执行建表语句
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for USERS
-- ----------------------------
DROP TABLE IF EXISTS `USERS`;
CREATE TABLE `USERS` (
  `USERID` int(11) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11304 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scancallbacklog
-- ----------------------------
DROP TABLE IF EXISTS `scancallbacklog`;
CREATE TABLE `scancallbacklog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scanid` int(11) DEFAULT NULL,
  `vulnparam` longtext,
  `vulntype` longtext,
  `vulnexists` varchar(255) DEFAULT NULL,
  `time` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9202 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for scans
-- ----------------------------
DROP TABLE IF EXISTS `scans`;
CREATE TABLE `scans` (
  `scanid` int(11) NOT NULL AUTO_INCREMENT,
  `scanresultjson` longtext,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`scanid`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

```
- 修改数据库密码，推荐修改为靶场代码默认账号密码root/12345678

#### 编译
```bash
mvn package -Dmaven.test.skip=true
```
生成的jar文件在target/antbenchmark-0.0.1-SNAPSHOT.jar

## 运行Benchmark
```bash
cd  target/
java -jar antbenchmark-0.0.1-SNAPSHOT.jar 
```
靶机默认开启8080端口，测试扫描引擎时，推荐配置扫描初始路径为/index.html和/index，以便爬虫获取所有接口信息。如果扫描引擎不支持爬虫，可通过浏览器配置代理，手动访问/index和/index.html以获取所有接口及其攻击参数

#### 刷新统计功能
由于靶场内置了爬虫统计功能，靶场无法感知扫描结束时间，因此再次测评扫描引擎时，需要访问靶场antbenchmar/__restart接口或重启靶场

#### 测试Case
*注意*：由于靶场部分接口具备入侵风险，强烈推荐在内部网络开启靶机提供给扫描器进行测试。如需在互联网环境开启靶机，需要将/src/main/java/com/alipay/antbenchmark/filter/ScanAuthFilter.java文件中的Component注解取消，开启鉴权。

开启鉴权所有的Case都有鉴权，需要在请求头中配置scannerauth才可以访问。

scannerauth会根据日期变化，启动靶场后会打印在终端，也可以执行src/main/resources/callscanner/Generator.class来获得
```bash
cd src/main/resources/callscanner/
java -cp ./ Generator
```

## 获得测试结果
对于扫描结束后，靶场具有根据扫描结果自动生成引擎评价卡的能力，步骤如下

（1）安装maven插件xastutils-maven-plugin
进度xastutils目录

```
cd xast-utils
```

执行安装命令
```
mvn install
```

（2）尝试生成xast统一评价结果
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

`或者也可以手动填写靶场中的样本csv文件`，修改其中的【被工具识别为漏洞】列，注意不要修改文件名最后的工具类型（sast/iast/dast）填写完成后，运行如下命令

```
mvn com.alipay.xast:xastutils-maven-plugin:create-scorecard -DdirectoryPath=靶场的绝对路径 -DcsvFile=人工填写后的csv文件绝对路径
```

例如：
```
mvn com.alipay.xast:xastutils-maven-plugin:create-scorecard -DdirectoryPath=/Users/admin/javaProject/ant-application-security-testing-benchmark -DcsvFile=/Users/admin/javaProject/ant-application-security-testing-benchmark/results/xAST_v_1.0.0_pmd_v5.2.3_sast.csv
```

运行成功后，会生成csv表和html文件，其中csv表记录了扫描工具在每个靶场case上的识别情况，html文件为最终的评价结果，使用浏览器打开html文件，根据html文件中的各项数值了解扫描工具在xast评价体系中的表现。

## FAQ
https://github.com/alipay/ant-application-security-testing-benchmark/wiki/FAQ

## 参与共建
https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/CONTRIBUTING.md
