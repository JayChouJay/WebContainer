package browser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Browser {
    private Socket socket = null;
    Scanner input = new Scanner(System.in);

    public void openBrowser() {
        System.out.print("URL:");
        String url = input.nextLine();
        this.paraseUrl(url);
    }

    private void paraseUrl(String url) {
        int colonIndex = url.indexOf(":");
        int slashIndex = url.indexOf("/");
        String ip = url.substring(0, colonIndex);
        int port = Integer.parseInt(url.substring(colonIndex + 1, slashIndex));
        String contentAndParams = url.substring(slashIndex + 1);
        this.createSocketAndSendRequest(ip, port, contentAndParams);
    }

    private void createSocketAndSendRequest(String ip, int port, String contentAndParams) {
        try {
            socket = new Socket(ip, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println(contentAndParams);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //接收服务器回写的响应信息
    private void recevieResponseContent() {
        try {
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseContent=reader.readLine();
            parseResponseContentAndShow(responseContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //解析响应信息并展示
    private void parseResponseContentAndShow(String responseContent){
        System.out.println(responseContent);
    }

    public static void main(String[] args) {
        new Browser().openBrowser();
    }
}
