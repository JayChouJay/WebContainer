package server;


import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

//找到Controller并处理
public class ServletController {
    //统计Controller名字
    private static HashMap<String, String> controllerNameMap = new HashMap<>();
    //统计所有配置的controller
    private static HashMap<String, HttpServlet> controllerObjectMap = new HashMap<>();

    //从配置文件加载controllerNameMap信息
    static {
        try {
            Properties pro = new Properties();
            InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream("web.properties");
            pro.load(new InputStreamReader(inputStream));
            Enumeration enumeration = pro.propertyNames();
            while (enumeration.hasMoreElements()) {
                String content = (String) enumeration.nextElement();
                String realControllerName = pro.getProperty(content);
                controllerNameMap.put(content, realControllerName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void findController(HttpServletRequest request, HttpServletResponse response) {
        String content = request.getContent();
        try {
            HttpServlet controllerObject = controllerObjectMap.get(content);
            if (controllerObject == null) {
                //获取类名
                String realControllerName = controllerNameMap.get(content);
                //判断有没有配置，有配置就加载，没有配置就报异常
                if (realControllerName!=null){
                    //反射获取类
                    Class clazz=Class.forName(realControllerName);
                    controllerObject=(HttpServlet) clazz.newInstance();
                    controllerObjectMap.put(content,controllerObject);
                }
            }
            Class controllerClass=controllerObject.getClass();
            Method serviceMethod=controllerClass.getMethod("service", HttpServletRequest.class,HttpServletResponse.class);
            serviceMethod.invoke(controllerObject,request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
