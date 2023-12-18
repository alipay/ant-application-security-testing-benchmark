package com.iast.astbenchmark;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.iast.astbenchmark.cases.bean.SourceTestObject;
import com.iast.astbenchmark.cases.bean.SourceTestWith100Filedsbject;
import com.iast.astbenchmark.cases.bean.SourceTestWith10Filedsbject;
import com.iast.astbenchmark.cases.bean.SoureWithQueueBean;
import com.iast.astbenchmark.cases.bean.SoureWithSetBean;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean2;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean9;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class AstbenchmarkApplicationTests {

    private static String url_root;
    /**
     * 此次本组Case执行时的唯一ID,用于在代理测对case数据的快速定位
     */
    private static Long caseUniqGroupId;

    @Test
    void contextLoads() {

    }

    @BeforeAll
    static void init() {
        url_root = "http://localhost:39100/";
        caseUniqGroupId=System.currentTimeMillis();
        //caseUniqGroupId = 54877081211069L;
        System.out.println("请保存此次跑case的关键字:" + caseUniqGroupId);
    }

    @BeforeEach
    void before() {
        try {
            TimeUnit.SECONDS.sleep(RandomUtil.randomInt(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void aTaintCase001() {
        HttpResponse response = doGet(url_root + "ataint/case001?cmd=1");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase002() {
        HttpResponse response = doGet(url_root + "ataint/case002?cmd=1");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase003() {
        HttpResponse response = doGet(url_root + "ataint/case003?cmd=1");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase004() {
        HttpResponse response = doGet(url_root + "ataint/case004?cmd=1");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase009() {
        HttpResponse response = doPost(url_root + "ataint/case009?cmd=1");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0010() {
        HttpResponse response = doPost(url_root + "ataint/case0010?cmd=1");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0011() {
        HttpResponse response = doPost(url_root + "ataint/case0011?cmd=1");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00901() {
        String sink = "ls";
        HttpResponse response = doPost(url_root + "ataint/case00901?cmd="+sink);
        System.out.println(response.body());
    }

    @Test
    void aTaintCase005() {
        Map<String, String> map = Maps.newHashMap();
        map.put("cmd", "ls");
        HttpResponse response = doPost(url_root + "ataint/case005", JSONUtil.toJsonStr(map));
        System.out.println(response.body());
    }

    @Test
    void aTaintCase006() {
        List<String> list = Lists.newArrayList();
        list.add("ls");
        HttpResponse response = doPost(url_root + "ataint/case006", JSONUtil.toJsonStr(list));
        System.out.println(response.body());
    }

    @Test
    void aTaintCase007() throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.put("ls");
        queue.put("cd /");
        SoureWithQueueBean queueBean = new SoureWithQueueBean();
        queueBean.setKey("key");
        queueBean.setQueue(queue);
        HttpResponse response = doPost(url_root + "ataint/case007", JSONUtil.toJsonStr(queueBean));
        System.out.println(response.body());
    }

    @Test
    void aTaintCase008() {
        Set<String> set= new HashSet<>();
        set.add("ls");
        set.add("key");
        SoureWithSetBean setBean = new SoureWithSetBean();
        setBean.setKey("key");
        setBean.setValue(set);
        HttpResponse response = doPost(url_root + "ataint/case008", JSONUtil.toJsonStr(setBean));
        System.out.println(response.body());
    }


    //@Test
    //void aTaintCase00927() {
    //    Map<String, String> map = Maps.newHashMap();
    //    map.put("cmd", "ls");
    //    HttpResponse response = doPost(url_root + "ataint/case00927", JSONUtil.toJsonStr(map));
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase00928() {
    //    List<String> list = Lists.newArrayList();
    //    list.add("ls");
    //    HttpResponse response = doPost(url_root + "ataint/case00928", JSONUtil.toJsonStr(list));
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase00929() {
    //    List<String> list = Lists.newArrayList();
    //    list.add("ls");
    //    HttpResponse response = doPost(url_root + "ataint/case00929", JSONUtil.toJsonStr(list));
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase00930() {
    //    List<String> list = Lists.newArrayList();
    //    list.add("ls");
    //    HttpResponse response = doPost(url_root + "ataint/case00930", JSONUtil.toJsonStr(list));
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase009() {
    //    HttpResponse response = doPost(url_root + "ataint/case009/1");
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0010() {
    //    HttpResponse response = doPost(url_root + "ataint/case0010/1");
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0011() {
    //    HttpResponse response = doPost(url_root + "ataint/case0011/1");
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0012() {
    //    HttpResponse response = doPost(url_root + "ataint/case0012/1");
    //    System.out.println(response.body());
    //}

    @Test
    void aTaintCase0013() {
        String[] strings = {"cd /", "ls"};
        HttpResponse response = doPost(url_root + "ataint/case0013", JSONUtil.toJsonStr(strings));
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0014() {
        //int[] datas = {1,2};
        HttpResponse response = doPost(url_root + "ataint/case0014?cmd=ls");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0015() {
        byte[] datas = {1, 2};
        HttpResponse response = doPost(url_root + "ataint/case0015", JSONUtil.toJsonStr(datas));
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0016() {
        SourceTestObject object1 = new SourceTestObject();
        SourceTestObject object2 = new SourceTestObject();
        object1.setCmd("ls");
        object2.setCmd("cd ~");
        SourceTestObject[] objects = new SourceTestObject[2];
        objects[0] = object1;
        objects[1] = object2;
        HttpResponse response = doPost(url_root + "ataint/case0016", JSONUtil.toJsonStr(objects));

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00926() {
        SourceTestObject object1 = new SourceTestObject();
        SourceTestObject object2 = new SourceTestObject();
        SourceTestObject object3 = new SourceTestObject();
        SourceTestObject object4 = new SourceTestObject();
        object1.setCmd("ls");
        object2.setCmd("cd ~");
        object3.setCmd("ls -a");
        object4.setCmd("cd /");
        SourceTestObject[][] objects = new SourceTestObject[2][2];
        objects[0][0] = object1;
        objects[1][1] = object2;
        objects[0][1] =object3;
        objects[1][0] = object4;
        HttpResponse response = doPost(url_root + "ataint/case00926", JSONUtil.toJsonStr(objects));

        System.out.println(response.body());
    }

    //@Test
    //void aTaintCase0017() {
    //    HttpResponse response = doPost(url_root + "ataint/case0017", "ls");
    //    System.out.println(response.body());
    //}
    //
    //@Test
    //void aTaintCase0018() {
    //    HttpResponse response = doPost(url_root + "ataint/case0018", "ls");
    //    System.out.println(response.body());
    //}
    //
    //@Test
    //@Test
    //void aTaintCase0019() {
    //    HttpResponse response = doPost(url_root + "ataint/case0019", "ls");
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0020() {
    //    SourceTestObject object = new SourceTestObject();
    //    object.setCmd("ls");
    //    HttpResponse response = doPost(url_root + "ataint/case0020", JSONUtil.toJsonStr(object));
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0021() {
    //    JSON json = JSONUtil.readJSON(FileUtil.file("data/big.json"), Charset.forName("utf-8"));
    //    SourceTestWithMPObject object = JSONUtil.toBean(json, SourceTestWithMPObject.class, true);
    //    HttpResponse response = HttpRequest.post(url_root + "ataint/case0021" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
    //    System.out.println(response.body());
    //}
    //
    //@Test
    //void aTaintCase0021_2() {
    //    JSON json = JSONUtil.readJSON(FileUtil.file("data/big.json"), Charset.forName("utf-8"));
    //    SourceTestWithMPObject object = JSONUtil.toBean(json, SourceTestWithMPObject.class, true);
    //    HttpResponse response = HttpRequest.post(url_root + "ataint/case0021/2" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
    //    System.out.println(response.body());
    //}
    //
    //@Test
    //void aTaintCase0021_3() {
    //    JSON json = JSONUtil.readJSON(FileUtil.file("data/big.json"), Charset.forName("utf-8"));
    //    SourceTestWithMPObject object = JSONUtil.toBean(json, SourceTestWithMPObject.class, true);
    //    HttpResponse response = HttpRequest.post(url_root + "ataint/case0021/3" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
    //    System.out.println(response.body());
    //}

    @Test
    void aTaintCase00921() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/big.json"), Charset.forName("utf-8"));
        SourceTestWith10Filedsbject object = JSONUtil.toBean(json, SourceTestWith10Filedsbject.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00921" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00921_2() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/big.json"), Charset.forName("utf-8"));
        SourceTestWith10Filedsbject object = JSONUtil.toBean(json, SourceTestWith10Filedsbject.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00921/2" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00921_3() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/big.json"), Charset.forName("utf-8"));
        SourceTestWith10Filedsbject object = JSONUtil.toBean(json, SourceTestWith10Filedsbject.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00921/3" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00922() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/big.json"), Charset.forName("utf-8"));
        SourceTestWith100Filedsbject object = JSONUtil.toBean(json, SourceTestWith100Filedsbject.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00922" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00922_2() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/big.json"), Charset.forName("utf-8"));
        SourceTestWith100Filedsbject object = JSONUtil.toBean(json, SourceTestWith100Filedsbject.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00922/2" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00922_3() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/big.json"), Charset.forName("utf-8"));
        SourceTestWith100Filedsbject object = JSONUtil.toBean(json, SourceTestWith100Filedsbject.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00922/3" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00923() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/biglayer.json"), Charset.forName("utf-8"));
        LayerBaseBean2 object = JSONUtil.toBean(json, LayerBaseBean2.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00923" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00923_2() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/biglayer.json"), Charset.forName("utf-8"));
        LayerBaseBean2 object = JSONUtil.toBean(json, LayerBaseBean2.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00923/2" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00923_3() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/biglayer.json"), Charset.forName("utf-8"));
        LayerBaseBean2 object = JSONUtil.toBean(json, LayerBaseBean2.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00923/3" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }
    @Test
    void aTaintCase00924() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/biglayer.json"), Charset.forName("utf-8"));
        LayerBaseBean2 object = JSONUtil.toBean(json, LayerBaseBean9.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00924" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00924_2() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/biglayer.json"), Charset.forName("utf-8"));
        LayerBaseBean2 object = JSONUtil.toBean(json, LayerBaseBean9.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00924/2" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00924_3() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/biglayer.json"), Charset.forName("utf-8"));
        LayerBaseBean9 object = JSONUtil.toBean(json, LayerBaseBean9.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00924/3" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }
    @Test
    void aTaintCase00925() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/biglayer.json"), Charset.forName("utf-8"));
        LayerBaseBean2 object = JSONUtil.toBean(json, LayerBaseBean2.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00925" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00925_2() {
        JSON json = JSONUtil.readJSON(FileUtil.file("data/biglayer.json"), Charset.forName("utf-8"));
        LayerBaseBean2 object = JSONUtil.toBean(json, LayerBaseBean2.class, true);
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00925/2" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(object)).execute();
        System.out.println(response.body());
    }
    @Test
    void aTaintCase0022() {
        HttpResponse response = doPost(url_root + "ataint/case0022?cmd=l%20s%20");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0023() {
        HttpResponse response = doPost(url_root + "ataint/case0023", "ls");
        System.out.println(response.body());
    }
    @Test
    void aTaintCase00931() {
        HttpResponse response = doPost(url_root + "ataint/case00931", "ls");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0024() {
        HttpResponse response = doPost(url_root + "ataint/case0024?cmd=ls");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0025() {
        String[] data = {"ls"};
        HttpResponse response = doPost(url_root + "ataint/case0025", JSONUtil.toJsonStr(data));
        System.out.println(response.body());
    }

    //    @Test
//    void aTaintCase0026(){
//        HttpResponse response = doPost(url_root+"ataint/case0026/alasa","");
//
//        System.out.println(response.body());
//    }
    @Test
    void aTaintCase0027() {
        HttpResponse response = doPost(url_root + "ataint/case0027?data=ls", "");

        System.out.println(response.body());
    }

    //    @Test
//    void aTaintCase0028(){
//        HttpResponse response = doPost(url_root+"ataint/case0028","");
//
//        System.out.println(response.body());
//    }
//    @Test
//    void aTaintCase0029(){
//        HttpResponse response = doPost(url_root+"ataint/case0029","");
//
//        System.out.println(response.body());
//    }
//    @Test
//    void aTaintCase0030(){
//        HttpResponse response = doPost(url_root+"ataint/case0030","");
//
//        System.out.println(response.body());
//    }
//    @Test
//    void aTaintCase0031(){
//        HttpResponse response = doPost(url_root+"ataint/case0031","");
//
//        System.out.println(response.body());
//    }
//    @Test
//    void aTaintCase0032(){
//        HttpResponse response = doPost(url_root+"ataint/case0032","");
//        System.out.println(response.body());
//    }
    @Test
    void aTaintCase0033() {
        HttpResponse response = doPost(url_root + "ataint/case0033", "{\"cmd\":\"ls\"}");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0034() {
        HttpResponse response = HttpRequest.post(url_root + "ataint/case0034" + "?auto_check_start_time=" + caseUniqGroupId).contentType(MediaType.APPLICATION_XML_VALUE)
                .body("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "\n" +
                        "<message> \n" +
                        "      <phase>ls</phase>  \n" +
                        "</message>").execute();

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0035() {
        HttpResponse response = HttpRequest.post(url_root + "ataint/case0035" + "?auto_check_start_time=" + caseUniqGroupId)
                .form("file", FileUtil.file("data/ls")).execute();

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0036() {
        HttpResponse response = HttpRequest.post(url_root + "ataint/case0036" + "?auto_check_start_time=" + caseUniqGroupId)
                .form("file", FileUtil.file("data/ls")).execute();
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0037() {
        HttpResponse response = doPost(url_root + "ataint/case0037?cmd=ls", "");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0038() {
        HttpResponse response = doPost(url_root + "ataint/case0038?cmd=ls", "");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0039() {
        HttpResponse response = doPost(url_root + "ataint/case0039?cmd=ls");
        System.out.println(response.body());

    }

    @Test
    void aTaintCase0040() {
        HttpResponse response = doPost(url_root + "ataint/case0040?cmd=ls");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0041() {
        String[] aa = {"l", "s"};
        // HttpResponse response = HttpRequest.post(url_root+"ataint/case0041/ls?cmd=ls").body("ls").execute();
        HttpResponse response = HttpRequest.post(url_root + "ataint/case0041" + "?auto_check_start_time=" + caseUniqGroupId).body(JSONUtil.toJsonStr(aa)).execute();
        //.form("file",FileUtil.file("data/ls")).execute();
        System.out.println(response.body());
    }

    //    @Test
//    void aTaintCase0042(){
//        HttpResponse response = doPost(url_root+"ataint/case0042/ls");
//        System.out.println(response.body());
//    }
//    @Test
//    void aTaintCase0043(){
//        HttpResponse response = doPost(url_root+"ataint/case0043/ls");
//        System.out.println(response.body());
//    }
    @Test
    void aTaintCase0044() {
        HttpResponse response = doPost(url_root + "ataint/case0044/ls123");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0045() {
        HttpResponse response = HttpRequest.post(url_root + "ataint/case0045" + "?auto_check_start_time=" + caseUniqGroupId)
                .cookie("ls").execute();

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0046() {
        HttpResponse response = HttpRequest.post(url_root + "ataint/case0046" + "?auto_check_start_time=" + caseUniqGroupId)
                .header("cmd", "ls").execute();

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0047() {
        HttpResponse response = HttpRequest.post(url_root + "ataint/case0047" + "?auto_check_start_time=" + caseUniqGroupId)
                .header("cmd", "ls").execute();

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00139() {
        HttpResponse response = HttpRequest.post(url_root + "ataint/case00139" + "?auto_check_start_time=" + caseUniqGroupId)
                .header("cmd", "ls").execute();
        System.out.println(response.body());
    }

    //@Test
    //void aTaintCase0048() {
    //    HttpResponse response = doPost(url_root + "ataint/case0048?cmd=ls");
    //
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0049() {
    //    HttpResponse response = doPost(url_root + "ataint/case0049?cmd=ls");
    //
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0050() {
    //    HttpResponse response = doPost(url_root + "ataint/case0050", "{\"cmd\":\"ls\"}");
    //
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0051() {
    //    HttpResponse response = doPost(url_root + "ataint/case0051?cmd1=alasa&cmd2=a");
    //
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0052() {
    //    HttpResponse response = doPost(url_root + "ataint/case0052?cmd=ls");
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0053() {
    //    HttpResponse response = doPost(url_root + "ataint/case0053?cmd=ls");
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0054() {
    //    SourceTestWithConstract01Bean bean = MyCommonTestUtil.buildTestObject("ls");
    //    HttpResponse response = doPost(url_root + "ataint/case0054", JSONUtil.toJsonStr(bean));
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0055() {
    //    HttpResponse response = doPost(url_root + "ataint/case0055?cmd1=alas&cmd=a");
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0056() {
    //    HttpResponse response = doPost(url_root + "ataint/case0056?cmd=ls");
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0056_2() {
    //    HttpResponse response = doPost(url_root + "ataint/case0056/2?cmd=ls");
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0057() {
    //    HttpResponse response = doPost(url_root + "ataint/case0057?cmd=ls");
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0058() {
    //    HttpResponse response = doPost(url_root + "ataint/case0058?cmd=ls");
    //
    //    System.out.println(response.body());
    //}

   // @Test
    //void aTaintCase0059() {
    //    HttpResponse response = doPost(url_root + "ataint/case0059?cmd=ls");
    //
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0060() {
    //    HttpResponse response = doPost(url_root + "ataint/case0060?cmd=ls");
    //
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0061() {
    //    HttpResponse response = doPost(url_root + "ataint/case0061?cmd=1");
    //
    //    System.out.println(response.body());
    //}

    @Test
    void aTaintCase0062() {
        HttpResponse response = doPost(url_root + "ataint/case0062?cmd=ls");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0063() {
        HttpResponse response = doPost(url_root + "ataint/case0063?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0064() {
        HttpResponse response = doPost(url_root + "ataint/case0064?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0065() {
        HttpResponse response = doPost(url_root + "ataint/case0065?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0066() {
        HttpResponse response = doPost(url_root + "ataint/case0066?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0067() {
        HttpResponse response = doPost(url_root + "ataint/case0067?cmd=lsaa");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0068() {
        HttpResponse response = doPost(url_root + "ataint/case0068?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0069() {
        HttpResponse response = doPost(url_root + "ataint/case0069?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0070() {
        HttpResponse response = doPost(url_root + "ataint/case0070?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0071() {
        HttpResponse response = doPost(url_root + "ataint/case0071?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00140() {
        HttpResponse response = doPost(url_root + "ataint/case00140?cmd=alasaa");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0072() {
        HttpResponse response = doPost(url_root + "ataint/case0072?cmd=ls%20pp");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0073() {
        HttpResponse response = doPost(url_root + "ataint/case0073?cmd=ls");
        System.out.println(response.body());
    }

    @Test
    void  aTaintCase0074() {
        HttpResponse response = doPost(url_root + "ataint/case0074?cmd=lsaa");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0075() {
        HttpResponse response = doPost(url_root + "ataint/case0075?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0076() {
        HttpResponse response = doPost(url_root + "ataint/case0076?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0077() {
        HttpResponse response = doPost(url_root + "ataint/case0077?cmd=LS");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0078() {
        HttpResponse response = doPost(url_root + "ataint/case0078?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0079() {
        HttpResponse response = doPost(url_root + "ataint/case0079?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0080() {
        HttpResponse response = doPost(url_root + "ataint/case0080?cmd=%20ls%20");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0081() {
        HttpResponse response = doPost(url_root + "ataint/case0081?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0082() {
        HttpResponse response = doPost(url_root + "ataint/case0082?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0083() {
        HttpResponse response = doPost(url_root + "ataint/case0083?cmd=ls");

        System.out.println(response.body());
    }

    //@Test
    //void aTaintCase0084() {
    //    HttpResponse response = doPost(url_root + "ataint/case0084?cmd=ls");
    //
    //    System.out.println(response.body());
    //}

    @Test
    void aTaintCase0085() {
        HttpResponse response = doPost(url_root + "ataint/case0085?cmd=lsabc");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0086() {
        HttpResponse response = doPost(url_root + "ataint/case0086?cmd=lsa");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0087() {
        HttpResponse response = doPost(url_root + "ataint/case0087?cmd=lsabc");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0088() {
        HttpResponse response = doPost(url_root + "ataint/case0088?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0089() {
        HttpResponse response = doPost(url_root + "ataint/case0089?cmd=lsa");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0090() {
        HttpResponse response = doPost(url_root + "ataint/case0090?cmd=lsabc");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0091() {
        HttpResponse response = doPost(url_root + "ataint/case0091?cmd=lsabc");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0092() {
        HttpResponse response = doPost(url_root + "ataint/case0092?cmd=lsabc");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0093() {
        HttpResponse response = doPost(url_root + "ataint/case0093?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0094() {
        HttpResponse response = doPost(url_root + "ataint/case0094?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0095() {
        HttpResponse response = doPost(url_root + "ataint/case0095?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase0096() {
        HttpResponse response = doPost(url_root + "ataint/case0096?cmd=ls");

        System.out.println(response.body());
    }

    //@Test
    //void aTaintCase0097() {
    //    HttpResponse response = doPost(url_root + "ataint/case0097?cmd1=ls&cmd2=cd");
    //    System.out.println(response.body());
    //}

    //@Test
    //void aTaintCase0098() {
    //    HttpResponse response = doPost(url_root + "ataint/case0098?cmd=ls");
    //
    //    System.out.println(response.body());
    //}

    @Test
    void aTaintCase0099() {
        HttpResponse response = doPost(url_root + "ataint/case0099?cmd=reportidsql");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase0099_2() {
        HttpResponse response2 = doPost(url_root + "ataint/case0099/2?cmd=reportidsql");
        System.out.println(response2.body());
    }

    @Test
    void aTaintCase00102() {
        HttpResponse response = doPost(url_root + "ataint/case00102?cmd=reportidsql");
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00102_2() {
        HttpResponse response2 = doPost(url_root + "ataint/case00102/2?cmd=reportidsql");
        System.out.println(response2.body());
    }

    @Test
    void aTaintCase00141() {
        HttpResponse response = doPost(url_root + "ataint/case00141?cmd=test");
        HttpResponse response1 = doPost(url_root + "ataint/case00141/1?cmd=test");
        System.out.println(response.body());
    }


    @Test
    void aTaintCase00103() {
        HttpResponse response = doPost(url_root + "ataint/case00103?cmd=<p>ls<p>");
        HttpResponse response2 = doPost(url_root + "ataint/case00103/2?cmd=<p>ls<p>");
        HttpResponse response1 = doPost(url_root + "ataint/case00103/1?cmd=<p>ls<p>");
        System.out.println(response.body());
    }


    /**
     * --------
     */
    @Test
    void aTaintCase00104() {
        HttpResponse response = doPost(url_root + "ataint/case00104?cmd=reportidsql");
        System.out.println(response.body());
    }
    @Test
    void aTaintCase00104_1() {
        HttpResponse response2 = doPost(url_root + "ataint/case00104/1?cmd=reportidsql");
        System.out.println(response2.body());
    }

    @Test
    void aTaintCase00104_2() {
        HttpResponse response2 = doPost(url_root + "ataint/case00104/2?cmd=reportidsql");
        System.out.println(response2.body());
    }

    //TODO 105
    @Test
    void aTaintCase00105() {
        HttpResponse response = doPost(url_root + "ataint/case00105?cmd=reportidsql");
        HttpResponse response1 = doPost(url_root + "ataint/case00105/1?cmd=reportidsql");
        HttpResponse response2 = doPost(url_root + "ataint/case00105/2?cmd=reportidsql");
        System.out.println(response.body());
    }
    @Test
    void aTaintCase00106() {
        HttpResponse response = doPost(url_root + "ataint/case00106?cmd=ls");

        System.out.println(response.body());
    }


    @Test
    void aTaintCase00107() {
        HttpResponse response = doPost(url_root + "ataint/case00107?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00108() {
        HttpResponse response = doPost(url_root + "ataint/case00108?path=data%2Fls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00109() {
        HttpResponse response = doPost(url_root + "ataint/case00109?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00110() {
        HttpResponse response = doPost(url_root + "ataint/case00110?cmd1=ls&cmd2=la");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00111() {

        HttpResponse response = doPost(url_root + "ataint/case00111?path=data\\/ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00112() {
        HttpResponse response = doPost(url_root + "ataint/case00112?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00113() {
        HttpResponse response = doPost(url_root + "ataint/case00113?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00114() {
        HttpResponse response = doPost(url_root + "ataint/case00114/ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00114/1");
        System.out.println("aTaintCase00114"+response.body());
    }

    @Test
    void aTaintCase00115() {
        HttpResponse response = doPost(url_root + "ataint/case00115?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00115/1");
        System.out.println("aTaintCase00115"+response.body());
    }

    @Test
    void aTaintCase00138() {
        HttpResponse response = doPost(url_root + "ataint/case00138?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00138/1");
        System.out.println("aTaintCase00138"+response.body());
    }

    @Test
    void aTaintCase00116() {
        HttpResponse response = doPost(url_root + "ataint/case00116?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00116/1");
        System.out.println("aTaintCase00116"+response.body());
    }
    /**
     *  aTaintCase00117 异步跟踪能力->存储行异步->污点通过缓存存储后触发->OSS
     */
    /**
     * aTaintCase00118 异步跟踪能力->存储行异步->支持自定义污点的存储和再次提取点
     */
    @Test
    void aTaintCase00119() {
        HttpResponse response = doPost(url_root + "ataint/case00119?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00120() {
        HttpResponse response = doPost(url_root + "ataint/case00120?cmd=ls");

        System.out.println(response.body());
    }

    //No 21~22
    @Test
    void aTaintCase00123() {
        HttpResponse response = doPost(url_root + "ataint/case00123?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00124() {
        HttpResponse response = doPost(url_root + "ataint/case00124?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00125() {
        HttpResponse response = doPost(url_root + "ataint/case00125?cmd1=cd%20/&cmd2=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00126() {
        HttpResponse response = doPost(url_root + "ataint/case00126?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00126_2() {
        HttpResponse response = doPost(url_root + "ataint/case00126/2?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00127() {
        HttpResponse response = doPost(url_root + "ataint/case00127?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00127_2() {
        HttpResponse response = doPost(url_root + "ataint/case00127/2?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00128() {
        HttpResponse response = doPost(url_root + "ataint/case00128?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00128_2() {
        HttpResponse response = doPost(url_root + "ataint/case00128/2?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00129() {
        HttpResponse response = doPost(url_root + "ataint/case00129?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00129_2() {
        HttpResponse response = doPost(url_root + "ataint/case00129/2?cmd=ls");

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00130() {
        String[][] strings = new String[2][2];
        strings[0][1]="ls";
        strings[1][1]="cd /";
        HttpResponse response = doPost(url_root + "ataint/case00130",JSONObject.toJSONString(strings));
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00130_2() {
        String[][] strings = new String[2][2];
        strings[0][1]="ls";
        strings[1][1]="cd /";
        HttpResponse response = doPost(url_root + "ataint/case00130",JSONObject.toJSONString(strings));
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00131() {
        String[][] strings = new String[2][2];
        strings[0][1]="ls";
        strings[1][1]="cd /";
        HttpResponse response = doPost(url_root + "ataint/case00131",JSONObject.toJSONString(strings));

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00131_2() {
        String[][] strings = new String[2][2];
        strings[0][1]="ls";
        strings[1][1]="cd /";
        HttpResponse response = doPost(url_root + "ataint/case00131",JSONObject.toJSONString(strings));

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00132() {
        List<String> stringList = Lists.newArrayList();
        stringList.add("ls");
        HttpResponse response = doPost(url_root + "ataint/case00132",JSONObject.toJSONString(stringList));

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00132_2() {
        List<String> stringList = Lists.newArrayList();
        stringList.add("ls");
        HttpResponse response = doPost(url_root + "ataint/case00132/2",JSONObject.toJSONString(stringList));

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00133() {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", "ls");
        HttpResponse response = doPost(url_root + "ataint/case00133",JSONObject.toJSONString(map));

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00133_2() {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", "ls");
        HttpResponse response = doPost(url_root + "ataint/case00133/2",JSONObject.toJSONString(map));
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00134() {
        Set<String> set= new HashSet<>();
        set.add("ls");
        SoureWithSetBean setBean = new SoureWithSetBean();
        setBean.setKey("key");
        setBean.setValue(set);
        HttpResponse response = doPost(url_root + "ataint/case00134",JSONObject.toJSONString(setBean));

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00134_2() {
        Set<String> set= new HashSet<>();
        set.add("ls");
        SoureWithSetBean setBean = new SoureWithSetBean();
        setBean.setKey("key");
        setBean.setValue(set);
        HttpResponse response = doPost(url_root + "ataint/case00134/2",JSONObject.toJSONString(setBean));

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00135() throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.put("ls");
        SoureWithQueueBean queueBean = new SoureWithQueueBean();
        queueBean.setKey("key");
        queueBean.setQueue(queue);
        HttpResponse response = doPost(url_root + "ataint/case00135",JSONObject.toJSONString(queueBean));

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00135_2() throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.put("ls");
        SoureWithQueueBean queueBean = new SoureWithQueueBean();
        queueBean.setKey("key");
        queueBean.setQueue(queue);
        HttpResponse response = doPost(url_root + "ataint/case00135/2",JSONObject.toJSONString(queueBean));


        System.out.println(response.body());
    }

    @Test
    void aTaintCase00136() {
        List<String> list = Lists.newArrayList();
        list.add("ls");
        HttpResponse response = doPost(url_root + "ataint/case00136",JSONObject.toJSONString(list));

        System.out.println(response.body());
    }

    @Test
    void aTaintCase00136_2() {
        List<String> list = Lists.newArrayList();
        list.add("ls");
        HttpResponse response = doPost(url_root + "ataint/case00136/2",JSONObject.toJSONString(list));

        System.out.println(response.body());
    }

    //@Test
    //void aTaintCase00137() {
    //    HttpResponse response = doPost(url_root + "ataint/case00137", "http://localhost/nothing");
    //    System.out.println(response.body());
    //}
    //
    //@Test
    //void aTaintCase00137_2() {
    //    HttpResponse response = doPost(url_root + "ataint/case00137/2", "/nothing");
    //    System.out.println(response.body());
    //}

    @Test
    void aTaintCase00932() {
        SourceTestObject testObject = new SourceTestObject();
        testObject.setCmd("ls");
        testObject.setCmd2("cd /");
        HttpResponse response = doPost(url_root + "ataint/case00932", JSONUtil.toJsonStr(testObject));
        System.out.println(response.body());
    }
    @Test
    void aTaintCase00142() {
        SourceTestObject testObject = new SourceTestObject();
        testObject.setCmd("ls");
        testObject.setCmd2("cd /");
        HttpResponse response = doPost(url_root + "ataint/case00142", JSONUtil.toJsonStr(testObject));
        System.out.println(response.body());
    }

    @Test
    void aTaintCase00142_2() {
        SourceTestObject testObject = new SourceTestObject();
        testObject.setCmd("ls");
        testObject.setCmd2("cd /");
        HttpResponse response2 = doPost(url_root + "ataint/case00142/2", JSONUtil.toJsonStr(testObject));
        System.out.println(response2.body());
    }

    @Test
    void aTaintCase00143() {
        HttpResponse response = doPost(url_root + "ataint/case00143?cmd=ls");
        System.out.println("aTaintCase00143" + response.body());
    }

    @Test
    void aTaintCase00144() {
        HttpResponse response = doPost(url_root + "ataint/case00144?cmd=ls");
        System.out.println("aTaintCase00144" + response.body());
    }

    @Test
    void aTaintCase00145() {
        int[] aa = {108, 115};
        HttpResponse response = doPost(url_root + "ataint/case00145", JSONUtil.toJsonStr(aa));
        System.out.println("aTaintCase00145" + response.body());
    }

    @Test
    void aTaintCase00146() {
        HttpResponse response = doPost(url_root + "ataint/case00146?cmd=ls");
        System.out.println("aTaintCase00146" + response.body());
    }

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void aTaintCase00147() {
        byte[] bytes = {108, 115};
        HttpEntity<ByteArrayResource> httpEntity = new HttpEntity<>(new ByteArrayResource(bytes), new HttpHeaders());
       restTemplate.exchange(url_root + "ataint/case00147"+ "?auto_check_start_time=" + caseUniqGroupId, HttpMethod.POST, httpEntity, Object.class);

        System.out.println("aTaintCase00147");
    }

    @Test
    void aTaintCase00148() {
        HttpResponse response = doPost(url_root + "ataint/case00148?cmd=ls");
        System.out.println("aTaintCase00148" + response.body());
    }

    @Test
    void aTaintCase00149() {
        HttpResponse response = doPost(url_root + "ataint/case00149?cmd=ls");
        System.out.println("aTaintCase00149" + response.body());
    }
    @Test
    void aTaintCase00940() {
        HttpResponse response = doPost(url_root + "ataint/case00940?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00940/2?cmd=ls");
        HttpResponse response3 = doPost(url_root + "ataint/case00940/3?cmd=ls");
        System.out.println(response.body()+response2.body()+response3.body());
    }
    @Test
    void aTaintCase00941() {
        HttpResponse response = doPost(url_root + "ataint/case00941?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00941/2?cmd=ls");
        HttpResponse response3 = doPost(url_root + "ataint/case00941/3?cmd=ls");
        System.out.println(response.body()+response2.body()+response3.body());
    }
    @Test
    void aTaintCase00942() {
        HttpResponse response = doPost(url_root + "ataint/case00942?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00942/2?cmd=ls");
        HttpResponse response3 = doPost(url_root + "ataint/case00942/3?cmd=ls");
        System.out.println(response.body()+response2.body()+response3.body());
    }
    @Test
    void aTaintCase00943() {
        HttpResponse response = doPost(url_root + "ataint/case00943?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00943/2?cmd=ls");
        HttpResponse response3 = doPost(url_root + "ataint/case00943/3?cmd=ls");
        System.out.println(response.body()+response2.body()+response3.body());
    }
    @Test
    void aTaintCase00944() {
        HttpResponse response = doPost(url_root + "ataint/case00944?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00944/2?cmd=ls");
        HttpResponse response3 = doPost(url_root + "ataint/case00944/3?cmd=ls");
        System.out.println(response.body()+response2.body()+response3.body());
    }
    @Test
    void aTaintCase00945() {
        HttpResponse response = doPost(url_root + "ataint/case00945?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00945/2?cmd=ls");
        System.out.println(response.body()+response2.body());
    }
    @Test
    void aTaintCase00946() {
        HttpResponse response = doPost(url_root + "ataint/case00946?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00946/2?cmd=ls");
        HttpResponse response3 = doPost(url_root + "ataint/case00946/3?cmd=ls");
        System.out.println(response.body()+response2.body()+response3.body());
    }  @Test
    void aTaintCase00947() {
        HttpResponse response = doPost(url_root + "ataint/case00947?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00947/2?cmd=ls");
        HttpResponse response3 = doPost(url_root + "ataint/case00947/2?cmd=ls");
        System.out.println(response.body()+response2.body()+response3.body());
    }
    @Test
    void aTaintCase00948() {
        HttpResponse response = doPost(url_root + "ataint/case00948?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00948/2?cmd=ls");
        HttpResponse response3 = doPost(url_root + "ataint/case00948/3?cmd=ls");
        System.out.println(response.body()+response2.body()+response3.body());
    }
    @Test
    void aTaintCase00949() {
        HttpResponse response = doPost(url_root + "ataint/case00949?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00949/2?cmd=ls");
        HttpResponse response3 = doPost(url_root + "ataint/case00949/3?cmd=ls");
        System.out.println(response.body()+response2.body()+response3.body());
    }

    @Test
    void aTaintCase00950() {
        HttpResponse response = doPost(url_root + "ataint/case00950?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00950/2?cmd=ls");
        HttpResponse response3 = doPost(url_root + "ataint/case00950/3?cmd=ls");
        System.out.println(response.body()+response2.body()+response3.body());
    }
    @Test
    void aTaintCase00951() {
        HttpResponse response = doPost(url_root + "ataint/case00951?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00951/2?cmd=ls");
        HttpResponse response3 = doPost(url_root + "ataint/case00951/3?cmd=ls");
        System.out.println(response.body()+response2.body()+response3.body());
    }
    @Test
    void aTaintCase00952() {
        HttpResponse response = doPost(url_root + "ataint/case00952?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00952/2?cmd=ls");
        HttpResponse response3 = doPost(url_root + "ataint/case00952/3?cmd=ls");
        System.out.println(response.body()+response2.body()+response3.body());
    }
    @Test
    void aTaintCase00953() {
        HttpResponse response = doPost(url_root + "ataint/case00953?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00953/2?cmd=ls");
        System.out.println(response.body()+response2.body());
    }
    @Test
    void aTaintCase00954() {
        HttpResponse response = doPost(url_root + "ataint/case00954?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00954/2?cmd=ls");
        System.out.println(response.body()+response2.body());
    }
    @Test
    void aTaintCase00955() {
        HttpResponse response = doPost(url_root + "ataint/case00955?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00955/2?cmd=ls");
        System.out.println(response.body()+response2.body());
    }
    @Test
    void aTaintCase00956() {
        HttpResponse response = doPost(url_root + "ataint/case00956?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00956/2?cmd=ls");
        System.out.println(response.body()+response2.body());
    }
    @Test
    void aTaintCase00957() {
        HttpResponse response = doPost(url_root + "ataint/case00957?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00957/2?cmd=ls");
        System.out.println(response.body()+response2.body());
    }
    @Test
    void aTaintCase00958() {
        HttpResponse response = doPost(url_root + "ataint/case00958?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00958/2?cmd=ls");
        System.out.println(response.body()+response2.body());
    }
    @Test
    void aTaintCase00959() {
        HttpResponse response = doPost(url_root + "ataint/case00959?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00959/2?cmd=ls");
        System.out.println(response.body()+response2.body());
    }
    @Test
    void aTaintCase00960() {
        HttpResponse response = doPost(url_root + "ataint/case00960?cmd=ls");
        HttpResponse response2 = doPost(url_root + "ataint/case00960/2?cmd=ls");
        System.out.println(response.body()+response2.body());
    }

    private HttpResponse doGet(String url) {
        url = urlWithTime(url);
        try {
            HttpResponse response = HttpUtil.createGet(url).execute();
            if (response.getStatus() == 500) {
                return retryDoGet(url);
            } else {
                return response;
            }
        } catch (Exception e) {
            return retryDoGet(url);
        }
    }

    private HttpResponse retryDoGet(String url) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
        }
        HttpResponse response2 = HttpUtil.createGet(url).execute();
        return response2;
    }

    private HttpResponse doPost(String url) {
        return doPost(url, "");
    }

    private HttpResponse doPost(String url, String body) {
        url = urlWithTime(url);
        try {
            HttpResponse response = HttpUtil.createPost(url).body(body).execute();
            if (response.getStatus() == 500) {
                return retryDoPost(url, body);
            } else {
                return response;
            }
        } catch (Exception e) {
            return retryDoPost(url, body);
        }
    }

    private HttpResponse retryDoPost(String url, String body) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
        }
        HttpResponse response2 = HttpUtil.createPost(url).body(body).execute();
        return response2;
    }

    private String urlWithTime(String url) {
        if (url.contains("?")) {
            return url + "&auto_check_start_time=" + caseUniqGroupId;
        } else {
            return url + "?auto_check_start_time=" + caseUniqGroupId;
        }
    }
}
