package TCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String []args){
        String clientSentence;
        try {
            ServerSocket serverSocket = new ServerSocket(6789);//监听端口
            while(true){
                Socket connSocket = serverSocket.accept();//监听到端口变化
                System.err.println("get the link...");
                BufferedReader fromClientReader = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
                DataOutputStream toClientStream = new DataOutputStream(connSocket.getOutputStream());
                clientSentence = fromClientReader.readLine();
                System.err.println("server: get the string from client is -->" + clientSentence);
                System.err.println(clientSentence);
                System.err.println(clientSentence);

                StringBuilder sb = new StringBuilder(clientSentence);
                toClientStream.writeBytes(sb.append(" had checked by server!").append('\n').toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
