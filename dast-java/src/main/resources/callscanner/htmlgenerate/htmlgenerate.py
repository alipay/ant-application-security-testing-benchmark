# encoding=utf-8
import time
import json

# 评价点
import requests

check_point_table = {
    "url_path":{"support":False, "name":"改包能力-url路径"},

    "url_key":{"support":False, "name":"改包能力-url参数-key"},
    "url_value":{"support":False, "name":"改包能力-url参数-value"},
    "url_value_json":{"support":False, "name":"改包能力-url参数-value中的json"},
    "url_value_xml":{"support":False, "name":"改包能力-url参数-value中的xml"},

    "header_key":{"support":False, "name":"改包能力-header-key"},
    "header_value":{"support":False, "name":"改包能力-header-value"},
    "header_cookie_key":{"support":False, "name":"改包能力-header-cookie中的key"},
    "header_cookie_value":{"support":False, "name":"改包能力-header-cookie中的value"},

    "body_key": {"support":False, "name":"改包能力-requestBody中的key"},
    "body_value": {"support":False, "name":"改包能力-requestBody中的value"},

    "body_json_key":{"support":False, "name":"改包能力-requestBody-json格式-key"},
    "body_json_value":{"support":False, "name":"改包能力-requestBody-json格式-value"},
    "body_json_list":{"support":False, "name":"改包能力-requestBody-json格式-列表的第n个值"},

    "body_xml_value":{"support":False, "name":"改包能力-requestBody-xml格式-标签值"},
    "body_xml_node_key":{"support":False, "name":"改包能力-requestBody-xml格式-标签属性的key"},
    "body_xml_node_value":{"support":False, "name":"改包能力-requestBody-xml格式-标签属性的value"},
    "body_file_upload":{"support":False, "name":"改包能力-requestBody-文件上传格式"},
    "body_stream":{"support":False, "name":"改包能力-requestBody-二进制流"},
    "multi_value": {"support": False, "name": "改包能力-设置多个值"},

    "send_package_concurrency": {"support": False, "name": "发包能力-并发数量控制"},
    "send_package_max_speed": {"support": False, "name": "发包能力-最大发包QPS(固定硬件/网络资源下)"},

    "method_get":{"support":False, "name":"改包能力-请求方法-GET请求"},
    "method_post":{"support":False, "name":"改包能力-请求方法-POST请求"},
    "method_put":{"support":False, "name":"改包能力-请求方法-PUT请求"},
    "method_delete":{"support":False, "name":"改包能力-请求方法-DELETE请求"},

    "payload_replace":{"support":False, "name":"payload变形-替换参数"},
    "payload_add":{"support":False, "name":"payload变形-追加"},
    "payload_special_char":{"support":False, "name":"payload变形-前后增加非字母符号"},
    "payload_upcase":{"support":False, "name":"payload变形-大小写"},
    "payload_proto":{"support":False, "name":"payload变形-原型"},
    "payload_base64":{"support":False, "name":"payload编码-base64"},
    "payload_url":{"support":False, "name":"payload编码-url"},

    "response_header":{"support":False, "name":"响应检测-header识别"},
    "response_body":{"support":False, "name":"响应检测-响应body识别"},
    "response_status":{"support":False, "name":"响应检测-状态码识别"},
    "response_time":{"support":False, "name":"响应检测-响应时长识别"},
    "response_body_acctually":{"support":False, "name":"响应检测-实际body类型识别"},
    "response_front_render":{"support":False, "name":"响应检测-前端渲染"},
    "response_dnslog":{"support":False, "name":"响应检测-dnslog检测无回显"},

    "login_self_define":{"support":False, "name":"登录态-自定义"},
    "login_not_support":{"support":False, "name":"登录态-不支持"},

    "crawler_active_depth":{"support":False, "name":"爬虫-主动式-支持爬取深度配置"},
    "crawler_active_cors":{"support":False, "name":"爬虫-主动式-支持爬取不同源站点"},
    "crawler_active_deny":{"support":False, "name":"爬虫-主动式-支持禁爬"},
    "crawler_active_jump":{"support":False, "name":"爬虫-主动式-支持跳转层数配置"},
    "crawler_active_html":{"support":False, "name":"爬虫-主动式-支持html表单爬取"},
    "crawler_active_ajax":{"support":False, "name":"爬虫-主动式-支持ajax爬取"},
    "crawler_active_completion":{"support":False, "name":"爬虫-主动式-爬取完整度"},
    "crawler_passive":{"support":False, "name":"爬虫-被动式"},
    "crawler_not_support":{"support":False, "name":"爬虫-不支持"},


    "log_target":{"support":False, "name":"监控-扫描对象状态监控"},
    "log_runtime":{"support":False, "name":"监控-运行日志"},
    "log_debug":{"support":False, "name":"监控-调试日志"},
    "log_task":{"support":False, "name":"监控-扫描任务状态监控"},

    "control_node_change":{"support":False, "name":"扫描控制-节点动态扩缩容"},
    "control_stop":{"support":False, "name":"扫描控制-支持扫描中止"},
    "control_plugin":{"support":False, "name":"扫描控制-支持扫描插件自定义"},
    "control_update":{"support":False, "name":"扫描控制-支持扫描插件热更新"},
}

