package com.alipay.antbenchmark.tools;

import org.apache.commons.codec.binary.Hex;
import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.Key;


public class Utils {

    private static final Logger log = LoggerFactory.getLogger("Utils");


    public static final String testfileDir = System.getProperty("user.dir") + File.separator + "testfiles"
            + File.separator;


    public static Boolean checkFastJsonExploit(String s) {

        try {
            String tmp = java.net.URLDecoder.decode(s, "UTF-8");
            return tmp.contains("'@type':") || tmp.contains("\"@type\"");
        } catch (UnsupportedEncodingException e) {
            log.error("checkFastJsonExploit error . the exception:{}", e.getMessage());
            return false;
        }
    }

    public static String getOSCommandString(String append) {
        String command = null;
        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") != -1) {
            command = "cmd.exe /c " + append + " ";
        } else {
            command = append + " ";
        }
        return command;
    }

    public static String getInsecureOSCommandString(ClassLoader classLoader) {
        String command = null;
        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") != -1) {
            command = "insecureCmd.bat";
        } else {
            command = "./insecureCmd.sh";
        }
        return command;
    }


    public static void printOSCommandResults(java.lang.Process proc, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.write(
                "<!DOCTYPE>\n"
                        + "<html>\n" + "<head>\n"
                        + "<meta charset=\"utf-8>\n" + "</head>\n"
                        + "<body>\n" + "<p>\n");

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        try {
            out.write("The standard output of the command:<br>");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                out.write(ESAPI.encoder().encodeForHTML(s));
                out.write("<br>");
            }

            out.write("<br>The std err of the command (if any):<br>");
            while ((s = stdError.readLine()) != null) {
                out.write(ESAPI.encoder().encodeForHTML(s));
                out.write("<br>");
            }
        } catch (IOException e) {
            log.error("printOSCommandResults error. the exception:{}", e.getMessage());
        }
    }

    /**
     * AES解密
     *
     * @param thisKey
     * @param data
     * @return
     */
    public static String aesDecode(String thisKey, String data) {
        try {
            Key key = new SecretKeySpec(Hex.decodeHex(thisKey.toCharArray()), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(Hex.decodeHex(data.toCharArray()));
            return new String(result);
        } catch (Exception e) {
            log.error("aesDecode error. the exception:{}", e.getMessage());
        }
        return null;
    }

    public static String aesEncode(String thisKey, String data) {
        try {
            Key key = new SecretKeySpec(Hex.decodeHex(thisKey.toCharArray()), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(data.getBytes());
            return Hex.encodeHexString(result);
        } catch (Exception e) {
            log.error("aesEncode error. the exception:{}", e.getMessage());
        }

        return null;
    }

    public static String getAesKey() {
        return "a841dd0541d40acb87feb121a4d4f9f7";
    }

}
