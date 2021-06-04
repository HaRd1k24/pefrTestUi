package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties property;
    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/config.properties");
            property = new Properties();
            property.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace(); } } }
    /**
     * метод для возврата строки со значением из файла с настройками
     */
    public static String getProperty(String key) {
        return property.getProperty(key); } }

