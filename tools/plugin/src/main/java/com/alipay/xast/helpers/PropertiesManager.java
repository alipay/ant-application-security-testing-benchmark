package com.alipay.xast.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    private File file = null;

    public PropertiesManager(String path, String fileName) {
        file = new File(path + File.separator + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(
                        "创建空文件异常: " + file.getAbsolutePath());
            }
        }
    }

    public String getProperty(String key, String defaultValue) {
        Properties props = loadProperties();
        return props.getProperty(key, defaultValue);
    }

    public int getProperty(String key, int defaultValue) {
        Properties props = loadProperties();
        return Integer.parseInt(props.getProperty(key, Integer.toString(defaultValue)));
    }

    public void saveProperty(String key, String value) {
        Properties props = loadProperties();
        try (FileOutputStream out = new FileOutputStream(file)) {
            props.setProperty(key, value);
            props.store(out, null);
        } catch (IOException e) {
            System.out.println("保存异常");
            e.printStackTrace();
        }
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream is = new FileInputStream(file)) {
            props.load(is);
        } catch (IOException e) {
            System.out.println("加载异常");
            e.printStackTrace();
        }
        return props;
    }
}