# 漏洞case对应的评价点
case_check_point_map = {
"BS00001": ["method_post", "header_cookie_value", "method_get", "response_body", "payload_replace"],
"BS00002": ["method_post", "header_value", "response_body", "payload_replace"],
"BS00003": ["method_post", "header_value", "method_get", "response_body", "payload_replace"],
"BS00004": ["method_post", "header_value", "method_get", "response_body", "payload_replace"],
"BS00005": ["method_get", "method_post", "header_value", "response_body", "payload_replace"],
"BS00006": ["method_post", "method_get", "header_value", "payload_replace", "payload_proto", "payload_special_char", "payload_replace"],
"BS00007": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "payload_proto", "response_body", "payload_replace"],
"BS00008": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "payload_proto", "response_body", "payload_replace"],
"BS00009": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "payload_proto", "response_body", "payload_replace"],
"BS00010": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "payload_proto", "response_body", "payload_replace"],
"BS00011": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00012": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00013": ["method_post", "method_get", "url_key", "body_key", "response_body", "payload_replace"],
"BS00014": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00015": ["method_post", "method_get", "response_body", "payload_replace"],
"BS00016": ["method_post", "method_get", "payload_url", "url_value", "body_value", "response_body", "payload_replace"],
"BS00017": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00018": ["method_post", "method_get", "response_body", "payload_replace"],
"BS00019": ["method_post", "method_get", "header_value", "header_cookie_value", "response_body", "payload_url", "payload_replace"],
"BS00020": ["method_post", "method_get", "response_body", "header_cookie_value", "payload_url", "payload_replace"],
"BS00021": ["method_post", "method_get", "header_cookie_value", "response_body", "payload_url", "payload_replace"],
"BS00022": ["method_post", "method_get", "response_body", "header_cookie_value", "payload_url", "payload_replace"],
"BS00023": ["method_post", "method_get", "response_body", "header_cookie_value", "payload_url", "payload_replace"],
"BS00024": ["method_post", "method_get", "response_body", "payload_url", "header_value", "payload_replace"],
"BS00025": ["method_post", "method_get", "payload_url", "payload_special_char", "header_value", "response_body", "payload_replace"],
"BS00026": ["method_post", "method_get", "header_value", "response_body", "payload_url", "payload_replace"],
"BS00027": ["method_post", "method_get", "header_value", "response_body", "payload_url", "payload_replace"],
"BS00028": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00029": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00030": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_special_char", "payload_replace"],
"BS00031": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_special_char", "payload_replace"],
"BS00032": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00033": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00034": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00035": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00036": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00037": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00038": ["method_post", "method_get", "header_key", "response_body", "payload_replace"],
"BS00039": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00040": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00041": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_url", "payload_replace"],
"BS00042": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_url", "payload_replace"],
"BS00043": ["method_post", "method_get"],
"BS00044": ["method_post", "method_get"],
"BS00045": ["method_post", "method_get", "header_value", "payload_url", "payload_replace", "response_body"],
"BS00046": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "response_body"],
"BS00047": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "response_body", "payload_url"],
"BS00048": ["method_post", "method_get"],
"BS00049": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "response_body", "response_dnslog", "payload_proto"],
"BS00050": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "response_body", "response_dnslog", "payload_proto"],
"BS00051": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "response_status", "response_header", "payload_proto"],
"BS00052": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "response_status", "response_header", "payload_proto"],
"BS00053": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "response_status", "response_header", "payload_proto"],
"BS00054": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "response_body", "response_front_render", "payload_proto"],
"BS00055": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "payload_proto"],
"BS00056": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "payload_proto"],
"BS00057": ["method_post", "method_get", "body_json_value", "response_body"],
"BS00058": ["method_post", "method_get"],
"BS00059": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "payload_proto"],
"BS00060": ["method_post", "method_get"],
"BS00061": ["method_post", "method_get", "url_value", "body_value", "body_xml_node_key", "body_xml_value", "response_body"],
"BS00062": ["method_post", "method_get", "url_value", "body_value", "body_xml_node_key", "body_xml_value", "response_body"],
"BS00063": ["method_post", "method_get", "payload_base64", "url_value", "body_value", "response_body", "payload_replace", "payload_special_char"],
"BS00064": ["method_post", "method_get", "payload_base64", "url_value", "body_value", "response_body", "payload_replace", "payload_special_char"],
"BS00065": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00066": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00067": ["method_post", "method_get", "payload_replace", "payload_proto", "body_json_value", "response_body"],
"BS00068": ["method_post", "method_get", "body_xml_value", "response_body", "payload_replace"],
"BS00069": ["method_post", "method_get", "response_body"],
"BS00070": ["method_post", "method_get", "response_body"],
"BS00071": ["method_post", "method_get", "url_value", "body_value", "payload_base64", "payload_replace", "response_body"],
"BS00072": ["method_post", "method_get", "body_json_value", "response_body", "payload_replace"],
"BS00073": ["method_post", "method_get", "body_xml_node_key", "body_xml_value", "response_body", "payload_replace"],
"BS00074": ["method_post", "method_get", "body_xml_value", "payload_replace", "response_body"],
"BS00075": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "payload_special_char", "response_body"],
"BS00076": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "response_body", "payload_proto"],
"BS00077": ["method_post", "method_get", "payload_base64", "url_value", "body_value", "response_body"],
"BS00078": ["method_post", "method_get", "body_json_value", "response_body"],
"BS00079": ["method_post", "method_get", "body_xml_value", "response_body", "payload_proto", "payload_replace"],
"BS00080": ["method_post", "method_get", "body_json_value", "response_body", "payload_proto", "payload_replace"],
"BS00081": ["method_post", "method_get", "body_xml_node_value", "response_body", "payload_replace"],
"BS00082": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00083": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00084": ["method_put", "payload_add", "payload_special_char", "url_value", "body_value", "response_body"],
"BS00085": ["method_delete", "payload_add", "payload_special_char", "url_value", "body_value", "response_body"],
"BS00086": ["method_get"],
"BS00087": ["method_post", "method_get", "payload_special_char", "header_value", "payload_url", "response_body"],
"BS00088": ["method_post", "method_get", "payload_special_char", "header_value", "payload_url", "response_body"],
"BS00089": ["method_get", "response_body"],
"BS00090": ["method_get", "response_body"],
"BS00091": ["method_get", "url_value", "response_body", "payload_replace"],
"BS00092": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace", "payload_special_char", "payload_url"],
"BS00093": ["method_get", "url_value", "response_body", "payload_replace", "payload_special_char", "payload_url"],
"BS00094": ["method_get", "url_value", "response_body", "payload_replace", "payload_special_char", "payload_url"],
"BS00095": ["method_post", "payload_special_char", "body_json_value", "response_body", "payload_replace"],
"BS00096": ["method_post", "method_get", "payload_special_char", "payload_base64", "body_stream", "response_body"],
"BS00097": ["method_get", "payload_special_char", "response_status", "url_value"],
"BS00098": ["method_get", "payload_special_char", "response_time", "url_value"],
"BS00099": ["method_get", "url_value", "response_time", "payload_proto"],
"BS00100": ["method_get", "url_value", "response_body"],
"BS00101": ["method_get", "url_value", "response_body"],
"BS00102": ["method_get", "response_body"],
"BS00103": ["method_get", "response_body"],
"BS00104": ["method_get", "response_body"],
"BS00105": ["method_get", "response_body"],
"BS00106": ["method_post", "method_get", "response_status", "response_header", "url_value", "body_value", "payload_proto", "payload_replace"],
"BS00107": ["method_post", "method_get", "url_value", "body_value", "response_front_render", "payload_replace", "payload_special_char"],
"BS00108": ["method_post", "method_get", "url_value", "body_value", "response_front_render", "payload_replace", "payload_special_char"],
"BS00109": ["method_get", "response_dnslog", "url_value", "payload_replace"],
"BS00110": ["method_get", "response_dnslog", "url_value", "multi_value", "payload_replace"],
"BS00111": ["method_post", "method_get", "header_value", "response_header"],
"BS00112": ["method_post", "method_get", "response_header"],
"BS00113": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_proto"],
"BS00114": ["method_post", "method_get", "response_body"],
"BS00115": ["method_post", "method_get", "url_value", "body_value", "response_dnslog"],
"BS00116": ["method_get", "method_post", "response_body", "response_front_render"],
"BS00117": ["method_post", "body_file_upload"],
"BS00118": ["method_get", "method_post", "url_value", "body_value", "payload_special_char", "payload_replace", "response_body"],
"BS00119": ["method_get", "method_post", "payload_upcase", "url_value", "body_value", "payload_replace", "response_body"],
"BS00120": ["method_get", "method_post", "payload_upcase", "url_value", "body_value", "payload_replace", "response_body"],
"BS00121": ["method_post", "method_get", "url_value", "body_value", "payload_url", "payload_replace", "response_body"],
"BS00122": ["method_get", "method_post", "url_value", "body_value", "payload_replace", "response_body"],
"BS00123": ["method_post", "method_get", "url_value", "body_value", "payload_url", "payload_replace", "response_body"],
"BS00124": ["method_post", "method_get", "body_json_list", "response_body", "payload_special_char"],
"BS00125": ["method_post", "method_get", "body_json_value", "response_body", "payload_special_char"],
"BS00126": ["method_post", "method_get", "url_value", "body_value", "payload_special_char", "response_body"],
"BS00127": ["method_post", "method_get", "response_body"],
"BS00128": ["method_get", "response_body"],
"BS00129": ["method_get", "response_body"],
"BS00130": ["method_get", "url_value", "multi_value", "response_body", "payload_replace", "payload_proto"],
"BS00131": ["method_post", "method_get", "body_json_value", "response_body", "payload_replace"],
"BS00132": ["method_post", "method_get", "body_json_value", "response_body", "payload_replace"],
"BS00133": ["method_post", "method_get", "body_json_value", "response_body", "payload_replace"],
"BS00134": ["method_post", "method_get", "body_json_value", "response_body", "response_dnslog", "payload_replace"],
"BS00135": ["method_post", "method_get", "body_json_value", "response_body", "response_dnslog", "payload_replace"],
"BS00136": ["method_post", "method_get", "url_value_json", "response_body", "response_dnslog", "payload_replace"],
"BS00137": ["method_post", "method_get", "url_value_json", "response_body", "response_dnslog", "payload_replace"],
"BS00138": ["method_post", "method_get", "url_value", "body_value", "response_body", "response_dnslog", "payload_replace"],
"BS00139": ["method_post", "method_get", "url_value", "body_value", "multi_value", "response_body", "payload_replace"],
"BS00140": ["method_post", "method_get", "url_value", "body_value", "payload_special_char", "payload_replace"],
"BS00141": ["method_post", "method_get", "url_value", "body_value", "payload_replace", "response_body"],
"BS00142": ["method_post", "method_get", "url_value", "body_value", "payload_special_char", "response_body", "payload_replace"],
"BS00143": ["method_post", "method_get", "url_value", "body_value", "payload_special_char", "response_front_render", "payload_replace"],
"BS00144": ["method_post", "method_get", "url_value", "body_value", "payload_special_char", "response_front_render", "payload_replace"],
"BS00145": ["method_post", "method_get", "url_value", "body_value", "payload_special_char", "response_front_render", "payload_replace"],
"BS00146": ["method_post", "method_get"],
"BS00147": ["method_post", "method_get", "url_value", "body_value", "payload_special_char", "response_body", "payload_replace"],
"BS00148": ["method_post", "method_get", "url_value", "body_value", "payload_special_char", "response_body", "payload_replace"],
"BS00149": ["method_post", "method_get", "url_value", "body_value", "payload_special_char", "response_body", "payload_replace"],
"BS00150": ["method_post", "method_get", "url_value", "body_value", "response_body", "payload_replace"],
"BS00151": ["method_post", "method_get", "url_value", "body_value", "payload_special_char", "response_body", "payload_replace"],
"BS00152": ["method_post", "method_get", "url_path", "response_body"],
"BS00153": ["method_post", "method_get", "response_body"],
"BS00154": ["method_post", "method_get", "url_value", "body_value", "response_dnslog", "response_body", "payload_replace"],
"BS00155": ["method_post", "method_get", "body_value", "body_xml_value", "response_body", "payload_replace"],
"BS00156": ["method_post", "method_get", "body_value", "url_value", "response_body", "payload_replace"],
"BS00157": ["method_post", "method_get", "body_value", "url_value", "response_body", "payload_replace"]
}

