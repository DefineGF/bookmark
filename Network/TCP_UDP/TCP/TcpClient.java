package TCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpClient {
    public static void main(String []args){
        String toServerStr;
        String fromServerStr;
        BufferedReader fromClientReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Socket clientSocket = new Socket("localhost",6789);
            DataOutputStream toServerStream = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader fromServerReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            toServerStr = fromClientReader.readLine();       //控制台输入直至回车
            toServerStream.writeBytes(toServerStr+'\n');  //注意添加 '/n' 符, server 接收流以回车符为结束
            System.out.println("transfer the string is: " + toServerStr);

            fromServerStr = fromServerReader.readLine();
            System.err.println("client:get the string from server is --> "+fromServerStr);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
