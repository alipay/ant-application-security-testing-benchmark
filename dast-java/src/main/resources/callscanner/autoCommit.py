# encoding=utf-8

import time
import requests
import json
import subprocess
from xml.etree import ElementTree as ET

#任务表
from htmlgenerate.htmlgenerate import generatereport




# 靶机地址
TARGET="http://localhost:8080"

# 检测到的漏洞
foundVuls = {
"redirect": [
],

"xss": ["http://localhost:8080/xss/BS00016",
        "http://localhost:8080/xss/BS00015",
        "http://localhost:8080/xss/BS00004",
        "http://localhost:8080/xss/BS00017",
        "http://localhost:8080/xss/BS00003",
        "http://localhost:8080/xss/BS00146",
        "http://localhost:8080/redirect/BS00108",
        "http://localhost:8080/redirect/BS00107",
        "http://localhost:8080/code_injection/BS00060",
        "http://localhost:8080/code_injection/BS00059",
        "http://localhost:8080/redirect/BS00054",
        "http://localhost:8080/ssrf/BS00049",
        "http://localhost:8080/xss/BS00017",
        "http://localhost:8080/xss/BS00016",
        "http://localhost:8080/xss/BS00015",
        "http://localhost:8080/xss/BS00011",
        "http://localhost:8080/xss/BS00008"
],


"ssrf": [
],

"sqli": ["http://localhost:8080/sqli/BS00122",
         "http://localhost:8080/sqli/BS00120",
         "http://localhost:8080/sqli/BS00118",
         "http://localhost:8080/sqli/BS00119",
         "http://localhost:8080/sqli/BS00091",
         "http://localhost:8080/sqli/BS00098",
         "http://localhost:8080/sqli/BS00094",
         "http://localhost:8080/sqli/BS00093",
         "http://localhost:8080/sqli/BS00118",
         "http://localhost:8080/sqli/BS00098",
         "http://localhost:8080/sqli/BS00094",
         "http://localhost:8080/sqli/BS00093",
         "http://localhost:8080/sqli/BS00091",
         "http://localhost:8080/sqli/BS00065",
         "http://localhost:8080/sqli/BS00012",
         "http://localhost:8080/sqli/BS00010",
         "http://localhost:8080/sqli/BS00007",
         "http://localhost:8080/sqli/BS00009",
],

"deserialization": [
],

"cors": ["http://localhost:8080/cors/BS00111",
],

"sensitive": ["http://localhost:8080/sensitive/BS00153",
              "http://localhost:8080/sensitive/script.js",
              "http://localhost:8080/sensitive/BS00114",
              "http://localhost:8080/sensitive/BS00128",
              "http://localhost:8080/sensitive/BS00105",
              "http://localhost:8080/sensitive/BS00086",
              "http://localhost:8080/sensitive/BS00069",
              "http://localhost:8080/sensitive/BS00153",
              "http://localhost:8080/sensitive/script.js",
              "http://localhost:8080/xxe/BS00061",
              "http://localhost:8080/sensitive/BS00114",
              "http://localhost:8080/sensitive/BS00105",
              "http://localhost:8080/sensitive/BS00086",
              "http://localhost:8080/sensitive/BS00069",
              "http://localhost:8080/ssrf/BS00049",
],

"cmdi": ["http://localhost:8080/cmdi/BS00042",
         "http://localhost:8080/cmdi/BS00041",
         "http://localhost:8080/cmdi/BS00046",
         "http://localhost:8080/cmdi/BS00042",
         "http://localhost:8080/cmdi/BS00041",
],

"xxe": ["http://localhost:8080/xxe/BS00061",
],

"jsonp": [
],

"code_injection": [
],

"pathtraver": [
],

}



APP_NAME = ""
APP_KEY = ""

CALLBACK="http://localhost/api/scancallback?scanid={0}"

USER_AGENT = (
    "Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X)"
    " AppleWebKit/600.1.3 (KHTML, like Gecko) "
    "Version/8.0 Mobile/12A4345d Safari/600.1.4;N1RvaNa;"
)

HEADERS = {
    'Accept': (
        'text/html,application/xhtml+xml,'
        'application/xml;q=0.9,image/webp,'
        'image/apng,*/*;q=0.8,'
        'application/signed-exchange;v=b3'
    ),
    'Connection': 'keep-alive',
    'Accept-Language': 'zh-CN,zh;q=0.9',
}

JSON_MIMETYPE = 'application/json'


from urllib3.exceptions import InsecureRequestWarning
requests.packages.urllib3.disable_warnings(category=InsecureRequestWarning)