REPORT_START = '''<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>扫描器评测结果报告</title>
    <style>
        table {
  border-collapse: collapse;}

table, th, td {
  border: 1px solid black;}
    </style>
</head>
<body>
    <h1 style="text-align:center">扫描器评测结果报告</h1>
    '''

REPORT_END = '''

</body>

</html>'''

BASIC_INFO_START = '''    <h1 id="-">基本信息</h1>
    <p>测试开始时间:{timestart}</p>
    <p>报告生成时间:{timegenerated}</p>
    <h1 id="-">扫描结果概览</h1>
    <table>
    <thead>
    <tr>
    <th style="text-align:center">检测类型</th>
    <th style="text-align:center">样例总数</th>
    <th style="text-align:center">真阳性(TP)</th>
    <th style="text-align:center">真阴性(TN)</th>
    <th style="text-align:center">假阳性(FP)</th>
    <th style="text-align:center">假阴性(FN)</th>
    <th style="text-align:center">准确率</th>
    <th style="text-align:center">召回率</th>
    </tr>
    </thead>
    <tbody>'''

BASIC_INFO_SEGMENT = '''    <tr>
    <td style="text-align:center">{vulntype}</td>
    <td style="text-align:center">{vulncount}</td>
    <td style="text-align:center">{TP}</td>
    <td style="text-align:center">{TN}</td>
    <td style="text-align:center">{FP}</td>
    <td style="text-align:center">{FN}</td>
    <td style="text-align:center">{accuracy}</td>
    <td style="text-align:center">{recall}</td>
    </tr>'''

