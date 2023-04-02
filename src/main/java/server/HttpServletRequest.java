package server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
@AllArgsConstructor
@Data
public class HttpServletRequest  {
    private String content;
    private Map paramsMap;
    public Object getParam(String key){
        return paramsMap.get(key);
    }
}
