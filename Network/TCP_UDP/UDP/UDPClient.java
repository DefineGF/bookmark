package UDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDPClient {
    private static final int BUFFER_SIZE = 1024;
    public static void main(String []args){
        BufferedReader fromClientReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName("localhost");
            //发送
            byte[] sendData;
            String sendStr = fromClientReader.readLine();
            sendData = sendStr.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,inetAddress,7890);
            clientSocket.send(sendPacket);
            System.out.println("Client.log: client send the message is :" + Arrays.toString(sendData));
            //接收
            byte[] receiveData = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
            clientSocket.receive(receivePacket);
            //注意接收大小
            byte[] temp = new byte[receivePacket.getLength()];
            System.arraycopy(receivePacket.getData(),0,temp,0,receivePacket.getLength());
            String receiveStr = new String(temp);
            System.err.println("Client:receive from server is -->" + receiveStr);
            clientSocket.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
