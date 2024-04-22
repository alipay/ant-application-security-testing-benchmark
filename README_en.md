
# <img src="floder-img/xastlogo.png"  />
## [Official Website](https://xastbenchmark.github.io/)
#### [简体中文](README.md) / [English](README_en.md)

# Project Introduction

xAST (Application Security Testing Technology) plays an increasingly important role in ensuring software security and reliability. Currently, there are dozens of commercial or open-source products available for each type of product (SAST/IAST/DAST/SCA/MAST, etc.), and some first-party enterprises are also developing their own xAST products. Whether it's commercial procurement, choosing open-source products, or self-developed products, everyone faces a common problem: how to objectively measure the technical level of an xAST product?

Currently, neither the industry nor the academic community has established evaluation standards for the technical capabilities of xAST products. Typically, the evaluation is based on the test results of several vulnerability sample sets. Commonly used vulnerability sample sets in the industry are shown in Figure 1, and an example of test results is shown in Figure 2.

<div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image1.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">Figure 1 Common Vulnerability Sample Sets in the Industry</div>
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
    padding: 2px;">Figure 2 Test Results of Different Open Source SAST on Juliet Java Vulnerability Sample Set</div>
  </center>
   </div>

On the one hand, as can be seen from Figure 1, there are huge differences between various sample sets (from tens of samples to hundreds of thousands of samples). The root cause lies in the lack of systematic design of vulnerability sample sets, which are simply stacked with vulnerability samples, and the integrity of the test results is not guaranteed. The distribution of functional points tested by vulnerability sample sets is uneven, and the test results lack rationality.

On the other hand, as shown in Figure 2, due to the lack of evaluation system, the evaluation results are like a "black box" to users. The evaluation results can only provide overall recall rate and false positive rate data, and cannot finely characterize the technical strengths and weaknesses of the products.

Addressing the pain point in the industry of lacking effective standards to measure technical capabilities in the xAST field, the Ant Security Team, in collaboration with more than 20 experts and scholars from Ant Program Analysis Team and the Network Space Security Institute of Zhejiang University, jointly designed the xAST evaluation system and its test sample suite Benchmark, aiming to become the "metric" of application security testing tools.

# Project Objectives and Values

**Objectives**: To create a xAST capability evaluation system with industry consensus **technical standards**.

**Values**: Measure the technical capabilities of xAST products, guide the direction of xAST technology development, and assist enterprises in product selection.

# Technical Highlights

## Industry's First Evaluation System-Driven Benchmark

Traditional vulnerability sample sets generally lack evaluation item design. They typically demonstrate their "completeness" simply by stacking samples, which may result in many samples testing the same functional point. This inevitably leads to the inability to ensure the completeness and rationality of the test results.

Before designing the test sample set, we first designed a set of evaluation systems containing evaluation items in various dimensions, which is the first time in the industry. Then, based on the evaluation system, we designed corresponding test sample sets, which improves completeness and rationality compared to traditional methods.

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image3.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">Figure 3 Industry's First Evaluation System-Driven Benchmark</div>
  </center>
   </div>

## Industry's First Tool-Centric Benchmark

Traditional vulnerability sample sets generally use vulnerability types as the evaluation perspective, and different types of vulnerabilities constitute the sample set. Different types of xAST products are tested on the same set of vulnerability samples. However, xAST technologies have different principles, and it is difficult for sample sets to be used universally across different types of products. For example, testing static analysis SAST path-sensitive samples may not be very meaningful for dynamic analysis IAST. This also affects the rationality of the test results.

In response to this situation, we changed the evaluation perspective, transforming from a vulnerability perspective to a tool-centric perspective for the first time in the industry. Different tools have different evaluation items, different languages have different evaluation items, and the design of evaluation items and samples is more reasonable.

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image4.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">Figure 4 Industry's First Tool-Centric Benchmark</div>
  </center>
   </div>

## Hierarchical Design of Evaluation System to Reduce Evaluation Complexity

Essentially, the capabilities of xAST are hierarchical. Some capabilities are relatively low-level, such as the ability to track tainted data, which is usually difficult to implement and costly, and requires users' attention. Some capabilities belong to the upper layer, such as support for a certain rule or framework's sink points, which can be supported by simple configuration. The importance is relatively lower compared to engine capabilities. Traditional vulnerability sample sets do not distinguish between these capabilities, and the test results cannot distinguish whether the lack of support is due to the absence of configuration of the rule or the lack of engine capabilities.

We propose for the first time in the industry to divide the capabilities of an xAST into three layers: engine capabilities, rule capabilities, and productization capabilities. Evaluation systems and test samples are designed for these three layers respectively, reducing the complexity of each layer's evaluation and enabling test results to directly reflect the layer where the problem lies.

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image5.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">Figure 5 Hierarchical Design of Evaluation System to Reduce Evaluation Complexity</div>
  </center>
   </div>

