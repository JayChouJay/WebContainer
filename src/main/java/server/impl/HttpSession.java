package server.impl;

import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import server.Session;

import java.util.HashMap;
import java.util.Map;

public class HttpSession implements Session {
    private Map<String,Object>map=new HashMap<>();

    @Override
    public void setAttribute(String key, Object value) {
        map.put(key,value);
    }

    @Override
    public Object getAttribute(String key) {
        return map.get(key);
    }
}
