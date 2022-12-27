package com.iast.astbenchmark.cli.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.iast.astbenchmark.cases.bean.SourceTestObject;
import com.iast.astbenchmark.cases.bean.big.BigBean;
import com.iast.astbenchmark.cases.bean.big.BigOneBean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class CopyTestCaseForRun {

    public static volatile String url_root;
    /**
     * 此次本组Case执行时的唯一ID,用于在代理测对case数据的快速定位
     */
    private static Long caseUniqGroupId;
    

    static {
        url_root="http://localhost:39100/";
        caseUniqGroupId=System.currentTimeMillis();

    }

    public void aTaintCase001(){
        HttpResponse response = doGet(url_root+"ataint/case001/1");
        System.out.println(response.body());
    }
    
    public void aTaintCase002(){
        HttpResponse response = doGet(url_root+"ataint/case002/1");
        System.out.println(response.body());
    }
    
    public void aTaintCase003(){
        HttpResponse response = doGet(url_root+"ataint/case003/1");
        System.out.println(response.body());
    }
    
    public void aTaintCase004(){
        HttpResponse response = doGet(url_root+"ataint/case004/1");
        System.out.println(response.body());
    }
    
    public void aTaintCase005(){
        Map<String,String> map = Maps.newHashMap();
        map.put("cmd","ls");
        HttpResponse response = doPost(url_root+"ataint/case005",JSONUtil.toJsonStr(map));
        System.out.println(response.body());
    }
    
    public void aTaintCase006(){
        List<String> list = Lists.newArrayList();
        list.add("ls");
        HttpResponse response = doPost(url_root+"ataint/case006",JSONUtil.toJsonStr(list));
        System.out.println(response.body());
    }
    
    public void aTaintCase007(){
        List<String> list = Lists.newArrayList();
        list.add("ls");
        HttpResponse response = doPost(url_root+"ataint/case007",JSONUtil.toJsonStr(list));
        System.out.println(response.body());
    }
    
    public void aTaintCase008(){
        List<String> list = Lists.newArrayList();
        list.add("ls");
        HttpResponse response = doPost(url_root+"ataint/case008",JSONUtil.toJsonStr(list));
        System.out.println(response.body());
    }
    
    public void aTaintCase009(){
        HttpResponse response = doPost(url_root+"ataint/case009/1");
        System.out.println(response.body());
    }

    
    public void aTaintCase0010(){
        HttpResponse response = doPost(url_root+"ataint/case0010/1");
        System.out.println(response.body());
    }
    
    public void aTaintCase0011(){
        HttpResponse response = doPost(url_root+"ataint/case0011/1");
        System.out.println(response.body());
    }
    
    public void aTaintCase0012(){
        HttpResponse response = doPost(url_root+"ataint/case0012/1");
        System.out.println(response.body());
    }
    
    public void aTaintCase0013(){
        String[] strings = {"cd /","ls"};
        HttpResponse response = doPost(url_root+"ataint/case0013",JSONUtil.toJsonStr(strings));
        System.out.println(response.body());
    }
    
    public void aTaintCase0014(){
        int[] datas = {1,2};
        HttpResponse response = doPost(url_root+"ataint/case0014",JSONUtil.toJsonStr(datas));

        System.out.println(response.body());
    }
    
    public void aTaintCase0015(){
        byte[] datas = {1,2};
        HttpResponse response = doPost(url_root+"ataint/case0015",JSONUtil.toJsonStr(datas));
        System.out.println(response.body());
    }
    
    public void aTaintCase0016(){
        SourceTestObject object1= new SourceTestObject();
        SourceTestObject object2= new SourceTestObject();
        object1.setCmd("ls");
        object2.setCmd("cd ~");
        SourceTestObject[] objects = new SourceTestObject[2];
        objects[0]=object1;
        objects[1]=object2;
        HttpResponse response = doPost(url_root+"ataint/case0016",JSONUtil.toJsonStr(objects));

        System.out.println(response.body());
    }
    
    public void aTaintCase0017(){
        HttpResponse response = doPost(url_root+"ataint/case0017","ls");
        System.out.println(response.body());
    }
    
    public void aTaintCase0018(){
        HttpResponse response = doPost(url_root+"ataint/case0018","ls");
        System.out.println(response.body());
    }
    
    public void aTaintCase0019(){
        HttpResponse response = doPost(url_root+"ataint/case0019","ls");
        System.out.println(response.body());
    }
    
    public void aTaintCase0020(){
        SourceTestObject object = new SourceTestObject();
        object.setCmd("ls");
        HttpResponse response = doPost(url_root+"ataint/case0020",JSONUtil.toJsonStr(object));
        System.out.println(response.body());
    }
    
    public void aTaintCase0021(){
//        HttpResponse response = HttpRequest.post(url_root+"ataint/case0021")
//                .form("file",FileUtil.file("data/big.json")).execute();
        // File fileTmp = FileUtil.createTempFile(FileUtil.getTmpDir());
        //FileUtil.writeFromStream(file.getInputStream(),fileTmp);
        JSONArray array = JSONUtil.readJSONArray(FileUtil.file("data/big.json"), Charset.forName("utf-8"));
        List<BigOneBean> bigOneBeanList = JSONUtil.toList(array,BigOneBean.class);
        BigBean bigBean = new BigBean();
        bigBean.setBigOneBeanList(bigOneBeanList);
        HttpResponse response = HttpRequest.post(url_root+"ataint/case0021").body(JSONUtil.toJsonStr(bigBean)).execute();
        System.out.println(response.body());
    }

    
    public void aTaintCase0022(){
        HttpResponse response = doPost(url_root+"ataint/case0022/l%20s%20");
        System.out.println(response.body());
    }
    
    public void aTaintCase0023(){
        HttpResponse response = doPost(url_root+"ataint/case0023","ls");
        System.out.println(response.body());
    }
    
    public void aTaintCase0024(){
        HttpResponse response = doPost(url_root+"ataint/case0024/as");
        System.out.println(response.body());
    }

    
    public void aTaintCase0025(){
        String[] data = {"l","s"};
        HttpResponse response = doPost(url_root+"ataint/case0025",JSONUtil.toJsonStr(data));
        System.out.println(response.body());
    }
    
    public void aTaintCase0026(){
        HttpResponse response = doPost(url_root+"ataint/case0026/alasa","");

        System.out.println(response.body());
    }
    
    public void aTaintCase0027(){
        HttpResponse response = doPost(url_root+"ataint/case0027?data=ls","");

        System.out.println(response.body());
    }
    
    public void aTaintCase0028(){
        HttpResponse response = doPost(url_root+"ataint/case0028","");

        System.out.println(response.body());
    }
    
    public void aTaintCase0029(){
        HttpResponse response = doPost(url_root+"ataint/case0029","");

        System.out.println(response.body());
    }
    
    public void aTaintCase0030(){
        HttpResponse response = doPost(url_root+"ataint/case0030","");

        System.out.println(response.body());
    }
    
    public void aTaintCase0031(){
        HttpResponse response = doPost(url_root+"ataint/case0031","");

        System.out.println(response.body());
    }
    
    public void aTaintCase0032(){
        HttpResponse response = doPost(url_root+"ataint/case0032","");
        System.out.println(response.body());
    }
    
    public void aTaintCase0033(){
        HttpResponse response = doPost(url_root+"ataint/case0033","{\"cmd\":\"ls\"}");
        System.out.println(response.body());
    }
    
    public void aTaintCase0034(){
//        HttpResponse response = HttpRequest.post(url_root+"ataint/case0034").body("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//                "<cmd>ls</cmd>").execute();
        HttpResponse response = HttpRequest.post(url_root+"ataint/case0034")
                .form("file",FileUtil.file("data/case0034.xml")).execute();
        System.out.println(response.body());
    }
    
    public void aTaintCase0035(){
        HttpResponse response = HttpRequest.post(url_root+"ataint/case0035")
                .form("file",FileUtil.file("data/ls")).execute();

        System.out.println(response.body());
    }
    
    public void aTaintCase0036(){
        HttpResponse response = HttpRequest.post(url_root+"ataint/case0036")
                .form("file",FileUtil.file("data/ls")).execute();
        System.out.println(response.body());
    }

    
    public void aTaintCase0037(){
        HttpResponse response = doPost(url_root+"ataint/case0037?cmd=ls","");
        System.out.println(response.body());
    }
    
    public void aTaintCase0038(){
        HttpResponse response = doPost(url_root+"ataint/case0038?cmd=ls","");
        System.out.println(response.body());
    }
    
    public void aTaintCase0039(){
        HttpResponse response =doPost(url_root+"ataint/case0039?cmd=ls");
        System.out.println(response.body());
    }
    
    public void aTaintCase0040(){
        HttpResponse response = doPost(url_root+"ataint/case0039?cmd=ls");
        System.out.println(response.body());
    }

    
    public void aTaintCase0041(){
        String[] aa= {"l","s"} ;
        // HttpResponse response = HttpRequest.post(url_root+"ataint/case0041/ls?cmd=ls").body("ls").execute();
        HttpResponse response = HttpRequest.post(url_root+"ataint/case0041").body(JSONUtil.toJsonStr(aa)).execute();
        //.form("file",FileUtil.file("data/ls")).execute();
        System.out.println(response.body());
    }
    
    public void aTaintCase0042(){
        HttpResponse response = doPost(url_root+"ataint/case0042/123");
        System.out.println(response.body());
    }
    
    public void aTaintCase0043(){
        HttpResponse response = doPost(url_root+"ataint/case0043/ls");
        System.out.println(response.body());
    }
    
    public void aTaintCase0044(){
        HttpResponse response = doPost(url_root+"ataint/case0044/ls123");
        System.out.println(response.body());
    }
    
    public void aTaintCase0045(){
        HttpResponse response = HttpRequest.post(url_root+"ataint/case0045/")
                .cookie("ls").execute();

        System.out.println(response.body());
    }

    
    public void aTaintCase0046(){
        HttpResponse response = HttpRequest.post(url_root+"ataint/case0046/")
                .header("cmd","ls").execute();

        System.out.println(response.body());
    }
    
    public void aTaintCase0047(){
        HttpResponse response = HttpRequest.post(url_root+"ataint/case0047/")
                .header("cmd","ls").execute();

        System.out.println(response.body());
    }
    
    public void aTaintCase00139(){
        HttpResponse response = HttpRequest.post(url_root+"ataint/case00139/")
                .header("cmd","ls").execute();
        System.out.println(response.body());
    }

    
    public void aTaintCase0048(){
        HttpResponse response = doPost(url_root+"ataint/case0048?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0049(){
        HttpResponse response = doPost(url_root+"ataint/case0049?cmd=ls");

        System.out.println(response.body());
    }

    
    public void aTaintCase0050(){
        HttpResponse response = doPost(url_root+"ataint/case0050","{\"cmd\":\"ls\"}");

        System.out.println(response.body());
    }

    
    public void aTaintCase0051(){
        HttpResponse response = doPost(url_root+"ataint/case0051?cmd1=alasa&cmd2=a");

        System.out.println(response.body());
    }
    
    public void aTaintCase0052(){
        HttpResponse response = doPost(url_root+"ataint/case0052?cmd=ls");
        System.out.println(response.body());
    }
    
    public void aTaintCase0053(){
        HttpResponse response = doPost(url_root+"ataint/case0053?cmd=ls");
        System.out.println(response.body());
    }
    
    public void aTaintCase0054(){
        HttpResponse response = doPost(url_root+"ataint/case0054?cmd=ls");
        System.out.println(response.body());
    }
    
    public void aTaintCase0055(){
        HttpResponse response = doPost(url_root+"ataint/case0055?cmd1=alas&cmd=a");
        System.out.println(response.body());
    }
    
    public void aTaintCase0056(){
        HttpResponse response = doPost(url_root+"ataint/case0056?cmd=ls");
        System.out.println(response.body());
    }
    
    public void aTaintCase0056_2(){
        HttpResponse response = doPost(url_root+"ataint/case0056/2?cmd=ls");
        System.out.println(response.body());
    }
    
    public void aTaintCase0057(){
        HttpResponse response = doPost(url_root+"ataint/case0057?cmd=ls");
        System.out.println(response.body());
    }
    
    public void aTaintCase0058(){
        HttpResponse response = doPost(url_root+"ataint/case0058?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0059(){
        HttpResponse response = doPost(url_root+"ataint/case0059?cmd=ls");

        System.out.println(response.body());
    }

    
    public void aTaintCase0060(){
        HttpResponse response = doPost(url_root+"ataint/case0060?cmd=1");

        System.out.println(response.body());
    }

    
    public void aTaintCase0061(){
        HttpResponse response = doPost(url_root+"ataint/case0061?cmd=1");

        System.out.println(response.body());
    }
    
    public void aTaintCase0062(){
        HttpResponse response = doPost(url_root+"ataint/case0062?cmd=ls");

        System.out.println(response.body());
    }

    
    public void aTaintCase0063(){
        HttpResponse response = doPost(url_root+"ataint/case0063?cmd=ls");

        System.out.println(response.body());
    }

    
    public void aTaintCase0064(){
        HttpResponse response = doPost(url_root+"ataint/case0064?cmd=ls");

        System.out.println(response.body());
    }

    
    public void aTaintCase0065(){
        HttpResponse response = doPost(url_root+"ataint/case0065?cmd=ls");

        System.out.println(response.body());
    }

    
    public void aTaintCase0066(){
        HttpResponse response = doPost(url_root+"ataint/case0066?cmd=ls");

        System.out.println(response.body());
    }

    
    public void aTaintCase0067(){
        HttpResponse response = doPost(url_root+"ataint/case0067?cmd=lsaa");

        System.out.println(response.body());
    }
    
    public void aTaintCase0068(){
        HttpResponse response = doPost(url_root+"ataint/case0068?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0069(){
        HttpResponse response = doPost(url_root+"ataint/case0069?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0070(){
        HttpResponse response = doPost(url_root+"ataint/case0070?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0071(){
        HttpResponse response = doPost(url_root+"ataint/case0071?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00140(){
        HttpResponse response = doPost(url_root+"ataint/case00140?cmd=alasaa");

        System.out.println(response.body());
    }
    
    public void aTaintCase0072(){
        HttpResponse response = doPost(url_root+"ataint/case0072?cmd=ls%20pp");

        System.out.println(response.body());
    }
    
    public void aTaintCase0073(){
        HttpResponse response = doPost(url_root+"ataint/case0073?cmd=ls");
        System.out.println(response.body());
    }
    
    public void aTaintCase0074(){
        HttpResponse response = doPost(url_root+"ataint/case0074?cmd=lsaa");

        System.out.println(response.body());
    }
    
    public void aTaintCase0075(){
        HttpResponse response = doPost(url_root+"ataint/case0075?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0076(){
        HttpResponse response = doPost(url_root+"ataint/case0076?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0077(){
        HttpResponse response = doPost(url_root+"ataint/case0077?cmd=LS");

        System.out.println(response.body());
    }
    
    public void aTaintCase0078(){
        HttpResponse response = doPost(url_root+"ataint/case0078?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0079(){
        HttpResponse response = doPost(url_root+"ataint/case0079?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0080(){
        HttpResponse response = doPost(url_root+"ataint/case0080?cmd=%20ls%20");

        System.out.println(response.body());
    }
    
    public void aTaintCase0081(){
        HttpResponse response = doPost(url_root+"ataint/case0081?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0082(){
        HttpResponse response = doPost(url_root+"ataint/case0082?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0083(){
        HttpResponse response = doPost(url_root+"ataint/case0083?cmd=ls");

        System.out.println(response.body());
    }

    
    public void aTaintCase0084(){
        HttpResponse response = doPost(url_root+"ataint/case0084?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0085(){
        HttpResponse response = doPost(url_root+"ataint/case0085?cmd=lsabc");

        System.out.println(response.body());
    }
    
    public void aTaintCase0086(){
        HttpResponse response = doPost(url_root+"ataint/case0086?cmd=lsa");

        System.out.println(response.body());
    }
    
    public void aTaintCase0087(){
        HttpResponse response = doPost(url_root+"ataint/case0087?cmd=lsabc");

        System.out.println(response.body());
    }
    
    public void aTaintCase0088(){
        HttpResponse response = doPost(url_root+"ataint/case0088?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0089(){
        HttpResponse response = doPost(url_root+"ataint/case0089?cmd=ls;-la");

        System.out.println(response.body());
    }
    
    public void aTaintCase0090(){
        HttpResponse response = doPost(url_root+"ataint/case0090?cmd=lsabc");

        System.out.println(response.body());
    }
    
    public void aTaintCase0091(){
        HttpResponse response = doPost(url_root+"ataint/case0091?cmd=lsabc");

        System.out.println(response.body());
    }
    
    public void aTaintCase0092(){
        HttpResponse response = doPost(url_root+"ataint/case0092?cmd=lsabc");

        System.out.println(response.body());
    }
    
    public void aTaintCase0093(){
        HttpResponse response = doPost(url_root+"ataint/case0093?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0094(){
        HttpResponse response = doPost(url_root+"ataint/case0094?cmd=ls");

        System.out.println(response.body());
    }

    
    public void aTaintCase0095(){
        HttpResponse response = doPost(url_root+"ataint/case0095?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0096(){
        HttpResponse response = doPost(url_root+"ataint/case0096?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0097(){
        HttpResponse response = doPost(url_root+"ataint/case0097?cmd=ls");

        System.out.println(response.body());
    }

    
    public void aTaintCase0098(){
        HttpResponse response = doPost(url_root+"ataint/case0098?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase0099(){
        HttpResponse response = doPost(url_root+"ataint/case0099?cmd=reportidsql");
        HttpResponse response2= doPost(url_root+"ataint/case0099/2?cmd=reportidsql");
        System.out.println(response.body()+"&"+response2.body());
    }

//    
//    public void aTaintCase00100(){
//        HttpResponse response = doPost(url_root+"ataint/case00100?cmd=reportidsql");
//        HttpResponse response2 = doPost(url_root+"ataint/case00100/2?cmd=reportidsql");
//        System.out.println(response.body()+"&"+response2.body());
//    }

    
    public void aTaintCase00102(){
        HttpResponse response = doPost(url_root+"ataint/case00102?cmd=reportidsql");
        HttpResponse response2 = doPost(url_root+"ataint/case00102/2?cmd=reportidsql");
        System.out.println(response.body()+"&"+response2.body());
    }

    
    public void aTaintCase00141(){
        HttpResponse response = doPost(url_root+"ataint/case00141?cmd=reportidsql");
        HttpResponse response2 = doPost(url_root+"ataint/case00141/2?cmd=reportidsql");
        System.out.println(response.body()+"&"+response2.body());
    }

    
    public void aTaintCase00103(){
        HttpResponse response = doPost(url_root+"ataint/case00103?cmd=reportidsql");
        System.out.println(response.body());
    }


    /**--------*/
    
    public void aTaintCase00104(){
        HttpResponse response = doPost(url_root+"ataint/case00104?cmd=reportidsql");
        HttpResponse response2 = doPost(url_root+"ataint/case00104/2?cmd=reportidsql");
        System.out.println(response.body()+"&"+response2.body());
    }
    //TODO 105
    
    public void aTaintCase00106(){
        HttpResponse response = doPost(url_root+"ataint/case00106?cmd=ls");

        System.out.println(response.body());
    }


    
    public void aTaintCase00107(){
        HttpResponse response = doPost(url_root+"ataint/case00107?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00108(){
        HttpResponse response = doPost(url_root+"ataint/case00108?path=data%2Fls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00109(){
        HttpResponse response = doPost(url_root+"ataint/case00109?cmd=ls");

        System.out.println(response.body());
    }

    
    public void aTaintCase00110(){
        HttpResponse response = doPost(url_root+"ataint/case00110?cmd1=ls&cmd2=la");

        System.out.println(response.body());
    }
    
    public void aTaintCase00111(){

        HttpResponse response = doPost(url_root+"ataint/case00111?path=data\\/ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00112(){
        HttpResponse response = doPost(url_root+"ataint/case00112?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00113(){
        HttpResponse response = doPost(url_root+"ataint/case00113?cmd=ls");

        System.out.println(response.body());
    }

    
    public void aTaintCase00114(){
        HttpResponse response = doPost(url_root+"ataint/case00114/ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00115(){
        HttpResponse response = doPost(url_root+"ataint/case00115?cmd=ls");
        System.out.println(response.body());
    }

    
    public void aTaintCase00138(){
        HttpResponse response = doPost(url_root+"ataint/case00138?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00116(){
        HttpResponse response = doPost(url_root+"ataint/case00116?cmd=ls");

        System.out.println(response.body());
    }
    /**
     *  aTaintCase00117 异步跟踪能力->存储行异步->污点通过缓存存储后触发->OSS
     */
    /**
     * TODO aTaintCase00118 异步跟踪能力->存储行异步->支持自定义污点的存储和再次提取点
     */
    
    public void aTaintCase00119(){
        HttpResponse response = doPost(url_root+"ataint/case00119?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00120(){
        HttpResponse response = doPost(url_root+"ataint/case00120?cmd=ls");

        System.out.println(response.body());
    }
    //No 21~22
    
    public void aTaintCase00123(){
        HttpResponse response = doPost(url_root+"ataint/case00123?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00124(){
        HttpResponse response = doPost(url_root+"ataint/case00124?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00125(){
        HttpResponse response = doPost(url_root+"ataint/case00125?cmd1=cd%20/&cmd2=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00126(){
        HttpResponse response = doPost(url_root+"ataint/case00126?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00126_2(){
        HttpResponse response = doPost(url_root+"ataint/case00126/2?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00127(){
        HttpResponse response = doPost(url_root+"ataint/case00127?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00127_2(){
        HttpResponse response = doPost(url_root+"ataint/case00127/2?cmd=ls");

        System.out.println(response.body());
    }

    
    public void aTaintCase00128(){
        HttpResponse response = doPost(url_root+"ataint/case00128?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00128_2(){
        HttpResponse response = doPost(url_root+"ataint/case00128/2?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00129(){
        HttpResponse response = doPost(url_root+"ataint/case00129?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00129_2(){
        HttpResponse response = doPost(url_root+"ataint/case00129/2?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00130(){
        HttpResponse response = doPost(url_root+"ataint/case00130?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00130_2(){
        HttpResponse response = doPost(url_root+"ataint/case00130/2?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00131(){
        HttpResponse response = doPost(url_root+"ataint/case00131?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00131_2(){
        HttpResponse response = doPost(url_root+"ataint/case00131/2?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00132(){
        HttpResponse response = doPost(url_root+"ataint/case00132?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00132_2(){
        HttpResponse response = doPost(url_root+"ataint/case00132/2?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00133(){
        HttpResponse response = doPost(url_root+"ataint/case00133?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00133_2(){
        HttpResponse response = doPost(url_root+"ataint/case00133/2?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00134(){
        HttpResponse response = doPost(url_root+"ataint/case00134?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00134_2(){
        HttpResponse response = doPost(url_root+"ataint/case00134/2?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00135(){
        HttpResponse response = doPost(url_root+"ataint/case00135?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00135_2(){
        HttpResponse response = doPost(url_root+"ataint/case00135/2?cmd=ls");;

        System.out.println(response.body());
    }
    
    public void aTaintCase00136(){
        HttpResponse response = doPost(url_root+"ataint/case00136?cmd=ls");;

        System.out.println(response.body());
    }
    
    public void aTaintCase00136_2(){
        HttpResponse response = doPost(url_root+"ataint/case00136/2?cmd=ls");

        System.out.println(response.body());
    }
    
    public void aTaintCase00137(){
        HttpResponse response = doPost(url_root+"ataint/case00137","http://localhost/nothing");
        System.out.println(response.body());
    }
    
    public void aTaintCase00137_2(){
        HttpResponse response = doPost(url_root+"ataint/case00137/2","/nothing");
        System.out.println(response.body());
    }
    
    public void aTaintCase00142(){
        SourceTestObject testObject = new SourceTestObject();
        testObject.setCmd("ls");
        HttpResponse response = doPost(url_root+"ataint/case00142",JSONUtil.toJsonStr(testObject));
        HttpResponse response2 = doPost(url_root+"ataint/case00142/2",JSONUtil.toJsonStr(testObject));
        System.out.println(response.body()+"&"+response2.body());
    }

    private  HttpResponse doGet(String url){
        url=urlWithTime(url);
        try {
            HttpResponse response = HttpUtil.createGet(url).execute();
            if(response.getStatus()==500){
                return retryDoGet(url);
            }else {
                return response;
            }
        }catch (Exception e){
            return retryDoGet(url);
        }
    }
    private HttpResponse retryDoGet(String url){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
        }
        HttpResponse response2 = HttpUtil.createGet(url).execute();
        return response2;
    }
    private HttpResponse doPost(String url){
        return doPost(url,"");
    }
    private  HttpResponse doPost(String url,String body){
        url=urlWithTime(url);
        try {
            HttpResponse response = HttpUtil.createPost(url).body(body).execute();
            if(response.getStatus()==500){
                return retryDoPost(url,body);
            }else {
                return response;
            }
        }catch (Exception e){
            return retryDoPost(url,body);
        }
    }
    private HttpResponse retryDoPost(String url,String body){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
        }
        HttpResponse response2 = HttpUtil.createPost(url).body(body).execute();
        return response2;
    }

    private String urlWithTime(String url){
        if(url.contains("?")){
            return url+"&auto_check_start_time="+caseUniqGroupId;
        }else {
            return url+"?auto_check_start_time="+caseUniqGroupId;
        }
    }
}
