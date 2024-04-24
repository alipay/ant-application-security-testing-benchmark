package com.iast.astbenchmark.cli.xmind.reader;




import cn.hutool.core.io.FileUtil;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.ArchiveException;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author liufree
 * @Classname ZipUtil
 * @Description zip解压工具
 * @Date 2020/4/14 15:39
 */
@Slf4j
public class ZipUtils {
    //private static String currentPath="/opt/case_management/Xmind";
     private static String currentPath=FileUtil.getTmpDirPath();

    /**
     * 找到压缩文件中匹配的子文件，返回的为
     * getContents("comments.xml,
     * unzip
     * @param subFileNames
     * @param fileName
     */
    public static Map<String,String> getContents(List<String> subFileNames, String fileName,String extractFileDir) throws IOException, ArchiveException {
        String destFilePath =extractFileDir;
        Map<String,String> map = new HashMap<>();
        File destFile = new File(destFilePath);
        if (destFile.isDirectory()) {
            String[] res = destFile.list(new FileFilter());
            for (int i = 0; i < Objects.requireNonNull(res).length; i++) {
                if (subFileNames.contains(res[i])) {
                    String s = destFilePath + File.separator + res[i];
                    String content = getFileContent(s);
                    map.put(res[i], content);
                }
            }
        }
        return map;
    }

    /**
     * 返回解压后的文件夹名字
     * @param fileName
     * @return
     * @throws IOException
     * @throws ArchiveException
     */
    public static String extract(String fileName)
            throws IOException, ArchiveException {
        File file = new File(fileName);
        Expander expander = new Expander();
        //目标文件夹名字
        String destFileName =currentPath +File.separator+ "XMind"+System.currentTimeMillis();
        File folder = new File(destFileName);
        FileUtil.mkdir(folder);
        boolean mkdir = folder.exists();
        log.info("destFileName"+destFileName);
        log.info("创建文件夹失败了吗"+mkdir);
        if(mkdir){
            expander.expand(file,folder );
        }
        return destFileName;
    }

    //这是一个内部类过滤器,策略模式
    static class FileFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            //String的 endsWith(String str)方法  筛选出以str结尾的字符串
            if (name.endsWith(".xml") || name.endsWith(".json")) {
                return true;
            }
            return false;
        }
    }

    public static String getFileContent(String fileName) throws IOException {
        File file;
        try {
            file = new File(fileName);
        } catch (Exception e) {
            throw new RuntimeException("找不到该文件");
        }
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReder = new BufferedReader(fileReader);
        StringBuilder stringBuffer = new StringBuilder();
        while (bufferedReder.ready()) {
            stringBuffer.append(bufferedReder.readLine());
        }
        //打开的文件需关闭，在unix下可以删除，否则在windows下不能删除（file.delete())
        bufferedReder.close();
        fileReader.close();
        return stringBuffer.toString();
    }

    /*public static void main(String[] args) throws IOException, ArchiveException {
        String fileName = "doc/XmindZen解析.xmind";
        List<String> list= new ArrayList<>();
   //     list.add("comments.xml");
        list.add("content.json");
        System.out.println(File.separator);
      //  System.out.println(getContents(list, fileName));
    }
*/
    public static void main(String[] args) throws IOException {
        String filepath = currentPath + File.separator + "XMind" + System.currentTimeMillis();
        System.out.println("filepath = " + filepath);
        File file = new File(filepath);
        boolean newFile = file.mkdir();
        System.out.println("newFile = " + newFile);
        System.out.println("file.isDirectory() = " + file.isDirectory());
        System.out.println("file.exists() = " + file.exists());
    }
}