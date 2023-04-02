package controller;

import server.HttpServlet;
import server.HttpServletRequest;
import server.HttpServletResponse;


//实例Controller代码
public class LoginController implements HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) {
        //设置与HTML的meta一样的字符集
        //request.setCharacterEncoding("UTF-8");
        //获取参数
        request.getParamsMap();
        request.getParam("key");
        //获取内容
        request.getContent();
        //设置回写字符集
        //response.setCharacterEncoding("UTF-8");
    }
}
