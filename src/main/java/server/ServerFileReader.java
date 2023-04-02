package server;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class ServerFileReader {
    private static HashMap<String, String> map = new HashMap<>();

    static {

        try {
            Properties pro = new Properties();
            InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream("server.properties");
//            pro.load(inputStream);
            pro.load(new InputStreamReader(inputStream));
            Enumeration en = pro.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String value = pro.getProperty(key);
                map.put(key, value);
                //测试导入的文件内容
//                System.out.println(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        return map.get(key);
    }
}
