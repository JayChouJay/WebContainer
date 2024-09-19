package server;

import server.impl.HttpSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Session管理类
 * 创建session并给予标识
 */
public class SessionManager {
    private static Map<String, HttpSession> sessionMap=new HashMap<>();
    public static HttpSession getSession(String key){
        return sessionMap.get(key);
    }
}
