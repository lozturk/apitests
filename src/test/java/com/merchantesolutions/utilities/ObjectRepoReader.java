package com.merchantesolutions.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectRepoReader {
    private static Properties obj;

    static {
        try {
            String path = "objectRepo.properties";

            FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\objectRepo.properties");

            obj = new Properties();
            obj.load(objfile);

            objfile.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getObject(String key) { return obj.getProperty(key); }
}



