package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        Logger logger= LoggerFactory.getLogger(Server.class);
        logger.info("服务器启动");
        //创建服务
        int port=Integer.parseInt(ServerFileReader.getValue("port"));
        ServerSocket server=new ServerSocket(port);
        while(true){
            //等待客户端连接
            Socket socket=server.accept();
            new ServerHandler(socket).start();
        }
    }
}
