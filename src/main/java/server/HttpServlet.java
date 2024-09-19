package server;

import server.impl.HttpServletRequest;
import server.impl.HttpServletResponse;

public interface HttpServlet {
    void service(HttpServletRequest request, HttpServletResponse response);
}
