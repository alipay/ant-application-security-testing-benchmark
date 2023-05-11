package com.sast.astbenchmark.cases;


import com.google.common.collect.Lists;
import com.sast.astbenchmark.common.utils.CmdUtil;
import com.sast.astbenchmark.common.utils.HttpUtil;
import com.sast.astbenchmark.service.SSRFShowManageImpl;
import com.sast.astbenchmark.service.SSRFShowManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import cn.hutool.http.HttpRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/**
 * 链路完整度
 */
@RestController()
public class AstTaintCase001 {

    private SSRFShowManager ssrfShowManager = new SSRFShowManageImpl();

    /**
     * SIMPLE_NAME
     */
    @GetMapping("case011/{cmd}")
    public Map<String, Object> aTaintCase011(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a = cmd;
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * InfixExpression
     */
    @GetMapping("case012/{cmd}")
    public Map<String, Object> aTaintCase012(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a = cmd+"a";
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }



    /**
     * MethodInvocation
     */
    @GetMapping("case013/{cmd}")
    public Map<String, Object> aTaintCase013(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            CmdUtil.run(cmd+"|grep a");
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * MethodInvocation+InfixExpression
     */
    @GetMapping("case014/{cmd}")
    public Map<String, Object> aTaintCase014(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            CmdUtil.run(cmd+ HttpUtil.doGet("www.test.com"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * ifStatement
     */
    @GetMapping("case015/{cmd}")
    public Map<String, Object> aTaintCase015(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            if(true == false){
                CmdUtil.run(cmd);
            }else{
                String cmdString = HttpUtil.doGet("www.test.com");
                CmdUtil.run(cmd+cmdString);
            }
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * Switch
     */
    @GetMapping("case016/{type}/{cmd}")
    public Map<String, Object> aTaintCase016(@PathVariable String cmd,@PathVariable String type) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            switch (type){

                case "mkdir":
                    CmdUtil.run("mkdir"+" "+cmd);
                    modelMap.put("status", "success");
                default:
                    modelMap.put("status", "success");
                    return null;
            }

        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * LambdaExpression
     */
    @GetMapping("case017")
    public Map<String, Object> aTaintCase017(@RequestBody List<String> urlList) {
        Map<String, Object> modelMap = new HashMap<>();
        {
            urlList.stream().map(url -> {
                try {

                    HttpUtil.doGet(url);
                    return "url can connect: " + url;
                } catch (Exception e) {
                    return "url connect exception: " + url;
                }}).collect(Collectors.toList());
        }
        return modelMap;
    }
    /**
     *变量传递通过native方法发生在两个入参上
     */
    @PostMapping("case018/{cmd}")
    public Map<String, Object> aTaintCase018(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            String[] b = {"a","b"};
            System.arraycopy(cmd,0,b,0,2);
            Runtime.getRuntime().exec(b[0]);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     *VariableDeclarationStatement
     */
    @PostMapping("case019")
    public Map<String, Object> aTaintCase019(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        char[] data = cmd.toCharArray();
        try {
            Runtime.getRuntime().exec(new String(data));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * Arrayaccess
     * @param cmd
     * @param request
     * @return
     */
    @PostMapping("case0110")
    public Map<String, Object> aTaintCase0110(@RequestParam("cmd") String cmd, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String cmdStr = request.getParameterMap().get("cmd")[0];
            Runtime.getRuntime().exec(cmdStr);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * Arrayaccess
     * @param request
     * @return
     */
    @PostMapping(value = "case0111")
    public Map<String, Object> aTaintCase0111( HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Cookie[] cookies = request.getCookies();
            Runtime.getRuntime().exec(cookies[0].getName());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * argument arrayaccess
     * @param
     * @return
     */
    @PostMapping(value = "case0112")
    public Map<String, Object> aTaintCase0112(@RequestParam(defaultValue = "ls") String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String strs[] = new String[1];
            strs[0]=cmd;
            List<String> target = Lists.newArrayList("cd /","ls","ls -la");
            CollectionUtils.mergeArrayIntoCollection(strs,target);
            Runtime.getRuntime().exec(target.get(3));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * classinstance + initfix
     */
    @PostMapping(value = "case0113")
    public Map<String, Object> aTaintCase0113(@RequestParam(defaultValue = "ls") String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(new String(cmd+" &"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * MI+MI
     * @param cmd
     * @return
     */
    @PostMapping(value = "case0114")
    public Map<String, Object> aTaintCase0114(@RequestParam(defaultValue = "ls") String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd.toUpperCase());
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * MI+arguement
     */
    @PostMapping(value = "case0115")
    public Map<String, Object> aTaintCase0115(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            char[] chars= new char[]{0,0};
            cmd.getChars(0,2,chars,0);
            Runtime.getRuntime().exec(String.valueOf(chars));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * MI的expression的arguement
     * @param cmd
     * @return
     */
    @PostMapping(value = "case0116")
    public Map<String, Object> aTaintCase0116(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            HttpRequest.post("http://localhost:39100/ataint/case00124/2?cmd="+cmd)
                    .execute();
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * arrayaccess
     */
    @PostMapping(value = "case0117")
    public Map<String, Object> aTaintCase0117(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] strings = new String[3];
            strings[0]="cd ~";
            strings[1]=cmd;
            strings[2]="cd /";
            Runtime.getRuntime().exec(strings[1]);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }


    /**
     * WhileStatement
     * @param cmd
     * @param type
     * @return
     */

    @GetMapping("case0118/{type}/{cmd}")
    public Map<String, Object> aTaintCase0118(@PathVariable String cmd,@PathVariable String type) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";;
            while(StringUtils.equals(type,"mkdir")) {
                a = " "+ cmd;
            }
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * forstatement
     * @param cmd
     * @return
     */
    @GetMapping("case0127/{cmd}")
    public Map<String, Object> aTaintCase0127(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";
            for(int i =0 ;i<10; i++){
                a= cmd+"|";
            }
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * DoStatement
     * @param cmd
     * @return
     */
    @GetMapping("case0128/{cmd}")
    public Map<String, Object> aTaintCase0128(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";
            int i = 10;

            do {
                a= cmd+"|";
                i++;
            }while (i<20);

            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * CastExpression
     * @param cmd
     * @return
     */
    @GetMapping("case0129/{cmd}")
    public Map<String, Object> aTaintCase0129(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Object cmdObject = new Object();
            cmdObject=cmd;
            Runtime.getRuntime().exec((String) cmdObject);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * ThisExpression
     * @param url
     * @return
     */
    @GetMapping("case0130/{url}")
    public Map<String, Object> aTaintCase0130(@PathVariable String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {

            this.ssrfShowManager.connectFacade(url);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * ThisExpression+anonymous
     * @param url
     * @return
     */

    @GetMapping("case0131/{url}")
    public Map<String, Object> aTaintCase0131(@PathVariable String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {

            this.ssrfShowManager.anonymousFacade(url);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * ThisExpression+lmbda
     * @param url
     * @return
     */

    @GetMapping("case0132/{url}")
    public Map<String, Object> aTaintCase0132(@PathVariable String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {

            this.ssrfShowManager.selfAnonymousFacade(url);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 三木运算符
     * @param url
     * @return
     */
    @GetMapping("case0133/{url}")
    public Map<String, Object> aTaintCase0133(@PathVariable String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String realUrl = StringUtils.isBlank(url) ? url : "https://www.alipay.com";
            this.ssrfShowManager.selfAnonymousFacade(realUrl);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 反射调用
     * @param cmd
     * @return
     */
    @GetMapping("case0134/{cmd}/{methodname}")
    public Map<String, Object> aTaintCase0134(@PathVariable String cmd,@PathVariable String methodname) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Class<CmdUtil> clazz = CmdUtil.class;
            Method method = clazz.getMethod(methodname, String.class);
            method.setAccessible(true);
            cmd = (String) method.invoke(clazz.newInstance(), cmd);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * PrefixExpression
     */
    @GetMapping("case0135/{cmd}")
    public Map<String, Object> aTaintCase0135(@PathVariable int cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd++;
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * PostfixExpression
     */
    @GetMapping("case0136/{cmd}")
    public Map<String, Object> aTaintCase0136(@PathVariable int cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            ++cmd;
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 基本类型char 作为污点源
     * 测试数据传（0～9）
     * @return
     */
    @GetMapping("case0137/{cmd}")
    public Map<String, Object> aTaintCase0137(@PathVariable char cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 基本类型byte 作为污点源
     * @param cmd
     * @return
     */
    @GetMapping("case0138/{cmd}")
    public Map<String, Object> aTaintCase0138(@PathVariable byte cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 基础类型long 作为污点源
     *
     * @param cmd
     * @return
     */
    @GetMapping("case0139/{cmd}")
    public Map<String, Object> aTaintCase0139(@PathVariable long cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }


    /**
     * 引用类型Map 作为污点源
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0140")
    public Map<String, Object> aTaintCase0140(@RequestBody Map<String, String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.isEmpty()) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd.toString());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 引用类型List 作为污点源
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0141")
    public Map<String, Object> aTaintCase0141(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || CollectionUtils.isEmpty(cmd)) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd.get(0));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 引用类型queue 作为污点源
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0142")
    public Map<String, Object> aTaintCase0142(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || CollectionUtils.isEmpty(cmd)) {
            modelMap.put("status", "error");
            return modelMap;
        }
        Queue<String> queue = new LinkedBlockingQueue();
        try {
            queue.add(cmd.get(0));
            Runtime.getRuntime().exec(queue.peek());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 引用类型Set 作为污点源
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0143")
    public Map<String, Object> aTaintCase0143(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || CollectionUtils.isEmpty(cmd)) {
            modelMap.put("status", "error");
            return modelMap;
        }
        Set<String> stringSet = new HashSet<>(cmd);
        try {

            Runtime.getRuntime().exec(stringSet.stream().iterator().next());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }


    /**
     * 基本数据类型的封装类型 Byte 作为污点源
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0144/{cmd}")
    public Map<String, Object> aTaintCase0144(@PathVariable Byte cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd.toString());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }


    /**
     * 基本数据类型的封装类型 Integer 作为污点源
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0145/{cmd}")
    public Map<String, Object> aTaintCase0145(@PathVariable Integer cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }


    /**
     * 基本数据类型的封装类型 Long 作为污点源
     * @param cmd
     * @return
     */
    @PostMapping("case0146/{cmd}")
    public Map<String, Object> aTaintCase0146(@PathVariable Long cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 基本数据类型的封装类型 Character 作为污点源
     * @param cmd 测试数据使用（0~9）
     * @return
     */
    @PostMapping("case0147/{cmd}")
    public Map<String, Object> aTaintCase0148(@PathVariable Character cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 数组 String[] 作为污点源
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0149")
    public Map<String, Object> aTaintCase0149(@RequestBody String[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd[0]);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 数组 char[] 作为污点源
     *
     * @param cmd [1,2]
     * @return
     */
    @PostMapping("case0150")
    public Map<String, Object> aTaintCase0150(@RequestBody int[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", "error");
            return modelMap;
        }
        char[] data = {(char) cmd[0], 2};
        try {
            Runtime.getRuntime().exec(data.toString());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 数组 byte[] 作为污点源
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0151")
    public Map<String, Object> aTaintCase0151(@RequestBody byte[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd.toString());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }


    /**
     * 其他对象 String 作为污点源
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0152")
    public Map<String, Object> aTaintCase0152(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 其他对象 StringBuffer 作为污点源
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0153")
    public Map<String, Object> aTaintCase0153(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        StringBuffer data = new StringBuffer();
        data.append(cmd);
        try {
            Runtime.getRuntime().exec(data.toString());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 其他对象 StringBuilder 作为污点源
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0154")
    public Map<String, Object> aTaintCase0154(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        StringBuilder data = new StringBuilder();
        data.append(cmd);
        try {
            Runtime.getRuntime().exec(data.toString());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }


    /**
     * 类对象找不到对应的实现类
     *
     * @param
     * @return
     */
    @PostMapping(value = "case0155")
    public Map<String, Object> aTaintCase0155(@RequestParam(defaultValue = "ls") String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String exec = String.valueOf(cmd);
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * castexpression
     * @param url
     * @return
     */

    @GetMapping("case0156/{url}")
    public Map<String, Object> aTaintCase0156(@PathVariable String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {

            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.getResponseMessage();
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 变量赋值表达式直接引入而不是import
     * @param url
     * @return
     */

    @GetMapping("case0157/{url}")
    public Map<String, Object> aTaintCase0157(@PathVariable String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String result = com.sast.astbenchmark.common.utils.HttpUtil.doGet(url);
            modelMap.put("status", "success");
            modelMap.put("result", result);
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 传播场景
     */
    /**
     * 传播场景->运算符->赋值
     */
    @PostMapping(value = "case0158")
    public Map<String, Object> aTaintCase0158(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd= "ls";
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 61 传播场景->运算符->位运算
     */
    @PostMapping(value = "case0159")
    public Map<String, Object> aTaintCase0159(@RequestParam char cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd= (char) (cmd<<1);
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 62 传播场景->String操作->构造方法
     */
    @PostMapping(value = "case0160")
    public Map<String, Object> aTaintCase0160(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(new String(cmd));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 63 传播场景->String操作->conact
     */
    @PostMapping(value = "case0161")
    public Map<String, Object> aTaintCase0161(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.concat(" -la");
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 传播场景->String操作->copyValueOf
     */
    @PostMapping(value = "case0162")
    public Map<String, Object> aTaintCase0162(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            char data[] =  cmd.toCharArray();
            Runtime.getRuntime().exec(String.copyValueOf(data));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 65 传播场景->String操作->format
     */
    @PostMapping(value = "case0163")
    public Map<String, Object> aTaintCase0163(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd =String.format("%s -la",cmd);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 66 传播场景->String操作->getBytes
     */

    @PostMapping(value = "case0164")
    public Map<String, Object> aTaintCase0164(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] bytes = cmd.getBytes();
            Runtime.getRuntime().exec(String.valueOf(bytes));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 67 传播场景->String操作->getChars
     */
    @PostMapping(value = "case0165")
    public Map<String, Object> aTaintCase0165(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            char[] chars= new char[]{0,0};
            cmd.getChars(0,2,chars,0);
            Runtime.getRuntime().exec(String.valueOf(chars));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 68 传播场景->String操作->intern
     */
    @PostMapping(value = "case0166")
    public Map<String, Object> aTaintCase0166(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.intern());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 69 传播场景->String操作->join
     */
    @PostMapping(value = "case0167")
    public Map<String, Object> aTaintCase0167(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=String.join(" ",cmd,"-la");
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }


    /**
     * 70 传播场景->String操作->repeat
     *
     */
    @PostMapping(value = "case0168")
    public Map<String, Object> aTaintCase0168(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {

            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 71 传播场景->String操作->replace
     * ls;-la
     */
    @PostMapping(value = "case0169")
    public Map<String, Object> aTaintCase0169(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.replace(";"," ");
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     *  传播场景->String操作->replace
     * alasa
     */
    @PostMapping(value = "case0170")
    public Map<String, Object> aTaintCase0170(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.replaceAll("a","");
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 72 传播场景->String操作->split
     */
    @PostMapping(value = "case0171")
    public Map<String, Object> aTaintCase0171(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.split(" ")[0];
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 73 传播场景->String操作->strip
     */
    @PostMapping(value = "case0172")
    public Map<String, Object> aTaintCase0172(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 74 传播场景->String操作->subSequence
     */
    @PostMapping(value = "case0173")
    public Map<String, Object> aTaintCase0173(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd.subSequence(0,2)));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 75 传播场景->String操作->substring
     * lsabc
     */
    @PostMapping(value = "case0174")
    public Map<String, Object> aTaintCase0174(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.substring(0,2));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 76 传播场景->String操作->toCharArray
     */
    @PostMapping(value = "case0175")
    public Map<String, Object> aTaintCase0175(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            char[] chars=cmd.toCharArray();
            Runtime.getRuntime().exec(String.valueOf(chars));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 77 传播场景->String操作->toLowerCase
     */
    @PostMapping(value = "case0176")
    public Map<String, Object> aTaintCase0176(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.toLowerCase();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 78 传播场景->String操作->toString
     */
    @PostMapping(value = "case0177")
    public Map<String, Object> aTaintCase0177(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.toString();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     *传播场景->String操作->toUpperCase
     */
    @PostMapping(value = "case0178")
    public Map<String, Object> aTaintCase0178(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.toUpperCase();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     *传播场景->String操作->trim
     */

    @PostMapping(value = "case0179")
    public Map<String, Object> aTaintCase0179(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.trim();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 传播场景->String操作->valueOf
     */
    @PostMapping(value = "case0180")
    public Map<String, Object> aTaintCase0180(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=String.valueOf(cmd);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     *传播场景->StringBuilder操作->构造方法
     */
    @PostMapping(value = "case0181")
    public Map<String, Object> aTaintCase0181(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(new StringBuilder(cmd)));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     *传播场景->StringBuilder操作->append
     */
    @PostMapping(value = "case0182")
    public Map<String, Object> aTaintCase0182(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     *传播场景->StringBuilder操作->charAt
     *
     */
    @PostMapping(value = "case0183")
    public Map<String, Object> aTaintCase0183(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            char c= builder.charAt(0);
            Runtime.getRuntime().exec(String.valueOf(c));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 传播场景->StringBuilder操作->delete
     */
    @PostMapping(value = "case0184")
    public Map<String, Object> aTaintCase0184(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            builder.delete(2,cmd.length());
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 传播场景->StringBuilder操作->deleteCharAt
     */
    @PostMapping(value = "case0185")
    public Map<String, Object> aTaintCase0185(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            builder.deleteCharAt(2);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 传播场景->StringBuilder操作->getChars
     */
    @PostMapping(value = "case0186")
    public Map<String, Object> aTaintCase0186(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            char[] chars = {0,0};
            builder.getChars(0,2,chars,0);
            Runtime.getRuntime().exec(String.valueOf(chars));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 传播场景->StringBuilder操作->insert
     */
    @PostMapping(value = "case0187")
    public Map<String, Object> aTaintCase0187(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.insert(0,cmd);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 传播场景->StringBuilder操作->replace
     */
    @PostMapping(value = "case0188")
    public Map<String, Object> aTaintCase0188(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder("abc");
            builder.replace(2,3,cmd);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 传播场景->StringBuilder操作->subSequence
     */
    @PostMapping(value = "case0189")
    public Map<String, Object> aTaintCase0189(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            builder.subSequence(0,2);
            Runtime.getRuntime().exec(String.valueOf(builder.subSequence(0,2)));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 传播场景->StringBuilder操作->subString
     */
    @PostMapping(value = "case0190")
    public Map<String, Object> aTaintCase0190(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            builder.substring(0,2);
            Runtime.getRuntime().exec(builder.substring(0,2));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 传播场景->StringBuilder操作->toString
     */
    @PostMapping(value = "case0191")
    public Map<String, Object> aTaintCase0191(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 传播场景-char[],byte[]操作->copyOf
     */
    @PostMapping(value = "case0192")
    public Map<String, Object> aTaintCase0192(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] b1 = cmd.getBytes();
            byte[] b2 = Arrays.copyOf(b1,10);
            Runtime.getRuntime().exec(new String(b2));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
    /**
     * 传播场景-char[],byte[]操作-->copyOfRange
     */
    @PostMapping(value = "case0193")
    public Map<String, Object> aTaintCase0193(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] b1 = cmd.getBytes();
            byte[] b2 = Arrays.copyOfRange(b1,0,2);
            Runtime.getRuntime().exec(new String(b2));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 传播场景-char[],byte[]操作->toString
     */
    @PostMapping(value = "case0194")
    public Map<String, Object> aTaintCase0194(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            char[] chars = cmd.toCharArray();
            Runtime.getRuntime().exec(chars.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    /**
     * 传播场景-数组初始化->new 方式初始化
     */
    @PostMapping(value = "case0195")
    public Map<String, Object> aTaintCase0195(@RequestParam String[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] chars = new String[]{cmd[0],cmd[1]};
            Runtime.getRuntime().exec(chars);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }






}
