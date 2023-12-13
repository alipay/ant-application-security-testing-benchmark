<div align='center' ><font size="18">xAST评价体系开源项目简介</font></div>

# 联系我们
## 微信

<p align="center">
  <img src="https://github.com/alipay/ant-application-security-testing-benchmark/raw/main/floder-img/Iwechat.png" alt="微信交流群" width="200"/>
  <img src="https://github.com/alipay/ant-application-security-testing-benchmark/raw/main/floder-img/xzs.png" alt="微信小助手" width="200"/>
</p>
<p align="center">
  <em>微信交流群</em> <em>微信小助手</em>
</p>


## 邮箱

xast-contact@service.alipay.com

## nodejs体系传送门
[【nodejs评价体系传送门】](https://github.com/alipay/ant-application-security-testing-benchmark-nodejs)

# 项目背景

xAST（应用安全测试技术）对于保障软件安全可靠发挥着越来越重要的作用，目前每一类产品（SAST/IAST/DAST/SCA/MAST等）都有至少数十款商业化或开源产品供客户选择。与此同时，一些甲方企业也在自研xAST产品。不管是商业采购还是选择开源产品还是自研，大家都面临一个共同的难题，如何客观衡量一款xAST产品的技术水平？

目前工业界和学术界都没有对xAST产品技术能力进行衡量的评价标准，通常是使用若干漏洞样本集的测试结果进行技术评价，业界常见的漏洞样本集见图1，测试结果示例见图2。

<div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image1.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">图1 业界常见漏洞样本集</div>
  </center>
   </div>

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image2.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">图2 不同开源SAST在Juliet Java漏洞样本集上的测试结果示例</div>
  </center>
   </div>

一方面，由图1可以看出各样本集之间差异巨大（从数十个样本到十万个样本），究其根源在于漏洞样本集缺乏体系化设计，都是漏洞样本的简单堆砌，测试结果的完整性得不到保障；漏洞样本集所测试的功能点分布不均，测试结果也缺乏合理性。

另一方面，由图2可以看出，由于缺乏评价体系，评价结果对用户是个“黑盒”，评价结果只能给出总体的召回率和误报率数据，无法细粒度的刻画产品的技术优势与不足。

针对xAST领域缺乏有效衡量技术能力标准的业界痛点，**蚂蚁安全团队**联合**蚂蚁程序分析团队**、**浙江大学网络空间安全学院**的20余位专家学者，共同设计了xAST评价体系及其测试样本套件Benchmark，致力于成为应用安全测试工具的“**度量衡**”。

# 项目目标与价值

**目标**：打造具备行业共识的xAST能力评价体系**技术标准**

**价值**：衡量xAST产品技术能力，指引xAST技术发展方向，辅助企业产品选型

# 技术亮点

## 业界首个评价体系驱动式Benchmark

传统漏洞样本集普遍没有做评价项设计，一般是简单的通过堆砌样本来体现其“完整度”，很可能有较多的样本都在测试同一个功能点，这势必导致测试结果既不能保证完备性，也不能保证测试结果的合理性。

我们在设计测试样本集之前，在业界首次设计了一套包含各个维度评价项的评价体系，再基于评价体系设计对应的测试样本集，较传统方式提高了完备性和合理性。

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image3.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">图3 业界首个评价体系设计驱动式Benchmark</div>
  </center>
   </div>

## 业界首个面向工具视角的Benchmark

传统漏洞样本集一般都是以漏洞类型作为评价视角，不同漏洞类型的样本组成了样本集，不同类型的xAST产品都在同一套漏洞样本集上进行测试。但xAST技术原理各异，样本集在不同类型产品之间很难通用。如测试静态分析SAST路径敏感的样本对于动态分析IAST来说就没有太多意义，这也将影响测试结果的合理性。

针对这种情况，我们转换了评价视角，在业界首次从漏洞视角转化成工具视角，不同工具不同评价项，不同语言不同评价项，评价项和样本的设计更合理。

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image4.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">图4 业界首个面向工具视角的Benchmark</div>
  </center>
   </div>

## 评价体系分层设计，降低评价复杂度

本质上，xAST的能力是分层的。有的能力比较底层，如对污点数据的跟踪能力，这类能力通常实现的难度较大，成本较高，是需要用户重点关注的；有的能力属于比较上层，如对某个规则或框架的sink点支持，通过简单的配置就可以支持，重要度相比引擎能力没那么高。传统漏洞样本集没有对这些能力做区分，“眉毛胡子一把抓”，测试结果无法区分究竟是规则没有配置导致的不支持还是引擎能力上不支持。

我们在业界首次提出对一款xAST可以从底层到上层分成引擎能力、规则能力和产品化能力这三层。对这三层分别设计评价体系和测试样本，既降低了每一层评价的复杂度，又使测试结果可以直接反映问题出在哪一层。

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image5.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">图5 评价体系分层设计，降低评价复杂度</div>
  </center>
   </div>

## “体检报告”式结果，细粒度可解释

传统漏洞样本集由于缺乏评价体系指导，每个样本的“测试功能点”是模糊的，评价结果是个“黑盒”，只能得到一个召回率和准确率的数据，无法得到更细粒度的信息。

我们基于评价体系，每个评价项对应生成一个测试样本，给每个测试样本都赋予了明确的“测试功能点”，使测试结果如同一份详尽的“体检报告”，细粒度可解释，知其然，知其所以然。

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image6.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">图6 “体检报告”式结果，细粒度可解释</div>
  </center>
   </div>

## 业界Benchmark交叉验证，确保完备性

为了保障评价体系及其Benchmark的完备性，我们还与业界常见的Benchmark进行了交叉验证，确保这些常见Benchmark的测试功能点都能在我们的评价体系中有体现，进一步确保了评价体系的完备性。

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image7.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">图7 业界Benchmark交叉验证，确保完备性</div>
  </center>
   </div>

# 评价体系内容

项目大图见图8，由SAST（静态应用安全测试）、IAST（交互式应用安全测试）、DAST（动态应用安全测试）、SCA（软件组成成份分析）、MAST（移动应用安全测试技术）大模型/机器学习漏洞检测等多个子模块组成。

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image8.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">图8 项目大图</div>
  </center>
   </div>

评价体系的每个子模块包括引擎能力评价体系（区分开发语言）和规则能力评价体系。以SAST\-Java引擎能力评价体系为例，评价体系由评价指标项和基于评价指标项的测试样本代码Benchmark两部分组成，见图9。

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image9.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">图9 评价体系内容示例</div>
  </center>
   </div>


用户通过各xAST产品在Benchmark上的实际测试结果，结合评价指标项，即可全面了解被测产品的能力详情。

# 项目进展与规划

项目进展与总体规划见图10，目前已完成IAST\-Java、SAST\-Java、SAST\-Node、DAST引擎能力评价体系的设计。项目自2023年开源以来，已有**阿里**、**华为**、**斗象**、**中国软件评测中心**、**科大讯飞**和**雪球**等十余家企业用户使用评价体系用于商采/开源产品选型或自研产品的技术衡量。其中IAST\-Java和DAST引擎能力评价体系已作为技术标准，用于**开放原子开源基金会**对开源安全工具的测评项目。

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image10.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">图10 项目进展与总体规划</div>
  </center>
   </div>


2024年项目计划落户一家开源基金会或联盟，依托于工作组进行共建，并每年基于xAST评价体系对业界的最新产品进行测评，发布信息安全行业**年度xAST产品的能力评测报告**，通过年度测评报告的方式推动项目的持续发展。

# 联系我们
## 微信

<p align="center">
  <img src="https://github.com/alipay/ant-application-security-testing-benchmark/raw/main/floder-img/Iwechat.png" alt="微信交流群" width="200"/><br>
  <span style="display: block; text-align: center;">微信交流群</span>
  <img src="https://github.com/alipay/ant-application-security-testing-benchmark/raw/main/floder-img/xzs.png" alt="微信小助手" width="200"/><br>
  <span style="display: block; text-align: center;">微信小助手</span>
</p>


## 邮箱

xast-contact@service.alipay.com

# License
This project is licensed under the Apache License 2.0