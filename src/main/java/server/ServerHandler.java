package server;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;

public class ServerHandler extends Thread {
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        this.receiveRequest();
    }

    //读取消息
    private void receiveRequest() {
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String contentAndParams = null;
            //读取消息 content?key=value&key=value
            contentAndParams = reader.readLine();
            //调用一个方法解析读取过来的信息
            this.parseContentAndParams(contentAndParams);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //解析
    private void parseContentAndParams(String contentAndParams) {
        //创建两个比阿娘，存储请求的资源名 携带的参数
        String content = null;
        HashMap<String, String> paramsMap = null;
        //content?key=value&key=value
        //找寻问号所在的位置
        int questionMarkIndex = contentAndParams.indexOf("?");
        if (questionMarkIndex != -1) {
            //判断是否携带了参数
            content = contentAndParams.substring(0, questionMarkIndex);
            paramsMap = new HashMap<>();
            String params=contentAndParams.substring(questionMarkIndex+1);
            String[]keyAndValues=params.split("&");
            for (String keyAndvalue: keyAndValues){
                String[]KV=keyAndvalue.split("=");
                paramsMap.put(KV[0],KV[1]);
            }
        }else {
            content=contentAndParams;
        }


        HttpServletRequest request = new HttpServletRequest(content, paramsMap);
        HttpServletResponse response = new HttpServletResponse();
        //找人干活--控制层
        ServletController.findController(request, response);
        //将最终的响应信息写回浏览器
        this.responseToBrowser(response);
    }

    private void responseToBrowser(HttpServletResponse response) {
        try {
            PrintWriter out =new PrintWriter(socket.getOutputStream());
            out.println(response.getResponseContent());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}