# 黑盒扫描器靶场
蚂蚁黑盒扫描器靶场是一款配合蚂蚁黑盒扫描引擎评价体系食用的黑盒靶场。我们借鉴了[OWASP Benchmark](https://github.com/OWASP-Benchmark/BenchmarkJava)靶场的设计思路，对接口管理进行了增强，此靶场具备以下增强功能
- 在/index.html和/index路径下，提供靶场全部接口信息，并内置了对接口的监控，自动统计爬虫对HTML表单和Ajax请求的爬取能力
- 根据蚂蚁黑盒评价体系，提供了丰富的接口以测评黑盒扫描引擎各项能力
- 提供了扫描引擎评价卡自动生成能力，只需将扫描结果输入相应文件即可生成扫描引擎能力评价卡和规则评价卡（[使用浏览器打开此示例HTML文件](src/main/resources/callscanner/report-2023-05-08-16-17-54.html)）

## 编译运行
### 数据库
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

### 编译
```bash
mvn package -Dmaven.test.skip=true
```
生成的jar文件在target/antbenchmark-0.0.1-SNAPSHOT.jar 

### 执行
```bash
cd  target/
java -jar antbenchmark-0.0.1-SNAPSHOT.jar 
```
靶机默认开启8080端口，测试扫描引擎时，推荐配置扫描初始路径为/index.html和/index，以便爬虫获取所有接口信息。如果扫描引擎不支持爬虫，可通过浏览器配置代理，手动访问/index和/index.html以获取所有接口及其攻击参数

### 刷新统计功能
由于靶场内置了爬虫统计功能，靶场无法感知扫描结束时间，因此再次测评扫描引擎时，需要访问靶场antbenchmar/__restart接口或重启靶场

## 测试Case
*注意*：由于靶场部分接口具备入侵风险，强烈推荐在内部网络开启靶机提供给扫描器进行测试。如需在互联网环境开启靶机，需要将/src/main/java/com/alipay/antbenchmark/filter/ScanAuthFilter.java文件中的Component注解取消，开启鉴权。

开启鉴权所有的Case都有鉴权，需要在请求头中配置scannerauth才可以访问。

scannerauth会根据日期变化，启动靶场后会打印在终端，也可以执行src/main/resources/callscanner/Generator.class来获得
```bash
cd src/main/resources/callscanner/
java -cp ./ Generator
```

## 添加Case

每添加一个case需要在这几个地方加：

|  路径   | 说明  |
|  ----  | ----  |
| src/main/java/com/alipay/antbenchmark/controller/bs/BS00097.java  | 控制器的代码 |
| src/main/resources/scorecard/BS00097.yaml  | 接口yaml描述 |
| src/main/resources/callscanner/taskinfo/taskinfo.py  | 用于测试该case的扫描任务信息 |
| src/main/resources/callscanner/payloads  | 可复现的Payload |
| src/main/java/com/alipay/antbenchmark/controller/IndexController.java#indexAjax  | 前端发起Ajax请求的JS代码 |

每个需要添加配置的地方，均可模仿已有案例进行添加
## Case的控制器代码

SOFABoot可用的控制器，URL路径为/{漏洞类型}/{Benchmark编号}

其中Benchmark编号+Controller作为控制器的类名

## Case Yaml描述

一个典型的Yaml描述如下：
```yaml
benchmark-version: "1.2"
category: "cmdi"
test-number: "00005"
vulnerability: "true"
cwe: "78"
```
其中必要的信息有category，即漏洞类型信息，test-number及去掉了BS的benchmark编号，vulnerability是漏洞是否真实存在。

靶场会扫描Yaml目录，读取Yaml以统计扫描结果


## Case 扫描任务信息

一个典型的扫描任务信息如下
```python
[
        '/pathtraver/BS00001',
        'read_arbitary_files',
        'post',
        {
            'BS00001': 'BS00001',
            'Cookie': 'BS00001=BS00001;',
            'Referer': 'http://localhost',
        },
        'test=123',
],
```
扫描任务信息是一个Python的List,内容是\[相对URL,扫描插件的名称，请求方式，头，POST数据\]，如果请求方式是GET，那么POST数据任意指定即可。

扫描客户端会读取扫描任务信息以调用扫描器


## Case 可复现Payload

不写可复现Payload不会影响系统的运行，但是为了保证Case质量和可用性，推荐填写。

可复现Payload是HTTP包，示例如下
BS00001.txt:
```
POST /pathtraver/BS00001 HTTP/1.1
Host: localhost
Connection: close
Accept-Encoding: gzip, deflate
Accept: text/html,application/json,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
User-Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4;
BS00101: BS00101
Cookie: BS00001=../../../../../../etc/passwd;
Referer: http://localhost/
Content-Type: application/x-www-form-urlencoded
scannerauth:cd117e0a9365670f19c768032f8dabfe
Content-Length: 8

test=123
```

## 引擎评价卡自动生成
对于扫描结束后，靶场具有根据扫描结果自动生成引擎评价卡的能力，步骤如下
- 修改/src/main/resources/callscanner/autoCommit.py中TARGET变量为当前靶机信息
- 按照autoCommit.py文件中foundVuls变量格式，将扫描结果提取到foundVuls变量中
- 运行在靶机启动的状态下，在python2.7环境下执行autoCommit.py
```bash
python autoCommit.py
```
- 执行autoCommit.py后生成的html文件即为此次扫描引擎评价卡