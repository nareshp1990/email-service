package com.naresh.consul;

import com.ecwid.consul.v1.ConsulClient;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.Properties;

public class FileToConsul {

    public void load(String applicationName,String springProfile, String filePath) {
        try {
            String targetDir = "config/" + applicationName + ","+springProfile+"/";
            FileReader fileReader = new FileReader(filePath);
            Properties properties = new Properties();
            properties.load(fileReader);
            parseAndLoad(properties, targetDir);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void parseAndLoad(Properties properties, String targetDir) {
        ConsulClient consulClient = new ConsulClient("http://192.168.1.2:8500");
        Enumeration<Object> keys = properties.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            sendKeyValueToConsul(key, properties.getProperty(key), targetDir, consulClient);
        }
    }

    private void sendKeyValueToConsul(String key, String value, String targetDir,
        ConsulClient consulClient) {
        try {
            consulClient.setKVValue(targetDir + key, value);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
