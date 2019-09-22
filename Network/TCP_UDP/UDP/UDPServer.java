package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    private static final int BUFFER_SIZE = 1024;
    public static void main(String []args){
        try {
            DatagramSocket serverSocket =  new DatagramSocket(7890);
            byte[] receiveData = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
            serverSocket.receive(receivePacket);
            //处理接收大小
            byte[] temp = new byte[receivePacket.getLength()];
            System.arraycopy(receivePacket.getData(),0,temp,0,receivePacket.getLength());
            String receiverStr = new String(temp);
            System.err.println("Server:receive string is -->" + receiverStr + " get data length :" + receivePacket.getLength());
            InetAddress inetAddress = receivePacket.getAddress();
            int         port        = receivePacket.getPort();
            System.out.println("Server.log:client address is -->" + inetAddress.toString()+ "port is -->" + port);

            //发送
            byte[] sendData;
            sendData = (new StringBuilder(receiverStr).append("had check by server!")).toString().getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,inetAddress,port);
            serverSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
