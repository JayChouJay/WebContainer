package controller;

import server.HttpServlet;
import server.impl.HttpServletRequest;
import server.impl.HttpServletResponse;

public class IndexController implements HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse resp)  {
        System.out.println(req.getContent()+"-----"+req.getParamsMap());

    }
}