BASIC_INFO_END = '''
</tbody>
    </table>
    <p>TP: 接口为A类型漏洞，被扫描引擎检出为A类型</p>
    <p>准确率 = (TP + TN) / (TP + TN + FP + FN)</p>
    <p>召回率 = (TP) / (TP + FN)</p>
    '''

CHECKFAILED_START = '''    <h1 id="-">检出失效情况汇总</h1>
'''

CHECKFAILED_END = ''''''

'''
防XSS
'''


def filterxss(s):
    return s.replace('>', '&gt;').replace('<', '&lt;').replace('"', '&ldquo;')


'''
生成一个基本信息的一行
'''


def generatepieofbasicinfo(vulntype, TP, FP, TN, FN):
    vulncount = TP + FP + TN + FN
    # 精确率
    presicion = 'ERROR'
    try:
        presicion = TP / (TP + FP)
        presicion = format(presicion, '.2f')
    except:
        pass

    # 召回率
    recall = 'ERROR'
    try:
        recall = float(TP) / (TP + FN) * 100
        recall = "%.2f%s" % (recall, "%")
        accuracy = float(TP + TN) / (TP+TN+FP+FN) * 100
        accuracy = "%.2f%s" % (accuracy, "%")
    except:
        pass
    # 约登指数
    youdenindex = 'ERROR'
    try:
        youdenindex = TP / (TP + FN) + TN / (TN + FP) - 1
        youdenindex = format(youdenindex, '.2f')
    except:
        pass
    return BASIC_INFO_SEGMENT.format(vulntype=filterxss(vulntype), vulncount=vulncount, TP=TP, FP=FP, TN=TN, FN=FN,
                                     accuracy=accuracy, presicion=presicion, recall=recall, youdenindex=youdenindex)