## "Physical Examination Report"-Style Results for Fine-Grained Interpretation

Traditional vulnerability sample sets lack guidance from evaluation systems, and the "test functional points" of each sample are vague. Evaluation results are like a "black box", providing only recall rate and accuracy data, unable to provide more fine-grained information.

Based on the evaluation system, each evaluation item corresponds to a test sample, giving each test sample clear "test functional points", making the test results resemble a detailed "physical examination report", with fine-grained interpretation, knowing the reasons behind them.

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image6.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">Figure 6 "Physical Examination Report"-Style Results for Fine-Grained Interpretation</div>
  </center>
   </div>

## Industry Benchmark Cross-Validation to Ensure Completeness

To ensure the completeness of the evaluation system and its Benchmark, we have also cross-validated with commonly used Benchmarks in the industry to ensure that the test functional points of these common Benchmarks are reflected in our evaluation system, further ensuring the completeness of the evaluation system.

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image7.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">Figure 7 Industry Benchmark Cross-Validation to Ensure Completeness</div>
  </center>
   </div>

# Evaluation System Contents

The overall structure of the project can be seen in Figure 8, consisting of several sub-modules such as SAST (Static Application Security Testing), IAST (Interactive Application Security Testing), DAST (Dynamic Application Security Testing), SCA (Software Composition Analysis), MAST (Mobile Application Security Testing Technology), large-scale models/machine learning vulnerability detection, etc.

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image8.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">Figure 8 Project Structure</div>
  </center>
   </div>

Each sub-module of the evaluation system includes engine capability evaluation system (differentiating by development language) and rule capability evaluation system. Taking SAST-Java engine capability evaluation system as an example, the evaluation system consists of evaluation indicator items and test sample code Benchmark based on evaluation indicator items, as shown in Figure 9.

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image9.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">Figure 9 Example of Evaluation System Contents</div>
  </center>
   </div>


Users can fully understand the capabilities of the tested product by combining the actual test results of various xAST products on the Benchmark with the evaluation indicator items.

# Project Progress and Plan

The project progress and overall plan can be seen in Figure 10. Currently, the design of IAST-Java, SAST-Java, SAST-Node, and DAST engine capability evaluation systems has been completed. Since the project was open-sourced in 2023, more than ten companies including Alibaba, Huawei, Douyi, China Software Testing Center, iFLYTEK, and Xueqiu have used the evaluation system for commercial procurement/open-source product selection or self-developed product technical measurement. Among them, the IAST-Java and DAST engine capability evaluation systems have been used as technical standards for the Open Atom Open Source Foundation's evaluation projects of open-source security tools.

   <div align=center>
<center>
    <img style="border-radius: 0.3125em;
    box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);"
    src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/image10.png">
    <br>
    <div style="color:orange; border-bottom: 1px solid #d9d9d9;
    display: inline-block;
    color: #999;
    padding: 2px;">Figure 10 Project Progress and Plan</div>
  </center>
   </div>


In 2024, the project plans to settle in an open-source foundation or alliance, co-build with working groups, and conduct annual evaluations of the latest products in the industry based on the xAST evaluation system, releasing an "Annual xAST Product Capability Evaluation Report" for the information security industry to promote the sustainable development of the project.

# Evaluation System
### [ SAST-Java Evaluation System ](https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/doc/sast-java%E8%AF%84%E4%BB%B7%E4%BD%93%E7%B3%BB.md)
### [ IAST-Java Evaluation System ](https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/doc/iast-java%E8%AF%84%E4%BB%B7%E4%BD%93%E7%B3%BB.md)
### [ DAST Evaluation System ](https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/doc/dast%E8%AF%84%E4%BB%B7%E4%BD%93%E7%B3%BB.md)
### [ Node.js Evaluation System](https://github.com/alipay/ant-application-security-testing-benchmark-nodejs)

# Contact Us
## WeChat

<div align="center">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td style="text-align: center;">
        <img src="https://github.com/alipay/ant-application-security-testing-benchmark/raw/main/floder-img/xzs.png" alt="WeChat Assistant" width="200"/><br>
        <em>WeChat Assistant</em>
      </td>
      <td style="text-align: center;">
            <img src="https://github.com/alipay/ant-application-security-testing-benchmark/blob/main/floder-img/gzh.png" alt="WeChat Official Account" width="200"/><br>
       </td>
    </tr>
  </table>
</div>


## Email

xast-contact@service.alipay.com

# [FAQ](https://github.com/alipay/ant-application-security-testing-benchmark/wiki/FAQ)

# [Participate in Co-construction](https://github.com/alipay/ant-application-security-testing-benchmark/wiki/%E5%8F%82%E4%B8%8E%E8%B4%A1%E7%8C%AE)

# License
This project is licensed under the Apache License 2.0