SCANTOKEN=''
def getscanauth():
    '''
    生成扫描器所需的auth
    '''
    global SCANTOKEN
    if SCANTOKEN=='':
        SCANTOKEN=subprocess.check_output(['java', '-cp', '.','generator']).strip().decode('utf-8').split(':')[0]
    if len(SCANTOKEN)!=32:
        print('[-]获取scantoken失败')
        exit()
    return SCANTOKEN

def checkjsonstring(s):
    '''
    检查是否是合法的json字符串
    '''
    try:
        json.loads(s)
        return True
    except:
        return False

def checkxmlstring(s):
    '''
    检查是否是合法的XML字符串
    '''
    try:
        ET.fromstring(s)
        return True
    except:
        return False



def startnewscan():
    url=TARGET+'/api/startnewstat'
    scanid=requests.post(url=url,data={'start':"true"}).text
    try:
        int(scanid)
        return scanid
    except Exception:
        print("[-]创建任务/获取scanid失败")
        exit(1)


def fetchresult():
    url=TARGET + '/api/fetchresult'
    return requests.get(url=url).text

def fetch_crawler_result():
    url = TARGET + "/antbenchmark/getData"
    return requests.get(url=url).text

def fetchcallbackcount():
    url=TARGET+'/api/fetchcallbackcount'
    res=requests.get(url=url).text
    try:
        return int(res)
    except:
        print('[-]发生错误:获取回调统计失败')
        return -1

def showresult(result):
    j=result
    vulns=j['vulns']
    for vulntype in vulns:
        FN=vulns[vulntype]['FN']
        TN=vulns[vulntype]['TN']
        FP=vulns[vulntype]['FP']
        TP=vulns[vulntype]['TP']
        YourdenIndex='Error'
        try:
            YourdenIndex=TP/(TP+FN)+TN/(TN+FP)-1
        except:
            pass
        print("类型:{0} FN:{1} TN:{2} FP:{3} TP:{4} 约登指数:{5}".format(vulntype,FN,TN,FP,TP,YourdenIndex))

def makereport(starttime,result, crawler_count_result):
    print('[*]正在生成报告')
    r=generatereport(starttime,result, crawler_count_result)
    reportfilename=time.strftime('report-%Y-%m-%d-%H-%M-%S.html',time.localtime())
    f=open(reportfilename,'wb')
    print(r)
    f.write(r)
    f.close()
    print('[+]报告已生成至{0}'.format(reportfilename))



def send_results(found_case, scan_id):
    from taskinfo.taskinfo import TASKS
    send_json_body_list = []
    # 所有漏洞类型
    all_case = [{"found": False, "url": x[0].split("?")[0]} for x in TASKS]
    not_found_case_list = []
    for vul_type in found_case.keys():
        t = found_case[vul_type]
        for item in set(t):
            if "sensitive/script.js" in item:
                item = item.replace("sensitive/script.js", "sensitive/BS00116")

            # vul_type = re.findall(".*/(\w+)/BS\d+", item)
            if len(vul_type) == 0:
                raise Exception("A path Error found : " + item)
            send_json_body_list.append({"vuls": [{"don not remove this dict": True, "vul_type":vul_type}], "url": item})

            for case in all_case:
                if case["url"] in item:
                    case["found"] = True
                    case["vul_type"] = vul_type

    for case in all_case:
        if not case["found"]:
            not_found_case_list.append({"vuls":[], "url": case["url"]})

    send_url = TARGET + "/api/scancallback?scanid=" + str(scan_id)
    for json_body in  send_json_body_list + not_found_case_list:
        requests.post(url=send_url, data=json.dumps(json_body))



if __name__=='__main__':
    starttime=time.localtime()
    print("[*]正在开启一轮新的记录")
    TASK_COUNT=0
    scanid=startnewscan()
    # 将扫描的结果发到靶机上进行统计计数

    send_results(foundVuls, scanid)


    while True:
        try:
            print('[*]正在等待结果')
            fetchcount=fetchcallbackcount()
            print("[*]当前扫描进度:{0}/{1}".format(fetchcount,TASK_COUNT))
            if fetchcount>=TASK_COUNT:
                print("[+]扫描完毕!")
                result = fetchresult()
                print(result)
                result=json.loads(result)

                crawler_count_result = fetch_crawler_result()
                print(crawler_count_result)
                crawler_count_result = json.loads(crawler_count_result)

                # showresult(result)
                makereport(starttime, result, crawler_count_result)
                exit(0)
        except Exception as e:
            print("[*]执行异常")
            print(e)
            exit(0)