'''
生成检出失败的基本信息的一行
'''


def generatecheckfailedoneline(vulntype, FNVULNS, FPVULNS):
    retval = '''    <h2 id="-vulntype-">检测类型：{vulntype}</h2>
      <h3 id="-">漏报（假阴性）</h3>
    <table>
    <thead>
    <tr>
    <th style="text-align:center">case编号</th>
    </tr>
    </thead>
    <tbody>'''.format(vulntype=filterxss(vulntype))
    for casenumber in FNVULNS:
        retval += '<tr> <td style="text-align:center">{casenumber}</td> </tr>\n'.format(
            casenumber=filterxss(casenumber))
    retval += '''</tbody>
    </table>
    <h3 id="-">误报（假阳性）</h3>
    <table> <thead>
    <tr>
    <th style="text-align:center">case编号</th>
    </tr>
    </thead>
    <tbody>'''
    for casenumber in FPVULNS:
        retval += '<tr> <td style="text-align:center">{casenumber}</td> </tr>\n'.format(
            casenumber=filterxss(casenumber))
    retval += '''    </tbody>
    </table>'''
    return retval


def generatereport(starttime, result, crawler_count_result):
    timestart = time.strftime("%Y-%m-%d %H:%M:%S", starttime)
    timegenerated = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
    retval = REPORT_START
    # 生成基本信息
    basicinfo = BASIC_INFO_START.format(timestart=timestart, timegenerated=timegenerated)
    vulns = result['vulns']
    allfp = 0
    allfn = 0
    alltp = 0
    alltn = 0
    for vulntype in vulns:
        thisvuln = vulns[vulntype]
        FN = thisvuln['FN']
        FP = thisvuln['FP']
        TN = thisvuln['TN']
        TP = thisvuln['TP']
        allfn += FN
        allfp += FP
        alltp += TP
        alltn += TN
        basicinfo_oneline = generatepieofbasicinfo(vulntype, TP, FP, TN, FN)
        basicinfo += basicinfo_oneline
    basicinfo += generatepieofbasicinfo("所有检测项", alltp, allfp, alltn, allfn)
    basicinfo += BASIC_INFO_END
    # 生成漏洞类型检测数据
    retval += basicinfo
    # 生成引擎评价卡
    retval += generate_attribute(result, crawler_count_result)
    # 生成检出失效情况
    checkfailed = CHECKFAILED_START
    for vulntype in vulns:
        thisvuln = vulns[vulntype]
        checkfailed += generatecheckfailedoneline(vulntype, thisvuln['FNVULNS'], thisvuln['FPVULNS'])
    checkfailed += CHECKFAILED_END
    retval += checkfailed
    retval += REPORT_END
    return retval

