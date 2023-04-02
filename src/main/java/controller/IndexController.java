package controller;

import server.HttpServlet;
import server.HttpServletRequest;
import server.HttpServletResponse;

import java.io.IOException;

public class IndexController implements HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse resp)  {
        System.out.println(req.getContent()+"-----"+req.getParamsMap());

    }
}