def sort_by_name(data):
    return data.values()[0]

def generate_attribute(result, crawler_count_result):
    vul_list= []
    print(result)
    # 取出所有检测出来的漏洞编号
    for vul_type in result["vulns"].keys():
        for case in result["vulns"][vul_type].get("TPVULNS", []):
            vul_list.append(case)  # case = BS00111

    # 遍历检测出的所有漏洞
    for case in vul_list:
        # 查询当前漏洞对应的check_point，并在check_point_table中相应的点进行标记
        for check_point in case_check_point_map[case]:
            check_point_table[check_point]["support"] = True

    # 查询爬虫统计结果
    if crawler_count_result["supportAjax"]:
        check_point_table["crawler_active_ajax"]["support"] = True
    if crawler_count_result["supportHtml"]:
        check_point_table["crawler_active_html"]["support"] = True
    if crawler_count_result["nowCount"]:
        check_point_table["crawler_active_completion"]["support"] = "%.2f%s" % (float(crawler_count_result["nowCount"]) / crawler_count_result["total"] * 100, "%")
    # html表格
    check_point_html_table = '''
    <h1 id="-">扫描引擎评价卡</h1>
    <table>
    <thead>
    <tr>
    <th style="text-align:center">评价点</th>
    <th style="text-align:center">是否支持</th>
    </tr>
    </thead>
    <tbody>'''

    # 单行格式
    line_format = '''    <tr>
        <td style="text-align:center">{0}</td>
        <td style="text-align:center">{1}</td>
        </tr>'''
    sort_check_point_table_keys = [{x:check_point_table[x]["name"]} for x in check_point_table]
    sort_check_point_table_keys.sort(key=sort_by_name)
    for check_point in [x.keys()[0] for x in sort_check_point_table_keys]:
        is_support = check_point_table[check_point]["support"]
        if is_support:
            if is_support is True:
                value = "是"
            else:
                value = is_support
        else:
            value = ""
        name = check_point_table[check_point]["name"]
        check_point_html_table += line_format.format(name, value)

    # 封闭table
    check_point_html_table += """
           </tbody>
    </table>"""


    return check_point_html_table




if __name__ == '__main__':
    starttime = time.localtime()
    result = json.loads(
        '''{"uncategoryvulns":[],"vulns":{"redirect":{"FPVULNS":[],"FN":6,"FP":0,"TN":1,"FNVULNS":["BS00051","BS00053","BS00054","BS00108","BS00106","BS00107"],"TP":0},"cmdi":{"FPVULNS":[],"FN":25,"FP":0,"TN":4,"FNVULNS":["BS00020","BS00092","BS00002","BS00123","BS00005","BS00040","BS00041","BS00042","BS00034","BS00035","BS00036","BS00037","BS00038","BS00025","BS00027","BS00045","BS00046","BS00047","BS00087","BS00088","BS00121","BS00078","BS00079","BS00115","BS00077"],"TP":0},"ssrf":{"FPVULNS":[],"FN":5,"FP":0,"TN":2,"FNVULNS":["BS00049","BS00084","BS00085","BS00110","BS00109"],"TP":0},"pathtraver":{"FPVULNS":[],"FN":5,"FP":0,"TN":1,"FNVULNS":["BS00019","BS00095","BS00001","BS00082","BS00083"],"TP":2},"cors":{"FPVULNS":[],"FN":2,"FP":0,"TN":0,"FNVULNS":["BS00111","BS00112"],"TP":0},"jsonp":{"FPVULNS":[],"FN":1,"FP":0,"TN":0,"FNVULNS":["BS00113"],"TP":0},"xxe":{"FPVULNS":[],"FN":2,"FP":0,"TN":1,"FNVULNS":["BS00061","BS00073"],"TP":0},"xss":{"FPVULNS":[],"FN":6,"FP":0,"TN":4,"FNVULNS":["BS00013","BS00003","BS00004","BS00126","BS00071","BS00072"],"TP":9},"deserialization":{"FPVULNS":[],"FN":4,"FP":0,"TN":1,"FNVULNS":["BS00096","BS00124","BS00125","BS00057"],"TP":0},"code_injection":{"FPVULNS":[],"FN":3,"FP":0,"TN":2,"FNVULNS":["BS00130","BS00056","BS00055"],"TP":0},"sensitive":{"FPVULNS":[],"FN":10,"FP":0,"TN":4,"FNVULNS":["BS00089","BS00127","BS00128","BS00086","BS00114","BS00116","BS00069","BS00102","BS00104","BS00105"],"TP":0},"sqli":{"FPVULNS":[],"FN":25,"FP":0,"TN":4,"FNVULNS":["BS00012","BS00014","BS00093","BS00094","BS00097","BS00010","BS00098","BS00099","BS00091","BS00009","BS00122","BS00006","BS00007","BS00023","BS00026","BS00063","BS00065","BS00066","BS00120","BS00119","BS00118","BS00067","BS00100","BS00068","BS00101"],"TP":0}}}''')
    r = generatereport(starttime, result)
    f = open('test.html', 'wb')
    f.write(r)
    f.close()